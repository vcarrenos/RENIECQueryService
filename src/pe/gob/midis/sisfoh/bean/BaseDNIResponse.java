package pe.gob.midis.sisfoh.bean;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.midis.sisfoh.action.IndexAction;
import pe.gob.midis.sisfoh.exception.RQSException;
import pe.gob.midis.sisfoh.exception.RQSInvalidErrorTypeException;
import pe.gob.midis.sisfoh.type.ErrorType;
import pe.gob.midis.sisfoh.utils.DateUtil;
import pe.gob.midis.sisfoh.utils.GeneralEntitiesHelper;
import pe.gob.midis.sisfoh.utils.StringUtil;

public class BaseDNIResponse {

	private static final Logger LOG = LoggerFactory.getLogger(IndexAction.class);

	// Default base DNI response values 
	static int __ADULT_DNI_RESPONSE_LENGTH   = 991;
	static int __GENERAL_ERROR_              = -1;

	// Base DNI response fields length
	final static int __BYTES_CODIGO_ERROR				=  4;
	final static int __BYTES_NUMERO_DNI 				=  8;
	final static int __BYTES_DIGITO_VERIFICACION		=  1;
	final static int __BYTES_APELLIDO_PADRE				= 40;
	final static int __BYTES_APELLIDO_MATERNO 			= 40;
	final static int __BYTES_APELLIDO_CASADA			= 40;
	final static int __BYTES_NOMBRES					= 60;
	final static int __BYTES_CODIGO_DEPA_DOMICILIO		=  2;
	final static int __BYTES_CODIGO_PROV_DOMICILIO		=  2;
	final static int __BYTES_CODIGO_DIST_DOMICILIO		=  2;
	final static int __BYTES_NOMBRE_DEPA_DOMICILIO		= 40;
	final static int __BYTES_NOMBRE_PROV_DOMICILIO		= 40;
	final static int __BYTES_NOMBRE_DIST_DOMICILIO		= 40;
	final static int __BYTES_ESTADO_CIVIL				=  1;
	final static int __BYTES_CODIGO_EDUCACION			=  2;
	final static int __BYTES_ESTATURA			 		=  3;
	final static int __BYTES_SEXO						=  1;
	final static int __BYTES_CODIGO_DEPA_NACIMIENTO		=  2;
	final static int __BYTES_CODIGO_PROV_NACIMIENTO		=  2;
	final static int __BYTES_CODIGO_DIST_NACIMIENTO		=  2;
	final static int __BYTES_NOMBRE_DEPA_NACIMIENTO		= 40;
	final static int __BYTES_NOMBRE_PROV_NACIMIENTO		= 40;
	final static int __BYTES_NOMBRE_DIST_NACIMIENTO		= 40;
	final static int __BYTES_FECHA_NACIMIENTO			=  8;
	final static int __BYTES_NUMERO_DNI_PADRE			= 20;
	final static int __BYTES_APELLIDO_PATERNO_PADRE		= 40;
	final static int __BYTES_APELLIDO_MATERNO_PADRE		= 40;
	final static int __BYTES_NOMBRE_PADRE				= 60;
	final static int __BYTES_NUMERO_DNI_MADRE			= 20;
	final static int __BYTES_APELLIDO_PATERNO_MADRE		= 40;
	final static int __BYTES_APELLIDO_MATERNO_MADRE		= 40;
	final static int __BYTES_APELLIDO_CASADA_MADRE		= 40;
	final static int __BYTES_NOMBRES_MADRE				= 60;
	final static int __BYTES_FECHA_REGISTRO				=  8;
	final static int __BYTES_FECHA_EXPEDICION 			=  8;
	final static int __BYTES_FECHA_EXPIRACION			=  8;
	final static int __BYTES_RESTRICCION				=  2;
	final static int __BYTES_PREFIJO_DIRECCION			=  2;
	final static int __BYTES_DIRECCION					= 60;
	final static int __BYTES_NUMERO_DIRECCION			=  4;
	final static int __BYTES_BLOQUE_O_CHALET			=  3;
	final static int __BYTES_INTERIOR					=  8;
	final static int __BYTES_URBANIZACION				= 30;
	final static int __BYTES_ETAPA						=  4;
	final static int __BYTES_MANZANA					=  4;
	final static int __BYTES_LOTE						=  4;
	final static int __BYTES_PREFIJO_BLOQUE_O_CHALET	=  2;
	final static int __BYTES_PREFIJO_DPTO_PISO_INTERIOR	=  2;
	final static int __BYTES_PREFIJO_URB_CONDOM_RESID	=  2;
	final static int __BYTES_RESERVADO					= 20;
	final static int __BYTES_LONGITUD_FOTO				=  9;
	
