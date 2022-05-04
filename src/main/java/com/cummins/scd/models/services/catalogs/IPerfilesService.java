package com.cummins.scd.models.services.catalogs;

import java.util.List;

import com.cummins.scd.models.entity.ConfMotor;
import com.cummins.scd.models.entity.Perfiles;
import com.cummins.scd.models.entity.RelPerfilExcs;
import com.cummins.scd.models.entity.RelPerfilMotor;

public interface IPerfilesService {

	Object save(String wwid,  String idEv, String idEvCopy);

	List<Perfiles> getPerfilesByDel();

	List<Perfiles> getPerfiles(String id,String code);

	List<RelPerfilExcs> getPerfilExceptions(String idPerfil,String idConfMotor);


	Object getPerfilesByEvAndResp(String idEvaluacion, String respBranchCode);

	List<RelPerfilMotor> getPerfilesMotor(String IdPerfil);





}
