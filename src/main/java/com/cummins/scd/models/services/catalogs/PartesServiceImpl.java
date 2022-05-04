package com.cummins.scd.models.services.catalogs;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IAux_PartesDao;
import com.cummins.scd.models.entity.Aux_Partes;
import com.cummins.scd.models.entity.Aux_Puestos;
import com.cummins.scd.models.entity.Perfiles;
import com.cummins.scd.models.entity.Users;
import com.cummins.scd.models.entity.UsersSpCodes;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PartesServiceImpl implements IPartesService {
    @Autowired
    IAux_PartesDao partsDao;

    private ObjectMapper mapper = new ObjectMapper();

    @PersistenceContext
    private EntityManager em;

    // ----------------------------------------------------------
    // GET ALL PARTS BY DEL='N'
    // ----------------------------------------------------------
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public List<Aux_Partes> getPartsByDel(Boolean flag, String lang) 
    {
	    List<Aux_Partes> listPartes = (List<Aux_Partes>) em
	        .createQuery("SELECT c FROM Aux_Partes c WHERE c.del='N'")
		    .getResultList();
	    if(flag==false) {
	        switch(lang) {
		        case"en_US":
		            for(int i=0; i<listPartes.size();i++) {
		        		listPartes.get(i).setdSpanish((listPartes.get(i).getdEnglish()));
		        	}
		        	break;
		        case"pt_BR":
		            for(int i=0; i<listPartes.size();i++) {
		        	    listPartes.get(i).setdSpanish((listPartes.get(i).getdPortuguese()));
		            }
		        	break;
		        default:
		            break;
	        }
        }
        listPartes.sort(Comparator.comparing(Aux_Partes::getdSpanish));
	    return listPartes;
    }
    
    //----------------------------------------------------------
    // SAVE PART
    //----------------------------------------------------------
  	@Override
  	public Aux_Partes save(String wwid, Aux_Partes entity)
  	{ 
  		Aux_Partes exist= findById(entity.getNp());
  		System.out.println(entity);
  		if(exist==null) {
  		    Date date= new Date();
  		    entity.setCreatedBy(wwid);
  		    entity.setLastUpdateBy(wwid);
  		    entity.setCreationDate(date);
  		    entity.setLastUpdateDate(date);
  		    entity.setDel('N');
  	 	    return partsDao.save(entity);
  		} else {
  		    return null;
  		}
  	}

    //----------------------------------------------------------
    // DELETE PART
    //----------------------------------------------------------
    @Override
    public Aux_Partes delete(String id, String wwid) 
    {
    	Aux_Partes exist= findById(id);
  		if(exist!=null) {
  		    Date date= new Date();
  		    exist.setLastUpdateBy(wwid);
  		    exist.setLastUpdateDate(date);
  		    exist.setDel('Y');
  	 	    return partsDao.save(exist);
  		} else {
  		    return null;
  		}
    }
    
    //----------------------------------------------------------
    // FIND PART BY ID
    //----------------------------------------------------------
    @Override
    public Aux_Partes findById(String id) 
    {
        Aux_Partes part=partsDao.findOne(id);
        return part;
    }
    
    //----------------------------------------------------------
    // UPDATE PART
    //----------------------------------------------------------
  	@Override
  	public Aux_Partes update(String wwid, Aux_Partes entity)
  	{ 
  		Aux_Partes exist= findById(entity.getNp());
  		
  		if(exist!=null) {
  		    Date date= new Date();
  		    exist.setCodigoVenta(entity.getCodigoVenta());
  		    exist.setdSpanish(entity.getdSpanish());
  		    exist.setdEnglish(entity.getdEnglish());
  		    exist.setdPortuguese(entity.getdPortuguese());
  		    exist.setNp(entity.getNp());
  		    exist.setnPAnterior(entity.getnPAnterior());
  		    exist.setnPEquivalente(entity.getnPEquivalente());
  		    exist.setLastUpdateBy(wwid);
  		    exist.setLastUpdateDate(date);
  	 	    return partsDao.save(exist);
  		} else {
  		    return null;
  		}
  	}
}
