package com.cummins.scd.models.entity;


import java.io.Serializable;
import java.math.BigInteger;
public class UsersSpCodesId  implements Serializable{

	//----------------------------------------------
	//					Fields
	//----------------------------------------------
	
	private String wwid;
	private String spCode;
	
	public String getWwid() {
		return wwid;
	}
	public void setWwid(String wwid) {
		this.wwid = wwid;
	}
	public String getSpCode() {
		return spCode;
	}
	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}
	
	

}
