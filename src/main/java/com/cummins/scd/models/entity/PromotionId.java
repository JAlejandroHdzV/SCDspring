package com.cummins.scd.models.entity;
import java.io.Serializable;




public class PromotionId implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String promotionId;
    private String spCode;
    private String programId;

    public PromotionId(){

    }

    public PromotionId(String prom, String s, String prog){
        this.promotionId = prom;
        this.spCode = s;
        this.programId= prog;
    }

    
    
    
    public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	
}