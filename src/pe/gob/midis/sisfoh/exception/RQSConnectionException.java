package pe.gob.midis.sisfoh.exception;

import pe.gob.midis.sisfoh.type.ErrorType;
import pe.gob.midis.sisfoh.type.PropertiesType;

public class RQSConnectionException extends Exception {

	private static final long serialVersionUID = -5424733316170689629L;
	
	public RQSConnectionException(String message) {
		super(message);		
	}
	
	public RQSConnectionException(PropertiesType params)
		throws RQSException {
		
		String message = String.format("%s:\n\t\tQueueManager[%s]\n"+
										"\t\tChannel[%s]\n" + 
										"\t\tQueueNameRequest[%s]\n" + 
										"\t\tQueueNameResponse[%s]\n" + 
										"\t\tIP[%s]\n" + 
										"\t\tPort[%d]\n" + 
										"\t\tInstitutionCode[%s]\n" + 
										"\t\tInstitutionName[%s]\n",
								ErrorType.__CANNOT_CONNECT_WITH_MQ_RENIEC_ERROR.getErrorText(),
								params.getApplMQQueueManager(),
								params.getApplMQChannel(),
								params.getApplMQQueueNameRequest(),
								params.getApplMQQueueNameResponse(),
								params.getApplMQHostName(),
								params.getApplMQPortNumber(),
								params.getApplWebInstitutionCode(),
								params.getApplWebInstitutionName());
		
		throw new RQSException(message);
	}
}
