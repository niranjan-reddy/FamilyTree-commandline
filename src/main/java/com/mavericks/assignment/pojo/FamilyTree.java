package com.mavericks.assignment.pojo;

import java.util.List;

public class FamilyTree {
	int refId;
    String name;
    String gender;
    int fatherRefId;
    int motherRefId;
    int spouseRefId;
    List<FamilyTree> subfamily;
    
    public FamilyTree() {
    	
    }
    
    public FamilyTree(String refId,  String name, String gender, String fatherRefId, String motherRefId, String spouseRefId) {    
    	try {
	        this.refId = Integer.parseInt(refId);	        
	        this.name = name;
	        this.gender = gender;
	        if(fatherRefId != null && fatherRefId.length() > 0)
	        	this.fatherRefId = Integer.parseInt(fatherRefId);
	        if(motherRefId != null && motherRefId.length() > 0)
	        	this.motherRefId = Integer.parseInt(motherRefId);
	        if(spouseRefId != null && spouseRefId.length() > 0)
	        	this.spouseRefId = Integer.parseInt(spouseRefId);
    	}  catch (Exception e) {
			 System.err.println("Exception creating/adding Family:" + e);
		}
    }
    
    public int getRefId() {
		return refId;
	}

	public void setRefId(int refId) {
		this.refId = refId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getFatherRefId() {
		return fatherRefId;
	}

	public void setFatherRefId(int fatherRefId) {
		this.fatherRefId = fatherRefId;
	}

	public int getMotherRefId() {
		return motherRefId;
	}

	public void setMotherRefId(int motherRefId) {
		this.motherRefId = motherRefId;
	}

	public int getSpouseRefId() {
		return spouseRefId;
	}

	public void setSpouseRefId(int spouseRefId) {
		this.spouseRefId = spouseRefId;
	}

	public void setSubfamily(List<FamilyTree> subfamily) {
		this.subfamily = subfamily;
	}

    public List<FamilyTree> getSubfamily() {
        return subfamily;
    }
    
    public String getName() {
        return name;
    }

    void setName(String n) {
        name = n;
    }
    
    public String toString() {
    	return Integer.toString(this.getRefId()) + " " +
    			this.getName() + " " +
    			this.getGender() + " " +
    			Integer.toString(this.getFatherRefId()) + " " +
    			Integer.toString(this.getMotherRefId()) + " " +
    			Integer.toString(this.getSpouseRefId()) + " ";
    }
}
