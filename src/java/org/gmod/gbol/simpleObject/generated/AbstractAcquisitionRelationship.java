package org.gmod.gbol.simpleObject.generated;


import org.gmod.gbol.simpleObject.*; 


/**
 * AcquisitionRelationship generated by hbm2java
 */
public abstract class AbstractAcquisitionRelationship extends AbstractSimpleObject implements java.io.Serializable {


     private Integer acquisitionRelationshipId;
     private CVTerm type;
     private Acquisition subjectAcquisition;
     private Acquisition objectAcquisition;
     private String value;
     private int rank;

    public AbstractAcquisitionRelationship() {
    }

    
    public AbstractAcquisitionRelationship(CVTerm type, Acquisition subjectAcquisition, Acquisition objectAcquisition, int rank) {
        this.type = type;
        this.subjectAcquisition = subjectAcquisition;
        this.objectAcquisition = objectAcquisition;
        this.rank = rank;
    }
    public AbstractAcquisitionRelationship(CVTerm type, Acquisition subjectAcquisition, Acquisition objectAcquisition, String value, int rank) {
       this.type = type;
       this.subjectAcquisition = subjectAcquisition;
       this.objectAcquisition = objectAcquisition;
       this.value = value;
       this.rank = rank;
    }
   
    public Integer getAcquisitionRelationshipId() {
        return this.acquisitionRelationshipId;
    }
    
    public void setAcquisitionRelationshipId(Integer acquisitionRelationshipId) {
        this.acquisitionRelationshipId = acquisitionRelationshipId;
    }
    public CVTerm getType() {
        return this.type;
    }
    
    public void setType(CVTerm type) {
        this.type = type;
    }
    public Acquisition getSubjectAcquisition() {
        return this.subjectAcquisition;
    }
    
    public void setSubjectAcquisition(Acquisition subjectAcquisition) {
        this.subjectAcquisition = subjectAcquisition;
    }
    public Acquisition getObjectAcquisition() {
        return this.objectAcquisition;
    }
    
    public void setObjectAcquisition(Acquisition objectAcquisition) {
        this.objectAcquisition = objectAcquisition;
    }
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    public int getRank() {
        return this.rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof AbstractAcquisitionRelationship) ) return false;
         AbstractAcquisitionRelationship castOther = ( AbstractAcquisitionRelationship ) other; 
         
         return ( (this.getType()==castOther.getType()) || ( this.getType()!=null && castOther.getType()!=null && this.getType().equals(castOther.getType()) ) )
 && ( (this.getSubjectAcquisition()==castOther.getSubjectAcquisition()) || ( this.getSubjectAcquisition()!=null && castOther.getSubjectAcquisition()!=null && this.getSubjectAcquisition().equals(castOther.getSubjectAcquisition()) ) )
 && ( (this.getObjectAcquisition()==castOther.getObjectAcquisition()) || ( this.getObjectAcquisition()!=null && castOther.getObjectAcquisition()!=null && this.getObjectAcquisition().equals(castOther.getObjectAcquisition()) ) )
 && (this.getRank()==castOther.getRank());
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getType() == null ? 0 : this.getType().hashCode() );
         result = 37 * result + ( getSubjectAcquisition() == null ? 0 : this.getSubjectAcquisition().hashCode() );
         result = 37 * result + ( getObjectAcquisition() == null ? 0 : this.getObjectAcquisition().hashCode() );
         
         result = 37 * result + this.getRank();
         return result;
   }   

public AbstractAcquisitionRelationship generateClone() {
    AbstractAcquisitionRelationship cloned = new AcquisitionRelationship(); 
           cloned.type = this.type;
           cloned.subjectAcquisition = this.subjectAcquisition;
           cloned.objectAcquisition = this.objectAcquisition;
           cloned.value = this.value;
           cloned.rank = this.rank;
    return cloned;
}


}

