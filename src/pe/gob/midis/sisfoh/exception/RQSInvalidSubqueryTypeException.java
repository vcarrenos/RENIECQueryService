package pe.gob.midis.sisfoh.exception;

public class RQSInvalidSubqueryTypeException extends Exception {

	private static final long serialVersionUID = -5424733316170689629L;
	
	public RQSInvalidSubqueryTypeException(String subQueryType) {
		super(String.format("El tipo de subconsulta '%s' es inválido", subQueryType));
	}

}
