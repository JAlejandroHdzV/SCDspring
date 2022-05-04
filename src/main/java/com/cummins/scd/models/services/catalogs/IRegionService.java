package com.cummins.scd.models.services.catalogs;

import java.util.List;

import com.cummins.scd.models.entity.Region;

public interface IRegionService {

	List<Region> regionBySpcodesAndWwid(String wwid);

	List<Region> getAllRegionsByDel();

	List<Region> getRegionsByWwid(String wwid);

}
