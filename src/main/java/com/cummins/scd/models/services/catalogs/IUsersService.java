package com.cummins.scd.models.services.catalogs;
import java.util.List;


import com.cummins.scd.models.entity.Users;
import com.cummins.scd.models.entity.UsersSpCodes;
import com.cummins.scd.models.entity.auxiliar.tables.Aux_Roles;



public interface IUsersService {

	List<Users> getUsersByDel();

	Object save(String wwid, Object entity);

	Object delete(String id, String wwid);

	Object update(Object entity, String wwid);

	Object getUsersByWWID(String wwid);

	List<UsersSpCodes> getSpCodesByWWID(String wwid);

	UsersSpCodes deleteCode(String id, String spcode, String wwid);

	UsersSpCodes addSpCode(Object spcode, String wwid);

	//List<Aux_Roles> getRolesByDel();

	//List<Aux_Roles> getRolesByDel(String lang);

	List<Aux_Roles> getRolesByDel(String lang, Boolean flag);

	
	
}
