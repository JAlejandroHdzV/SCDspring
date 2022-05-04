package com.cummins.scd.models.services.catalogs;

import java.util.List;

import com.cummins.scd.models.entity.Aux_Partes;

public interface IPartesService 
{
    List<Aux_Partes> getPartsByDel(Boolean flag, String lang);
    Aux_Partes save(String wwid, Aux_Partes entity);
    Aux_Partes delete(String id, String wwid);
    Aux_Partes findById(String id);
    Aux_Partes update(String wwid, Aux_Partes entity);
}
