package pe.gob.midis.sisfoh.exception;

public class RQSInvalidFamilyTiesTypeException extends Exception {

	private static final long serialVersionUID = -5424733316170689629L;
	
	public RQSInvalidFamilyTiesTypeException(int familyTiesType) {
		super(String.format("El tipo de vinculo '%d' es inválido", familyTiesType));
	}

}
