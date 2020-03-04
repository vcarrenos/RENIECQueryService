package pe.gob.midis.sisfoh.type;

import pe.gob.midis.sisfoh.exception.RQSInvalidErrorTypeException;

public enum ErrorType {

	__OK_SUCCESSFUL								(   0, "Proceso exit�so."                                                                                            ),
	__CANNOT_CONNECT_WITH_MQ_RENIEC_ERROR		(9999, "No se pudo establecer conexi�n com RENIEC"                                                                   ),
	__CANNOT_CONNECT_WITH_WS_RENIEC_ERROR		(9998, "Error de Conectividad. No se pudo establecer conexi�n con el servicio web de la RENIEC"                      ),	
	__INVALID_USER_OR_PASSWORD_ERROR			(9997, "Usuario o clave incorrecto"                                                                                  ),
	__DNI_EMPTY_OR_NULL_ERROR					(9996, "DNI vacio o nulo"                                                                                            ),
	__USER_NOT_EXISTS_ERROR						(9995, "Usuario no existe (Error RENIEC: 0001)."                                                                     ),
	__SERVER_CANNOT_LISTEN_REQUEST_ERROR		(9994, "El Servidor no puede atender el requerimiento (Error RENIEC: 0002)."                                         ),
	__MAXIMUM_QUERIES_NUMBER_IS_REACHED_ERROR	(9993, "Excedi� el m�ximo n�mero de consultas asignadas por d�a (Error RENIEC: 5)."                                  ),
	__QUERY_NOT_ALLOWED_IN_THIS_TIME_ERROR		(9992, "Esta consultando en un día y hora no permitido seg�n convenio (Error RENIEC: 3)."                            ),
	__DNI_CANCELLED_BY_RUIPN_ERROR 				(9991, "El DNI solicitado se encuentra cancelado en el RUIPN (Error RENIEC: 21)."                                    ),
	__DNI_RESTRICTED_BY_RUIPN_ERROR				(9990, "El DNI solicitado se encuentra restringido en el RUIPN (Error RENIEC: 22)."                                  ),
	__DNI_OBSERVED_BY_RUIPN_ERROR				(9989, "El DNI solicitado se encuentra observado en el RUIPN (Error RENIEC: 23)."                                    ),
	__NOT_AUTHORIZED_ACCESS_TO_CONSULT_ERROR	(9988, "No tienen permisos de acceso al m�todo consulta (Error RENIEC: NTP)."                                        ),
	__SESSION_CODE_IS_INVALID_ERROR				(9987, "C�digo de Sesi�n ingresado inv�lido (Error RENIEC: SINV)."                                                   ),
	__INVALID_DNI_REQUIRED_ERROR				(9986, "El DNI consultado es inv�lido (Error RENIEC: DNE)."                                                          ),
	__INVALID_ENTITY_DNI_ERROR					(9985, "El DNI del usuario de la empresa es inv�lido. No est� autorizado a consultar (Error RENIEC: DNV)."           ),
	__UNAUTHENTICATED_APPLICATION_ERROR			(9984, "Aplicaci�n que consulta no se ha autenticado, debe invocar previamente getSession (Error RENIEC: UNL)."      ),
	__OPERATION_FAIL_ERROR						(9984, "Error en la operaci�n (Error RENIEC: 2)."                                                                    ),
	__INVALID_USER_ERROR						(9995, "Usuario incorrecto  (Error RENIEC: 6 o 4)."                                                                  ),
	__INVALID_PASSWORD_ERROR					(9996, "Contrase�a incorrecta (Error RENIEC: 8)."                                                                    ),

