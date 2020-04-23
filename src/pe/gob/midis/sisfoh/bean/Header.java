package pe.gob.midis.sisfoh.bean;

import pe.gob.midis.sisfoh.type.InquiryType;

public class Header {
	
	// Default header values 
	static String __VERSION = "0002";
	static String __SERVICE_TYPE = "000";
	static int __HEADER_LENGTH = 128;
	static String __SPACE =  " ";
	static int __TTL = 0;
	static String __CHECK_CHARACTERS	= "RENIECPERURENIEC";
	static String __RENIEC_SERVER_CODE = "RENIEC001";
	
	// Header fields length
	static int __VERSION_LEN                       =  4;
	static int __HEADER_LENGTH_LEN                 =  4;
	static int __SERVICE_TYPE_LEN                  =  3;
	static int __REQUEST_LENGTH_LEN                =  9;
	static int __FRAGMENTATION_LEN                 = 22;
	static int __MESSAGE_LIFE_TIME_LEN             =  9;
	static int __INQUIRY_TYPE_LEN                  =  1;
	static int __CHECK_CHARACTERS_LEN              = 16;
	static int __APPLICANT_INSTITUTION_CODE_LEN    = 10;
	static int __RENIEC_SERVER_CODE_LEN            = 10;
	static int __APPLICANT_INSTITUTION_LEN         = 10;
	static int __APPLICANT_INSTITUTION_USER_LEN    = 10;
	static int __APPLICANT_INSTITUTION_HOST_LEN    = 10;
	static int __RESERVED_LEN                      = 10;

	// Header fields
	String  version;						// Versión de la trama
	int		headerLength;					// Longitud de cabecera
	String  serviceType;					// Tipo de servicio
	int		requestLength;					// Longitud de trama
	String  fragmentation;					// fragmentacion
	int		messageLifetime;				// Tiempo de vida del mensaje en la cola de respuesta(seg.). Uso futuro.
	int		inquiryType;					// Tipo de consulta: 1 = Por nombres, 2 =  Por DNI Mayores, 4 = Por DNI Menores
	String  checkCharacters;				// Caracteresde verificaci�n
	String  applicantInstitutionCode;		// Código de Institución Solicitante. Proporcionado por RENIEC: DE2068 = Desarrollo, LD2068 = Producci�n
	String  reniecServerCode;				// Códigode servidor RENIEC
	String  applicantInstitutionName;		// Agencia de la institución solicitante desde donde se realiz� la consulta
	String  applicantInstitutionUser;		// DNI del consultante
	String  applicantInstitutionHost;		// HOST de la institución solicitante desde donde se realizó la consulta
	String  reserved;						// Reservado para uso futuro

	
	public Header() {
	}	
	
