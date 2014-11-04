package org.bbop.apollo

import grails.transaction.Transactional
import org.grails.datastore.mapping.query.api.Criteria

@Transactional
class FeatureRelationshipService {

//    def cvTermService

    List<Feature> getChildrenForFeature(Feature feature, CVTerm cvFeatureTerm, CVTerm cvRelationshipTerm) {
        Collection<Feature> features = new ArrayList<Feature>();
        for (FeatureRelationship fr : feature.getChildFeatureRelationships()) {
            if (cvRelationshipTerm == fr.type && cvFeatureTerm == fr.subjectFeature.type) {
                features.add(fr.subjectFeature)
            }
        }
        return features;
    }

//    List<Feature> getChildrenForFeatureAndTypes(Feature feature, FeatureStringEnum featureStringEnum) {
//        CVTerm featureCvTerm = cvTermService.getTerm(featureStringEnum)
//        return getChildrenForFeatureAndTypes(feature, featureCvTerm, cvTermService.partOf)
//    }

    List<Feature> getChildrenForFeatureAndTypes(Feature feature, String ... ontologyId) {

        Criteria criteria = FeatureRelationship.createCriteria()
        List<FeatureRelationship> featureRelationshipList = criteria {
            eq("objectFeature", feature)
            inList("subjectFeature.ontologyId", ontologyId)
        }

        return featureRelationshipList*.subjectFeature
    }

//    List<Feature> getParentForFeature(Feature feature, CVTerm cvFeatureTerm, CVTerm cvRelationshipTerm) {
//        Collection<Feature> features = new ArrayList<Feature>();
//        for (FeatureRelationship fr : feature.getParentFeatureRelationships()) {
//            if (cvRelationshipTerm == fr.type && cvFeatureTerm == fr.objectFeature.type) {
//                features.add(fr.objectFeature)
//            }
//        }
//        return features;
//    }

//    List<Feature> getParentForFeature(Feature feature, FeatureStringEnum featureStringEnum) {
//        CVTerm featureCvTerm = cvTermService.getTerm(featureStringEnum)
//        return getParentForFeature(feature, featureCvTerm, cvTermService.partOf)
//    }


    Feature getChildForFeature(Feature feature, String ontologyId) {
        List<Feature> featureList = getChildrenForFeatureAndTypes(feature,ontologyId)

        if (featureList.size() == 0) {
            return null
        }

        if (featureList.size() > 1) {
            log.error "More than one child feature relationships found for ${feature} and ID ${ontologyId}"
            return null
        }

        return featureList.get(0)
    }


    Feature getParentForFeature(Feature feature,String ontologyId) {
        List<Feature> featureList = getParentsForFeature(feature,ontologyId)

        if (featureList.size() == 0) {
            return null
        }

        if (featureList.size() > 1) {
            log.error "More than one feature relationships parent found for ${feature} and ID ${ontologyId}"
            return null
        }

        return featureList.get(0)
    }

    List<Feature> getParentsForFeature(Feature feature,String ontologyId) {

        Criteria criteria = FeatureRelationship.createCriteria()
        List<FeatureRelationship> featureRelationshipList = criteria {
            eq("subjectFeature", feature)
            eq("objectFeature.ontologyId", ontologyId)
        }

        return featureRelationshipList*.objectFeature


//        CVTerm featureCvTerm = cvTermService.getTerm(featureStringEnum)
//        return getParentForFeature(feature, featureCvTerm, cvTermService.partOf)
    }

//    def deleteRelationships(Feature feature, FeatureStringEnum parentFeatureEnum, FeatureStringEnum childFeatureEnum) {
        def deleteRelationships(Feature feature, String parentOntologyId, String childOntologyId) {
//        CVTerm parentFeatureCvTerm = cvTermService.getTerm(parentFeatureEnum)
//        CVTerm childFeatureCvTerm = cvTermService.getTerm(childFeatureEnum)
//        CVTerm partOfCvTerm = cvTermService.partOf

        deleteChildrenForTypes(feature, childOntologyId)
        deleteParentForTypes(feature, parentOntologyId)

    }

    def setChildForType(Feature feature, Feature childFeature) {
        // delete transcript -> non canonical 3' splice site child relationship
        def criteria = FeatureRelationship.createCriteria()

        def results = criteria{
            eq("objectFeature", feature)
            eq("subjectFeature.ontologyId", childFeature.ontologyId)
        }

        if(results.size()==1){
            results.get(0).subjectFeature = childFeature
            return true
        }
        else{
            if(results.size()==0){
                log.error "No feature relationships exist for parent ${feature} and child ${childFeature}"
            }
            if(results.size()>1){
                log.error "${results.size()} feature relationships exist for parent ${feature} and child ${childFeature}"
            }
            return false
        }




//        for (FeatureRelationship fr : feature.getChildFeatureRelationships()) {
//            if ( ontologyId == fr.subjectFeature.ontologyId
//                    && fr.getSubjectFeature().equals(feature)) {
//                boolean ok = feature.getChildFeatureRelationships().remove(fr);
//            }
//        }
    }

