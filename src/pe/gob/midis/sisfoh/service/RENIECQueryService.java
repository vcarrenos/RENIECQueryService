// TODO: Generar log de auditoria.
// TODO: Incluir perfiles de ejecución.
// TODO: La consulta masiva debe ser registrada y programada su ejecución.
// TODO: DEfinir Excepciones para los errores


package pe.gob.midis.sisfoh.service;

import java.net.Authenticator;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.reniec.rel.SRELServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import pe.gob.midis.sisfoh.bean.AdultDNIRequest;
import pe.gob.midis.sisfoh.bean.AdultDNIResponse;
import pe.gob.midis.sisfoh.bean.ChildDNIRequest;
import pe.gob.midis.sisfoh.bean.ChildDNIResponse;
import pe.gob.midis.sisfoh.bean.NamesRequest;
import pe.gob.midis.sisfoh.bean.NamesResponse;
import pe.gob.midis.sisfoh.bean.NamesSubResponse;
import pe.gob.midis.sisfoh.connection.MQSendRecv;
import pe.gob.midis.sisfoh.exception.RQSException;
import pe.gob.midis.sisfoh.exception.RQSInvalidSubqueryTypeException;
import pe.gob.midis.sisfoh.type.ErrorType;
import pe.gob.midis.sisfoh.type.FamilyTiesType;
import pe.gob.midis.sisfoh.type.InquiryType;
import pe.gob.midis.sisfoh.type.PropertiesType;
import pe.gob.midis.sisfoh.type.SubqueryType;
import pe.gob.midis.sisfoh.utils.ErrorRENIECHelper;
import pe.gob.midis.sisfoh.utils.PropertiesHelper;
import pe.gob.midis.sisfoh.utils.ProxyAuthenticator;
import pe.gob.midis.sisfoh.utils.StringUtil;

import com.ibm.mq.MQException;

@Controller
public class RENIECQueryService implements IRENIECQueryService {
	
	private static final Logger LOG = LoggerFactory.getLogger(RENIECQueryService.class);
	public static int __SIGNATURE_JPG = 1;

	public AdultDNIResponse queryByAdultId(String usuarioInstitucionApli,
			String numeroDNI, String tipoSubConsulta)
		throws MQException, Exception {
		
		PropertiesType props = PropertiesHelper.getProperties();
		AdultDNIResponse responseDNI = null;
		AdultDNIRequest requestDNI = null;
		
		requestDNI = new AdultDNIRequest(InquiryType.__SEARCH_FOR_ADULT_ID, 
				props.getApplWebInstitutionCode(), 
				props.getApplWebInstitutionName(),
				usuarioInstitucionApli, 
				props.getApplWebInstitutionHost(), 
				numeroDNI, 
				SubqueryType.fromValue(tipoSubConsulta), 
				RENIECQueryService.__SIGNATURE_JPG);
		
		MQSendRecv mqsr = new  MQSendRecv(props);
		
		String messageIn = requestDNI.toString(),
			   messageOut = null;
		String messageID = mqsr.MQSendMessage(messageIn, messageIn.length());
		
		messageOut = mqsr.MQReceivesMessage(messageID);
		responseDNI = new AdultDNIResponse(messageOut, String.valueOf(tipoSubConsulta));

		return responseDNI;
	}

