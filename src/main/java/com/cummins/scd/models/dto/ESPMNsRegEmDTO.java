package com.cummins.scd.models.dto;

import java.util.List;

import com.cummins.scd.models.entity.ESPM;
import com.cummins.scd.models.entity.ESPM_Emision;
import com.cummins.scd.models.entity.ESPM_NS;
import com.cummins.scd.models.entity.ESPM_Region;

public class ESPMNsRegEmDTO {
	//----------------------------------------------
	//				FIELDS DTO CLASS
	//----------------------------------------------
	ESPM espm;
	List<ESPM_Emision> emision;
	List<ESPM_Region> region;
	List<ESPM_NS> ns;



	//----------------------------------------------
	//				FIELDS DTO CLASS
	//----------------------------------------------
	public ESPMNsRegEmDTO(ESPM espm, List<ESPM_Emision> emision, List<ESPM_Region> region, List<ESPM_NS> ns) {
		this.espm = espm;
		this.emision = emision;
		this.region = region;
		this.ns = ns;
	}

	public ESPMNsRegEmDTO() {
		
	}
	public ESPM getEspm() {
		return espm;
	}
	public void setEspm(ESPM espm) {
		this.espm = espm;
	}
	public List<ESPM_Emision> getEmision() {
		return emision;
	}
	public void setEmision(List<ESPM_Emision> emision) {
		this.emision = emision;
	}
	public List<ESPM_Region> getRegion() {
		return region;
	}
	public void setRegion(List<ESPM_Region> region) {
		this.region = region;
	}
	public List<ESPM_NS> getNs() {
		return ns;
	}
	public void setNs(List<ESPM_NS> ns) {
		this.ns = ns;
	}


}
