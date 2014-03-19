package org.gmod.gbol.simpleObject.generated;


import org.gmod.gbol.simpleObject.*; 


/**
 * StudyFactorValue generated by hbm2java
 */
public abstract class AbstractStudyFactorValue extends AbstractSimpleObject implements java.io.Serializable {


     private Integer studyFactorValueId;
     private StudyFactor studyFactor;
     private Assay assay;
     private String factorValue;
     private String name;
     private int rank;

    public AbstractStudyFactorValue() {
    }

	
    public AbstractStudyFactorValue(StudyFactor studyFactor, Assay assay, int rank) {
        this.studyFactor = studyFactor;
        this.assay = assay;
        this.rank = rank;
    }
    public AbstractStudyFactorValue(StudyFactor studyFactor, Assay assay, String factorValue, String name, int rank) {
       this.studyFactor = studyFactor;
       this.assay = assay;
       this.factorValue = factorValue;
       this.name = name;
       this.rank = rank;
    }
   
    public Integer getStudyFactorValueId() {
        return this.studyFactorValueId;
    }
    
    public void setStudyFactorValueId(Integer studyFactorValueId) {
        this.studyFactorValueId = studyFactorValueId;
    }
    public StudyFactor getStudyFactor() {
        return this.studyFactor;
    }
    
    public void setStudyFactor(StudyFactor studyFactor) {
        this.studyFactor = studyFactor;
    }
    public Assay getAssay() {
        return this.assay;
    }
    
    public void setAssay(Assay assay) {
        this.assay = assay;
    }
    public String getFactorValue() {
        return this.factorValue;
    }
    
    public void setFactorValue(String factorValue) {
        this.factorValue = factorValue;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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
		 if ( !(other instanceof AbstractStudyFactorValue) ) return false;
		 AbstractStudyFactorValue castOther = ( AbstractStudyFactorValue ) other; 
         
		 return ( (this.getStudyFactor()==castOther.getStudyFactor()) || ( this.getStudyFactor()!=null && castOther.getStudyFactor()!=null && this.getStudyFactor().equals(castOther.getStudyFactor()) ) )
 && ( (this.getAssay()==castOther.getAssay()) || ( this.getAssay()!=null && castOther.getAssay()!=null && this.getAssay().equals(castOther.getAssay()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getStudyFactor() == null ? 0 : this.getStudyFactor().hashCode() );
         result = 37 * result + ( getAssay() == null ? 0 : this.getAssay().hashCode() );
         
         
         
         return result;
   }   

public AbstractStudyFactorValue generateClone() {
	AbstractStudyFactorValue cloned = new AbstractStudyFactorValue; 
    	   cloned.studyFactor = this.studyFactor;
    	   cloned.assay = this.assay;
    	   cloned.factorValue = this.factorValue;
    	   cloned.name = this.name;
    	   cloned.rank = this.rank;
	return cloned;
}


}

