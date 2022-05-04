package com.cummins.scd.models.services;

public interface IAccessControlService {
	public Object findAll(String catalogue);
	
	public Object findById(String catalogue, String id);
	
	public Object save(String catalogue, Object entity,String wwid);
	
	public void delete(String catalogue, String id);

	Object findByCriteria(String catalogue, Object filter);
}
