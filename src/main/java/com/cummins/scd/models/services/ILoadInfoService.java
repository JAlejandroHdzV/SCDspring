package com.cummins.scd.models.services;
// import com.cummins.payroll.suite.models.entity.DiaFestivo;

import java.sql.SQLException;
import java.util.List;

import com.cummins.scd.models.dto.MatrizPartesHdDTO;
import com.cummins.scd.models.dto.MotorProductJoinESPM;
import com.cummins.scd.models.entity.AtributosMatricesHtas;
import com.cummins.scd.models.entity.Aux_DRDLR;
import com.cummins.scd.models.entity.Aux_StatusGar;
import com.cummins.scd.models.entity.Aux_TipoQ;
import com.cummins.scd.models.entity.ConfMotor;
import com.cummins.scd.models.entity.ConfOem;
import com.cummins.scd.models.entity.ConfSpcodes;
import com.cummins.scd.models.entity.ESPM;
import com.cummins.scd.models.entity.ESPM_Emision;
import com.cummins.scd.models.entity.ESPM_NS;
import com.cummins.scd.models.entity.ESPM_Region;
import com.cummins.scd.models.entity.Garantia;
import com.cummins.scd.models.entity.MatrizHtasDet;
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.cummins.scd.models.entity.MatrizPartesDet;
import com.cummins.scd.models.entity.MatrizPartesHd;
import com.cummins.scd.models.entity.MotorProducts;
import com.cummins.scd.models.entity.Promotion;
import com.cummins.scd.models.entity.SpCodes;
import com.cummins.scd.models.entity.auxiliar.tables.Aux_Opciones;
import com.cummins.scd.models.entity.auxiliar.tables.Aux_RelModulos;
import com.cummins.scd.models.entity.auxiliar.tables.Aux_StatusEv;
import com.cummins.scd.models.entity.Aux_CC;

// import com.cummins.payroll.suite.models.entity.DetalleCalendario;

public interface ILoadInfoService {

    public Object findAll(String catalogue);
	
	//public Object findById(String catalogue, String id);
	
	public void delete(String catalogue, String id);
	
	//public Object findByCriteria(String catalogue, Object filter);
	
	public Object findExisting(String catalogue, Object filter);
	
	Aux_StatusGar findStatusGarByName(Object entityFilter);
	
	Integer logicDeleteSpCode() throws SQLException;

	Integer logicDeleteLicencias() throws SQLException;

	Integer logicDeleteRescates() throws SQLException;

	Integer logicDeleteElectronicTools() throws SQLException;

	Integer logicDeleteNoDisponible() throws SQLException;

	Integer logicDeleteGarantia() throws SQLException;

	Object deletePromotion(Object entity);

	Object save(String catalogue, Object entity, String wwid);

	Aux_DRDLR findTiposByName(Object entityFilter, String language);

	List<Promotion> findPromotionByCriteria(Object entityFilter);

	List<ESPM> findESPMByCriteria(Object entityFilter);

	List<ESPM_Emision> findESPMEmisionByCriteria(Object entityFilter);

	List<ESPM_Region> findESPMRegionByCriteria(Object entityFilter);

	List<ESPM_NS> findESPMNSByCriteria(Object entityFilter);

	//List<Garantia> findGarantiasByCriteria(Object entityFilter);

	//List<MatrizPartesHd> findMatrizPartesHdByCriteria(Object entityFilter);

	//List<MatrizPartesDet> findMatrizPartesDetByCriteria(Object entityFilter);

	//List<MatrizHtasHd> findMatrizHtasHdByCriteria(Object entityFilter);

	//List<MatrizHtasDet> findMatrizHtasDetByCriteria(Object entityFilter);

	List<Aux_RelModulos> findRelModulosByCriteria(Object entityFilter);

	List<Aux_Opciones> findOpcionesByCriteria(Object entityFilter);

	//List<Aux_StatusEv> findStatusEvByCriteria(Object entityFilter);

	List<ConfOem> findConfOemByCriteria(Object entityFilter);

	List<ConfMotor> findConfMotorByCriteria(Object entityFilter);

	List<ConfSpcodes> findConfSpcodesByCriteria(Object entityFilter);

	List<AtributosMatricesHtas> findAtributosMatricesHtasByCriteria(Object entityFilter);

	List<Aux_TipoQ> findTipoQByCriteria(Object entityFilter);

	//List<SpCodes> findSpcodesXregion(String region);

	List<MotorProducts> findMotorProductsXregion(String region);

	List<MotorProductJoinESPM> findMotorRegionEspm(String region);
	
	List<Aux_CC> findCCByCriteria(Object entityFilter);

	//List<SpCodes> findSpcodesXregion(String region, String lang);

	Object findByCriteria(String catalogue, Object filter, String lang, Boolean flag);

	List<MatrizPartesHd> findMatrizPartesHdByCriteria(Object entityFilter, String lang, Boolean flag);

	List<MatrizHtasHd> findMatrizHtasHdByCriteria(Object entityFilter, String lang, Boolean flag);

	List<SpCodes> findSpcodesXregion(String region, String lang, Boolean flag);

	List<Garantia> findGarantiasByCriteria(Object entityFilter, String lang, Boolean flag);

	List<Aux_StatusEv> findStatusEvByCriteria(Object entityFilter, String lang, Boolean flag);

	List<MatrizPartesDet> findMatrizPartesDetByCriteria(Object entityFilter, String lang, Boolean flag);

	Object findById(String catalogue, String id, String lang, Boolean flag);

	List<MatrizHtasDet> findMatrizHtasDetByCriteria(Object entityFilter, String lang, Boolean flag);
}