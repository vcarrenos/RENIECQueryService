package pe.gob.midis.sisfoh.exception;


public class RQSPropertyNotFoundException extends Exception {

	private static final long serialVersionUID = -6519279758820500529L;

	public RQSPropertyNotFoundException(String propertyName) {
		super(String.format("Error: Propiedad '%s' NO encontrada en archivo de configuración", propertyName));
		
	}
	
}
