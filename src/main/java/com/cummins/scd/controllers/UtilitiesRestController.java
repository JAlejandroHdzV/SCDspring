package com.cummins.scd.controllers;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cummins.scd.global.FileStorageException;
import com.cummins.scd.models.dao.ICountryPerRegionDao;
import com.cummins.scd.models.dao.IOemsDao;
import com.cummins.scd.models.dao.IPerfilesDao;
import com.cummins.scd.models.dao.IRelPerfilExcsDao;
import com.cummins.scd.models.dao.IRelPerfilMecanicos;
import com.cummins.scd.models.dao.IRelPerfilMotor;
import com.cummins.scd.models.dto.ConfMotorDTO;
import com.cummins.scd.models.dto.MatrizDTO;
import com.cummins.scd.models.dto.SpCodesDTO;
import com.cummins.scd.models.entity.Application;
import com.cummins.scd.models.entity.Aux_DRDLR;
import com.cummins.scd.models.entity.Aux_StatusGar;
import com.cummins.scd.models.entity.ConfMotor;
import com.cummins.scd.models.entity.ConfOem;
import com.cummins.scd.models.entity.ConfSpcodes;
import com.cummins.scd.models.entity.Country;
import com.cummins.scd.models.entity.CountryPerRegion;
import com.cummins.scd.models.entity.ESPM;
import com.cummins.scd.models.entity.ESPM_NS;
import com.cummins.scd.models.entity.ElectronicTools;
import com.cummins.scd.models.entity.Errores;
import com.cummins.scd.models.entity.Garantia;
import com.cummins.scd.models.entity.LicenciasQsol;
import com.cummins.scd.models.entity.MatrizHtasHd;
import com.cummins.scd.models.entity.MatrizPartesHd;
import com.cummins.scd.models.entity.Mensajes;
import com.cummins.scd.models.entity.Oems;
import com.cummins.scd.models.entity.Perfiles;
import com.cummins.scd.models.entity.Ranks;
import com.cummins.scd.models.entity.RelPerfilExcs;
import com.cummins.scd.models.entity.RelPerfilMotor;
import com.cummins.scd.models.entity.ReporteCargaMasiva;
import com.cummins.scd.models.entity.ReporteCargaMasivaConfMotor;
import com.cummins.scd.models.entity.Rescates;
import com.cummins.scd.models.entity.ServiceLevel;
import com.cummins.scd.models.entity.MotorProducts;
import com.cummins.scd.models.entity.NoDisponible;
import com.cummins.scd.models.entity.Tools;
import com.cummins.scd.models.entity.SpCodes;
import com.cummins.scd.models.entity.UploadFileResponse;
import com.cummins.scd.models.entity.UploadFileResponseStr;
import com.cummins.scd.models.services.ILoadInfoService;
import com.cummins.scd.models.services.catalogs.ConfOemService;
import com.cummins.scd.models.services.catalogs.ConfSpcodesService;
import com.cummins.scd.models.services.catalogs.IConfMotorService;
import com.cummins.scd.models.services.catalogs.IEspmNsService;
import com.cummins.scd.models.services.catalogs.IEspmService;
import com.cummins.scd.models.services.catalogs.IMatrizHtasService;
import com.cummins.scd.models.services.catalogs.IMatrizPartesHdService;
import com.cummins.scd.models.services.catalogs.IOemService;
import com.cummins.scd.models.services.catalogs.IPerfilesService;
import com.cummins.scd.models.services.catalogs.ISpCodesService;
import com.cummins.scd.models.services.catalogs.SpCodesService;
import com.cummins.scd.models.services.ICatalogsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cummins.scd.models.entity.Aux_CC;

//@CrossOrigin(origins = {"http://localhost:4200"}) //descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.
@RestController
@RequestMapping("/_api")
public class UtilitiesRestController {
	@Autowired
	private ICatalogsService catalogsService;

	@Autowired
	private ILoadInfoService catalogsAdminService;

	@Autowired
	private ISpCodesService spcodesService;

	@Autowired
	private IConfMotorService confMotorService;

	@Autowired
	private IMatrizHtasService matrizHtasService;
	@Autowired
	private IMatrizPartesHdService matrizPartesService;
	@Autowired
	private IOemService oemService;
	@Autowired
	private IEspmNsService espNsService;
	
	@Autowired
	private IPerfilesDao perfilesDao;

	@Autowired
	private IRelPerfilExcsDao exceptionsDao;
	
	@Autowired
	private IRelPerfilMotor relPerfilMotorService;
	
	@Autowired
	private IRelPerfilMecanicos relPerfilMecanicosService;
	
	@Autowired
	private ICountryPerRegionDao cxrService;
	
	@Autowired
	private ConfSpcodesService confspcodesService;
	
	@Autowired
	private ConfOemService confOemService;
	@Autowired
	private IPerfilesService perfilService;
	
	@Autowired
	private MessageSource messageSource;
	// private IUtilitiesService utilitiesService;

	ObjectMapper mapper = new ObjectMapper();

	/**
	 * @desc ruta general para cargar archivos a la BD
	 * @param MultipartFile file - archivo a guardar
	 * @return UploadFileResponse - Entidad con info general de archivo
	 */
	@PostMapping("utilities/uploadfileMotorProducts")
	public UploadFileResponse uploadFileMotorProducts(@RequestParam(required = false, name = "id") BigInteger id,
			@RequestParam("name") String name, @RequestParam("comercialName") String comercialName,
			@RequestParam("idCategoria") BigInteger idCategoria, @RequestParam("file") MultipartFile file,

			// @RequestParam("createdBy") String createdBy,

			// @RequestParam("lastUpdateBy") String lastUpdateBy,
			@RequestParam("delete") Character delete, HttpServletRequest request) throws FileStorageException {

		Date dat = new Date();
		String wwid = (String) request.getSession(false).getAttribute("wwid");

		MotorProducts dbFile = catalogsService.storeFileMotorProducts(id, name, comercialName, idCategoria, file, dat,
				wwid, dat, wwid, delete);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/utilities/downloadfileMotorProducts/").path(String.valueOf(dbFile.getId())).toUriString();

		return new UploadFileResponse(dbFile.getId(), dbFile.getFileName(), fileDownloadUri, file.getContentType(),
				file.getSize());
	}

	/**
	 * @desc ruta general para cargar archivos a la BD
	 * @param MultipartFile file - archivo a guardar
	 * @return UploadFileResponse - Entidad con info general de archivo
	 */

	@PostMapping("utilities/uploadfileOem")
	public UploadFileResponse uploadFileOem(@RequestParam(required = false, name = "id") BigInteger id,
			@RequestParam("oem") String oem, @RequestParam("english") String english,
			@RequestParam("portuguese") String portuguese,
			@RequestParam(required = false, name = "file") MultipartFile file, @RequestParam("delete") Character delete,
			HttpServletRequest request) throws FileStorageException {
		String loggedUser = (String) request.getSession(false).getAttribute("wwid");
		String createdBy = "rq897";
		String lastUpdateBy = "rq897";
		if (loggedUser != null) {
			createdBy = loggedUser;
			lastUpdateBy = loggedUser;
		}

		Date dat = new Date();
		Oems dbFile = catalogsService.storeFileOem(id, oem, english, portuguese, file, dat, createdBy, dat,
				lastUpdateBy, delete);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/utilities/downloadfileOem/").path(String.valueOf(dbFile.getId())).toUriString();

		return new UploadFileResponse(dbFile.getId(), dbFile.getFileName(), fileDownloadUri, file.getContentType(),
				file.getSize());
	}

	/**
	 * @desc ruta general para cargar archivos a la BD
	 * @param MultipartFile file - archivo a guardar
	 * @return UploadFileResponse - Entidad con info general de archivo
	 */

	@PostMapping("utilities/uploadfileTools")
	public UploadFileResponseStr uploadFileTools(@RequestParam("np") String np,
			@RequestParam("npAnterior") String npAnterior, @RequestParam("spanish") String spanish,
			@RequestParam("english") String english, @RequestParam("portuguese") String portuguese,
			@RequestParam("codigoVenta") String codigoVenta, @RequestParam("file") MultipartFile file,
			// @RequestParam("creationDate") Date creationDate,
			// @RequestParam("createdBy") String createdBy,
			// @RequestParam("lastUpdateDate") Date lastUpdateDate,
			// @RequestParam("lastUpdateBy") String lastUpdateBy,
			@RequestParam("del") Character del, HttpServletRequest request) throws FileStorageException {
		String wwid = (String) request.getSession(false).getAttribute("wwid");
		// String language = (String)request.getSession(false).getAttribute("language");

		Tools dbFile = catalogsService.storeFileTools(np, npAnterior, spanish, english, portuguese, codigoVenta, file,
				wwid, del);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/utilities/downloadfileTools/").path(String.valueOf(dbFile.getnP())).toUriString();

		return new UploadFileResponseStr(dbFile.getnP(), dbFile.getFileName(), fileDownloadUri, file.getContentType(),
				file.getSize());
	}

	/**
	 * @desc ruta general para descargar archivos a la BD
	 * @param String fileId - id de archivo a descargar
	 * @return ResponseEntity<?>(JSON del registro que se guardo, estatus de
	 *         respuesta)
	 */
	@GetMapping("utilities/downloadfileMotorProducts/{fileId}")
	public ResponseEntity<Resource> downloadFileMotorProducts(@PathVariable String fileId) {
		// Load file from database
		MotorProducts dbFileNew = catalogsService.getFileMotorProducts(fileId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFileNew.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFileNew.getFileName() + "\"")
				.body(new ByteArrayResource(dbFileNew.getCoverPage()));
	}

	/**
	 * @desc ruta general para descargar archivos a la BD
	 * @param String fileId - id de archivo a descargar
	 * @return ResponseEntity<?>(JSON del registro que se guardo, estatus de
	 *         respuesta)
	 */
	@GetMapping("utilities/downloadfileOem/{fileId}")
	public ResponseEntity<Resource> downloadFileOem(@PathVariable String fileId) {
		// Load file from database
		Oems dbFileNew = catalogsService.getFileOem(fileId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFileNew.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFileNew.getFileName() + "\"")
				.body(new ByteArrayResource(dbFileNew.obtenerLogo()));
	}

