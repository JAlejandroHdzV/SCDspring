package com.cummins.scd.models.services.catalogs;

import java.util.List;

import com.cummins.scd.models.entity.Oems;

public interface IOemService  {

	List<Oems> OemById(String oem);

}
