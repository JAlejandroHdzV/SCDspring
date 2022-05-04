package com.cummins.scd.models.services.catalogs;

import java.util.ArrayList;
import java.util.Comparator;
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

import com.cummins.scd.models.dao.IEvaluacionesDao;
import com.cummins.scd.models.dto.EvaluacionesDTO;
import com.cummins.scd.models.entity.Evaluaciones;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EvaluacionesService implements IEvaluacionesService {
	@Autowired
	private IEvaluacionesDao evaluacionesDao;

	private ObjectMapper mapper = new ObjectMapper();

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<EvaluacionesDTO> findEvaluacionesByCriteria(Object entityFilter, String lang) {
		System.out.println(entityFilter);
		Evaluaciones filter = mapper.convertValue(entityFilter, Evaluaciones.class);
		

		List<Evaluaciones> list = evaluacionesDao.findAll(new Specification<Evaluaciones>() {

			@Override
			public Predicate toPredicate(Root<Evaluaciones> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (filter.getId() != null) {
					predicates.add(cb.and(cb.equal(root.get("idEvaluacion"), filter.getId())));
				}
				if (filter.getIdRegion() != null) {
					predicates.add(cb.and(cb.equal(root.get("idRegion"), filter.getIdRegion())));

				}
				if (filter.getIdTEvaluacion() != null) {
					predicates.add(cb.and(cb.equal(root.get("idTevaluacion"), filter.getIdTEvaluacion())));
				}
				if (filter.getIdRevision() != null) {
					predicates.add(cb.and(cb.equal(root.get("idRevision"), filter.getIdRevision())));
				}
				if (filter.getIdAnio() != null) {
					predicates.add(cb.and(cb.equal(root.get("idAnio"), filter.getIdAnio())));
				}
				if (filter.getIdStatus() != null) {
					predicates.add(cb.and(cb.equal(root.get("idStatus"), filter.getIdStatus())));
				}

				if (filter.getInitDate() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("initDate")),
							"%" + filter.getInitDate().toString().toUpperCase() + "%")));
				}
				if (filter.getEndDate() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("endDate")),
							"%" + filter.getEndDate().toString().toUpperCase() + "%")));
				}
				if (filter.getText1() != null) {
					predicates.add(
							cb.and(cb.like(cb.upper(root.get("text1")), "%" + filter.getText1().toUpperCase() + "%")));
				}
				if (filter.getText2() != null) {
					predicates.add(
							cb.and(cb.like(cb.upper(root.get("text2")), "%" + filter.getText2().toUpperCase() + "%")));
				}
				if (filter.getText3() != null) {
					predicates.add(
							cb.and(cb.like(cb.upper(root.get("text3")), "%" + filter.getText3().toUpperCase() + "%")));
				}
				if (filter.getCreationDate() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("creationDate")),
							"%" + filter.getCreationDate().toString().toUpperCase() + "%")));
				}
				if (filter.getCreatedBy() != null) {
					predicates.add(cb.and(
							cb.like(cb.upper(root.get("createdBy")), "%" + filter.getCreatedBy().toUpperCase() + "%")));
				}
				if (filter.getLastUpdateDate() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateDate")),
							"%" + filter.getLastUpdateDate().toString().toUpperCase() + "%")));
				}
				if (filter.getLastUpdatedBy() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("lastUpdateBy")),
							"%" + filter.getLastUpdatedBy().toUpperCase() + "%")));
				}
				if (filter.getDel() != null) {
					predicates.add(cb.and(cb.equal(root.get("del"), filter.getDel())));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		}, new Sort(Sort.Direction.ASC, "idRegion"));

		for (Evaluaciones ev : list) {
			ev.setNombreRegion(ev.getRegion().getName());
		}

		list.sort(Comparator.comparing(Evaluaciones::obtenerNombreRegion));
		List<EvaluacionesDTO> listEvDto = new ArrayList<EvaluacionesDTO>();
		for (Evaluaciones ev : list) {
			EvaluacionesDTO exist = new EvaluacionesDTO();
			// System.out.println(ev.toString());
			switch(lang) {
	        case"en_US":
	        	exist.setRevision(ev.getRevisiones().getEnglish());
	        	exist.settEvaluacion(ev.getTEvaluacion().getEnglish());
	        	exist.setName(ev.getRevisiones().getEnglish() + "-" + ev.getTEvaluacion().getEnglish() + "-"+ ev.getAnio().getAnio());
	        	exist.setStatus(ev.getStatus().getEnglish());
	        	break;
	        	
	        case"pt_BR":
	        	exist.setRevision(ev.getRevisiones().getPortuguese());
	        	exist.settEvaluacion(ev.getTEvaluacion().getPortuguese());
	        	exist.setName(ev.getRevisiones().getPortuguese() + "-" + ev.getTEvaluacion().getPortuguese() + "-"+ ev.getAnio().getAnio());
	        	exist.setStatus(ev.getStatus().getPortuguese());
	        	break;
	        	
	        default:
	        	exist.setRevision(ev.getRevisiones().getRevision());
	        	exist.settEvaluacion(ev.getTEvaluacion().getEvaluacion());
	        	exist.setName(ev.getRevisiones().getRevision() + "-" + ev.getTEvaluacion().getEvaluacion() + "-"+ ev.getAnio().getAnio());
	        	exist.setStatus(ev.getStatus().getStatus());
	        	break;
			}
			exist.setAnio(ev.getAnio().getAnio());
			exist.setIdEvaluacion(ev.getId());
			//exist.setRevision(ev.getRevisiones().getRevision());
			//exist.settEvaluacion(ev.getTEvaluacion().getEvaluacion());
			//exist.setName(ev.getRevisiones().getRevision() + "-" + ev.getTEvaluacion().getEvaluacion() + "-"+ ev.getAnio().getAnio());
			exist.setRegion(ev.getRegion().getName());
			exist.setIdRegion(ev.getRegion().getId().toString());
			//exist.setStatus(ev.getStatus().getStatus());
			listEvDto.add(exist);
		}
		listEvDto.sort(Comparator.comparing(EvaluacionesDTO::getAnio).thenComparing(EvaluacionesDTO::getRevision));
		return listEvDto;

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<EvaluacionesDTO> findEv(String lang) {

		List<Evaluaciones> listEvaluaciones = (List<Evaluaciones>) em
				.createQuery("SELECT c from Evaluaciones c  where c.del='N' And c.idStatus='1'").getResultList();

		for (Evaluaciones ev : listEvaluaciones) {
			ev.setNombreRegion(ev.getRegion().getName());
		}

		listEvaluaciones.sort(Comparator.comparing(Evaluaciones::obtenerNombreRegion));
		List<EvaluacionesDTO> listEvDto = new ArrayList<EvaluacionesDTO>();
		for (Evaluaciones ev : listEvaluaciones) {
			EvaluacionesDTO exist = new EvaluacionesDTO();
			switch(lang) {
	        case"en_US":
	        	exist.setRevision(ev.getRevisiones().getEnglish());
	        	exist.settEvaluacion(ev.getTEvaluacion().getEnglish());
	        	exist.setName(ev.getRevisiones().getEnglish() + "-" + ev.getTEvaluacion().getEnglish() + "-"+ ev.getAnio().getAnio());
	        	exist.setStatus(ev.getStatus().getEnglish());
	        	break;
	        	
	        case"pt_BR":
	        	exist.setRevision(ev.getRevisiones().getPortuguese());
	        	exist.settEvaluacion(ev.getTEvaluacion().getPortuguese());
	        	exist.setName(ev.getRevisiones().getPortuguese() + "-" + ev.getTEvaluacion().getPortuguese() + "-"+ ev.getAnio().getAnio());
	        	exist.setStatus(ev.getStatus().getPortuguese());
	        	break;
	        	
	        default:
	        	exist.setRevision(ev.getRevisiones().getRevision());
	        	exist.settEvaluacion(ev.getTEvaluacion().getEvaluacion());
	        	exist.setName(ev.getRevisiones().getRevision() + "-" + ev.getTEvaluacion().getEvaluacion() + "-"+ ev.getAnio().getAnio());
	        	exist.setStatus(ev.getStatus().getStatus());
	        	break;
			}
			
			exist.setAnio(ev.getAnio().getAnio());
			exist.setIdEvaluacion(ev.getId());
			//exist.setRevision(ev.getRevisiones().getRevision());
			//exist.settEvaluacion(ev.getTEvaluacion().getEvaluacion());
			//exist.setName(ev.getRevisiones().getRevision() + "-" + ev.getTEvaluacion().getEvaluacion() + "-"+ ev.getAnio().getAnio());
			exist.setRegion(ev.getRegion().getName());
			exist.setIdRegion(ev.getRegion().getId().toString());
			//exist.setStatus(ev.getStatus().getStatus());
			listEvDto.add(exist);
		}
		listEvDto.sort(Comparator.comparing(EvaluacionesDTO::getAnio).thenComparing(EvaluacionesDTO::getRevision));
		return listEvDto;

	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Evaluaciones> listEvConfMotor() {
		List<Evaluaciones> listEvaluaciones = (List<Evaluaciones>) em
				.createQuery("SELECT DISTINCT c.ev from ConfMotor c " + "left join c.ev  where c.del='N' ")
				.getResultList();
		System.out.println("evaluaciones confMotor");
		return listEvaluaciones;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Evaluaciones> listEvQuejas() {
		List<Evaluaciones> listEvaluaciones = (List<Evaluaciones>) em
				.createQuery("SELECT DISTINCT c.ev from Quejas c " + "left join c.ev  where c.del='N' ")
				.getResultList();
		System.out.println("evaluaciones Quejas");
		return listEvaluaciones;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Evaluaciones> listEvQsol() {
		List<Evaluaciones> listEvaluaciones = (List<Evaluaciones>) em
				.createQuery("SELECT DISTINCT c.ev from QSol c " + "left join c.ev  where c.del='N' ")
				.getResultList();
		System.out.println("evaluaciones Qsol");
		return listEvaluaciones;
	}
	
	

	
	@Override
	@Transactional(readOnly = true)
	public List<EvaluacionesDTO> findEvaluationsByCatalog(String catalog, String lang) {
		List<Evaluaciones> listEvaluaciones = null;
		switch (catalog) {
		case "confMotor":
			listEvaluaciones = listEvConfMotor();
			break;
		case "quejas":
			listEvaluaciones = listEvQuejas();
			break;
		case "qsol":
			listEvaluaciones = listEvQsol();
			break;
		}

		System.out.println(listEvaluaciones);
		for (Evaluaciones ev : listEvaluaciones) {
			ev.setNombreRegion(ev.getRegion().getName());
		}

		listEvaluaciones.sort(Comparator.comparing(Evaluaciones::obtenerNombreRegion));
		List<EvaluacionesDTO> listEvDto = new ArrayList<EvaluacionesDTO>();
		for (Evaluaciones ev : listEvaluaciones) {
			EvaluacionesDTO exist = new EvaluacionesDTO();
			
			switch(lang) {
	        case"en_US":
	        	exist.setRevision(ev.getRevisiones().getEnglish());
	        	exist.settEvaluacion(ev.getTEvaluacion().getEnglish());
	        	exist.setName(ev.getRevisiones().getEnglish() + "-" + ev.getTEvaluacion().getEnglish() + "-"+ ev.getAnio().getAnio());
	        	exist.setStatus(ev.getStatus().getEnglish());
	        	break;
	        	
	        case"pt_BR":
	        	exist.setRevision(ev.getRevisiones().getPortuguese());
	        	exist.settEvaluacion(ev.getTEvaluacion().getPortuguese());
	        	exist.setName(ev.getRevisiones().getPortuguese() + "-" + ev.getTEvaluacion().getPortuguese() + "-"+ ev.getAnio().getAnio());
	        	exist.setStatus(ev.getStatus().getPortuguese());
	        	break;
	        	
	        default:
	        	exist.setRevision(ev.getRevisiones().getRevision());
	        	exist.settEvaluacion(ev.getTEvaluacion().getEvaluacion());
	        	exist.setName(ev.getRevisiones().getRevision() + "-" + ev.getTEvaluacion().getEvaluacion() + "-"+ ev.getAnio().getAnio());
	        	exist.setStatus(ev.getStatus().getStatus());
	        	break;
			}
			
			exist.setAnio(ev.getAnio().getAnio());
			exist.setIdEvaluacion(ev.getId());
			//exist.setRevision(ev.getRevisiones().getRevision());
			//exist.settEvaluacion(ev.getTEvaluacion().getEvaluacion());
			//exist.setName(ev.getRevisiones().getRevision() + "-" + ev.getTEvaluacion().getEvaluacion() + "-"+ ev.getAnio().getAnio());
			exist.setRegion(ev.getRegion().getName());
			exist.setIdRegion(ev.getRegion().getId().toString());
			//exist.setStatus(ev.getStatus().getStatus());
			listEvDto.add(exist);
		}
		listEvDto.sort(Comparator.comparing(EvaluacionesDTO::getAnio).thenComparing(EvaluacionesDTO::getRevision));
		return listEvDto;

	}
	/*
	 * SELECT A.ID_EVALUACION, C.REGION, D.TEVALUACION, E.REVISION, F.ANIO FROM
	 * ZMKT.ZMKT7352_DES_CONF_MOTOR A LEFT JOIN ZMKT.ZMKT7352_DES_CONF_EV B ON
	 * B.ID_EVALUACION = A.ID_EVALUACION LEFT JOIN ZMKT.ZMKT7352_DES_CAT_REGION C ON
	 * C.ID_REGION = B.ID_REGION LEFT JOIN ZMKT.ZMKT7352_DES_CAT_TEVALUACION D ON
	 * D.ID_TEVALUACION = B.ID_TEVALUACION LEFT JOIN
	 * ZMKT.ZMKT7352_DES_CAT_REVISIONES E ON E.ID_REVISION = B.ID_REVISION LEFT JOIN
	 * ZMKT.ZMKT7352_DES_CAT_ANIOS F ON F.ID_ANIO = B.ID_ANIO WHERE A.DELETE_IND =
	 * 'N' GROUP BY A.ID_EVALUACION, C.REGION, D.TEVALUACION, E.REVISION, F.ANIO
	 * ORDER BY C.REGION
	 */
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Evaluaciones> listEvByRegion(String Regiones) {
		List<Evaluaciones> listEvaluaciones = (List<Evaluaciones>) em
				.createQuery("SELECT c from Evaluaciones  c  where c.del='N' AND c.idRegion IN ("+Regiones+")")
				//SELECT * FROM ZMKT7352_DES_CONF_EV d where d.ID_REGION in (4,2)
				.getResultList();
		System.out.println("evaluaciones Qsol");
		return listEvaluaciones;
	}
}