	/**
	 * @desc ruta general para descargar archivos a la BD
	 * @param String fileId - id de archivo a descargar
	 * @return ResponseEntity<?>(JSON del registro que se guardo, estatus de
	 *         respuesta)
	 */
	@GetMapping("utilities/downloadfileTools/{np}")
	public ResponseEntity<Resource> downloadFileTools(@PathVariable String np) {
		// Load file from database
		Tools dbFileNew = catalogsService.getFileTools(np);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFileNew.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFileNew.getFileName() + "\"")
				.body(new ByteArrayResource(dbFileNew.getEspecificacion()));
	}

	// --------------------------------------------------------------------------------------------------------
	// CARGA MASIVA DE SP CODES
	// --------------------------------------------------------------------------------------------------------
	/**
	 * @desc metodo para cargar multiples registros a una entidad desde un excel
	 *       para SPCODES
	 * @param MultipartFile reapExcelDataFile - El archivo excel con los registros a
	 *                      cargar
	 * @return ResponseEntity<Object, Response> - La lista de los registros y la
	 *         respuesta de la peticion
	 * @throws SQLException
	 */
	@PostMapping("/loadInfo/spcodes/cargamasiva")
	public ResponseEntity<?> chargeExcel(@RequestParam("file") MultipartFile reapExcelDataFile,
			@RequestParam("eraseData") String delete, HttpServletRequest request)
			throws IOException, FileStorageException, ParseException, SQLException {

		String wwid = (String) request.getSession(false).getAttribute("wwid");
		/*String language = (String) request.getSession(false).getAttribute("language");
		if (language == null)
			language = "Español";*/
		String len = LocaleContextHolder.getLocale().toString();
		Map<String, Object> response = new HashMap<>();

		int countErrores = 0;
		// Se crea una lista para agregar todos los registros
		List<Object> cargaList = new ArrayList<Object>();

		// Se crea una lista para agregar todos los errores
		List<Errores> errores = new ArrayList<Errores>();

		// Contadores de reporte
		int countDel = 0; // Cuantos eliminaste
		int countAdd = 0; // Cuantos nuevos
		int countUp = 0; // Cuantos se actualizan

		// Verifica el parametro de limpiado de tabla
		if (delete.equals("1")) {
			System.out.println("Entra a eliminar");
			countDel = catalogsAdminService.logicDeleteSpCode();
		}

		// Leer archivo
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);

			// Por cada fila detectada se genera una instancia de tipo CargaMasiva
			for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
				SpCodes carga = new SpCodes();
				System.out.println("ENTRE A CARGA MASIVA");
				XSSFRow row = worksheet.getRow(i);

				int count = 0; // Errores

				// Se revisa que la celda contenga un atributo valido antes de setear
				// SP CODE
				try {

					if (!row.getCell(0).toString().equals("") && row.getCell(0).toString() != null) {
						row.getCell(0).setCellType(row.getCell(0).CELL_TYPE_STRING);
						carga.setSpCode(row.getCell(0).toString());
						// Revisamos si existe o no
						System.out.println(carga.getSpCode());
					} else {
						System.out.println("Campo SP CODE en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo SP CODE en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error campo SP CODE");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				// NOMBRE
				try {
					System.out.println("TRY");
					if (!row.getCell(1).toString().equals("") && row.getCell(1).toString() != null) {
						row.getCell(1).setCellType(row.getCell(1).CELL_TYPE_STRING);
						carga.setProviderName(row.getCell(1).toString());
						System.out.println(row.getCell(1).toString());
					} else {

						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo NOMBRE en blanco");

						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en campo NOMBRE");
					// error.setErrorType(e.getMessage());
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				// TIPO
				try {
					row.getCell(2).setCellType(row.getCell(2).CELL_TYPE_STRING);
					if (!row.getCell(2).toString().equals("") && row.getCell(2).toString() != null) {
						Aux_DRDLR st = new Aux_DRDLR();
						st.setTipo(row.getCell(2).toString());
						Object ob = catalogsAdminService.findTiposByName(st, len);
						Aux_DRDLR el = mapper.convertValue(ob, Aux_DRDLR.class);
						if (el != null) {
							carga.setTipo(el.getId());
							System.out.println(el.getId());
						} else {

							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType(
									"El valor del campo TIPO no existe (Verifica Idioma, Actual: " + len + ")");
							// Se agregan los errores a la lista
							errores.add(error);
							count++;
							continue;
						}
					} else {

						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("El campo TIPO esta en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error campo TIPO");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				// PAIS
				try {

					row.getCell(3).setCellType(row.getCell(3).CELL_TYPE_STRING);
					if (!row.getCell(3).toString().equals("") && row.getCell(3).toString() != null) {
						Country st = new Country();
						st.setName(row.getCell(3).toString());
						System.out.println("Pais " + row.getCell(3).toString());
						Object ob = catalogsService.findCountryByName(st);
						Country el = mapper.convertValue(ob, Country.class);
						if (el != null) {
							System.out.println("PAIS " + el.getId());
							carga.setIdPais(el.getId());
						} else {

							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("El valor del campo PAIS no esta dado de alta");
							// Se agregan los errores a la lista
							errores.add(error);
							count++;
							continue;
						}
					} else {

						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo PAIS en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error campo PAIS");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				// RESPONSIBLE BRANCH
				try {
					long distribuitor = 1;
					long dealer = 2;
					if (carga.getTipo() == BigInteger.valueOf(distribuitor)) {
						carga.setResponsibleBranchCode(carga.getSpCode());
					} else if (carga.getTipo() == BigInteger.valueOf(dealer)) {
						row.getCell(4).setCellType(row.getCell(4).CELL_TYPE_STRING);
						if (!row.getCell(4).toString().equals("") && row.getCell(4).toString() != null) {
							
								List<SpCodes> exist= spcodesService.getSpCodeById(row.getCell(4).toString(),BigInteger.valueOf(1));
								if (exist.size()>0) {
									
									carga.setResponsibleBranchCode(row.getCell(4).toString().toUpperCase());
								}else {
									System.out.println("El DISTRIBUIDOR no existe o no esta activo");
									Errores error = new Errores();
									error.setLine(String.valueOf(i + 1));
									error.setErrorType("El DISTRIBUIDOR no existe o no esta activo");
									// Se agregan los errores a la lista
									errores.add(error);
									count++;
									continue;
									
								}
						}
						else {

							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Campo DISTRIBUIDOR en blanco");
							// Se agregan los errores a la lista
							errores.add(error);
							count++;
							continue;
						}
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error para asignar DISTRIBUIDOR o DEALER");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				// OEMS

				try {

					row.getCell(5).setCellType(row.getCell(5).CELL_TYPE_STRING);
					if (!row.getCell(5).toString().equals("") && row.getCell(5).toString() != null) {
						Oems st = new Oems();
						st.setOem(row.getCell(5).toString());
						Object ob = catalogsService.findOemsByName(st);
						Oems el = mapper.convertValue(ob, Oems.class);
						if (el != null) {
							carga.setIdOem(el.getId());
							System.out.println(el.getId());
						} else {

							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("El valor del campo OEM no existe en la tabla OEMS");
							// Se agregan los errores a la lista
							errores.add(error);
							count++;
							continue;
						}
					} else {

						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo OEM en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error campo OEM");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				// DIRECCION
				try {
					row.getCell(6).setCellType(row.getCell(6).CELL_TYPE_STRING);
					if (!row.getCell(6).toString().equals("") && row.getCell(6).toString() != null)
						carga.setAdd(row.getCell(6).toString());
					else {

						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo DIRECCION en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en campo DIRECCION ");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				// CIUDAD
				try {
					row.getCell(7).setCellType(row.getCell(7).CELL_TYPE_STRING);
					if (!row.getCell(7).toString().equals("") && row.getCell(7).toString() != null)
						carga.setCity(row.getCell(7).toString());
					else {

						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo CIUDAD o ESTADO en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error campo CIUDAD o ESTADO");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				// CODIGO ISO
				try {
					row.getCell(8).setCellType(row.getCell(8).CELL_TYPE_STRING);
					if (!row.getCell(8).toString().equals("") && row.getCell(8).toString() != null)
						carga.setIso(row.getCell(8).toString());

				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en campo CODIGO ISO");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				// Se guarda

				// Por default
				carga.setDel('N');

				try {
					Object ob = catalogsAdminService.findById("spcodes", carga.getSpCode(), len, false);
					SpCodes existe = null;
					existe = mapper.convertValue(ob, SpCodes.class);
					catalogsAdminService.save("spcodes", carga, wwid);
					if (existe != null)
						countUp++;
					else
						countAdd++;
					cargaList.add(carga);
				} catch (DataAccessException e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType(
							"Error al intentar inserción, consulta las tablas de Referencia y verifica los valores");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				if (count >= 7)
					break;

			}

			System.out.println(countAdd);
			System.out.println(countUp);
			System.out.println(countDel);

			ReporteCargaMasiva rep = new ReporteCargaMasiva();
			rep.setRegistrosEliminados(countDel);
			rep.setRegistrosNuevos(countAdd);
			rep.setRegistrosActualizados(countUp);
			rep.setErrores(errores);
			cargaList.add(rep);

			return new ResponseEntity<List>(cargaList, HttpStatus.OK);

			// return new ResponseEntity<Object>(procesosEntity, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Se excedieron las filas permitidas");
			return new ResponseEntity<List>(cargaList, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// -------------------------------------------------------------------------------------------------
	// CARGA MASIVA QSOL
	// -------------------------------------------------------------------------------------------------
	/**
	 * @desc metodo para cargar multiples registros a una entidad desde un excel
	 *       para QSOL
	 * @param MultipartFile reapExcelDataFile - El archivo excel con los registros a
	 *                      cargar
	 * @return ResponseEntity<Object, Response> - La lista de los registros y la
	 *         respuesta de la peticion
	 * @throws SQLException
	 */
	@PostMapping("/loadInfo/licenciasqsol/cargamasiva")
	public ResponseEntity<?> chargeExcelQsol(@RequestParam("file") MultipartFile reapExcelDataFile,
			@RequestParam("eraseData") String delete, HttpServletRequest request)
			throws IOException, FileStorageException, ParseException, SQLException {
		String wwid = (String) request.getSession(false).getAttribute("wwid");
		String len = LocaleContextHolder.getLocale().toString();
		Map<String, Object> response = new HashMap<>();

		DateFormat formatter;
		Date date = new Date();
		Date date1 = new Date();

		formatter = new SimpleDateFormat("dd/MM/yyyy");

		int countErrores = 0;
		// Se crea una lista para agregar todos los registros
		List<Object> cargaList = new ArrayList<Object>();
		// Se valida el numero de filas
		List<Errores> errores = new ArrayList<Errores>();

		int countDel = 0;
		int countAdd = 0;
		int countUp = 0;

		// Verifica el parametro de limpiado de tabla
		if (delete.equals("1")) {
			System.out.println("Entra a eliminar");
			countDel = catalogsAdminService.logicDeleteLicencias();
		}

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);

			// Por cada fila detectada se genera una instancia de tipo CargaMasiva
			for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
				LicenciasQsol carga = new LicenciasQsol();
				System.out.println("ENTRE A CARGA MASIVA");
				XSSFRow row = worksheet.getRow(i);
				int count = 0;
				// Se revisa que la celda contenga un atributo valido antes de setear
				try {
					row.getCell(0).setCellType(row.getCell(0).CELL_TYPE_STRING);
					if (!row.getCell(0).toString().equals("") && row.getCell(0).toString() != null) {
						// Revisamos si existe o no
						System.out.println("Revisa celda 1: " + row.getCell(0).toString());
						carga.setSpCode(row.getCell(0).toString());
						Object ob = catalogsAdminService.findById("spcodes", carga.getSpCode(), len, false);
						SpCodes existe = null;
						existe = mapper.convertValue(ob, SpCodes.class);
						if (existe == null) {
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("El valor del Campo SP CODE no existe en catalogo PUNTOS DE SERVICIO");
							// Se agregan los errores a la lista
							errores.add(error);
							continue;
						}

					} else {
						System.out.println("Campo SP CODE en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo SP CODE en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error campo SP CODE");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				try {
					row.getCell(1).setCellType(row.getCell(1).CELL_TYPE_STRING);
					if (!row.getCell(1).toString().equals("") && row.getCell(1).toString() != null) {
						try {
							date1 = formatter.parse(row.getCell(1).toString());
							carga.setFechaRegistro(date1);
						} catch (Exception e) {

							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("El formato de fecha del campo FECHA REGISTRO es incorrecto");
							// Se agregan los errores a la lista
							errores.add(error);
							count++;
							continue;
						}

					} else {
						System.out.println("Campo FECHA REGISTRO en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo FECHA REGISTRO en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en campo FECHA REGISTRO");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				try {
					row.getCell(2).setCellType(row.getCell(2).CELL_TYPE_STRING);
					if (!row.getCell(2).toString().equals("") && row.getCell(2).toString() != null) {
						try {
							date1 = formatter.parse(row.getCell(2).toString());
							carga.setFechaExpiracion(date1);
							if (carga.getFechaExpiracion().before(carga.getFechaRegistro())) {
								Errores error = new Errores();
								error.setLine(String.valueOf(i + 1));
								error.setErrorType("Error FECHA DE REGISTRO mayor a FECHA EXPIRACION");
								// Se agregan los errores a la lista
								errores.add(error);

								continue;
							}
						} catch (Exception e) {

							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("El formato de fecha del campo FECHA EXPIRACION es incorrecto");
							// Se agregan los errores a la lista
							errores.add(error);
							count++;
							continue;
						}

					} else {
						System.out.println("Campo FECHA EXPIRACION en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo FECHA EXPIRACION en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error campo FECHA EXPIRACION");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				// Se guarda

				carga.setCreationDate(date);
				carga.setCreatedBy(wwid);
				carga.setLastUpdateDate(date);
				carga.setLastUpdatedBy(wwid);
				carga.setDel('N');

				try {
					Object ob = catalogsAdminService.findById("licenciasqsol", carga.getSpCode(), len, false);
					LicenciasQsol existe = mapper.convertValue(ob, LicenciasQsol.class);

					catalogsAdminService.save("licenciasqsol", carga, wwid);
					cargaList.add(carga);
					if (existe != null)
						countUp++;
					else
						countAdd++;
				} catch (DataAccessException e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType(e.getMessage());
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				if (count >= 3) {
					break;
				}

			}

			System.out.println(countAdd);
			System.out.println(countUp);
			System.out.println(countDel);

			ReporteCargaMasiva rep = new ReporteCargaMasiva();
			rep.setRegistrosEliminados(countDel);
			rep.setRegistrosNuevos(countAdd);
			rep.setRegistrosActualizados(countUp);
			rep.setErrores(errores);
			cargaList.add(rep);

			if (countErrores > 0)
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			else
				return new ResponseEntity<List>(cargaList, HttpStatus.OK);

			// return new ResponseEntity<Object>(procesosEntity, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Se excedieron las filas permitidas");
			return new ResponseEntity<List>(cargaList, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// ----------------------------------------------------------------------------------------------------------------
	// CARGA MASIVA RESCATES
	// ----------------------------------------------------------------------------------------------------------------

	@PostMapping("/loadInfo/rescates/cargamasiva")
	public ResponseEntity<?> chargeExcelRescates(@RequestParam("file") MultipartFile reapExcelDataFile,
			@RequestParam("eraseData") String delete, HttpServletRequest request)
			throws IOException, FileStorageException, ParseException, SQLException {
		String len = LocaleContextHolder.getLocale().toString();
		String wwid = (String) request.getSession(false).getAttribute("wwid");
		System.out.println("WWID" + wwid);
		Map<String, Object> response = new HashMap<>();

		DateFormat formatter;
		Date date = new Date();
		Date date1 = new Date();
		// String wwid= "qy411";
		formatter = new SimpleDateFormat("dd/MM/yyyy");

		int countErrores = 0;
		// Se crea una lista para agregar todos los registros
		List<Object> cargaList = new ArrayList<Object>();
		List<Errores> errores = new ArrayList<Errores>();

		int countDel = 0;
		int countAdd = 0;
		int countUp = 0;

		// Verifica el parametro de limpiado de tabla
		if (delete.equals("1")) {
			System.out.println("Entra a eliminar");
			countDel = catalogsAdminService.logicDeleteRescates();
		}

		// Se valida el numero de filas
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			System.out.println(worksheet.getSheetName());
//contador de columnas vacias

			// Por cada fila detectada se genera una instancia de tipo CargaMasiva
			for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
				int count = 0;
				Rescates carga = new Rescates();
				System.out.println("ENTRE A CARGA MASIVA");
				XSSFRow row = worksheet.getRow(i);

				// Se revisa que la celda contenga un atributo valido antes de setear

				try {
					row.getCell(0).setCellType(row.getCell(0).CELL_TYPE_STRING);
					if (!row.getCell(0).toString().equals("") && row.getCell(0).toString() != null) {
						// Revisamos si existe o no
						System.out.println("FOLIO: " + row.getCell(0).toString());
						carga.setFolio(row.getCell(0).toString());

					} else {
						System.out.println("Campo FOLIO en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo FOLIO en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en campo FOLIO");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}
				try {
					row.getCell(1).setCellType(row.getCell(1).CELL_TYPE_STRING);
					if (!row.getCell(1).toString().equals("") && row.getCell(1).toString() != null) {

						carga.setSpCode(row.getCell(1).toString());
						Object ob = catalogsAdminService.findById("spcodes", carga.getSpCode(), len, false);
						SpCodes existe = null;
						existe = mapper.convertValue(ob, SpCodes.class);
						if (existe == null) {
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("El valor del Campo SP CODE no existe en catalogo PUNTOS DE SERVICIO");
							// Se agregan los errores a la lista
							errores.add(error);
							continue;
						}

						System.out.println(carga.getSpCode());
					} else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo SP CODE en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en campo SP CODE");
					// Se agregan los errores a la lista
					errores.add(error);
					System.out.println("Error" + e.getMessage());
					continue;
				}

				try {
					row.getCell(2).setCellType(row.getCell(2).CELL_TYPE_STRING);
					if (!row.getCell(2).toString().equals("") && row.getCell(2).toString() != null) {
						try {
							date1 = formatter.parse(row.getCell(2).toString());
						} catch (Exception e) {
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Formato de fecha incorrecto en campo FECHA FALLA ");
							// Se agregan los errores a la lista
							errores.add(error);
							continue;
						}
						carga.setFechaFalla(date1);
					} else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo FECHA FALLA en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en campo FECHA FALLA ");
					// Se agregan los errores a la lista
					errores.add(error);
					System.out.println("Error" + e.getMessage());
					continue;
				}

				try {
					row.getCell(3).setCellType(row.getCell(3).CELL_TYPE_STRING);
					if (!row.getCell(3).toString().equals("") && row.getCell(3).toString() != null)
						carga.setCliente(row.getCell(3).toString());
					else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo CLIENTE en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en campo CLIENTE");
					// Se agregan los errores a la lista
					errores.add(error);
					System.out.println("Error" + e.getMessage());
					continue;
				}

				try {
					row.getCell(4).setCellType(row.getCell(4).CELL_TYPE_STRING);
					if (!row.getCell(4).toString().equals("") && row.getCell(4).toString() != null)
						carga.setTiempoRespuesta(new Double(row.getCell(4).toString()));
					else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo TIEMPO RESPUESTA en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en Campo TIEMPO RESPUESTA");
					// Se agregan los errores a la lista
					errores.add(error);
					System.out.println("Error" + e.getMessage());
					continue;
				}

				try {
					row.getCell(5).setCellType(row.getCell(5).CELL_TYPE_STRING);
					if (!row.getCell(5).toString().equals("") && row.getCell(5).toString() != null)
						carga.setTiempoReparacion(new Double(row.getCell(5).toString()));
					else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo TIEMPO REPARACION en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en Campo TIEMPO REPARACION");
					// Se agregan los errores a la lista
					errores.add(error);
					System.out.println("Error" + e.getMessage());
					continue;
				}

				try {
					row.getCell(6).setCellType(row.getCell(6).CELL_TYPE_STRING);
					if (!row.getCell(6).toString().equals("") && row.getCell(6).toString() != null)
						carga.setTiempoMuerto(new Double(row.getCell(6).toString()));
					else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo TIEMPO MUERTO en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en Campo TIEMPO MUERTO");
					// Se agregan los errores a la lista
					errores.add(error);
					System.out.println("Error" + e.getMessage());
					continue;
				}

				try {

					if (row.getCell(7) == null)
						System.out.println("NULL");
					else {
						if (!row.getCell(7).toString().equals("") && row.getCell(7).toString() != null) {
							row.getCell(7).setCellType(row.getCell(7).CELL_TYPE_STRING);
							carga.setMotivo(row.getCell(7).toString());

						}
						
					}

				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en campo MOTIVO");
					// Se agregan los errores a la lista
					errores.add(error);
					System.out.println("Error" + e.getMessage());
					continue;
				}
				// Se guarda

				carga.setCreationDate(date);
				carga.setCreatedBy(wwid);
				carga.setLastUpdateDate(date);
				carga.setLastUpdateBy(wwid);
				carga.setDel('N');

				try {
					System.out.println("Trata de agregar ");
					System.out.println(carga.getFolio());
					Object ob = catalogsAdminService.findById("rescates", carga.getFolio(), len , false);

					Rescates existe = mapper.convertValue(ob, Rescates.class);
					if (existe != null)
						countUp++;
					else
						countAdd++;
					catalogsAdminService.save("rescates", carga, wwid);
					// Se agrega la instancia de CargaMasiva generada a la lista
					cargaList.add(carga);
				} catch (DataAccessException e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error al tratar de insertar, verifica archivo y tablas de referencia");
					// Se agregan los errores a la lista
					errores.add(error);
					System.out.println("Error Final: " + e.getMessage());
				}

				if (count >= 7)
					break;

			}

			ReporteCargaMasiva rep = new ReporteCargaMasiva();
			rep.setErrores(errores);
			rep.setRegistrosEliminados(countDel);
			rep.setRegistrosNuevos(countAdd);
			rep.setRegistrosActualizados(countUp);
			cargaList.add(rep);

			return new ResponseEntity<List>(cargaList, HttpStatus.OK);

			// return new ResponseEntity<Object>(procesosEntity, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Se excedieron las filas permitidas");
			return new ResponseEntity<List>(cargaList, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// -----------------------------------------------------------------------------------------
	// CARGA MASIVA HERRAMIENTAS ELECTRONICAS
	// -----------------------------------------------------------------------------------------

	@PostMapping("/loadInfo/electronictools/cargamasiva")
	public ResponseEntity<?> chargeExcelElectronicas(@RequestParam("file") MultipartFile reapExcelDataFile,
			@RequestParam("eraseData") String delete)
			throws IOException, FileStorageException, ParseException, SQLException {
		String len = LocaleContextHolder.getLocale().toString();
		Map<String, Object> response = new HashMap<>();

		DateFormat formatter;
		Date date1 = new Date();
		String wwid = "qy411";
		formatter = new SimpleDateFormat("dd/MM/yyyy");

		int countErrores = 0;
		// Se crea una lista para agregar todos los registros
		List<Object> cargaList = new ArrayList<Object>();
		List<Errores> errores = new ArrayList<Errores>();

		int countDel = 0;
		int countAdd = 0;
		int countUp = 0;

		// Verifica el parametro de limpiado de tabla
		if (delete.equals("1")) {
			System.out.println("Entra a eliminar");
			countDel = catalogsAdminService.logicDeleteElectronicTools();
		}

		// Se valida el numero de filas
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);

			// Por cada fila detectada se genera una instancia de tipo CargaMasiva
			for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
				ElectronicTools carga = new ElectronicTools();
				System.out.println("ENTRE A CARGA MASIVA");
				XSSFRow row = worksheet.getRow(i);
				int count = 0;
				// Se revisa que la celda contenga un atributo valido antes de setear

				// SP CODE
				try {
					row.getCell(0).setCellType(row.getCell(0).CELL_TYPE_STRING);
					if (!row.getCell(0).toString().equals("") && row.getCell(0).toString() != null) {

						carga.setSPCode(row.getCell(0).toString());
						Object ob = catalogsAdminService.findById("spcodes", carga.getSPCode(), len, false);
						SpCodes existe = null;
						existe = mapper.convertValue(ob, SpCodes.class);
						if (existe == null) {
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType(
									"El valor del campo SP CODE no existe en el catalogo PUNTOS DE SERVICIO");
							// Se agregan los errores a la lista
							errores.add(error);
							continue;
						}

					} else {
						System.out.println("SP CODE en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("SP CODE en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error campo SP CODE");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				// ID
				try {
					row.getCell(1).setCellType(row.getCell(1).CELL_TYPE_STRING);
					if (!row.getCell(1).toString().equals("") && row.getCell(1).toString() != null) {
						// Revisamos si existe o no
						System.out.println(carga.getId());
						carga.setId(row.getCell(1).toString());
					} else {
						System.out.println("Campo PCID/TOOL en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo PCID/TOOL en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en Campo PCID/TOOL");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				try {
					row.getCell(2).setCellType(row.getCell(2).CELL_TYPE_STRING);
					if (!row.getCell(2).toString().equals("") && row.getCell(2).toString() != null)
						carga.setNP(row.getCell(2).toString());
					else {
						System.out.println("Campo NUMERO DE PARTE en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo NUMERO DE PARTE en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en campo NUMERO DE PARTE");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				try {
					row.getCell(3).setCellType(row.getCell(3).CELL_TYPE_STRING);
					if (!row.getCell(3).toString().equals("") && row.getCell(3).toString() != null) {
						try {
							date1 = formatter.parse(row.getCell(3).toString());
							carga.setFechaReg(date1);
						} catch (Exception e) {
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Error en formato de fecha del campo FECHA REGISTRO");
							// Se agregan los errores a la lista
							errores.add(error);

							continue;
						}

					} else {
						System.out.println("Campo FECHA REGISTRO en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo FECHA REGISTRO en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en Campo FECHA REGISTRO");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				try {
					row.getCell(4).setCellType(row.getCell(4).CELL_TYPE_STRING);
					if (!row.getCell(4).toString().equals("") && row.getCell(4).toString() != null) {
						try {
							date1 = formatter.parse(row.getCell(4).toString());
							carga.setFechaExp(date1);
							if (carga.getFechaExp().before(carga.getFechaReg())) {
								Errores error = new Errores();
								error.setLine(String.valueOf(i + 1));
								error.setErrorType("Error FECHA DE REGISTRO mayor a FECHA EXPIRACION");
								// Se agregan los errores a la lista
								errores.add(error);

								continue;
							}

						} catch (Exception e) {
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Error en formato de fecha en campo FECHA EXPIRACION");
							// Se agregan los errores a la lista
							errores.add(error);

							continue;
						}

					} else {
						System.out.println("Campo FECHA EXPIRACION en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo FECHA EXPIRACION en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error en campo FECHA EXPIRACION");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				// Se guarda

				carga.setDel('N');

				try {
					Object ob = catalogsAdminService.findById("electronictools", carga.getId(), len, false);
					ElectronicTools existe = null;

					catalogsAdminService.save("electronictools", carga, wwid);
					existe = mapper.convertValue(ob, ElectronicTools.class);
					cargaList.add(carga);
					if (existe != null)
						countUp++;
					else
						countAdd++;

				} catch (DataAccessException e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error al insertar registro, revisa archivo y tablas de referencia");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}
				if (count >= 3)
					break;

			}

			ReporteCargaMasiva rep = new ReporteCargaMasiva();
			rep.setRegistrosEliminados(countDel);
			rep.setRegistrosNuevos(countAdd);
			rep.setRegistrosActualizados(countUp);
			rep.setErrores(errores);
			cargaList.add(rep);

			return new ResponseEntity<List>(cargaList, HttpStatus.OK);

			// return new ResponseEntity<Object>(procesosEntity, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Se excedieron las filas permitidas");
			return new ResponseEntity<List>(cargaList, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// -------------------------------------------------------------------
	// CARGA MASIVA NO DISPONIBLE
	// -------------------------------------------------------------------

	@PostMapping("/loadInfo/nodisponible/cargamasiva")
	public ResponseEntity<?> chargeExcelNoDisponible(@RequestParam("file") MultipartFile reapExcelDataFile,
			@RequestParam("eraseData") String delete)
			throws IOException, FileStorageException, ParseException, SQLException {
		String len = LocaleContextHolder.getLocale().toString();
		Map<String, Object> response = new HashMap<>();

		DateFormat formatter;
		Date date = new Date();
		Date date1 = new Date();
		String wwid = "qy411";
		formatter = new SimpleDateFormat("dd/MM/yyyy");

		int countErrores = 0;
		// Se crea una lista para agregar todos los registros
		List<Object> cargaList = new ArrayList<Object>();
		List<Errores> errores = new ArrayList<Errores>();

		int countDel = 0;
		int countAdd = 0;
		int countUp = 0;

		// Verifica el parametro de limpiado de tabla
		if (delete.equals("1")) {
			System.out.println("Entra a eliminar");
			countDel = catalogsAdminService.logicDeleteNoDisponible();
		}

		// Se valida el numero de filas
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);

			// Por cada fila detectada se genera una instancia de tipo CargaMasiva
			for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
				int count = 0;
				NoDisponible carga = new NoDisponible();
				System.out.println("ENTRE A CARGA MASIVA");
				XSSFRow row = worksheet.getRow(i);

				// Se revisa que la celda contenga un atributo valido antes de setear

				// FOLIO
				try {
					row.getCell(0).setCellType(row.getCell(0).CELL_TYPE_STRING);
					if (!row.getCell(0).toString().equals("") && row.getCell(0).toString() != null) {

						carga.setFolio(row.getCell(0).toString());
						// Revisamos si existe o no
						System.out.println(carga.getFolio());
					} else {
						System.out.println("Campo FOLIO en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo FOLIO en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error campo FOLIO");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}
				try {
					row.getCell(3).setCellType(row.getCell(3).CELL_TYPE_STRING);
					if (!row.getCell(3).toString().equals("") && row.getCell(3).toString() != null)
						carga.setCliente(row.getCell(3).toString());
					else {
						System.out.println("Campo CLIENTE en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo CLIENTE en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error campo Cliente");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				// SP CODE
				try {
					row.getCell(1).setCellType(row.getCell(1).CELL_TYPE_STRING);
					if (!row.getCell(1).toString().equals("") && row.getCell(1).toString() != null) {

						carga.setCode(row.getCell(1).toString());
						System.out.println(carga.getCode());
						Object ob = catalogsAdminService.findById("spcodes", carga.getCode(), len, false);
						SpCodes existe = null;
						existe = mapper.convertValue(ob, SpCodes.class);
						if (existe == null) {
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Campo SP CODE no existe en catalogo PUNTOS DE SERVICIO");
							// Se agregan los errores a la lista
							errores.add(error);
							continue;
						}

					} else {
						System.out.println("Campo SP CODE en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo SP CODE en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error campo SP CODE");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				try {
					row.getCell(2).setCellType(row.getCell(2).CELL_TYPE_STRING);
					if (!row.getCell(2).toString().equals("") && row.getCell(2).toString() != null) {
						try {
							date1 = formatter.parse(row.getCell(2).toString());
							carga.setFecha(date1);
						} catch (Exception e) {
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Error formato FECHA");
							// Se agregan los errores a la lista
							errores.add(error);
							continue;
						}

					} else {
						System.out.println("Campo FECHA en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo FECHA en blanco");
						// Se agregan los errores a la lista
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error campo FECHA");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				try {
					row.getCell(4).setCellType(row.getCell(4).CELL_TYPE_STRING);
					if (!row.getCell(4).toString().equals("") && row.getCell(4).toString() != null)
						carga.setRazon(row.getCell(4).toString());

				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error campo RAZON/MOTIVO");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				// Se guarda

				carga.setDel('N');

				try {
					Object ob = catalogsAdminService.findById("nodisponible", carga.getFolio(), len, false);
					NoDisponible existe = null;
					existe = mapper.convertValue(ob, NoDisponible.class);
					catalogsAdminService.save("nodisponible", carga, wwid);
					cargaList.add(carga);
					if (existe != null)
						countUp++;
					else
						countAdd++;

				} catch (DataAccessException e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error al Insertar, consulta tablas de referencia");
					// Se agregan los errores a la lista
					errores.add(error);
					continue;
				}

				if (count >= 3)
					break;
			}

			ReporteCargaMasiva rep = new ReporteCargaMasiva();
			rep.setRegistrosEliminados(countDel);
			rep.setRegistrosNuevos(countAdd);
			rep.setRegistrosActualizados(countUp);
			rep.setErrores(errores);
			cargaList.add(rep);

			if (countErrores > 0)
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			else
				return new ResponseEntity<List>(cargaList, HttpStatus.OK);

			// return new ResponseEntity<Object>(procesosEntity, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Se excedieron las filas permitidas");
			return new ResponseEntity<List>(cargaList, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// ----------------------------------------------------------------------------------
	// CARGA MASIVA GARANTIAS
	// ----------------------------------------------------------------------------------

	@PostMapping("/loadInfo/garantias/cargamasiva")
	public ResponseEntity<?> chargeExcelGarantia(
			@RequestParam("file") MultipartFile reapExcelDataFile,
			@RequestParam("eraseData") String delete,
			HttpServletRequest request)
			throws IOException, 
				   FileStorageException, 
				   ParseException, 
				   SQLException {
		String len = LocaleContextHolder.getLocale().toString();
		Map<String, Object> response = new HashMap<>();

		DateFormat formatter;
		DataFormatter df=new DataFormatter();
		Date date = new Date();
		Date date1 = new Date();
		//String wwid = "qy411";
		String wwid = (String) request.getSession(false).getAttribute("wwid");
		formatter = new SimpleDateFormat("dd/MM/yyyy");

		int countErrores = 0;
		// Se crea una lista para agregar todos los registros
		List<Object> cargaList = new ArrayList<Object>();

		// Se crea una lista para agregar todos los errores
		List<Errores> errores = new ArrayList<Errores>();

		int countDel = 0;
		int countAdd = 0;
		int countUp = 0;

		// Verifica el parametro de limpiado de tabla
		if (delete.equals("1")) {
			System.out.println("Entra a eliminar");
			countDel = catalogsAdminService.logicDeleteGarantia();
		}

		// Se valida el numero de filas
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);

			// Por cada fila detectada se genera una instancia de tipo CargaMasiva
			for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
				Garantia carga = new Garantia();
				System.out.println("-----------------------------------   Fila: "+(i+1)+"   -----------------------------------------------------");
				XSSFRow row = worksheet.getRow(i);
				int count = 0;
				Garantia existe = null;
				DataFormatter data= new DataFormatter();
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
				// Se revisa que la celda contenga un atributo valido antes de setear

				try {
				//FOLIO
					row.getCell(0).setCellType(row.getCell(0).CELL_TYPE_STRING);

					if (!row.getCell(0).toString().equals("") && row.getCell(0).toString() != null) {
						if (row.getCell(0).toString().length() > 20) {
							System.out.println("Campo Folio excede el tamaño maximo");
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Campo Folio excede el tamaño maximo de 20 caracteres");
							errores.add(error);
							count++;
							continue;
						}

						carga.setFolio(row.getCell(0).toString());
						// Revisamos si existe o no
						Object ob = catalogsAdminService.findById("garantias", carga.getFolio(), len, false);
						if (ob != null) {
							existe = mapper.convertValue(ob, Garantia.class);
						}
					} else {
						System.out.println("Campo Folio en blanco");
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo Folio vacío");
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error" + e.getMessage());
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error al buscar el FOLIO en la base de datos.");
					errores.add(error);
					count++;
					continue;
				}
				try {
				//STATUS
					row.getCell(1).setCellType(row.getCell(1).CELL_TYPE_STRING);
					if (!row.getCell(1).toString().equals("") && row.getCell(1).toString() != null) {
						if (!row.getCell(1).toString().equalsIgnoreCase("PAGADO")
								&& !row.getCell(1).toString().equalsIgnoreCase("RECHAZADO")) {
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Campo Status incorrecto debe de contener 'PAGADO' O 'RECHAZADO'");
							errores.add(error);
							count++;
							continue;
						}

						Aux_StatusGar st = new Aux_StatusGar();
						st.setStatus(row.getCell(1).toString());
						Object ob = catalogsAdminService.findStatusGarByName(st);
						Aux_StatusGar el = mapper.convertValue(ob, Aux_StatusGar.class);

						carga.setStatus(el.getIdstatus().toString());
					} else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo status vacío");
						errores.add(error);
						count++;
						continue;
					}

				} catch (Exception e) {
					System.out.println("Error" + e.getMessage());
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error al buscar registro en la base de datos.");
					errores.add(error);
					count++;
					continue;
				}

				try {
				//SPCODE
					row.getCell(2).setCellType(row.getCell(2).CELL_TYPE_STRING);
					if (!row.getCell(2).toString().equals("") && row.getCell(2).toString() != null) {

						carga.setSpCode(row.getCell(2).toString());

					} else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo SpCode vacío");
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error" + e.getMessage());

					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error al buscar el SPCODE en la base de datos");
					errores.add(error);
					count++;
					continue;
				}

				try {
				//CC
					row.getCell(3).setCellType(row.getCell(3).CELL_TYPE_STRING);
					if (!row.getCell(3).toString().equals("") && row.getCell(3).toString() != null) {
						// Validate that CC exist on the system
						Aux_CC cc = new Aux_CC();
						cc.setCodigoCta(row.getCell(3).toString());
						cc.setDel('N');
						List<Aux_CC> listCC = catalogsAdminService.findCCByCriteria(cc);
						if (listCC == null || listCC.size() <= 0) {
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Campo Código Cliente no existe");
							errores.add(error);
							count++;
							continue;
						}
						carga.setCodigoCta(row.getCell(3).toString());
					} else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo Código Cliente vacío");
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error" + e.getMessage());
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error al buscar código cliente en la base de datos.");
					errores.add(error);
					count++;
					continue;
				}

				try {
				//ESN
					row.getCell(4).setCellType(row.getCell(4).CELL_TYPE_STRING);
					if (!row.getCell(4).toString().equals("") && row.getCell(4).toString() != null) {
						if (row.getCell(4).toString().length() > 10) {
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Campo ESN excede el tamaño maximo de 10 caracteres");
							errores.add(error);
							count++;
							continue;
						}
						carga.setEsn(row.getCell(4).toString());
					} else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo ESN en blanco");
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error" + e.getMessage());
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Campo ESN incorrecto");
					errores.add(error);
					count++;
					continue;
				}

				try {
				// MONTO RECLAMADO
					row.getCell(5).setCellType(row.getCell(5).CELL_TYPE_STRING);
					if (!row.getCell(5).toString().equals("") && row.getCell(5).toString() != null)
						carga.setMontoReclamado(new Double(row.getCell(5).toString()));
					else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo Monto Reclamado vacío");
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error" + e.getMessage());
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Campo Monto Reclamado incorrecto");
					errores.add(error);
					count++;
					continue;
				}

				try {
				//MONTO PAGADO
					row.getCell(6).setCellType(row.getCell(6).CELL_TYPE_STRING);
					if (!row.getCell(6).toString().equals("") && row.getCell(6).toString() != null)
						carga.setMontoPagado(new Double(row.getCell(6).toString()));
					else if (carga.getStatus().equalsIgnoreCase("Pagado")) {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo Monto Pagado vacío");
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error" + e.getMessage());
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Campo Monto Pagado incorrecto");
					errores.add(error);
					count++;
					continue;
				}

				try {
					/*row.getCell(7).setCellType(row.getCell(7).CELL_TYPE_STRING);
					if (!row.getCell(7).toString().equals("") && row.getCell(7).toString() != null) {
						if (!row.getCell(7).toString().equals("") && row.getCell(7).toString() != null) {
						try {
							System.out.println(row.getCell(7).getStringCellValue());
							date1 = formatter.parse(row.getCell(7).getStringCellValue());
						} */
					//row.getCell(7).setCellType(row.getCell(7).CELL_TYPE_STRING);
					if (!row.getCell(7).toString().equals("") && row.getCell(7).toString() != null) {
					//if (DateUtil.isCellDateFormatted(row.getCell(7).getCellStyle().getDataFormat())) {
						try {
							//System.out.println(row.getCell(7).getDateCellValue());
							//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
							//String daat = sdf.format(row.getCell(7).getDateCellValue());
							//carga.setFechaReclamo(formatter.parse(daat));
							
							//Date reclamo=sdf1.parse(row.getCell(7).getDateCellValue().toString());
							Date reclamo=row.getCell(7).getDateCellValue();
							System.out.println("Validacion fecha reclamo"+row.getCell(7).getDateCellValue()+" fecha leida: "+ reclamo);
							carga.setFechaReclamo(reclamo);
						}catch (Exception e) {
							System.out.println("error en validacion de fecha reclamo: "+row.getCell(7).getDateCellValue());
							System.out.println(e);
							date1 = new Date();
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Campo Fecha Reclamo con formato incorrecto");
							errores.add(error);
							count++;
							continue;
						}
						
					} else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo Fecha Reclamo vacío");
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error " + e.getMessage());
					System.out.println("Valor " + row.getCell(7).getStringCellValue().toString());
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Campo Fecha Reclamo incorrecto");
					errores.add(error);
					count++;
					continue;
				}
				try {
					//row.getCell(8).setCellType(row.getCell(8).CELL_TYPE_STRING);
					if (!row.getCell(8).toString().equals("") && row.getCell(8).toString() != null) {
						try {
							//date1 = formatter.parse(row.getCell(8).getStringCellValue());
							//Date falla=sdf.parse(row.getCell(8).getStringCellValue().trim());
							Date falla=row.getCell(8).getDateCellValue();
							System.out.println("Validacion fecha falla"+row.getCell(8).getDateCellValue()+" fecha leida: "+ falla);
							carga.setFechaFalla(falla);
						} catch (Exception e) {
							date1 = new Date();
							System.out.println("Error"+row.getCell(7).getDateCellValue());
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Campo Fecha Falla con formato incorrecto");
							errores.add(error);
							carga.setFechaFalla(date1);
							count++;
							continue;
						}
						
					} else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo Fecha Falla vacía");
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error" + e.getMessage());

					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Campo Fecha Falla incorrecta");
					errores.add(error);
					count++;
					continue;
				}
				try {
					//row.getCell(9).setCellType(row.getCell(9).CELL_TYPE_STRING);
					if (!row.getCell(9).toString().equals("") && row.getCell(9).toString() != null) {
						try {
							//date1 = formatter.parse(row.getCell(9).getStringCellValue());
							//Date reparacion=sdf.parse(row.getCell(8).getStringCellValue().trim());
							Date reparacion=row.getCell(9).getDateCellValue();
							System.out.println("Validacion fecha reparacion"+row.getCell(9).getDateCellValue()+" fecha leida: "+ reparacion);
							carga.setFechaFinReparacion(reparacion);
						} catch (Exception e) {
							date1 = new Date();
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Campo Fecha Fin reparacion con formato incorrecto");
							errores.add(error);
							//carga.setFechaFinReparacion(date1);
							count++;
							continue;
						}
						
					} else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo Fecha Fin reparacion vació");
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error" + e.getMessage());
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Campo Fecha Fin reparacion incorrecto");
					errores.add(error);
					count++;
					continue;
				}
				try {
					//row.getCell(10).setCellType(row.getCell(10).CELL_TYPE_STRING);
					System.out.println("Status: "+carga.getStatus()+" - Fila: "+(i+1) );
					if (!row.getCell(10).toString().equals("") && row.getCell(10).toString() != null) {
						try {
							//date1 = formatter.parse(row.getCell(10).getStringCellValue());
							Date rechazo=row.getCell(10).getDateCellValue();
							carga.setFechaRechazo(rechazo);
						} catch (Exception e) {
							date1 = new Date();
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Campo Fecha Rechazo con formato incorrecto");
							errores.add(error);
							count++;
							continue;
						}
						
					} else if (row.getCell(1).toString().equals("Rechazado")||row.getCell(1).toString().equals("RECHAZADO")) {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo Fecha Rechazo vacío");
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Campo Fecha Rechazo incorrecto");
					errores.add(error);
					count++;
					continue;
				}
				try {
					//row.getCell(11).setCellType(row.getCell(11).CELL_TYPE_STRING);
					if (!row.getCell(11).toString().equals("") && row.getCell(11).toString() != null ) {
						try {
							Date solucion=row.getCell(11).getDateCellValue();
							carga.setFechSolucion(solucion);
						} catch (Exception e) {
							date1 = new Date();
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Campo Fecha Solucion formato incorrecto");
							errores.add(error);
							count++;
							continue;
						}
						
					} else if (row.getCell(1).toString().equals("Pagado")||row.getCell(1).toString().equals("PAGADO")) {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo Fecha Solución vacío");
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error" + e.getMessage());
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Campo Fecha Solución incorrecto");
					errores.add(error);
					count++;
					continue;
				}

				try {
					row.getCell(12).setCellType(row.getCell(12).CELL_TYPE_STRING);
					if (!row.getCell(12).toString().equals("") && row.getCell(12).toString() != null)
						carga.setDiasReparacion(Double.valueOf(row.getCell(12).toString()));
					else if (carga.getStatus().equalsIgnoreCase("Pagado")) {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo Días Reparación en blanco");
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error" + e.getMessage());
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Campo Dias Reparación incorrecto");
					errores.add(error);
					count++;
					continue;
				}

				try {
					row.getCell(13).setCellType(row.getCell(13).CELL_TYPE_STRING);
					if (!row.getCell(13).toString().equals("") && row.getCell(13).toString() != null)
						carga.setTotal(Double.valueOf(row.getCell(13).toString()));
					else if (row.getCell(1).toString().equals("Pagado")||row.getCell(1).toString().equals("PAGADO")) {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Campo Total vacío");
						errores.add(error);
						count++;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error" + e.getMessage());
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Campo Total incorrecto");
					errores.add(error);
					count++;
					continue;
				}

				if (existe == null) {
					carga.setCreationDate(date);
					carga.setCreatedBy(wwid);
				}

				carga.setLastUpdateDate(date);
				carga.setLastUpdatedBy(wwid);
				carga.setDel('N');

				if (count > 0)
					continue;

				try {
					catalogsAdminService.save("garantias", carga, wwid);
					if (existe != null)
						countUp++;
					else
						countAdd++;
				} catch (DataAccessException e) {
					response.put("message", "Error when inserting/updating on the data base.");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error al crear o actualizar el archivo en la base de datos.");
					errores.add(error);
					count++;
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// Se agrega la instancia de CargaMasiva generada a la lista
				try {

					cargaList.add(carga);
				} catch (DataAccessException e) {
					countErrores++;
					response.put("mensaje", "Error al insertar/actualizar en la base de datos.");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Error al crear o actualizar el archivo en la base de datos.");
					errores.add(error);
					count++;
				}

			}

			ReporteCargaMasiva rep = new ReporteCargaMasiva();
			rep.setRegistrosEliminados(countDel);
			rep.setRegistrosNuevos(countAdd);
			rep.setRegistrosActualizados(countUp);
			rep.setErrores(errores);
			cargaList.add(rep);

			if (countErrores > 0)
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			else
				return new ResponseEntity<List>(cargaList, HttpStatus.OK);

			// return new ResponseEntity<Object>(procesosEntity, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Se excedieron las filas permitidas");
			return new ResponseEntity<List>(cargaList, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// ----------------------------------------------------------------------------------
	// CARGA MASIVA PRODUCTOS POR EVALUACIÓN
	// ----------------------------------------------------------------------------------

	@SuppressWarnings("null")
	@PostMapping("/v1/admin/loadInfo/confMotor/cargamasiva")
	public ResponseEntity<?> chargeExcelProductosEval(
			@RequestParam("file") MultipartFile reapExcelDataFile,
			@RequestParam("region") String region, 
			@RequestParam("evaluacion") String evaluacion,
			Locale locale)
			throws IOException, FileStorageException, ParseException, SQLException {
		String len = LocaleContextHolder.getLocale().toString();
		System.out.println(region + " " + evaluacion);
		Map<String, Object> response = new HashMap<>();

		int countErrores = 0;
		List<Errores> errores = new ArrayList<Errores>();
		List<Object> cargaList = new ArrayList<Object>();
		List<ConfMotorDTO> confMotor = new ArrayList<ConfMotorDTO>();

		// Se valida el numero de filas
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);

			// Por cada fila detectada se genera una instancia de tipo CargaMasiva
			for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
				// -------------------------------------------------
				// OBJETOS
				// -------------------------------------------------

				// MOTOR/PRODUCTO RANGO APLICACIÓN NIVEL DE SERVICIO DISTRIBUIDORES OEMS MATRIZ
				// PARTES MATRIZ HERRAMIENTAS MECANICOS
				String MotorProducto = null;
				String Rango = null;
				String Aplicacion = null;
				String NivelServicio = null;
				String Distribuidores = null;
				String[] spCodeArray = null;
				String OEMS = null;
				String[] oemsArray = null;
				String MatrizPartes = null;
				String MatrizHerramientas = null;
				String Mecanicos = null;

				ConfMotor motor = new ConfMotor();
				SpCodes spCode = new SpCodes();
				ConfOem oem = new ConfOem();

				System.out.println("ENTRE A CARGA MASIVA CONF MOTOR");
				XSSFRow row = worksheet.getRow(i);

				// Se revisa que la celda contenga un atributo valido antes de setear

				System.out.println(
						"----------------------- Linea: " + i + " ------------------------------------------------");
				String errorStr="";
				try {
					for (int j = 0; j < 9; j++) {
						row.getCell(j).setCellType(row.getCell(j).CELL_TYPE_STRING);

						if (!row.getCell(j).toString().equals("") && row.getCell(j).toString() != null) {
							switch (j) {
							case 0:
								errorStr= "text.utilities.error.confmotor.motor.null";
								MotorProducto = row.getCell(j).toString().trim();
								System.out.println("MotorProducto: " + MotorProducto + " ");
								break;
							case 1:
								errorStr= "text.utilities.error.confmotor.range.null";
								Rango = row.getCell(j).toString().trim();
								System.out.println("Rango: " + Rango + " ");
								break;
							case 2:
								errorStr= "text.utilities.error.confmotor.app.null";
								Aplicacion = row.getCell(j).toString().trim();
								System.out.println("Aplicacion: " + Aplicacion + " ");
								break;
							case 3:
								errorStr= "text.utilities.error.confmotor.servicelevel.null";
								NivelServicio = row.getCell(j).toString().trim();
								System.out.println("Nivel de Servicio: " + NivelServicio + " ");
								break;
							case 4:
								errorStr= "text.utilities.error.confmotor.dist.null";
								Distribuidores = row.getCell(j).toString().trim();
								spCodeArray = Distribuidores.split(",");
								System.out.println("Distribuidores: " + Distribuidores + " " + spCodeArray.length);
								break;
							case 5:
								errorStr= "text.utilities.error.confmotor.oem.null";
								OEMS = row.getCell(j).toString().trim();
								oemsArray = OEMS.split(",");
								System.out.println("OEMS: " + OEMS + " " + oemsArray.length);
								break;
							case 6:
								errorStr= "text.utilities.error.confmotor.matparts.null";
								MatrizPartes = row.getCell(j).toString().trim();
								System.out.println("MatrizPartes: " + MatrizPartes + " ");
								break;
							case 7:
								errorStr= "text.utilities.error.confmotor.mattools.null";
								MatrizHerramientas = row.getCell(j).toString().trim();
								System.out.println("MatrizHerramientas: " + MatrizHerramientas + " ");
								break;
							case 8:
								errorStr= "text.utilities.error.confmotor.mec.null";
								Mecanicos = row.getCell(j).toString().trim();
								System.out.println("Mecanicos: " + Mecanicos + " ");
								break;
							}
						} else {
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("Error al leer la fila");
							errores.add(error);
							continue;
						}

					}

				} catch (Exception e) {

					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType(messageSource.getMessage(errorStr, null, locale));
					errores.add(error);
					System.out.println("Error" + e.getMessage());
					continue;

				}

				// --------------------------------------------------------------------------------------------------
				// VALIDATION TO: APPLICATION, SERVICE LEVEL, MOTOR PRODUCTS, RANKS
				// --------------------------------------------------------------------------------------------------

				// matrizHtasService.matricesValidasCargaMasiva("ns", "dist", "mod", "oem",
				// "matriz"); ns=
				
				List<ESPM_NS> espm = espNsService.espmNs(MotorProducto, Rango, Aplicacion, NivelServicio,len);
				String idEspmotor = "";
				String idNs = "";
				if (espm.size() > 0) {
					idEspmotor = espm.get(0).getIdESPM().toString();
					idNs = espm.get(0).getIdNs().toString();
					System.out.println(espm.get(0));
				} else {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("Motor/Producto, Rango, Aplicación y Nivel de Servicio no existe");
					errores.add(error);
					continue;
				}
				List<BigInteger> oems = new ArrayList<BigInteger>();
				List<String> oems1 = new ArrayList<String>();
				for (String oe : oemsArray) {
					List<Oems> oemExist = (List<Oems>) oemService.OemById(oe);
					if (oemExist.size() > 0) {
						oems.add(oemExist.get(0).getId());
						oems1.add(oemExist.get(0).getId().toString());

					} else {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("El OEM: " + oe + " no se encuentra registrado");
						errores.add(error);
					}
				}

				if (oems.size() != oemsArray.length) {
					continue;
				}
				System.out.println(oems);

				List<String> listSpcode = spcodesService.spCodesValidos(region);
				// oems.toString()
				List<MatrizHtasHd> listMatrizHtas = matrizHtasService.matricesValidasCargaMasiva(NivelServicio,
						Distribuidores, idEspmotor, String.join(", ", oems1), MatrizHerramientas);
				if (listMatrizHtas.size() < 1) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType(
							"La Matriz de Herramientas: " + MatrizHerramientas + " no es válida para ese producto ");
					errores.add(error);
					continue;
				}
				System.out.println(listMatrizHtas);
				List<MatrizPartesHd> listMatrizPartes = matrizPartesService.matricesValidasCargaMasiva(NivelServicio,
						Distribuidores, idEspmotor, String.join(", ", oems1), MatrizPartes);
				if (listMatrizPartes.size() < 1) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("La Matriz de Partes: " + MatrizPartes + " no es válida para ese producto");
					errores.add(error);
					continue;
				}
				System.out.println(listMatrizPartes);
				System.out.println(listSpcode);

				for (String sp : spCodeArray) {

					Boolean exist = false;
					for (String st : listSpcode) {
						System.out.println(sp + "-" + st);
						if (sp.equals(st)) {
							System.out.println("Sp code Valido");
							exist = true;
							break;
						}

					}
					if (exist == false)
						System.out.println("Sp code no valido");
				}

				BigInteger mec = null;
				try {
					mec = new BigInteger(Mecanicos);
				} catch (Exception ex) {
					Errores error = new Errores();
					error.setLine(String.valueOf(i + 1));
					error.setErrorType("El Número de Mecánicos no es válido");
					errores.add(error);

				}

				System.out.println("hasta aqui todo bien");
				BigInteger oemArr[] = new BigInteger[oems.size()];

				int cont = 0;
				for (BigInteger o : oems) {
					oemArr[cont] = o;
					cont++;
				}
				
				MatrizDTO mHtas= new MatrizDTO();
				          mHtas.setIdMatriz(listMatrizHtas.get(0).getIdMatriz().toString());
				          mHtas.setNombre(listMatrizHtas.get(0).getNombre());
				          mHtas.setNoRevision(listMatrizHtas.get(0).getNoRevision());
			    
			    MatrizDTO mPartes= new MatrizDTO();
				          mPartes.setIdMatriz(listMatrizPartes.get(0).getIdMatriz().toString());
				          mPartes.setNombre(listMatrizPartes.get(0).getNombre());
				          mPartes.setNoRevision(listMatrizPartes.get(0).getNoRevision());
				
				ConfMotorDTO conf = new ConfMotorDTO();
				conf.setIdEspmotor(new BigInteger(idEspmotor));
				conf.setIdNs(new BigInteger(idNs));
				conf.setIdMatrizHtas(listMatrizHtas.get(0).getIdMatriz());
				conf.setNs(espm.get(0).getNs());;
				conf.setMatHtas(mHtas);
				conf.setMatPartes(mPartes);
				conf.setIdMatrizPartes(listMatrizPartes.get(0).getIdMatriz());
				conf.setOem(oemArr);
				conf.setMecReq(mec);
				conf.setSp(spCodeArray);

				confMotor.add(conf);
			}

			ReporteCargaMasivaConfMotor rep = new ReporteCargaMasivaConfMotor();
			rep.setErrores(errores);
			rep.setConfMotor(confMotor);
			cargaList.add(rep);

			// return new ResponseEntity( HttpStatus.OK);
			if (countErrores > 0)
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			else
				return new ResponseEntity<List>(cargaList, HttpStatus.OK);

			// return new ResponseEntity<Object>(procesosEntity, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "Error");
			System.out.println("Se excedieron las filas permitidas");
			return new ResponseEntity<List>(cargaList, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
		// ----------------------------------------------------------------------------------
		// CARGA MASIVA PERFILES
		// ----------------------------------------------------------------------------------

		@SuppressWarnings("null")
		@PostMapping("/v1/admin/loadInfo/perfiles/cargamasiva")
		public ResponseEntity<?> chargeExcelPerfiles(
				HttpServletRequest request,
				@RequestParam("file") MultipartFile reapExcelDataFile,
				@RequestParam("region") BigInteger region, 
				@RequestParam("evaluacion") String evaluacion,
				@RequestParam("evaluacionCopia") String evaluacionCopia
				)throws IOException, FileStorageException, ParseException, SQLException 
		{
			System.out.println("Region: "+region + " Evaluacion: " + evaluacion+ " EvaluacionCopia: "+ evaluacionCopia);
			Map<String, Object> response = new HashMap<>();

			int countErrores = 0;
			Date date= new Date();
			String wwid = (String)request.getSession(false).getAttribute("wwid");
			List<Errores> errores = new ArrayList<Errores>();
			List<Mensajes> messages= new ArrayList<Mensajes>();
			List<Object> cargaList = new ArrayList<Object>();
			List<String> ListaSpcodes = new ArrayList<String>();
			List<RelPerfilExcs> listaexc = new ArrayList<RelPerfilExcs>();
			String idOem="";
			String IDconfMotor="";
			String IDconfMotorOem="";
			String NoBahias="";
			String fronterizo="";
			// GET VALID SPCODES
			List<SpCodesDTO> spc= spcodesService.getSpCodesByRegion(region.toString());
			for(SpCodesDTO s : spc)
			{
				ListaSpcodes.add(s.getSpCode());
			}
			System.out.println(spc);
			System.out.println(ListaSpcodes);
			// Se valida el numero de filas
			try {
				XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
				XSSFSheet worksheet = workbook.getSheetAt(0);

				// Por cada fila detectada se genera una instancia de tipo CargaMasiva
				for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
					// -------------------------------------------------
					// OBJETOS
					// -------------------------------------------------
					//numero spcode
					//nombre rango
					//nombre aplicacion
					//nombre motor
					//nombre nivel servicio
					
					// PERFIL
					String Spcode = null;
					String Rango = null;
					String Aplicacion = null;
					String Motor = null;
					String NServicio = null;
					boolean bandSpcode=false;


					System.out.println("ENTRE A CARGA MASIVA PERFILES");
					XSSFRow row = worksheet.getRow(i);

					// Se revisa que la celda contenga un atributo valido antes de setear

					System.out.println(
							"----------------------- Linea: " + i + " ------------------------------------------------");
					try {
						for (int j = 0; j < 5; j++) {
							row.getCell(j).setCellType(row.getCell(j).CELL_TYPE_STRING);

							if (!row.getCell(j).toString().equals("") && row.getCell(j).toString() != null) {
								switch (j) {
								case 0:
									Spcode = row.getCell(j).toString().trim();
									System.out.println("SpCode: " + Spcode + " ");
									break;
								case 1:
									Rango = row.getCell(j).toString().trim();
									System.out.println("Rango: " + Rango + " ");
									break;
								case 2:
									Aplicacion = row.getCell(j).toString().trim();
									System.out.println("Aplicacion: " + Aplicacion + " ");
									break;
								case 3:
									Motor = row.getCell(j).toString().trim();
									System.out.println("Motor: " + Motor + " ");
									break;
								case 4:
									NServicio = row.getCell(j).toString().trim();
									System.out.println("Nivel de Servicio: " + NServicio + " ");
									break;
								
								}
							} else {
								Errores error = new Errores();
								error.setLine(String.valueOf(i + 1));
								error.setErrorType("Error al leer la fila");
								errores.add(error);
								continue;
							}

						}
					
					} catch (Exception e) {

						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("Error al leer la fila");
						errores.add(error);
						System.out.println("Error" + e.getMessage());
						continue;

					}
					
					// --------------------------------------------------------------------------------------------------
					// VALIDATION 
					// --------------------------------------------------------------------------------------------------
					//List<String> spc = spcodesService.regionPorSpCodes(region);
					
					 //VALIDA SPCODES
					 if(!Spcode.isEmpty())
					 {
						 bandSpcode= ListaSpcodes.contains(Spcode.trim());
						
						 if(bandSpcode)
						 {
							 for(SpCodesDTO sp: spc)
							 {
								 if(sp.getSpCode().equals(Spcode.trim()))
								 {			 
									 idOem= sp.getIdOem().toString();
									 break;
								 }
							 }
							 
							 System.out.println("Id OEM " +  idOem);
							 //-------------------------------------------------------------
							 //     	VALIDA MOTOR
							 //----------------------------------------------------------
							 
							 if((Motor!=null || Motor!="") && (NServicio!=null || NServicio!="") )
							 {
								 List<ConfSpcodes> resultValidacionDistribuidor= confspcodesService.validaMotorDistribuidor(Spcode,evaluacion,NServicio,Motor,Rango,Aplicacion,"es_ES");
								 List<ConfOem> resultValidacionOem= confOemService.validaMotorOem(idOem,evaluacion,NServicio,Motor,Rango,Aplicacion,"es_ES");
								 
								 System.out.println("validacion distribuidor: "+resultValidacionDistribuidor.toString());
								 System.out.println("validacion oem: "+resultValidacionOem.toString());
								 if(resultValidacionDistribuidor!=null && resultValidacionOem!=null)
								 {
									 IDconfMotor= resultValidacionDistribuidor.get(0).getIdConfMotor().toString();
									 
									 /*
									  * VALIDACION PERFILES
									 */
									 
									 List<Perfiles> PerfilExistente= perfilService.getPerfiles(evaluacion,Spcode);
									 System.out.println(PerfilExistente);
									 if(PerfilExistente.size()>0)
									 {
										 
									
										 List<RelPerfilMotor> perfilMotorExistente= perfilService.getPerfilesMotor(PerfilExistente.get(0).getIdPerfil().toString());
										 if(perfilMotorExistente.size()>0)
										 {
											 Errores error = new Errores();
											 error.setLine(String.valueOf(i + 1));
											 error.setErrorType("RENGLÓN No" + (i + 1) + " - Ya existe esa relación en la aplicación");
											 System.out.println(error.getErrorType());
											 errores.add(error);
											 
										 }
										 else // si no existe un perfil de motor 
										 {
												RelPerfilMotor rpm = new RelPerfilMotor();
												rpm.setIdPerfil(PerfilExistente.get(0).getIdPerfil());
												rpm.setIdConfMotor(new BigInteger(IDconfMotor));
												rpm.setCalificacion(null);
												rpm.setIdEstatus(new BigInteger("1"));
												rpm.setComentarios(null);
												rpm.setCreationDate(date);
												rpm.setCreatedBy(wwid);
												rpm.setLastUpdateBy(wwid);
												rpm.setLastUpdateDate(date);
												rpm.setDel('N');
												relPerfilMotorService.save(rpm);
												//- Msj "RENGLÓN No n - El registro fué generado"
												Mensajes m= new Mensajes();
												m.setLine(String.valueOf(i + 1));
												m.setMessage("RENGLÓN No " +  (i + 1) + " - El registro fué generado");
												messages.add(m);
												
										 }
									 }
									 else // Si no Encontro un perfil
									 {
										 List<Perfiles> perfilNew= perfilService.getPerfiles(evaluacionCopia,Spcode);
										 if(perfilNew.size()>0)
										 {
											 fronterizo= perfilNew.get(0).getFronterizo().toString();
											 NoBahias=perfilNew.get(0).getNoBahias().toString();
											 listaexc= perfilService.getPerfilExceptions(perfilNew.get(0).getIdPerfil().toString(),IDconfMotor);
											 											
											 
										 }
										 else {
											 fronterizo= null;
											 NoBahias=null;
											 
										 }
										 
										//GENERA PERFIL
									 	Perfiles perfilNewtoSave = new Perfiles();
									 	Perfiles perfilSaved = new Perfiles();
									 	perfilNewtoSave.setIdEvaluacion(new BigInteger(evaluacion));
									 	perfilNewtoSave.setSpCode(Spcode);
									 	perfilNewtoSave.setIdOem(new BigInteger(idOem));
									 	perfilNewtoSave.setFronterizo(fronterizo !=null ? new BigInteger(fronterizo) : null);
									 	perfilNewtoSave.setNoBahias(NoBahias!=null ? new BigInteger(NoBahias) : null);
									 	perfilNewtoSave.setNoMecPromotion(perfilNew.size()>0 ? perfilNew.get(0).getNoMecPromotion() : null);
									 	perfilNewtoSave.setNoMecanicos(perfilNew.size()>0 ? perfilNew.get(0).getNoMecanicos() : null);
									 	perfilNewtoSave.setNoAyudantes(perfilNew.size()>0 ? perfilNew.get(0).getNoAyudantes() : null);
									 	perfilNewtoSave.setComentarios(perfilNew.size()>0 ? perfilNew.get(0).getComentarios() :  null);
									 	perfilNewtoSave.setResultadoBloqueado(perfilNew.size()>0 ? perfilNew.get(0).getResultadoBloqueado() : null);
										
									 	perfilNewtoSave.setCreatedBy(wwid);
									 	perfilNewtoSave.setCreationDate(date);
									 	perfilNewtoSave.setLastUpdateBy(wwid);
									 	perfilNewtoSave.setLastUpdateDate(date);
									 	perfilNewtoSave.setDel('N');
									 	perfilSaved= perfilesDao.save(perfilNewtoSave);
									 	// Msj "RENGLÓN No n - Se generó el Perfil del SP Code"
									 	Mensajes m= new Mensajes();
										m.setLine(String.valueOf(i + 1));
										m.setMessage("RENGLÓN No " +  (i + 1) + " - Se generó el Perfil del SP Code");
										messages.add(m);
									 	
									 	// GENERA PERFIL MOTOR
									 	RelPerfilMotor rpm = new RelPerfilMotor();
										rpm.setIdPerfil(perfilSaved.getIdPerfil());
										rpm.setIdConfMotor(new BigInteger(IDconfMotor));
										rpm.setCalificacion(null);
										rpm.setIdEstatus(new BigInteger("1"));
										rpm.setComentarios(null);
										rpm.setCreationDate(date);
										rpm.setCreatedBy(wwid);
										rpm.setLastUpdateBy(wwid);
										rpm.setLastUpdateDate(date);
										rpm.setDel('N');
										relPerfilMotorService.save(rpm);
										// Msj "RENGLÓN No n - El registro fué generado"
										Mensajes mesmotor= new Mensajes();
										m.setLine(String.valueOf(i + 1));
										m.setMessage("RENGLÓN No " +  (i + 1) + " - El registro fué generado");
										messages.add(mesmotor);
										
										//GENERA EXCEPCIONES
										if(listaexc.size()>0)
										{
											for(RelPerfilExcs ex: listaexc)
											{
												RelPerfilExcs pex= new RelPerfilExcs();
												pex.setIdPerfil(perfilSaved.getIdPerfil());
												pex.setIdConfMotor(new BigInteger(IDconfMotor));
												pex.setIdModulo(ex.getIdModulo());
												pex.setIdSubmodulo(ex.getIdSubmodulo());
												pex.setCreationDate(date);
												pex.setCreatedBy(wwid);
												pex.setLastUpdateDate(date);
												pex.setLastUpdateBy(wwid);
												pex.setDel('N');
												exceptionsDao.save(pex);
												
											}
										//  Msj "RENGLÓN No n - Se generaron los resgitros de excepción para ese SP Code"-
											Mensajes mesex= new Mensajes();
											m.setLine(String.valueOf(i + 1));
											m.setMessage("RENGLÓN No " +  (i + 1) + " - Se generaron los resgitros de excepción para ese SP Code");
											messages.add(mesex);
											
										}
										 
									 }
									 
									 
									 
								 }
								 else
								 {
									 Errores error = new Errores();
									 error.setLine(String.valueOf(i + 1));
									 error.setErrorType("RENGLÓN No" + (i + 1) + " - El Motor No es válido");
									 System.out.println(error.getErrorType());
									 errores.add(error);
									 continue;
								
								 }
								 
							 }
							 else  // Si esta vacia Celda de motor o nivel de servicio
							 {
								 Errores error = new Errores();
								 error.setLine(String.valueOf(i + 1));
								 error.setErrorType("RENGLÓN No" + (i + 1) + " - Falta el valor del Motor con su Nivel de Servicio");
								 System.out.println(error.getErrorType());
								 errores.add(error);
								 continue;
							 }
							 
							
						 }
						 else
						 {
							Errores error = new Errores();
							error.setLine(String.valueOf(i + 1));
							error.setErrorType("RENGLÓN No " + (i + 1) + " - El SP Code No es válido");
							System.out.println(error.getErrorType());
							errores.add(error);
							continue;
						 }
						 	 
					 }
					 else
					 {
						Errores error = new Errores();
						error.setLine(String.valueOf(i + 1));
						error.setErrorType("RENGLÓN No " + (i + 1) +  " - Falta el valor del SP Code");
						System.out.println(error.getErrorType());
						errores.add(error);
						continue;
					 }		
					
				}

				
				
				// return new ResponseEntity( HttpStatus.OK);
				

				// return new ResponseEntity<Object>(procesosEntity, HttpStatus.OK);
			} catch (Exception e) {
				System.out.println(e.getMessage() + "Error");
				System.out.println("Se excedieron las filas permitidas");
				return new ResponseEntity<List>(cargaList, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			/*if (countErrores > 0) {
				response.put("errores", errores);
				response.put("messages", messages);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			else
				return new ResponseEntity<List>(cargaList, HttpStatus.OK);*/
			
			response.put("errores", errores);
			response.put("messages", messages);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		

		private Errores getError(String errorType, String linea) {
			Errores error= new Errores();
			error.setLine(linea);
			error.setErrorType(errorType);
			return error;
		}

}



























