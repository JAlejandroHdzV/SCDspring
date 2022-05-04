package com.cummins.scd.models.services.catalogs;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cummins.scd.models.dao.IMatrizPartesHdDao;
import com.cummins.scd.models.dto.MatrizDTO;
import com.cummins.scd.models.entity.AtributosMatricesHtas;
import com.cummins.scd.models.entity.ESPM;
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.cummins.scd.models.entity.MatrizPartesHd;
import com.cummins.scd.models.services.ILoadInfoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MatrizPartesHdService implements IMatrizPartesHdService {
	@Autowired
	IMatrizPartesHdDao matrizPartesHdDao;
	@Autowired
	ILoadInfoService loadInfo;
	private ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<MatrizPartesHd> matricesValidasCargaMasiva(String ns, String dist, String mod, String oem, String matriz) {
		System.out.println("ns: "+ns+"mod: "+mod+"oem: "+oem+" matriz: "+matriz);
		List<MatrizPartesHd> listMatriz=(List<MatrizPartesHd> ) em.createQuery(
				"SELECT c from MatrizPartesHd c  "
						+ "where c.del='N' And "
						+ " c.nombre= '"+matriz+"' And "
						+ " c.idStatus = '1' "
						+"And c.idNs= (SELECT e.idServiceLevel FROM ServiceLevel e where e.serviceLevel='"+ns+"')"
							+ "And c.idMatriz in"
							+ "(select d.idMatriz from AtributosMatricesHtas d "
							+ "where d.tipoMatriz='1' And d.tipoAtributo='1' And d.idText in ("+dist+") ) "
								+ "And c.idMatriz in"
								+ "(select d.idMatriz from AtributosMatricesHtas d "
								+ "where d.tipoMatriz='1' And d.tipoAtributo='2' And d.idNumber in ("+mod+") ) "
									+ "And c.idMatriz in"
									+ "(select d.idMatriz from AtributosMatricesHtas d "
									+ "where d.tipoMatriz='1' And d.tipoAtributo='3' And d.idNumber in ("+oem+") ) "
										+ "").getResultList();
		
		return listMatriz;
		
	}
	
	
	
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<MatrizDTO> matricesValidas(Object entityFilter) {
		try {
		JsonNode actualObj1 = null;
		actualObj1 = mapper.convertValue(entityFilter, JsonNode.class);
		System.out.println(actualObj1);
		//String tipoMatriz=actualObj1.get("tipoMatriz").toString().replaceAll("\"", "");
		String ns=actualObj1.get("ns").toString().replaceAll("\"", "");
		String dist=(actualObj1.get("distribuidor").toString().replaceAll("\"", ""));
		System.out.println("dist: "+dist);
        String mod=(actualObj1.get("modProductos").toString().replaceAll("\"", ""));
        System.out.println("mod: "+mod);
        String oem=(actualObj1.get("oems").toString().replaceAll("\"", ""));
        System.out.println("oem: "+oem);
		List<MatrizPartesHd> listMatriz=(List<MatrizPartesHd> ) em.createQuery(
				"SELECT c from MatrizPartesHd c  "
						+ "where c.del='N' And "
						+ " c.idStatus = '1' "
						+"And c.idNs='"+ns+"'"
							+ "And c.idMatriz in"
							+ "(select d.idMatriz from AtributosMatricesHtas d "
							+ "where d.tipoMatriz='1' And d.tipoAtributo='1' And d.idText in ("+dist+") ) "
								+ "And c.idMatriz in"
								+ "(select d.idMatriz from AtributosMatricesHtas d "
								+ "where d.tipoMatriz='1' And d.tipoAtributo='2' And d.idNumber in ("+mod+") ) "
									+ "And c.idMatriz in"
									+ "(select d.idMatriz from AtributosMatricesHtas d "
									+ "where d.tipoMatriz='1' And d.tipoAtributo='3' And d.idNumber in ("+oem+") ) "
										
										+ "").getResultList();
		List<MatrizDTO> response= new ArrayList<>();
		 for(MatrizPartesHd matriz: listMatriz) {
			 MatrizDTO newMatriz= new MatrizDTO();
			 newMatriz.setIdMatriz(matriz.getIdMatriz().toString());
			 newMatriz.setNombre(matriz.getNombre());
			 newMatriz.setNoRevision(matriz.getNombre());
			 response.add(newMatriz);
		 }
		
		
		return response;
		}catch(Exception e) {
			System.out.println("Entra en el catch");
			return null;
		}
		
		/*
		 + "And c.idMatriz in"
										+ "(select d.idMatriz from AtributosMatricesHtas d "
										+ "where d.tipoMatriz='"+tipoMatriz+"' And d.tipoAtributo='4' And d.idNumber in ("+can+") ) " 
		  
		 c.del='N' 
		  }
		 return em.createQuery("SELECT c from MatrizPartesHd c ,AtributosMatricesHtas d "
															+ "where c.del='N' "
															+ "And c.idStatus='1'").getResultList();
	}
		 */
	}
	
	
	
	@Override
	@Transactional(readOnly = true)
    public List<MatrizPartesHd> findMatrizPartesHdByCriteria(Object entityFilter){
        
        MatrizPartesHd filter = mapper.convertValue(entityFilter, MatrizPartesHd.class);
       SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
       return matrizPartesHdDao.findAll( new  Specification<MatrizPartesHd>() {

           @Override
           public Predicate toPredicate(Root<MatrizPartesHd> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               
                if(filter.getIdMatriz() != null ) {
                predicates.add(cb.and(cb.equal(cb.upper(root.get("idMatriz")), filter.getIdMatriz())));
                }
                                			
                if(filter.getNombre() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("nombre")), "%" + filter.getNombre().toUpperCase() + "%")));
                }			
                if(filter.getNoRevision() != null) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("noRevision")), "%" + filter.getNoRevision().toUpperCase() + "%")));
                }
                if(filter.getIdTipo() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idTipo")), filter.getIdTipo())));
                }
                if(filter.getIdNs() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idNs")), filter.getIdNs())));
                }
                if(filter.getIdStatus() != null ) {
                    predicates.add(cb.and(cb.equal(cb.upper(root.get("idStatus")), filter.getIdStatus())));
                }
                if(filter.getSo() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("so")), "%" + filter.getSo().toUpperCase() + "%")));
                }
                if(filter.getESN() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("esn")), "%" + filter.getESN().toUpperCase() + "%")));
                }
                if(filter.getCPL() != null) {
                    predicates.add(cb.and(cb.like(cb.upper(root.get("cpl")), "%" + filter.getCPL().toUpperCase() + "%")));
                }
                        
                if(filter.getCreatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdateDate() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")), "%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
                }                
                if(filter.getLastUpdatedBy() != null ) {
                   predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")), "%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
                }
                if(filter.getDel() != null) {
                   predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
                }				
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
           }
           
       }, new Sort(Sort.Direction.ASC, "nombre"));
    }
	
	
	
	
	@Override
	@Transactional(readOnly = true)
    public List<MatrizPartesHd> partsMatrixExistConf(Object entityFilter, String idioma){
		MatrizPartesHd filter= new MatrizPartesHd();
		filter.setDel('N');
		List<MatrizPartesHd> listPartesHd= findMatrizPartesHdByCriteria(filter);
		List<MatrizPartesHd>response= new ArrayList<MatrizPartesHd>();
      
		
		JsonNode actualObj1 = null;
		actualObj1 = mapper.convertValue(entityFilter, JsonNode.class);
		String tipoMatriz=actualObj1.get("tipoMatriz").toString().replaceAll("\"", "");
		String[] dist=(actualObj1.get("distribuidor").toString().replaceAll("\"", "")).split(",");
        String[] mod=(actualObj1.get("modProductos").toString().replaceAll("\"", "")).split(",");
        String[] oem=(actualObj1.get("oems").toString().replaceAll("\"", "")).split(",");
        String[] can=(actualObj1.get("canal").toString().replaceAll("\"", "")).split(",");
        
		for(MatrizPartesHd lista: listPartesHd) {
			boolean exist=false;
			//ATRIBUTOS MATRIZ PARTES DISTRIBUIDORES
			if(exist==false) {
            for(String d:dist) {
            	System.out.println("distribuidor: "+d);
            	AtributosMatricesHtas att= new AtributosMatricesHtas();
            	att.setTipoAtributo(new BigInteger("1"));
            	att.setIdMatriz(lista.getIdMatriz());
            	att.setTipoMatriz(new BigInteger(tipoMatriz));
            	att.setIdText(d);
            	att.setDel('N');
            	List<AtributosMatricesHtas> count= loadInfo.findAtributosMatricesHtasByCriteria(att);
            	if(count.size()<1) {
            		exist=true;
            		break;
            	}
            }
			}
          //ATRIBUTOS MATRIZ PARTES MODELOS / PRODUCTOS
			if(exist==false) {
            for(String m:mod) {
            	System.out.println("modelos: "+m);
            	AtributosMatricesHtas att= new AtributosMatricesHtas();
            	att.setTipoAtributo(new BigInteger("2"));
            	att.setIdNumber(new BigInteger(m));
            	att.setIdMatriz(lista.getIdMatriz());
            	att.setTipoMatriz(new BigInteger(tipoMatriz));
            	att.setDel('N');
            	List<AtributosMatricesHtas> count= loadInfo.findAtributosMatricesHtasByCriteria(att);
            	if(count.size()<1) {
            		exist=true;
            		break;
            	}
            }
			}
          //ATRIBUTOS MATRIZ PARTES OEMS
			if(exist==false) {
            for(String o:oem) {
            	//System.out.println("oems: "+o);
            	AtributosMatricesHtas att= new AtributosMatricesHtas();
            	att.setTipoAtributo(new BigInteger("3"));
            	att.setIdNumber(new BigInteger(o));
            	att.setIdMatriz(lista.getIdMatriz());
            	att.setTipoMatriz(new BigInteger(tipoMatriz));
            	att.setDel('N');
            	List<AtributosMatricesHtas> count= loadInfo.findAtributosMatricesHtasByCriteria(att);
            	if(count.size()<1) {
            		exist=true;
            		break;
            	}
            }
			}
          //ATRIBUTOS MATRIZ PARTES CANAL
			if(exist==false) {
            for(String c:can) {
            	System.out.println("canal: "+c);
            	AtributosMatricesHtas att= new AtributosMatricesHtas();
            	att.setTipoAtributo(new BigInteger("4"));
            	att.setIdNumber(new BigInteger(c));
            	att.setIdMatriz(lista.getIdMatriz());
            	att.setTipoMatriz(new BigInteger(tipoMatriz));
            	att.setDel('N');
            	List<AtributosMatricesHtas> count= loadInfo.findAtributosMatricesHtasByCriteria(att);
            	if(count.size()<1) {
            		exist=true;
            		break;
            	}
            }
			}
			
			if(exist==false) {
				response.add(lista);
			}
		}
		
		return response;
    }
	
	
}