	public NamesResponse queryByNames(String usuarioInstitucionApli,
			String apellidoPaterno, String apellidoMaterno, String nombres)
		throws MQException, Exception {

		NamesResponse responseNames = null;
		PropertiesType params = PropertiesHelper.getProperties(); 
		
		apellidoPaterno = URLDecoder.decode(apellidoPaterno, "UTF-8");
		apellidoMaterno = URLDecoder.decode(apellidoMaterno, "UTF-8");

		NamesRequest requestBody = new NamesRequest(InquiryType.__SEARCH_FOR_NAMES,  
				params.getApplWebInstitutionCode(), 
				params.getApplWebInstitutionName(),
				usuarioInstitucionApli, 
				params.getApplWebInstitutionHost(),
				params.getApplWebMatchesNumber(),
				apellidoPaterno,
				apellidoMaterno,
				nombres);

		MQSendRecv mqsr = new  MQSendRecv(params);
		
		int returnCode = 0,
		    groupHome = 0;  
		List<NamesSubResponse>	namesSubResponseList = new ArrayList<NamesSubResponse>();		
		
		do {
			String messageIn = requestBody.toString(),
				   messageID = mqsr.MQSendMessage(messageIn, messageIn.length());
			
			String messageOut = mqsr.MQReceivesMessage(messageID);
			responseNames = new NamesResponse(messageOut);
			
			returnCode = responseNames.getCodigoError(); 
			
			if ( returnCode != 0)
				throw new RQSException(returnCode);
			
			List<NamesSubResponse>	groupList;		
			groupList = responseNames.getListaPersonasEncontradas();
			
			namesSubResponseList.addAll(groupList);
			groupList.clear();
			groupList = null;
			
			// Update home group to get next list
			groupHome += params.getApplWebMatchesNumber(); 
			requestBody.setGroupHome(groupHome);

		} while (responseNames.getTotalCoincidencias() > groupHome );
		
		responseNames.getListaPersonasEncontradas().clear();
		responseNames.getListaPersonasEncontradas().addAll(namesSubResponseList);
		
		namesSubResponseList.clear();
		namesSubResponseList =  null;
		
//		int total = 0;
		
		LOG.debug("Coincidencias Encontradas: " + responseNames.getListaPersonasEncontradas().size() );
		
//		for (NamesSubResponse namesSubResponse : responseNames.getListaPersonasEncontradas()) {
//			LOG.debug(String.format("[%04d][%8.8s%s%s%40.40s%40.40s%60.60s%s%s%10.10s]", ++total, 
//					namesSubResponse.getNumeroDNI(),
//					namesSubResponse.getDigitoVerificacion(),
//					namesSubResponse.getTipoFichaRegistral(),
//					namesSubResponse.getApellidoPaterno(),
//					namesSubResponse.getApellidoMaterno(),
//					namesSubResponse.getNombres(),
//					namesSubResponse.getMostrarDatos(),
//					namesSubResponse.getExisteFoto(),
//					namesSubResponse.getReservado()) );
//			
//		}
		
		if ( returnCode == 0)
			return responseNames;
		
		return responseNames; 
		
	}