    def deleteChildrenForTypes(Feature feature, String ... ontologyIds) {
        // delete transcript -> non canonical 3' splice site child relationship
        def criteria = FeatureRelationship.createCriteria()

        criteria{
            eq("objectFeature", feature)
            inList("subjectFeature.ontologyId", ontologyIds)
        }.each {
            feature.removeFromChildFeatureRelationships(it)
        }

//        for (FeatureRelationship fr : feature.getChildFeatureRelationships()) {
//            if ( ontologyId == fr.subjectFeature.ontologyId
//                    && fr.getSubjectFeature().equals(feature)) {
//                boolean ok = feature.getChildFeatureRelationships().remove(fr);
//            }
//        }
    }

    def deleteParentForTypes(Feature feature, String ... ontologyIds) {
        // delete transcript -> non canonical 3' splice site child relationship
        def criteria = FeatureRelationship.createCriteria()

        criteria{
            eq("subjectFeature", feature)
            inList("objectFeature.ontologyId", ontologyIds)
        }.each {
            feature.removeFromParentFeatureRelationships(it)
        }


//        for (FeatureRelationship fr : feature.getChildFeatureRelationships()) {
//            if (cvRelationshipTerm == fr.type
//                    && cvFeatureTerm == fr.objectFeature.type
//                    && fr.getSubjectFeature().equals(feature)) {
//                boolean ok = feature.getParentFeatureRelationships().remove(fr);
//            }
//        }
    }

    // based on Transcript.setCDS
    def addChildFeature(Feature parent, Feature child,boolean replace=true) {
//        CVTerm partOfCvTerm = cvTermService.partOf
//        CVTerm childType = child.type
//        CVTerm parentType = parent.type

        // replace if of the same type
        if(replace){
            def criteria = FeatureRelationship.createCriteria()
            criteria{
                    eq("objectFeature", parent)
                    eq("subjectFeature.ontologyId", child.ontologyId)
            }
            .each {
                it.subjectFeature = child
//                feature.removeFromParentFeatureRelationships(it)
            }

//            for (FeatureRelationship fr : parent.getChildFeatureRelationships()) {
//                if (partOfCvTerm == fr.getType() &&
//                        childType == fr.objectFeature.type) {
//                    fr.setSubjectFeature(child);
//                    return
//                }
//            }
        }

        FeatureRelationship fr = new FeatureRelationship(
                objectFeature: parent
                ,subjectFeature: child
                ,rank: 0 // TODO: Do we need to rank the order of any other transcripts?
        );
        parent.getChildFeatureRelationships().add(fr);
        child.getParentFeatureRelationships().add(fr);
    }

    public void removeFeatureRelationship(Transcript transcript, Feature feature) {

        FeatureRelationship featureRelationship = FeatureRelationship.findByObjectFeatureAndSubjectFeature(transcript,feature)
        if(featureRelationship){
            FeatureRelationship.deleteAll()
        }

//        CVTerm partOfCvterm = cvTermService.partOf
////        CVTerm exonCvterm = cvTermService.getTerm(type);
//        CVTerm transcriptCvterms = cvTermService.getTerm(FeatureStringEnum.TRANSCRIPT);
//
//        // delete transcript -> exon child relationship
//        for (FeatureRelationship fr : transcript.getChildFeatureRelationships()) {
//            if (partOfCvterm == fr.type
//                    && exonCvterm == fr.subjectFeature.type
//                    && fr.getSubjectFeature().equals(feature)
//            ) {
//                boolean ok = transcript.getChildFeatureRelationships().remove(fr);
//                break;
//
//            }
//        }

    }

//    public void removeParentFeature(Transcript transcript, Feature feature) {
//
//
//
//        CVTerm partOfCvterm = cvTermService.partOf
////        CVTerm exonCvterm = cvTermService.getTerm(type);
//        CVTerm transcriptCvterms = cvTermService.getTerm(FeatureStringEnum.TRANSCRIPT);
//
//        // delete transcript -> exon child relationship
//        for (FeatureRelationship fr : transcript.getParentFeatureRelationships()) {
//            if (partOfCvterm == fr.type
//                    && transcriptCvterms == fr.objectFeature.type
//                    && fr.getSubjectFeature().equals(feature)
//            ) {
//                boolean ok = feature.getParentFeatureRelationships().remove(fr);
//                break;
//
//            }
//        }
//
//    }
    List<Frameshift> getFeaturePropertyForTypes(Transcript transcript, List<String> strings) {
        return (List<Frameshift>) FeatureProperty.findAllByFeaturesInListAndOntologyIdsInList([transcript],strings)
//        for (FeatureProperty featureProperty : transcript.getFeatureProperties()) {
//            if (frameshiftCvterms.contains(featureProperty.getType())) {
//                frameshiftList.add((Frameshift) featureProperty);
//            }
//        }
    }
}