	// Base DNI response fields 
	private int    codigoError; 				// Código de error
	private String numeroDNI; 					// Número de DNI
	private int    digitoVerificacion;			// D�gito de verificaci�n
	private String apellidoPaterno;  			// Apellido Paterno
	private String apellidoMaterno; 			// Apellido Materno
	private String apellidoCasada; 				// Apellido Casada
	private String nombres; 					// Nombres
	private String codigoUbigeoDomicilio;		// Código de UBIGEO domicilio
	private String codigoDepaDomicilio;			// Código de UBIGEO departamento domicilio
	private String codigoProvDomicilio;			// Código de UBIGEO provincia domicilio
	private String codigoDistDomicilio;			// Código de UBIGEO distrito domicilio
	private String localidadDomicilio;			// Localidad domicilio
	private String nombreDepaDomicilio;			// Departamento domicilio
	private String nombreProvDomicilio;			// Provincia domicilio
	private String nombreDistDomicilio;			// Distrito domicilio
	private int    codigoEstadoCivil;			// Código estado civil
	private String estadoCivil;					// Estado civil
	private String codigoNivelEducacion;		// C�digo de Grado de instrucci�n
	private String nivelEducacion;				// Grado de instrucción
	private int    estatura;					// Estatura
	private int    codigoSexo;					// Código sexo
	private String sexo;						// Sexo
	private String codigoUbigeoNacimiento;		// Código de ubigeo nacimiento
	private String codigoDepaNacimiento;		// Código de ubigeo departamento nacimiento
	private String codigoProvNacimiento;		// Código de ubigeo provincia nacimiento
	private String codigoDistNacimiento;		// Código de ubigeo distrito nacimiento
	private String localidadNacimiento;			// Localidadnacimiento
	private String nombreDepaNacimiento;		// Departamento nacimiento
	private String nombreProvNacimiento;		// Provincia nacimiento
	private String nombreDistNacimiento;		// Distrito nacimiento
	private Date   fechaNacimiento;				// Fecha de nacimiento
	private String numeroDNIPadre;				// Documento del Padre
	private String apellidoPaternoPadre;		// Apellido Paterno Padre
	private String apellidoMaternoPadre;		// Apellido Materno Padre
	private String nombresPadre;				// Nombre del padre
	private String numeroDNIMadre;				// Documento de la Madre
	private String apellidoPaternoMadre;		// Apellido Paterno Madre
	private String apellidoMaternoMadre;		// Apellido Materno Madre
	private String apellidoCasadaMadre;			// Apellido Casada Madre
	private String nombresMadre;				// Nombre de la madre
	private Date   fechaRegistro;				// Fecha de inscripci�n
	private Date   fechaExpedicion; 			// Fecha de expedici�n
	private Date   fechaExpiracion;				// Fecha de caducidad
	private String codigoRestriccion;			// Código de Restricción
	private String restriccion;					// Restricci�n
	private String prefijoDireccion;			// Prefijo dirección
	private String direccion;					// Direcci�n
	private String numeroDireccion;				// N�mero direcci�n
	private String bloqueOChalet;				// Block o chalet
	private String interior;					// Interior
	private String urbanizacion;				// Urbanización
	private String etapa;						// Etapa
	private String manzana;						// Manzana
	private String lote;						// Lote
	private String prefijoBloqueChalet;			// Prefijo de block o chalet
	private String prefijoDptoPisoInterior;		// Prefijo de dpto piso interior
	private String prefijoUrbCondomResid;		// Prefijo de urb cond resid
	private String reservado;					// Reservado
	private String direccionCompleta;			// Dirección completa
	private String fotografiaBase64;			// Fotografía en formato Base64
	private int    longitudFoto;				// Longitud de la foto
	
	public BaseDNIResponse() {
		this.codigoError = 0;
		this.numeroDNI = "";
		this.digitoVerificacion = 0;
		this.apellidoPaterno = "";
		this.apellidoMaterno = "";
		this.apellidoCasada = "";
		this.nombres = "";
		this.codigoUbigeoDomicilio = "";
		this.codigoDepaDomicilio = "";
		this.codigoProvDomicilio = "";
		this.codigoDistDomicilio = "";
		this.nombreDepaDomicilio = "";
		this.nombreProvDomicilio = "";
		this.nombreDistDomicilio = "";
		this.codigoEstadoCivil = 0;
		this.estadoCivil = "";
		this.codigoNivelEducacion = "";
		this.nivelEducacion = "";
		this.estatura = 0;
		this.codigoSexo = 0;
		this.sexo = "";
		this.codigoUbigeoNacimiento = "";
		this.codigoDepaNacimiento = "";
		this.codigoProvNacimiento = "";
		this.codigoDistNacimiento = "";
		this.nombreDepaNacimiento = "";
		this.nombreProvNacimiento = "";
		this.nombreDistNacimiento = "";
		this.fechaNacimiento = null;
		this.numeroDNIPadre = "";
		this.apellidoPaternoPadre = "";
		this.apellidoMaternoPadre = "";
		this.nombresPadre = "";
		this.numeroDNIMadre = "";
		this.apellidoPaternoMadre = "";
		this.apellidoMaternoMadre = "";
		this.apellidoCasadaMadre = "";
		this.nombresMadre = "";
		this.fechaRegistro = null;
		this.fechaExpedicion = null;
		this.fechaExpiracion = null;
		this.codigoRestriccion = "";
		this.restriccion = "";
		this.prefijoDireccion = "";
		this.direccion = "";
		this.numeroDireccion = "";
		this.bloqueOChalet = "";
		this.interior = "";
		this.urbanizacion = "";
		this.etapa = "";
		this.manzana = "";
		this.lote = "";
		this.prefijoBloqueChalet = "";
		this.prefijoDptoPisoInterior = "";
		this.prefijoUrbCondomResid = "";
		this.reservado = "";
		this.direccionCompleta = "";
		this.longitudFoto = 0;
		
	}

	public BaseDNIResponse(String response, String subqueryType) 
			throws RQSException, RQSInvalidErrorTypeException  {
		BaseDNIResponse.valueOf(response, this, subqueryType);

	}

	public int getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(int codigoError) {
		this.codigoError = codigoError;
	}

	public String getNumeroDNI() {
		return numeroDNI;
	}

	public void setNumeroDNI(String numeroDNI) {
		this.numeroDNI = numeroDNI;
	}

	public int getDigitoVerificacion() {
		return digitoVerificacion;
	}

