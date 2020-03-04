package pe.gob.midis.sisfoh.exception;


public class RQSParameterNotFoundException extends Exception {

	private static final long serialVersionUID = -288269685691382425L;

	public RQSParameterNotFoundException(String parameterName) {
		super(String.format("Error: Parámetro '%s' NO encontrado en tabla `grl_parameter`", parameterName));
		
	}
	
}
