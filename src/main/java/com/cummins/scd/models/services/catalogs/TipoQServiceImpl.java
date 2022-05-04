package com.cummins.scd.models.services.catalogs;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IAux_TipoQDao;
import com.cummins.scd.models.entity.Aux_CC;
import com.cummins.scd.models.entity.Aux_TipoQ;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TipoQServiceImpl implements ITipoQService {
	@Autowired
	IAux_TipoQDao tipoQDao;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext
	private EntityManager em;
	
	//---------------------------------------------------------------------
	//                  GET TIPO BY DEL 'N' AND LANG
	//---------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Object getTipoQByDel(String lang) {
		List<Aux_TipoQ> listTipoQ=null;
		switch(lang) {
		case "es_ES":
			listTipoQ=(List<Aux_TipoQ> ) em.createQuery("SELECT c from Aux_TipoQ c  where c.del='N' order by c.tipo").getResultList();
			
			break;
		case "en_US":
			listTipoQ=(List<Aux_TipoQ> ) em.createQuery("SELECT c from Aux_TipoQ c  where c.del='N' order by c.english").getResultList();
			
			for(int i=0; i<listTipoQ.size();i++) {
        		listTipoQ.get(i).setTipo(listTipoQ.get(i).getEnglish());
        		//System.out.println("en");
        	}
			break;
		case "pt_BR":
			listTipoQ=(List<Aux_TipoQ> ) em.createQuery("SELECT c from Aux_TipoQ c  where c.del='N' order by c.portuguese").getResultList();
			for(int i=0; i<listTipoQ.size();i++) {
        		listTipoQ.get(i).setTipo(listTipoQ.get(i).getPortuguese());
        		//System.out.println("en");
        	}
			break;

		}
		//listTipoQ=(List<Aux_TipoQ> ) em.createQuery("SELECT c from Aux_TipoQ c  where c.del='N' order by c.portuguese").setMaxResults(1).getResultList();
		//Stream<Aux_TipoQ> miStream=listTipoQ.stream().filter(x->x.getIdTipo().equals(BigInteger.valueOf(1)));
		//Stream<Author> authors = em.createQuery("SELECT a FROM Author a", Author.class).getResultStream();
		//CriteriaBuilder builder=em.getCriteriaBuilder();
		//CriteriaQuery query=builder.createQuery();
		//Root<Aux_TipoQ> tipo= query.from(Aux_TipoQ.class);
		//Predicate tipos=builder.and(tipo.get("tipo"));
		
		return listTipoQ;
		
		
		
	}
}