	public void setDigitoVerificacion(int digitoVerificacion) {
		this.digitoVerificacion = digitoVerificacion;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoCasada() {
		return apellidoCasada;
	}

	public void setApellidoCasada(String apellidoCasada) {
		this.apellidoCasada = apellidoCasada;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getCodigoUbigeoDomicilio() {
		return codigoUbigeoDomicilio;
	}

	public void setCodigoUbigeoDomicilio(String codigoUbigeoDomicilio) {
		this.codigoUbigeoDomicilio = codigoUbigeoDomicilio;
	}

	private String getCodigoDepaDomicilio() {
		return codigoDepaDomicilio;
	}

	private void setCodigoDepaDomicilio(String codigoDepaDomicilio) {
		this.codigoDepaDomicilio = codigoDepaDomicilio;
	}

	private String getCodigoProvDomicilio() {
		return codigoProvDomicilio;
	}

	private void setCodigoProvDomicilio(String codigoProvDomicilio) {
		this.codigoProvDomicilio = codigoProvDomicilio;
	}

	private String getCodigoDistDomicilio() {
		return codigoDistDomicilio;
	}

	private void setCodigoDistDomicilio(String codigoDistDomicilio) {
		this.codigoDistDomicilio = codigoDistDomicilio;
	}

	public String getLocalidadDomicilio() {
		return localidadDomicilio;
	}

	public void setLocalidadDomicilio(String localidadDomicilio) {
		this.localidadDomicilio = localidadDomicilio;
	}

	public String getNombreDepaDomicilio() {
		return nombreDepaDomicilio;
	}

	public void setNombreDepaDomicilio(String nombreDepaDomicilio) {
		this.nombreDepaDomicilio = nombreDepaDomicilio;
	}

	public String getNombreProvDomicilio() {
		return nombreProvDomicilio;
	}

	public void setNombreProvDomicilio(String nombreProvDomicilio) {
		this.nombreProvDomicilio = nombreProvDomicilio;
	}

	public String getNombreDistDomicilio() {
		return nombreDistDomicilio;
	}

	public void setNombreDistDomicilio(String nombreDistDomicilio) {
		this.nombreDistDomicilio = nombreDistDomicilio;
	}

	private int getCodigoEstadoCivil() {
		return codigoEstadoCivil;
	}

	private void setCodigoEstadoCivil(int codigoEstadoCivil) {
		this.codigoEstadoCivil = codigoEstadoCivil;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	private String getCodigoNivelEducacion() {
		return codigoNivelEducacion;
	}

	private void setCodigoNivelEducacion(String codigoNivelEducacion) {
		
		try {
			
			codigoNivelEducacion = codigoNivelEducacion.trim();
			codigoNivelEducacion = String.format("%02d", Integer.valueOf(codigoNivelEducacion));
			
		} catch (Exception e) {
			
			codigoNivelEducacion = "";
		}
		
		this.codigoNivelEducacion = codigoNivelEducacion;
	}

	public String getNivelEducacion() {
		return nivelEducacion;
	}

	public void setNivelEducacion(String nivelEducacion) {
		this.nivelEducacion = nivelEducacion;
	}

	public int getEstatura() {
		return estatura;
	}

	public void setEstatura(int estatura) {
		this.estatura = estatura;
	}

	private int getCodigoSexo() {
		return codigoSexo;
	}

	private void setCodigoSexo(int codigoSexo) {
		this.codigoSexo = codigoSexo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCodigoUbigeoNacimiento() {
		return codigoUbigeoNacimiento;
	}

	public void setCodigoUbigeoNacimiento(String codigoUbigeoNacimiento) {
		this.codigoUbigeoNacimiento = codigoUbigeoNacimiento;
	}

	private String getCodigoDepaNacimiento() {
		return codigoDepaNacimiento;
	}

	private void setCodigoDepaNacimiento(String codigoDepaNacimiento) {
		this.codigoDepaNacimiento = codigoDepaNacimiento;
	}

	private String getCodigoProvNacimiento() {
		return codigoProvNacimiento;
	}

	private void setCodigoProvNacimiento(String codigoProvNacimiento) {
		this.codigoProvNacimiento = codigoProvNacimiento;
	}

	private String getCodigoDistNacimiento() {
		return codigoDistNacimiento;
	}

	private void setCodigoDistNacimiento(String codigoDistNacimiento) {
		this.codigoDistNacimiento = codigoDistNacimiento;
	}

	public String getLocalidadNacimiento() {
		return localidadNacimiento;
	}

	public void setLocalidadNacimiento(String localidadNacimiento) {
		this.localidadNacimiento = localidadNacimiento;
	}

	public String getNombreDepaNacimiento() {
		return nombreDepaNacimiento;
	}

	public void setNombreDepaNacimiento(String nombreDepaNacimiento) {
		this.nombreDepaNacimiento = nombreDepaNacimiento;
	}

	public String getNombreProvNacimiento() {
		return nombreProvNacimiento;
	}

	public void setNombreProvNacimiento(String nombreProvNacimiento) {
		this.nombreProvNacimiento = nombreProvNacimiento;
	}

	public String getNombreDistNacimiento() {
		return nombreDistNacimiento;
	}

	public void setNombreDistNacimiento(String nombreDistNacimiento) {
		this.nombreDistNacimiento = nombreDistNacimiento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNumeroDNIPadre() {
		return numeroDNIPadre;
	}

	public void setNumeroDNIPadre(String numeroDNIPadre) {
		this.numeroDNIPadre = numeroDNIPadre;
	}

	public String getApellidoPaternoPadre() {
		return apellidoPaternoPadre;
	}

	public void setApellidoPaternoPadre(String apellidoPaternoPadre) {
		this.apellidoPaternoPadre = apellidoPaternoPadre;
	}

	public String getApellidoMaternoPadre() {
		return apellidoMaternoPadre;
	}

	public void setApellidoMaternoPadre(String apellidoMaternoPadre) {
		this.apellidoMaternoPadre = apellidoMaternoPadre;
	}

	public String getNombresPadre() {
		return nombresPadre;
	}

	public void setNombresPadre(String nombresPadre) {
		this.nombresPadre = nombresPadre;
	}

	public String getNumeroDNIMadre() {
		return numeroDNIMadre;
	}

	public void setNumeroDNIMadre(String numeroDNIMadre) {
		this.numeroDNIMadre = numeroDNIMadre;
	}

	public String getApellidoPaternoMadre() {
		return apellidoPaternoMadre;
	}

	public void setApellidoPaternoMadre(String apellidoPaternoMadre) {
		this.apellidoPaternoMadre = apellidoPaternoMadre;
	}

	public String getApellidoMaternoMadre() {
		return apellidoMaternoMadre;
	}

	public void setApellidoMaternoMadre(String apellidoMaternoMadre) {
		this.apellidoMaternoMadre = apellidoMaternoMadre;
	}

	public String getApellidoCasadaMadre() {
		return apellidoCasadaMadre;
	}

	public void setApellidoCasadaMadre(String apellidoCasadaMadre) {
		this.apellidoCasadaMadre = apellidoCasadaMadre;
	}

	public String getNombresMadre() {
		return nombresMadre;
	}

	public void setNombresMadre(String nombresMadre) {
		this.nombresMadre = nombresMadre;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	private String getCodigoRestriccion() {
		return codigoRestriccion;
	}

	private void setCodigoRestriccion(String codigoRestriccion) {
		this.codigoRestriccion = codigoRestriccion;
	}

	public String getRestriccion() {
		return restriccion;
	}

	public void setRestriccion(String restriccion) {
		this.restriccion = restriccion;
	}

	private String getPrefijoDireccion() {
		return prefijoDireccion;
	}

	private void setPrefijoDireccion(String prefijoDireccion) {
		this.prefijoDireccion = prefijoDireccion;
	}

	private String getDireccion() {
		return direccion;
	}

	private void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	private String getNumeroDireccion() {
		return numeroDireccion;
	}

	private void setNumeroDireccion(String numeroDireccion) {
		this.numeroDireccion = numeroDireccion;
	}

	private String getBloqueOChalet() {
		return bloqueOChalet;
	}

	private void setBloqueOChalet(String bloqueOChalet) {
		this.bloqueOChalet = bloqueOChalet;
	}

	private String getInterior() {
		return interior;
	}

	private void setInterior(String interior) {
		this.interior = interior;
	}

	private String getUrbanizacion() {
		return urbanizacion;
	}

	private void setUrbanizacion(String urbanizacion) {
		this.urbanizacion = urbanizacion;
	}

	private String getEtapa() {
		return etapa;
	}

	private void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	private String getManzana() {
		return manzana;
	}

	private void setManzana(String manzana) {
		this.manzana = manzana;
	}

	private String getLote() {
		return lote;
	}

	private void setLote(String lote) {
		this.lote = lote;
	}

	private String getPrefijoBloqueChalet() {
		return prefijoBloqueChalet;
	}

	private void setPrefijoBloqueChalet(String prefijoBloqueChalet) {
		this.prefijoBloqueChalet = prefijoBloqueChalet;
	}

	private String getPrefijoDptoPisoInterior() {
		return prefijoDptoPisoInterior;
	}

	private void setPrefijoDptoPisoInterior(String prefijoDptoPisoInterior) {
		this.prefijoDptoPisoInterior = prefijoDptoPisoInterior;
	}

	private String getPrefijoUrbCondomResid() {
		return prefijoUrbCondomResid;
	}

	private void setPrefijoUrbCondomResid(String prefijoUrbCondomResid) {
		this.prefijoUrbCondomResid = prefijoUrbCondomResid;
	}

	public String getReservado() {
		return reservado;
	}

	public void setReservado(String reservado) {
		this.reservado = reservado;
	}

	public String getDireccionCompleta() {
		return direccionCompleta;
	}

	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}

	public String getFotografiaBase64() {
		return fotografiaBase64;
	}

	public void setFotografiaBase64(String fotografiaBase64) {
		this.fotografiaBase64 = fotografiaBase64;
	}

	public int getLongitudFoto() {
		return longitudFoto;
	}

	public void setLongitudFoto(int longitudFoto) {
		this.longitudFoto = longitudFoto;
	}

	static public int valueOf (String response, BaseDNIResponse responseBody, String subqueryType) 
			throws RQSException, RQSInvalidErrorTypeException {
		
		int index = Header.__HEADER_LENGTH;
		
		responseBody.setCodigoError(Integer.parseInt(response.substring(index, index + BaseDNIResponse.__BYTES_CODIGO_ERROR)));
		index += BaseDNIResponse.__BYTES_CODIGO_ERROR;
		
		if ( ErrorType.fromValue(responseBody.getCodigoError()) != ErrorType.__OK_SUCCESSFUL)
			throw new RQSException(responseBody.getCodigoError()); 

		// if the data is present, parser it
		if (subqueryType.contains("1"))
		{
			responseBody.setNumeroDNI(response.substring(index, index + BaseDNIResponse.__BYTES_NUMERO_DNI));
			index += BaseDNIResponse.__BYTES_NUMERO_DNI;
			
			responseBody.setDigitoVerificacion(Integer.parseInt(response.substring(index, index + BaseDNIResponse.__BYTES_DIGITO_VERIFICACION)));
			index += BaseDNIResponse.__BYTES_DIGITO_VERIFICACION;
			
			responseBody.setApellidoPaterno(response.substring(index, index + BaseDNIResponse.__BYTES_APELLIDO_PADRE).trim());
			index += BaseDNIResponse.__BYTES_APELLIDO_PADRE;
			
			responseBody.setApellidoMaterno(response.substring(index, index + BaseDNIResponse.__BYTES_APELLIDO_MATERNO).trim());
			index += BaseDNIResponse.__BYTES_APELLIDO_MATERNO;
			
			responseBody.setApellidoCasada(response.substring(index, index + BaseDNIResponse.__BYTES_APELLIDO_CASADA).trim());
			index += BaseDNIResponse.__BYTES_APELLIDO_CASADA;
			
			responseBody.setNombres(response.substring(index, index + BaseDNIResponse.__BYTES_NOMBRES).trim());
			index += BaseDNIResponse.__BYTES_NOMBRES;
			
			responseBody.setCodigoDepaDomicilio(response.substring(index, index + BaseDNIResponse.__BYTES_CODIGO_DEPA_DOMICILIO));
			index += BaseDNIResponse.__BYTES_CODIGO_DEPA_DOMICILIO;
			
			responseBody.setCodigoProvDomicilio(response.substring(index, index + BaseDNIResponse.__BYTES_CODIGO_PROV_DOMICILIO));
			index += BaseDNIResponse.__BYTES_CODIGO_PROV_DOMICILIO;
			
			responseBody.setCodigoDistDomicilio(response.substring(index, index + BaseDNIResponse.__BYTES_CODIGO_DIST_DOMICILIO));
			index += BaseDNIResponse.__BYTES_CODIGO_DIST_DOMICILIO;

			responseBody.setCodigoUbigeoDomicilio(responseBody.getAddressPlaceCode());

			responseBody.setNombreDepaDomicilio(response.substring(index, index + BaseDNIResponse.__BYTES_NOMBRE_DEPA_DOMICILIO).trim());
			index += BaseDNIResponse.__BYTES_NOMBRE_DEPA_DOMICILIO;
			
			responseBody.setNombreProvDomicilio(response.substring(index, index + BaseDNIResponse.__BYTES_NOMBRE_PROV_DOMICILIO).trim());
			index += BaseDNIResponse.__BYTES_NOMBRE_PROV_DOMICILIO;
			
			responseBody.setNombreDistDomicilio(response.substring(index, index + BaseDNIResponse.__BYTES_NOMBRE_DIST_DOMICILIO).trim());
			index += BaseDNIResponse.__BYTES_NOMBRE_DIST_DOMICILIO;
			
			responseBody.setLocalidadDomicilio(responseBody.getAddressLocation());
			
			try {
				responseBody.setCodigoEstadoCivil(Integer.parseInt(response.substring(index, index
								+ BaseDNIResponse.__BYTES_ESTADO_CIVIL)));
			} catch (NumberFormatException e) {

				responseBody.setCodigoEstadoCivil(1);
			}
			
			responseBody.setEstadoCivil(GeneralEntitiesHelper.getMaritalStatusPrefixes().get(String.format("%02d", responseBody.getCodigoEstadoCivil())));
			index += BaseDNIResponse.__BYTES_ESTADO_CIVIL;
			
			responseBody.setCodigoNivelEducacion(response.substring(index, index + BaseDNIResponse.__BYTES_CODIGO_EDUCACION));
			responseBody.setNivelEducacion(GeneralEntitiesHelper.getInstructionLevelPrefixes().get(responseBody.getCodigoNivelEducacion()));
			index += BaseDNIResponse.__BYTES_CODIGO_EDUCACION;

			try {
				responseBody.setEstatura(Integer.parseInt(response.substring(index, index + BaseDNIResponse.__BYTES_ESTATURA)));
				
			} catch (NumberFormatException e) {
			
				responseBody.setEstatura(0);
			}
			
			index += BaseDNIResponse.__BYTES_ESTATURA;
			
			responseBody.setCodigoSexo(Integer.parseInt(response.substring(index, index + BaseDNIResponse.__BYTES_SEXO)));
			responseBody.setSexo(GeneralEntitiesHelper.getSexPrefixes().get(String.format("%02d", responseBody.getCodigoSexo())));
			index += BaseDNIResponse.__BYTES_SEXO;
			
			responseBody.setCodigoDepaNacimiento(response.substring(index, index + BaseDNIResponse.__BYTES_CODIGO_DEPA_NACIMIENTO));
			index += BaseDNIResponse.__BYTES_CODIGO_DEPA_NACIMIENTO;
			
			responseBody.setCodigoProvNacimiento(response.substring(index, index + BaseDNIResponse.__BYTES_CODIGO_DIST_NACIMIENTO));
			index += BaseDNIResponse.__BYTES_CODIGO_DIST_NACIMIENTO;

			responseBody.setCodigoDistNacimiento(response.substring(index, index + BaseDNIResponse.__BYTES_CODIGO_PROV_NACIMIENTO));
			index += BaseDNIResponse.__BYTES_CODIGO_PROV_NACIMIENTO;
			
			responseBody.setCodigoUbigeoNacimiento(responseBody.getBirthPlaceCode());
			
			responseBody.setNombreDepaNacimiento(response.substring(index, index + BaseDNIResponse.__BYTES_NOMBRE_DEPA_NACIMIENTO).trim());
			index += BaseDNIResponse.__BYTES_NOMBRE_DEPA_NACIMIENTO;
			
			responseBody.setNombreProvNacimiento(response.substring(index, index + BaseDNIResponse.__BYTES_NOMBRE_PROV_NACIMIENTO).trim());
			index += BaseDNIResponse.__BYTES_NOMBRE_PROV_NACIMIENTO;
			
			responseBody.setNombreDistNacimiento(response.substring(index, index + BaseDNIResponse.__BYTES_NOMBRE_DIST_NACIMIENTO).trim());
			index += BaseDNIResponse.__BYTES_NOMBRE_DIST_NACIMIENTO;
			
			responseBody.setLocalidadNacimiento(responseBody.getBirthLocation());
			
			responseBody.setFechaNacimiento(DateUtil.toDate("yyyyMMdd", response.substring(index, index + BaseDNIResponse.__BYTES_FECHA_NACIMIENTO)));
			index += BaseDNIResponse.__BYTES_FECHA_NACIMIENTO;
			
			responseBody.setNumeroDNIPadre(response.substring(index, index + BaseDNIResponse.__BYTES_NUMERO_DNI_PADRE).trim());
			index += BaseDNIResponse.__BYTES_NUMERO_DNI_PADRE;
			
			responseBody.setApellidoPaternoPadre(response.substring(index, index + BaseDNIResponse.__BYTES_APELLIDO_PATERNO_PADRE).trim());
			index += BaseDNIResponse.__BYTES_APELLIDO_PATERNO_PADRE;
			
			responseBody.setApellidoMaternoPadre(response.substring(index, index + BaseDNIResponse.__BYTES_APELLIDO_MATERNO_PADRE).trim());
			index += BaseDNIResponse.__BYTES_APELLIDO_MATERNO_PADRE;
			
			responseBody.setNombresPadre(response.substring(index, index + BaseDNIResponse.__BYTES_NOMBRE_PADRE).trim());
			index += BaseDNIResponse.__BYTES_NOMBRE_PADRE;
			
			responseBody.setNumeroDNIMadre(response.substring(index, index + BaseDNIResponse.__BYTES_NUMERO_DNI_MADRE).trim());
			index += BaseDNIResponse.__BYTES_NUMERO_DNI_MADRE;
			
			responseBody.setApellidoPaternoMadre(response.substring(index, index + BaseDNIResponse.__BYTES_APELLIDO_PATERNO_MADRE).trim());
			index += BaseDNIResponse.__BYTES_APELLIDO_PATERNO_MADRE;
			
			responseBody.setApellidoMaternoMadre(response.substring(index, index + BaseDNIResponse.__BYTES_APELLIDO_MATERNO_MADRE).trim());
			index += BaseDNIResponse.__BYTES_APELLIDO_MATERNO_MADRE;
			
			responseBody.setApellidoCasadaMadre(response.substring(index, index + BaseDNIResponse.__BYTES_APELLIDO_CASADA_MADRE).trim());
			index += BaseDNIResponse.__BYTES_APELLIDO_CASADA_MADRE;
			
			responseBody.setNombresMadre(response.substring(index, index + BaseDNIResponse.__BYTES_NOMBRES_MADRE).trim());
			index += BaseDNIResponse.__BYTES_NOMBRES_MADRE    ;
			
			responseBody.setFechaRegistro(DateUtil.toDate("yyyyMMdd", response.substring(index, index + BaseDNIResponse.__BYTES_FECHA_REGISTRO)));
			index += BaseDNIResponse.__BYTES_FECHA_REGISTRO    ;
			
			responseBody.setFechaExpedicion(DateUtil.toDate("yyyyMMdd", response.substring(index, index + BaseDNIResponse.__BYTES_FECHA_EXPEDICION)));
			index += BaseDNIResponse.__BYTES_FECHA_EXPEDICION;
			
			responseBody.setFechaExpiracion(DateUtil.toDate("yyyyMMdd", response.substring(index, index + BaseDNIResponse.__BYTES_FECHA_EXPIRACION)));
			index += BaseDNIResponse.__BYTES_FECHA_EXPIRACION;
			
			responseBody.setCodigoRestriccion(response.substring(index, index + BaseDNIResponse.__BYTES_RESTRICCION).trim());
			index += BaseDNIResponse.__BYTES_RESTRICCION;
			
			responseBody.setRestriccion(GeneralEntitiesHelper.getRestrictionPrefixes().get(responseBody.getCodigoRestriccion()));
			
			responseBody.setPrefijoDireccion(response.substring(index,index +  BaseDNIResponse.__BYTES_PREFIJO_DIRECCION).trim());
			index += BaseDNIResponse.__BYTES_PREFIJO_DIRECCION;
			
			responseBody.setDireccion(response.substring(index, index + BaseDNIResponse.__BYTES_DIRECCION).trim());
			index += BaseDNIResponse.__BYTES_DIRECCION;
			
			responseBody.setNumeroDireccion(response.substring(index, index + BaseDNIResponse.__BYTES_NUMERO_DIRECCION ).trim());
			index += BaseDNIResponse.__BYTES_NUMERO_DIRECCION;
			
			responseBody.setBloqueOChalet(response.substring(index, index + BaseDNIResponse.__BYTES_BLOQUE_O_CHALET).trim());
			index += BaseDNIResponse.__BYTES_BLOQUE_O_CHALET;
			
			responseBody.setInterior(response.substring(index, index + BaseDNIResponse.__BYTES_INTERIOR).trim());
			index += BaseDNIResponse.__BYTES_INTERIOR;
			
			responseBody.setUrbanizacion(response.substring(index, index + BaseDNIResponse.__BYTES_URBANIZACION).trim());
			index += BaseDNIResponse.__BYTES_URBANIZACION;
			
			responseBody.setEtapa(response.substring(index, index + BaseDNIResponse.__BYTES_ETAPA).trim());
			index += BaseDNIResponse.__BYTES_ETAPA;
			
			responseBody.setManzana(response.substring(index, index + BaseDNIResponse.__BYTES_MANZANA).trim());
			index += BaseDNIResponse.__BYTES_MANZANA ;
			
			responseBody.setLote(response.substring(index, index + BaseDNIResponse.__BYTES_LOTE).trim());
			index += BaseDNIResponse.__BYTES_LOTE;
			
			responseBody.setPrefijoBloqueChalet(response.substring(index, index + BaseDNIResponse.__BYTES_PREFIJO_BLOQUE_O_CHALET).trim());
			index += BaseDNIResponse.__BYTES_PREFIJO_BLOQUE_O_CHALET;
			
			responseBody.setPrefijoDptoPisoInterior(response.substring(index, index + BaseDNIResponse.__BYTES_PREFIJO_DPTO_PISO_INTERIOR).trim());
			index += BaseDNIResponse.__BYTES_PREFIJO_DPTO_PISO_INTERIOR;
			
			responseBody.setPrefijoUrbCondomResid(response.substring(index, index + BaseDNIResponse.__BYTES_PREFIJO_URB_CONDOM_RESID).trim());
			index += BaseDNIResponse.__BYTES_PREFIJO_URB_CONDOM_RESID;
			
			responseBody.setReservado(response.substring(index, index + BaseDNIResponse.__BYTES_RESERVADO).trim());
			index += BaseDNIResponse.__BYTES_RESERVADO;
			
			responseBody.setDireccionCompleta();
			
		}
		else
		// if the data is not present, parser DNI
		{
			responseBody.setNumeroDNI(response.substring(index, index + BaseDNIResponse.__BYTES_NUMERO_DNI));
			index += BaseDNIResponse.__BYTES_NUMERO_DNI;
			
		}
		
		
		return index;
	}
		
	public String toString() {
		String result = 
				"CodigoError              : [" + getCodigoError()             + "]\n" +
				"NumeroDNI                : [" + getNumeroDNI()               + "]\n" +
				"DigitoVerificacion       : [" + getDigitoVerificacion()      + "]\n" +
				"ApellidoPaterno          : [" + getApellidoPaterno()         + "]\n" +
				"ApellidoMaterno          : [" + getApellidoMaterno()         + "]\n" +
				"ApellidoCasada           : [" + getApellidoCasada()          + "]\n" +
				"Nombres                  : [" + getNombres()                 + "]\n" +
				"CodigoUbigeoDomicilio    : [" + getAddressPlaceCode()        + "]\n" +
//				"CodigoDepaDomicilio      : [" + getCodigoDepaDomicilio()     + "]\n" +
//				"CodigoProvDomicilio      : [" + getCodigoProvDomicilio()     + "]\n" +
//				"CodigoDistDomicilio      : [" + getCodigoDistDomicilio()     + "]\n" +
				"NombreDepaDomicilio      : [" + getNombreDepaDomicilio()     + "]\n" +
				"NombreProvDomicilio      : [" + getNombreProvDomicilio()     + "]\n" +
				"NombreDistDomicilio      : [" + getNombreDistDomicilio()     + "]\n" +
				"EstadoCivil              : [" + getEstadoCivil()             + "]\n" +
				"CodigoNivelEducacion     : [" + getCodigoNivelEducacion()    + "]\n" +
				"Estatura                 : [" + getEstatura()                + "]\n" +
				"Sexo                     : [" + getSexo()                    + "]\n" +
				"CodigoUbigeoNacimiento   : [" + getBirthPlaceCode()          + "]\n" +
//				"CodigoDepaNacimiento     : [" + getCodigoDepaNacimiento()    + "]\n" +
//				"CodigoProvNacimiento     : [" + getCodigoProvNacimiento()    + "]\n" +
//				"CodigoDistNacimiento     : [" + getCodigoDistNacimiento()    + "]\n" +
				"NombreDepaNacimiento     : [" + getNombreDepaNacimiento()    + "]\n" +
				"NombreProvNacimiento     : [" + getNombreProvNacimiento()    + "]\n" +
				"NombreDistNacimiento     : [" + getNombreDistNacimiento()    + "]\n" +
				"FechaNacimiento          : [" + getFechaNacimiento()         + "]\n" +
				"NumeroDNIPadre           : [" + getNumeroDNIPadre()          + "]\n" +
				"ApellidoPaternoPadre     : [" + getApellidoPaternoPadre()    + "]\n" +
				"ApellidoMaternoPadre     : [" + getApellidoMaternoPadre()    + "]\n" +
				"NombresPadre             : [" + getNombresPadre()            + "]\n" +
				"NumeroDNIMadre           : [" + getNumeroDNIMadre()          + "]\n" +
				"ApellidoPaternoMadre     : [" + getApellidoPaternoMadre()    + "]\n" +
				"ApellidoMaternoMadre     : [" + getApellidoMaternoMadre()    + "]\n" +
				"ApellidoCasadaMadre      : [" + getApellidoCasadaMadre()     + "]\n" +
				"NombresMadre             : [" + getNombresMadre()            + "]\n" +
				"FechaRegistro            : [" + getFechaRegistro()           + "]\n" +
				"FechaExpedicion          : [" + getFechaExpedicion()         + "]\n" +
				"FechaExpiracion          : [" + getFechaExpiracion()         + "]\n" +
				"Restriccion              : [" + getRestriccion()             + "]\n" +
				"Direccion Completa       : [" + getDireccionCompleta()       + "]\n" +
//				"PrefijoDireccion         : [" + getPrefijoDireccion()        + "]\n" +
//				"Direccion                : [" + getDireccion()               + "]\n" +
//				"NumeroDireccion          : [" + getNumeroDireccion()         + "]\n" +
//				"BloqueOChalet            : [" + getBloqueOChalet()           + "]\n" +
//				"Interior                 : [" + getInterior()                + "]\n" +
//				"Urbanizacion             : [" + getUrbanizacion()            + "]\n" +
//				"Etapa                    : [" + getEtapa()                   + "]\n" +
//				"Manzana                  : [" + getManzana()                 + "]\n" +
//				"Lote                     : [" + getLote()                    + "]\n" +
//				"PrefijoBloqueChalet      : [" + getPrefijoBloqueChalet()     + "]\n" +
//				"PrefijoDptoPisoInterior  : [" + getPrefijoDptoPisoInterior() + "]\n" +
//				"PrefijoUrbCondomResid    : [" + getPrefijoUrbCondomResid()   + "]\n" +
				"Reservado                : [" + getReservado()               + "]\n" +
				"LongitudFoto             : [" + getLongitudFoto()            + "]\n";
		
		return result;
	}
	
	public String toStringLine() {
		String formatSpec = "[%04d][%8.8s][%1.1s][%-40.40s][%-40.40s]"
				+ "[%-40.40s][%-60.60s][%-6.6s][%-40.40s][%-40.40s]"
				+ "[%-40.40s][%1.1s][%-30.30s][%03d][%-9.9s]"
				+ "[%-6.6s][%-40.40s][%-40.40s][%-40.40s][%-10.10s]"
				+ "[%-10.10s][%-40.40s][%-40.40s][%-60.60s][%-10.10s]"
				+ "[%-40.40s][%-40.40s][%-40.40s][%-60.60s][%-10.10s]"
				+ "[%-10.10s][%-10.10s][%-50.50s][%-150.150s][%s]";
		
		String result = String.format(formatSpec, 
							getCodigoError()             ,
							getNumeroDNI()               ,
							getDigitoVerificacion()      ,
							getApellidoPaterno()         ,
							getApellidoMaterno()         ,
							getApellidoCasada()          ,
							getNombres()                 ,
							getCodigoUbigeoDomicilio()   ,
							getNombreDepaDomicilio()     ,
							getNombreProvDomicilio()     ,
							getNombreDistDomicilio()     ,
							getEstadoCivil()             ,
							getNivelEducacion()          ,
							getEstatura()                ,
							getSexo()                    ,
							getCodigoUbigeoNacimiento()  ,
							getNombreDepaNacimiento()    ,
							getNombreProvNacimiento()    ,
							getNombreDistNacimiento()    ,
							getFechaNacimiento()         ,
							getNumeroDNIPadre()          ,
							getApellidoPaternoPadre()    ,
							getApellidoMaternoPadre()    ,
							getNombresPadre()            ,
							getNumeroDNIMadre()          ,
							getApellidoPaternoMadre()    ,
							getApellidoMaternoMadre()    ,
							getApellidoCasadaMadre()     ,
							getNombresMadre()            ,
							getFechaRegistro()           ,
							getFechaExpedicion()         ,
							getFechaExpiracion()         ,
							getRestriccion()             ,
							getDireccionCompleta()       ,
							getFotografiaBase64()        
						);
		
		return result;
	}
		
	
	private String getAddressPlaceCode() {

		return this.getCodigoDepaDomicilio() 
				+ this.getCodigoProvDomicilio()
				+ this.getCodigoDistDomicilio();
	};

	private String getAddressLocation() {

		return this.getNombreDepaDomicilio() + ", "
				+ this.getNombreProvDomicilio() + ", "
				+ this.getNombreDistDomicilio();
	};

	private String getBirthPlaceCode() {

		return this.getCodigoDepaNacimiento() 
				+ this.getCodigoProvNacimiento()
				+ this.getCodigoDistNacimiento();
	};

	private String getBirthLocation() {

		return this.getNombreDepaNacimiento() + ", "
				+ this.getNombreProvNacimiento() + ", "
				+ this.getNombreDistNacimiento();
	};

	public void setDireccionCompleta() {
		
		if (!StringUtil.isNullOrEmpty(this.getPrefijoDireccion()))
			this.direccionCompleta = GeneralEntitiesHelper.getAddressPrefixes().get(this.getPrefijoDireccion()) + " ";
		
		if (!StringUtil.isNullOrEmpty(this.getDireccion()))
			this.direccionCompleta += this.getDireccion();	

		if (!StringUtil.isNullOrEmpty(this.getNumeroDireccion()))
			this.direccionCompleta += " " + this.getNumeroDireccion();
		
		if (!StringUtil.isNullOrEmpty(this.getPrefijoBloqueChalet()))
			this.direccionCompleta += GeneralEntitiesHelper.getBlockOrChaletPrefixes().get(this.getPrefijoBloqueChalet()) + " ";
		
		if (!StringUtil.isNullOrEmpty(this.getBloqueOChalet()))
			this.direccionCompleta += this.getBloqueOChalet();
		
		if (!StringUtil.isNullOrEmpty(this.getPrefijoDptoPisoInterior()))
			this.direccionCompleta += " " + GeneralEntitiesHelper.getDptoFloorInternalPrefixes().get(this.getPrefijoDptoPisoInterior()) + " ";
		
		if (!StringUtil.isNullOrEmpty(this.getInterior()))
			this.direccionCompleta += " " + this.getInterior();
		
		if (!StringUtil.isNullOrEmpty(this.getPrefijoUrbCondomResid()))
			this.direccionCompleta += " " + GeneralEntitiesHelper.getUrbCondomResidPrefixes().get(this.getPrefijoUrbCondomResid()) + " ";
		
		if (!StringUtil.isNullOrEmpty(getUrbanizacion()))
			direccionCompleta += " " + this.getUrbanizacion();
		
		if (!StringUtil.isNullOrEmpty(this.getEtapa()))
			this.direccionCompleta += " ETAPA " + this.getEtapa();
		
		if (!StringUtil.isNullOrEmpty(this.getManzana()))
			this.direccionCompleta += " MZ. " + this.getManzana();

		if (!StringUtil.isNullOrEmpty(this.getLote()))
			this.direccionCompleta += " LT. " + this.getLote();
	}
	

}