package com.cummins.scd.models.services.catalogs;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IUsersDao;
import com.cummins.scd.models.dao.IUsersSpCodesDao;
import com.cummins.scd.models.entity.Aux_StatusMatriz;
import com.cummins.scd.models.entity.Quejas;
import com.cummins.scd.models.entity.Users;
import com.cummins.scd.models.entity.UsersSpCodes;
import com.cummins.scd.models.entity.auxiliar.tables.Aux_Roles;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class UsersServiceImpl implements IUsersService{
	@Autowired
	IUsersDao usersDao;
	@Autowired
	IUsersSpCodesDao usersSpcodesDao;
	
	private ObjectMapper mapper = new ObjectMapper();

	@PersistenceContext
	private EntityManager em;
	
	
	//----------------------------------------------------------
	//					GET ALL USERS
	//----------------------------------------------------------
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getUsersByDel(){
		List<Users> listUsers=(List<Users> ) em.createQuery(
				"SELECT c from Users c where c.del='N' order by c.wwid asc").getResultList();
		return  listUsers;
	}
	
	//----------------------------------------------------------
	//					GET 1 SPCODE BY WWID
	//----------------------------------------------------------
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public UsersSpCodes getSpCodeByWWID(String wwid,String spcode, String del){
		List<UsersSpCodes> listCode=(List<UsersSpCodes>) em.createQuery(
				"SELECT c from UsersSpCodes c where c.del= '" + del + "' AND c.wwid= '" + wwid + "' AND c.spCode= '" + spcode + "'" ).getResultList();
		return  listCode.size()>0 ? listCode.get(0) : null ;
	}
	//----------------------------------------------------------
	//					GET SPCODES BY WWID
	//----------------------------------------------------------
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<UsersSpCodes> getSpCodesByWWID(String wwid){
		List<UsersSpCodes> listSpCodes=(List<UsersSpCodes> ) em.createQuery(
				"SELECT c from UsersSpCodes c where c.del='N' AND c.wwid= '" + wwid + "'" ).getResultList();
		return  listSpCodes;
	}
	
	//----------------------------------------------------------
	//					GET USER BY WWID
	//----------------------------------------------------------
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public Object getUsersByWWID(String wwid){
		Map<String, Object> response = new HashMap<>();
		List<Users> listUsers=(List<Users> ) em.createQuery(
				"SELECT c from Users c where c.del='N' AND c.wwid= '" + wwid  + "' ORDER BY c.rol" ).getResultList();
		
		
		List<UsersSpCodes> listSpCodes=getSpCodesByWWID(wwid);
		response.put("spcodes",listSpCodes);
		response.put("user", listUsers);
		
		return  response;
	}
	//----------------------------------------------------------
	//					GET ROLES
	//----------------------------------------------------------
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Aux_Roles> getRolesByDel(String lang, Boolean flag){
		List<Aux_Roles> listRoles=(List<Aux_Roles> ) em.createQuery(
				"SELECT c from Aux_Roles c where c.del='N'").getResultList();
		if(flag==false) {
	        switch(lang) {
		        case"en_US":
		        	for(int i=0; i<listRoles.size();i++) {
		        		listRoles.get(i).setRol(listRoles.get(i).getIngles());
		        		//System.out.println("en");
		        	}
		        	break;
		        	
		        case"pt_BR":
		        	for(int i=0; i<listRoles.size();i++) {
		        		listRoles.get(i).setRol(listRoles.get(i).getPortugues());
		        	}
		        	
		        	break;
		        	
		        default:
		        	break;
	        }
    }
    listRoles.sort(Comparator.comparing(Aux_Roles::getRol));
    return listRoles;
	}
	//----------------------------------------------------------
	//					SAVE USER
	//----------------------------------------------------------
	@Override
	public Object save(String wwid, Object entity)
	{ 
		Users q= new Users();
	 	Date date = new Date();
	 	Map<String, Object> response = new HashMap<>();
	 	JsonNode actualObj =  mapper.convertValue(entity, JsonNode.class);
	 	String spcodes[]=null;
	 	q.setWwid(actualObj.get("wwid").toString().replace("\"", ""));
	 	q.setNombre(actualObj.get("nombre").toString().replace("\"", ""));
	 	q.setEmail(actualObj.get("email").toString().replace("\"", ""));
	 	q.setIdRol(new BigInteger (actualObj.get("idRol").toString().replace("\"", "")));
	 	q.setTodas((actualObj.get("todas").toString().replace("\"", "").charAt(0)));
	 	q.setCreationDate(date);
	 	q.setCreatedBy(wwid);
	 	q.setLastUpdateDate(date);
	 	q.setLastUpdateBy(wwid);
	 	q.setDel('N');
	 	usersDao.save(q);
	 	List<UsersSpCodes> listaSc= new ArrayList<UsersSpCodes>();
	 	if(q.getTodas().equals('0')) {
	 		spcodes=actualObj.get("spcodes").toString().replace("\"", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
	 	
	 	for(String sc:spcodes)
	 	{
	 		UsersSpCodes usp= new UsersSpCodes();
	 		UsersSpCodes deletedSpCode=getSpCodeByWWID(wwid,sc,"Y");
	 		if(deletedSpCode!=null)
	 		{
	 			usp.setWwid(q.getWwid());
		 		usp.setSpCode(sc.toString());
		 		usp.setCreationDate(deletedSpCode.getCreationDate());
		 		usp.setCreatedBy(deletedSpCode.getCreatedBy());
		 		usp.setLastUpdateDate(date);
		 		usp.setLastUpdateBy(wwid);
		 		usp.setDel('N');
	 		}
	 		else
	 		{
		 		usp.setWwid(q.getWwid());
		 		usp.setSpCode(sc.toString());
		 		usp.setCreationDate(date);
		 		usp.setCreatedBy(wwid);
		 		usp.setLastUpdateDate(date);
		 		usp.setLastUpdateBy(wwid);
		 		usp.setDel('N');
	 		}
	 		listaSc.add(usersSpcodesDao.save(usp));	
	 	}
	 	}
	 	System.out.println(q);
	 	response.put("Users", q);
	 	response.put("spcodes", listaSc);
	 	
	 	return response;
	 	//return usersDao.save(q);
	}

	//----------------------------------------------------------
	//				DELETE USER
	//----------------------------------------------------------
	@Override
	public Object delete(String id, String wwid) {
		Users q=usersDao.findOne(id);
		Map<String, Object> response = new HashMap<>();
		Date date = new Date();
		q.setDel('Y');
	 	q.setLastUpdateDate(date);
	 	q.setLastUpdateBy(wwid);
	 	usersDao.save(q);
	 	List<UsersSpCodes> listSpCodes=getSpCodesByWWID(id);
	 	List<UsersSpCodes> listaSc= new ArrayList<UsersSpCodes>();
	 	for(UsersSpCodes sc: listSpCodes)
	 	{
	 		UsersSpCodes delCode = new UsersSpCodes();
	 		delCode = mapper.convertValue(sc, UsersSpCodes.class);
	 		delCode.setDel('Y');
	 		delCode.setLastUpdateBy(wwid);
	 		delCode.setLastUpdateDate(date);
	 		listaSc.add(usersSpcodesDao.save(delCode));	
	 		
	 	}
		response.put("spcodes",listaSc);
		response.put("user", q);
	 	
	 	return response;
		
	}
	//----------------------------------------------------------
	//				UPDATE USER
	//----------------------------------------------------------
	@Override
	public Object update(Object entity, String wwid) {
		System.out.println(entity);
		Users q= new Users();
	 	Date date = new Date();
	 	Map<String, Object> response = new HashMap<>();
	 	JsonNode actualObj =  mapper.convertValue(entity, JsonNode.class);
	 	String spcodes[]=   actualObj.get("spcodes").toString().replace("\"", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
	 	System.out.println("Total spcodes: "+spcodes.length);
	 	System.out.println(spcodes.toString());
	 	q.setWwid(actualObj.get("wwid").toString().replace("\"", ""));
	 	q.setNombre(actualObj.get("nombre").toString().replace("\"", ""));
	 	q.setEmail(actualObj.get("email").toString().replace("\"", ""));
	 	q.setIdRol(new BigInteger (actualObj.get("idRol").toString().replace("\"", "")));
	 	q.setTodas((actualObj.get("todas").toString().replace("\"", "").charAt(0)));
	 	q.setCreationDate(date);
	 	q.setCreatedBy(wwid);
	 	q.setLastUpdateDate(date);
	 	q.setLastUpdateBy(wwid);
	 	q.setDel('N');
	 	usersDao.save(q);
	 	//try {
	 	List<UsersSpCodes> listaSpCodes = getSpCodesByWWID(q.getWwid());
	 	for(UsersSpCodes spc: listaSpCodes)
	 	{
	 		Boolean band=false;
	 		for(String newSPC: spcodes)
	 		{
	 			if(spc.getSpCode() == newSPC)
	 			{
	 				band= true;
	 				break;
	 			}
	 		}
	 		if(band==false)
	 		{
		 		spc.setLastUpdateDate(date);
		 		spc.setLastUpdateBy(wwid);
		 		spc.setDel('Y');
		 		usersSpcodesDao.save(spc);
	 		}
	 		
	 	
	 	}
	 	
	 	
	 	
	 	List<UsersSpCodes> listaSc= new ArrayList<UsersSpCodes>();
	 	
	 	for(String sc:spcodes)
	 	{
	 		if(!sc.isEmpty()) {
	 		UsersSpCodes usp= new UsersSpCodes();
	 		UsersSpCodes deletedSpCode=getSpCodeByWWID(wwid,sc,"Y");
	 		if(deletedSpCode!=null)
	 		{
	 			usp.setWwid(q.getWwid());
		 		usp.setSpCode(sc.toString());
		 		usp.setCreationDate(deletedSpCode.getCreationDate());
		 		usp.setCreatedBy(deletedSpCode.getCreatedBy());
		 		usp.setLastUpdateDate(date);
		 		usp.setLastUpdateBy(wwid);
		 		usp.setDel('N');
	 		}
	 		else
	 		{
		 		usp.setWwid(q.getWwid());
		 		usp.setSpCode(sc.toString());
		 		usp.setCreationDate(date);
		 		usp.setCreatedBy(wwid);
		 		usp.setLastUpdateDate(date);
		 		usp.setLastUpdateBy(wwid);
		 		usp.setDel('N');
	 		}
	 		listaSc.add(usersSpcodesDao.save(usp));	
	 		}
	 	}
	 	System.out.println(q);
	 	response.put("Users", q);
	 	response.put("spcodes", listaSc);
	 	/*}catch(Exception e) {
	 		System.out.println(e.getMessage());
	 	}*/
	 	
	 	return response;

		
		
		
		/*Users q=usersDao.findOne(entity.getWwid());
	 	Date date = new Date();
		q.setNombre(entity.getNombre());
		q.setEmail(entity.getEmail());
		q.setIdRol(entity.getIdRol());
		q.setTodas(entity.getTodas());
	 	q.setDel('N');
	 	q.setLastUpdateDate(date);
	 	q.setLastUpdateBy(wwid);
		return usersDao.save(q);
		*/
	
	}
	
	//----------------------------------------------------------
	//			 POST  USER SPCODE 
	//----------------------------------------------------------
	@Override
	public UsersSpCodes addSpCode(Object spcode,String wwid)
	{
		Date date = new Date();
		UsersSpCodes Code = new UsersSpCodes();
		Code = mapper.convertValue(spcode, UsersSpCodes.class);
		UsersSpCodes deletedSpCode=getSpCodeByWWID(Code.getWwid(),Code.getSpCode(),"Y");
 		if(deletedSpCode!=null)
 		{
	 		Code.setCreationDate(deletedSpCode.getCreationDate());
	 		Code.setCreatedBy(deletedSpCode.getCreatedBy());
	 		Code.setLastUpdateDate(date);
	 		Code.setLastUpdateBy(wwid);
	 		Code.setDel('N');
 		}
 		else
 		{
	 		Code.setCreationDate(date);
	 		Code.setCreatedBy(wwid);
	 		Code.setLastUpdateDate(date);
	 		Code.setLastUpdateBy(wwid);
	 		Code.setDel('N');
 		}
		return usersSpcodesDao.save(Code);
	}
		
	//----------------------------------------------------------
	//			 DELETE USER SPCODE BY WWID AND SPCODE
	//----------------------------------------------------------
	@Override
	public UsersSpCodes deleteCode(String id, String spcode, String wwid)
	{
		UsersSpCodes spCode=getSpCodeByWWID(id,spcode,"N");
		if (spCode!=null)
		{
			UsersSpCodes delCode = new UsersSpCodes();
			delCode = mapper.convertValue(spCode, UsersSpCodes.class);
			Date date = new Date();
			delCode.setDel('Y');
	 		delCode.setLastUpdateBy(wwid);
	 		delCode.setLastUpdateDate(date);
	 		return usersSpcodesDao.save(delCode);
		}
		else
			return null;
 		
	}
	


}