	public Header(InquiryType inquiryType, String applicantInstitutionCode,
			String applicantInstitutionName, String applicantInstitutionUser,
			String applicantInstitutionHost) {
		super();
		this.version = Header.__VERSION;
		this.headerLength = Header.__HEADER_LENGTH;
		this.serviceType = Header.__SERVICE_TYPE;
		this.requestLength = 0;
		this.fragmentation = Header.__SPACE;
		this.messageLifetime = Header.__TTL;
		this.inquiryType = inquiryType.getInquiryType();
		this.checkCharacters = Header.__CHECK_CHARACTERS;
		this.applicantInstitutionCode = applicantInstitutionCode;
		this.reniecServerCode = Header.__RENIEC_SERVER_CODE;
		this.applicantInstitutionName = applicantInstitutionName;
		this.applicantInstitutionUser = applicantInstitutionUser;
		this.applicantInstitutionHost = applicantInstitutionHost;
		this.reserved = Header.__SPACE;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getHeaderLength() {
		return headerLength;
	}
	public void setHeaderLength(int headerLength) {
		this.headerLength = headerLength;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public int getRequestLength() {
		return requestLength;
	}
	public void setRequestLength(int requestLength) {
		this.requestLength = Header.__HEADER_LENGTH + requestLength;
	}
	public String getFragmentation() {
		return fragmentation;
	}
	public void setFragmentation(String fragmentation) {
		this.fragmentation = fragmentation;
	}
	public int getMessageLifetime() {
		return messageLifetime;
	}
	public void setMessageLifetime(int messageLifetime) {
		this.messageLifetime = messageLifetime;
	}
	public int getInquiryType() {
		return inquiryType;
	}
	public void setInquiryType(int inquiryType) {
		this.inquiryType = inquiryType;
	}
	public String getCheckCharacters() {
		return checkCharacters;
	}
	public void setCheckCharacters(String checkCharacters) {
		this.checkCharacters = checkCharacters;
	}
	public String getApplicantInstitutionCode() {
		return applicantInstitutionCode;
	}
	public void setApplicantInstitutionCode(String applicantInstitutionCode) {
		this.applicantInstitutionCode = applicantInstitutionCode;
	}
	public String getReniecServerCode() {
		return reniecServerCode;
	}
	public void setReniecServerCode(String reniecServerCode) {
		this.reniecServerCode = reniecServerCode;
	}
	public String getApplicantInstitution() {
		return applicantInstitutionName;
	}
	public void setApplicantInstitution(String applicantInstitutionName) {
		this.applicantInstitutionName = applicantInstitutionName;
	}
	public String getApplicantInstitutionUser() {
		return applicantInstitutionUser;
	}
	public void setApplicantInstitutionUser(String applicantInstitutionUser) {
		this.applicantInstitutionUser = applicantInstitutionUser;
	}
	public String getApplicantInstitutionHost() {
		return applicantInstitutionHost;
	}
	public void setApplicantInstitutionHost(String applicantInstitutionHost) {
		this.applicantInstitutionHost = applicantInstitutionHost;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reservado) {
		this.reserved = reservado;
	}
	
	public String toString() {
		String format = "%s%04d%s%09d%22s%09d%d%-16s%-10s%-10s%-10s%-10s%-10s%10s";
		
		return String.format(format, 
				getVersion(),
				getHeaderLength(),
				getServiceType(),
				getRequestLength(),
				getFragmentation(),		
				getMessageLifetime(),
				getInquiryType(),
				getCheckCharacters(),
				getApplicantInstitutionCode(),
				getReniecServerCode(),
				getApplicantInstitution(),
				getApplicantInstitutionUser(),
				getApplicantInstitutionHost(),
				getReserved());
	}	
	
	static public Header valueOf (String request, Header header) {
		int index = 0;
		
		header.setVersion(request.substring(index, index + __VERSION_LEN));
		index += __VERSION_LEN;
		
		header.setHeaderLength(Integer.valueOf(request.substring(index, index + __HEADER_LENGTH_LEN)));
		index += __HEADER_LENGTH_LEN;
		
		header.setServiceType(request.substring(index, index + __SERVICE_TYPE_LEN));
		index += __SERVICE_TYPE_LEN;
		
		header.setRequestLength(Integer.valueOf(request.substring(index, index + __REQUEST_LENGTH_LEN)));
		index += __REQUEST_LENGTH_LEN;
		
		header.setFragmentation(request.substring(index, index + __FRAGMENTATION_LEN));
		index += __FRAGMENTATION_LEN;
		
		header.setMessageLifetime(Integer.valueOf(request.substring(index, index + __MESSAGE_LIFE_TIME_LEN)));
		index += __MESSAGE_LIFE_TIME_LEN;
		
		header.setInquiryType(Integer.valueOf(request.substring(index, index + __INQUIRY_TYPE_LEN)));
		index += __INQUIRY_TYPE_LEN;
		
		header.setCheckCharacters(request.substring(index, index + __CHECK_CHARACTERS_LEN));
		index += __CHECK_CHARACTERS_LEN;
		
		header.setApplicantInstitutionCode(request.substring(index, index + __APPLICANT_INSTITUTION_CODE_LEN));
		index += __APPLICANT_INSTITUTION_CODE_LEN;
		
		header.setReniecServerCode(request.substring(index, index + __RENIEC_SERVER_CODE_LEN));
		index += __RENIEC_SERVER_CODE_LEN;
		
		header.setApplicantInstitution(request.substring(index, index + __APPLICANT_INSTITUTION_LEN));
		index += __APPLICANT_INSTITUTION_LEN;
		
		header.setApplicantInstitutionUser(request.substring(index, index + __APPLICANT_INSTITUTION_USER_LEN));
		index += __APPLICANT_INSTITUTION_USER_LEN;

		header.setApplicantInstitutionHost(request.substring(index, index + __APPLICANT_INSTITUTION_HOST_LEN));
		index += __APPLICANT_INSTITUTION_HOST_LEN;

		header.setReserved(request.substring(index, index + __RESERVED_LEN));
		index += __RESERVED_LEN;
		
		return header;		
	}
	
}