	public ChildDNIResponse queryByChildId(String usuarioInstitucionApli,
			String numeroDNI, String numeroDNIApoderado, int tipoVinculo,
			String tipoSubConsulta) 
		throws MQException, Exception {

		PropertiesType params = PropertiesHelper.getProperties();
		ChildDNIResponse responseDNI = null;
		ChildDNIRequest requestDNI = null;
		
		if (tipoSubConsulta.contains("3"))
			throw new RQSInvalidSubqueryTypeException(tipoSubConsulta);
		
		requestDNI = new ChildDNIRequest(InquiryType.__SEARCH_FOR_CHILD_ID, 
				params.getApplWebInstitutionCode(), 
				params.getApplWebInstitutionName(),
				usuarioInstitucionApli, 
				params.getApplWebInstitutionHost(), 
				numeroDNIApoderado, 
				numeroDNI, 
				FamilyTiesType.fromValue(tipoVinculo),
				SubqueryType.fromValue(tipoSubConsulta));
		
		MQSendRecv mqsr = new  MQSendRecv(params);
		
		String messageIn = requestDNI.toString(),
			   messageOut = null;
		String messageID = mqsr.MQSendMessage(messageIn, messageIn.length());
		
		messageOut = mqsr.MQReceivesMessage(messageID);
		
		responseDNI = new ChildDNIResponse(messageOut, String.valueOf(tipoSubConsulta));

		return responseDNI;
	}
	
	
	public List<String> consultarPersona(String dni, String usuario, String password) 
		throws RQSException {
		
		List<String> lst = new ArrayList<String>();
		
		LOG.debug(">>> Servicio 'consultarPersona' llamado...");

		PropertiesType params = PropertiesHelper.getProperties();
		
		if (!params.getApplWSUser().equals(usuario) || !params.getApplWSPassword().equals(password))
		{
			// Usuario o clave incorrecto
			lst.add("2");
			LOG.error("Usuario o clave incorrecto");
		}
		else if (StringUtil.isNullOrEmpty(dni) && dni.trim().length() != 8)
		{
			// DNI nulo o invalido 
			lst.add("3");
			LOG.error("DNI vacio o nulo");
		}
		else
		{
			int retriesNumber = 0;

			if (params.isApplProxySet()) {
				
				LOG.info("Estableciendo parámetros del PROXY");
				LOG.debug("proxyHost [" + params.getApplProxyHost() + "]");
				LOG.debug("proxyPort [" + params.getApplProxyPort() + "]");
				LOG.debug("proxyUser [" + params.getApplProxyUser() + "]");
				LOG.debug("proxyPassword [" + params.getApplProxyPassword().replaceAll(".", "*") + "]");

				System.setProperty("http.proxyHost", params.getApplProxyHost());
				System.setProperty("http.proxyPort", params.getApplProxyPort());
				
				Authenticator.setDefault(new ProxyAuthenticator(params.getApplProxyUser(), 
							params.getApplProxyPassword()));
			}
			
			try {

				SRELServiceService ws = new SRELServiceService();
				String errorCode = "0001";
				
				while ( ++retriesNumber <= params.getApplReniecWSMaxRetries() && "0001/SINV/UNL".contains(errorCode) )
				{
					
					lst.clear();
					
					// Obtenemos la session de RENIEC 
					String sessionRENIEC = ws.getSRELServicePort().getSession(
							params.getApplReniecWSUser(),
							params.getApplReniecWSPassword());
					
					// Si es una sesión correcta ... 
					if ( !"2/3/4/5/6/8".contains(sessionRENIEC.trim()) )
					{
						// ... Obtenemos la identidad consolidada
						List<String> lstReturn = ws.getSRELServicePort()
								.getRegIdentConsolidada2(sessionRENIEC,
										params.getApplReniecWSUser(),
										params.getApplReniecWSTraceCode(),
										params.getApplReniecWSEntityUser(), dni);

						// Verificamos que no exista error con la operación anterior
						errorCode = lstReturn.get(0);
						
						if ( errorCode.equals("0000") )
						{
							lst.add ("1");
							lst.add (lstReturn.get(1));							// Apellido Paterno
							lst.add (lstReturn.get(2));							// Apellido Materno
							lst.add (lstReturn.get(3));							// Nombres
							lst.add (lstReturn.get(4).equals("1")? "M" : "F");	// Sexo
							lst.add (lstReturn.get(5));							// Fecha de Nacimiento
							lst.add (lstReturn.get(6));							// Código
							lst.add (lstReturn.get(7));							// Restricción
						}
						else
						{
							// 0000 : Operación Correcta.
							// 0001 : Usuario no existe.
							// 0002 : El Servidor no puede atender el requerimiento.
							// 5    : Excedió el máximo número de consultas asignadas por día.
							// 3    : Esta consultando en un día y hora no permitido según convenio.
							// 21   : El DNI solicitado se encuentra cancelado en el RUIPN
							// 22   : El DNI solicitado se encuentra restringido en el RUIPN
							// 23   : El DNI solicitado se encuentra observado en el RUIPN
							// NTP  : No tienen permisos de acceso al método consulta.
							// SINV : Código de Sesión ingresado inválido.
							// DNE  : El DNI consultado es inválido.
							// DNV  : El DNI del usuario de la empresa es inválido. No está autorizado a consultar.
							// UNL  : Aplicación que consulta a Web Service no se ha autenticado, debe invocar previamente getSession.
							
							ErrorType et = ErrorRENIECHelper.getError(errorCode);
							
							if ( et != ErrorType.__UNKNOW_RENIEC_ERROR_CODE_ERROR ) {
								
								lst.add(String.valueOf(et.getErrorCode()));
								LOG.error(String.format("%s ()", et.getErrorText(), et.getErrorCode()));
							}
							else {
								
								lst.add("4");
								LOG.error(String.format("DNI no encontrado (Error %s)", errorCode));
							}
						}				
						
					}
					else
					{
						// 2 : Error en la Operación
						// 3 : La Consulta esta fuera del Horario Permitido
						// 4 : Usuario no valido
						// 5 : La Consulta excedió la cantidad máxima permitida
						// 6 : Usuario no existe
						// 8 : contraseña incorrecta.			

						ErrorType et = ErrorRENIECHelper.getError(errorCode);
						
						if ( et != ErrorType.__UNKNOW_RENIEC_ERROR_CODE_ERROR ) {
							
							lst.add(String.valueOf(et.getErrorCode()));
							LOG.error(String.format("%s ()", et.getErrorText(), et.getErrorCode()));
						}
						else {
							
							lst.add("4");
							LOG.error(String.format("DNI no encontrado (Error %s)", errorCode));
						}
					}
					
				}
				
				if ( --retriesNumber ==  params.getApplReniecWSMaxRetries() && !errorCode.equals("0000") ) {
					
					lst.add("4");
					LOG.error(String.format("Se alcanzó el máximo de intentos con la RENIEC"));
				}
				
			} catch (Exception e) {
				LOG.error("Error de Conectividad. No se pudo establecer conexión con el servicio web de la RENIEC");
				throw new RQSException(ErrorType.__CANNOT_CONNECT_WITH_WS_RENIEC_ERROR);
			}
		}
		
		LOG.debug(">>> RENIECQueryService Retorna: " + lst.toString());

		return lst;
	}

}
