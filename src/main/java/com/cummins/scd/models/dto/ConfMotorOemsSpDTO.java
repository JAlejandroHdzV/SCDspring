package com.cummins.scd.models.dto;

import java.util.List;

import com.cummins.scd.models.entity.ConfMotor;
import com.cummins.scd.models.entity.ConfOem;
import com.cummins.scd.models.entity.ConfSpcodes;

public class ConfMotorOemsSpDTO {
	ConfMotor confMotor;
	List<ConfSpcodes> sp;
	List<ConfOem> oem;
	public ConfMotor getConfMotor() {
		return confMotor;
	}
	public void setConfMotor(ConfMotor confMotor) {
		this.confMotor = confMotor;
	}
	public List<ConfSpcodes> getSp() {
		return sp;
	}
	public void setSp(List<ConfSpcodes> sp) {
		this.sp = sp;
	}
	public List<ConfOem> getOem() {
		return oem;
	}
	public void setOem(List<ConfOem> oem) {
		this.oem = oem;
	}
	
	
}
