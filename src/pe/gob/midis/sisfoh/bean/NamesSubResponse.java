package pe.gob.midis.sisfoh.bean;

public class NamesSubResponse {

	// Default sub response names values 
	static int __NAMES_SUB_RESPONSE_LENGTH = 162;

	// Sub response names fields length
	static int __BYTES_NUMERO_DNI				= 8;
	static int __BYTES_DIGITO_VERIFICACION		= 1;
	static int __BYTES_TIPO_FICHA_REGISTRAL		= 1;
	static int __BYTES_APELLIDO_PATERNO			= 40;
	static int __BYTES_APELLIDO_MATERNO			= 40;
	static int __BYTES_NOMBRES					= 60;
	static int __BYTES_MOSTRAR_DATOS			=  1;
	static int __BYTES_EXISTE_FOTO				=  1;
	static int __BYTES_RESERVADO				= 10;
	
	// Sub response names fields
	private String numeroDNI;			// Número de DNI
	private String digitoVerificacion;	// Dígito de verificaci�n
	private String tipoFichaRegistral;	// Tipo de Ficha Registral
	private String apellidoPaterno; 	// Apellido Paterno
	private String apellidoMaterno; 	// Apellido Materno
	private String nombres; 			// Nombres
	private String mostrarDatos;		// Indica si se pueden mostrar datos. S = No, N = No
	private String existeFoto;		    // Indica si existe una foto. . S = Existe, N = No Existe
	private String reservado; 			// Reservado para uso futuro
	
	public NamesSubResponse() {
	}

	public NamesSubResponse(String response) {
		NamesSubResponse.valueOf(response, this);
	}

	public String getNumeroDNI() {
		return numeroDNI;
	}

	public void setNumeroDNI(String numeroDNI) {
		this.numeroDNI = numeroDNI;
	}

	public String getDigitoVerificacion() {
		return digitoVerificacion;
	}

	public void setDigitoVerificacion(String digitoVerificacion) {
		this.digitoVerificacion = digitoVerificacion;
	}

	public String getTipoFichaRegistral() {
		return tipoFichaRegistral;
	}

	public void setTipoFichaRegistral(String tipoFichaRegistral) {
		this.tipoFichaRegistral = tipoFichaRegistral;
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

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getMostrarDatos() {
		return mostrarDatos;
	}

	public void setMostrarDatos(String mostrarDatos) {
		this.mostrarDatos = mostrarDatos;
	}

	public String getExisteFoto() {
		return existeFoto;
	}

	public void setExisteFoto(String existeFoto) {
		this.existeFoto = existeFoto;
	}

	public String getReservado() {
		return reservado;
	}

	public void setReservado(String reservado) {
		this.reservado = reservado;
	}

	static public NamesSubResponse valueOf (String response, NamesSubResponse responseBody) {
		int index = 0;
		
		responseBody.setNumeroDNI(response.substring(index, index + NamesSubResponse.__BYTES_NUMERO_DNI));
		index += __BYTES_NUMERO_DNI;

		responseBody.setDigitoVerificacion(response.substring(index, index + NamesSubResponse.__BYTES_DIGITO_VERIFICACION));
		index += __BYTES_DIGITO_VERIFICACION;
		
		responseBody.setTipoFichaRegistral(response.substring(index, index + NamesSubResponse.__BYTES_TIPO_FICHA_REGISTRAL));
		index += __BYTES_TIPO_FICHA_REGISTRAL;

		responseBody.setApellidoPaterno(response.substring(index, index + NamesSubResponse.__BYTES_APELLIDO_PATERNO));
		index += __BYTES_APELLIDO_PATERNO;
		
		responseBody.setApellidoMaterno(response.substring(index, index + NamesSubResponse.__BYTES_APELLIDO_MATERNO));
		index += __BYTES_APELLIDO_MATERNO;
		
		responseBody.setNombres(response.substring(index, index + NamesSubResponse.__BYTES_NOMBRES));
		index += __BYTES_NOMBRES;
		
		responseBody.setMostrarDatos(response.substring(index, index + NamesSubResponse.__BYTES_MOSTRAR_DATOS));
		index += __BYTES_MOSTRAR_DATOS;
		
		responseBody.setExisteFoto(response.substring(index, index + NamesSubResponse.__BYTES_EXISTE_FOTO));
		index += __BYTES_EXISTE_FOTO;
		
		responseBody.setReservado(response.substring(index, index + NamesSubResponse.__BYTES_RESERVADO));
		index += __BYTES_RESERVADO;
		
		return responseBody;
	}

}
