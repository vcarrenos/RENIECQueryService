package pe.gob.midis.sisfoh.exception;


public class RQSInvalidErrorTypeException extends Exception {

	private static final long serialVersionUID = -5424733316170689629L;

	public RQSInvalidErrorTypeException(int errorCode) {

		super(String.format("[%d][El Código de error devuelto por RENIEC es desconocido]", errorCode));	
	}
}

