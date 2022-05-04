package com.cummins.scd.models.entity;

import java.util.List;

import com.cummins.scd.models.dto.ConfMotorDTO;

public class ReporteCargaMasivaConfMotor {
	
List<Errores> errores;
List<ConfMotorDTO> confMotor;


public List<Errores> getErrores() {
	return errores;
}
public void setErrores(List<Errores> errores) {
	this.errores = errores;
}
public List<ConfMotorDTO> getConfMotor() {
	return confMotor;
}
public void setConfMotor(List<ConfMotorDTO> confMotor) {
	this.confMotor = confMotor;
}
	


}
