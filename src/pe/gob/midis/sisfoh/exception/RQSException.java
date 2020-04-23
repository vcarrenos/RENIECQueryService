package pe.gob.midis.sisfoh.exception;

import pe.gob.midis.sisfoh.type.ErrorType;

public class RQSException extends Exception {

	private static final long serialVersionUID = -6519279758820500529L;

	public RQSException() {
		
	}
	
	public RQSException(String message) {
		super(message);
		
	}
	
	public RQSException(ErrorType et) throws RQSException {
		
		String message = String.format("Error [%d][%s]", et.getErrorCode(), et.getErrorText());
		
		throw new RQSException(message);

	}
	
	public RQSException(int errorCode) 
			throws RQSException, RQSInvalidErrorTypeException {
		
		ErrorType et = ErrorType.fromValue(errorCode);
		
		if ( et != null)
			throw new RQSException(et);
		else
			throw new RQSException(String.format("[%d][Código de error desconocido]", errorCode));
	}
	
}