	__INVALID_VERSION_ERROR						(5002, "Versi�n inv�lida"                                                                                            ),
	__INVALID_HDR_LENGTH_ERROR					(5003, "Longitud de cabecera inv�lida"                                                                               ),
	__INVALID_CHECK_CHARS_ERROR					(5004, "Caracteres de verificaci�n incorrectos"                                                                      ),
	__INVALID_RENIEC_SERVER_ERROR				(5008, "Servidor no v�lido"                                                                                          ),
	__INVALID_QUERY_TYPE_ERROR					(5009, "Tipo de consulta inv�lido"                                                                                   ),
	__UNSUPPORT_QUERY_TYPE_ERROR				(5010, "Tipo de consulta no permitida"                                                                               ),
	__SUBQUERY_TYPE_NOT_PRESENT_ERROR			(5011, "No se ha ingresado subtipo de consulta"                                                                      ),
	__ENTITY_NOT_EXISTS_ERROR					(5020, "No existe la empresa ingresada para usar el servicio"                                                        ),
	__ENTITY_IS_DISABLED_ERROR					(5021, "La empresa registrada no est� habilitada para usar el servicio"                                              ),
	__DNI_FOR_INQUERY_IS_INVALID_ERROR      	(5030, "El usuario final de consulta ingresado no es valido"                                                         ),
	__NO_INFORMATION_TO_USER_ERROR				(5031, "No se tiene informaci�n solicitada del usuario ingresado"                                                    ),
	__DNI_CANCELLED_ERROR						(5032, "El DNI no puede realizar consultas por encontrarse cancelado en el RUIPN"                                    ),
	__DNI_RESTRICTED_ERROR						(5033, "El DNI no puede realizar consultas por encontrarse restringido en el RUIPN"                                  ),
	__DNI_OBSERVED_ERROR						(5034, "El DNI no puede realizar consultas por encontrarse observado en el RUIPN"                                    ),
	__DNI_CANNOT_INQUIRIES_FOR_CHILD_ERROR		(5035, "El DNI no puede realizar consultas por pertenecer a un menor de edad"                                        ),
	__DNI_IS_DISABLED_ERROR						(5036, "El DNI se encuentra con baja temporal en el servicio"                                                        ),
	__DNI_IS_DELETED_ERROR						(5037, "El DNI se encuentra con baja definitiva en el servicio"                                                      ),
	__USER_CANNOT_THIS_TYPE_INQUEIRIES_ERROR	(5038, "El usuario ingresado no puede hacer este tipo de consulta"                                                   ),
	__ENTITY_CANNOT_THIS_TYPE_INQUEIRIES_ERROR	(5039, "La empresa no puede hacer este tipo de consulta"                                                             ),
	__INVALID_REQUEST_LENGTH_ERROR				(5100, "Longitud de trama de consulta inv�lida"                                                                      ),
	__INVALID_MATCHES_NUMBER_ERROR				(5101, "Error en n�mero de coincidencias solicitadas o inicio de grupo"                                              ),
	__MATCHES_EXCEED_LIMIT_ERROR				(5102, "Coincidencias superan el l�mite establecido"                                                                 ),
	__DATABASE_ERROR							(5103, "Error en base de datos"                                                                                      ),
	__NO_REQUEST_INFORMATION_FOUND_ERROR		(5104, "No se encontr� informaci�n de la estructura solicitada"                                                      ),
	__NO_REQUEST_FIELDS_FOUND_ERROR				(5105, "No se encontr� los campos a mostrar para la estructura solicitada"                                           ),
	__INVALID_CHARACTER_IN_LASTNAME_ERROR		(5108, "Car�cter ingresado en apellido paterno es inv�lido"                                                          ),
	__INVALID_CHARACTER_IN__ERROR				(5109, "Car�cter ingresado en apellido materno es inv�lido"                                                          ),
	__INVALID_CHARACTER_IN_NAMES_ERROR			(5110, "Car�cter ingresado en nombres es inv�lido"                                                                   ),
	__REQUEST_DNI_CANCELLED_ERROR				(5111, "El DNI solicitado se encuentra cancelado en el archivo magn�tico del RUIPN"                                  ),
	__REQUEST_DNI_RESTRICTED_ERROR				(5112, "El DNI solicitado se encuentra restringido en el archivo magn�tico del RUIPN"                                ),
	__REQUEST_DNI_OBSERVED_ERROR				(5113, "El DNI solicitado se encuentra observado en el archivo magn�tico del RUIPN"                                  ),
	__DNI_IS_INVALID_ERROR						(5114, "El DNI ingresado no es v�lido"                                                                               ),
	__DECLARANT_DNI_CANCELLED_ERROR				(5120, "El DNI del padre o apoderado solicitado se encuentra cancelado en el archivo magn�tico del RUIPN"            ),
	__DECLARANT_DNI_RESTRICTED_ERROR			(5121, "El DNI del padre o apoderado solicitado se encuentra restringido en el archivo magn�tico del RUIPN"          ),
	__DECLARANT_DNI_OBSERVED_ERROR				(5122, "El DNI del padre o apoderado solicitado se encuentra observado en el archivo magn�tico del RUIPN"            ),
	__CHILD_DECLARANT_DNI_CANCELLED_ERROR 		(5131, "El DNI del padre o apoderado del menor solicitado se encuentra cancelado en el archivo magn�tico del RUIPN"  ),
	__CHILD_DECLARANT_DNI_RESTRICTED_ERROR		(5132, "El DNI del padre o apoderado del menor solicitado se encuentra restringido en el archivo magn�tico del RUIPN"),
	__CHILD_DECLARANT_DNI_OBSERVED_ERROR		(5133, "El DNI del padre o apoderado del menor solicitado se encuentra observado en el archivo magn�tico del RUIPN"  ),
	__CHILD_DNI_CANCELLED_ERROR					(5134, "El DNI del menor solicitado se encuentra cancelado en el archivo magn�tico del RUIPN"                        ),
	__CHILD_DNI_RESTRICTED_ERROR				(5135, "El DNI del menor solicitado se encuentra restringido en el archivo magn�tico del RUIPN"                      ),
	__CHILD_DNI_OBSERVED_ERROR					(5136, "El DNI del menor solicitado se encuentra observado en el archivo magn�tico del RUIPN"                        ),
	__INVALID_FAMILY_TIES_ERROR					(5150, "No ha ingresado tipo de v�nculo correcto."                                                                   ),
	__FATHER_DNI_NO_FAMILY_TIE_ERROR			(5151, "El DNI del mayor enviado no tiene el v�nculo de PADRE con el DNI del menor"                                  ),
	__MOTHER_DNI_NO_FAMILY_TIE_ERROR			(5152, "El DNI del mayor enviado no tiene el v�nculo de MADRE con el DNI del menor"                                  ),
	__DECLARANT_DNI_NO_FAMILY_TIE_ERROR			(5153, "El DNI del mayor enviado no tiene el v�nculo de APODERADO con el DNI del menor"                              ),
	__REQUESTED_DATA_NOT_FOUND_ERROR		 	(5200, "No existen los datos solicitados"                                                                            ),
	__NO_DATA_REQUESTED_ERROR					(5200, "No existen los datos solicitados"                                                                            ),
	__FAULT_IMAGES_ERROR						(5201, "Error en im�genes"                                                                                           ),
	__FAULT_PHOTOGRAPhY_ERROR					(5202, "Error en foto"                                                                                               ),
	__FAULT_SIGNATURE_ERROR						(5203, "Error en firma"                                                                                              ),
	__UNKNOW_RENIEC_ERROR_CODE_ERROR			(5204, "El C�digo de error devuelto por RENIEC es desconocido"                                                       );
	

	private int errorCode;
	private String errorText;
	
	private ErrorType(int errorCode, String errorText) {
		this.errorCode = errorCode;
		this.errorText = errorText;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorText
	 */
	public String getErrorText() {
		return errorText;
	}

	static public ErrorType fromValue(int value) 
			throws RQSInvalidErrorTypeException {

		for (ErrorType et : ErrorType.values()) {
			if (et.getErrorCode() == value) {
				return et;
			}
		}
		
		throw new RQSInvalidErrorTypeException(value);
	}
	
}


