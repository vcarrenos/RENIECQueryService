package pe.gob.midis.sisfoh.utils;

import java.util.HashMap;
import java.util.Map;

import pe.gob.midis.sisfoh.exception.RQSInvalidErrorTypeException;
import pe.gob.midis.sisfoh.type.ErrorType;

public class ErrorRENIECHelper {
	
	private static Map<String, Integer> mapWSReniec;
	
	public static void create() {

		mapWSReniec = new HashMap<String, Integer>();
		
		ErrorRENIECHelper.mapWSReniec.put ("0000", 0   );
		ErrorRENIECHelper.mapWSReniec.put ("0001", 9995);
		ErrorRENIECHelper.mapWSReniec.put ("0002", 9994);
		ErrorRENIECHelper.mapWSReniec.put ("2"   , 9984);
		ErrorRENIECHelper.mapWSReniec.put ("3"   , 9992);
		ErrorRENIECHelper.mapWSReniec.put ("4"   , 9995);
		ErrorRENIECHelper.mapWSReniec.put ("5"   , 9993);
		ErrorRENIECHelper.mapWSReniec.put ("6"   , 9995);
		ErrorRENIECHelper.mapWSReniec.put ("8"   , 9996);
		ErrorRENIECHelper.mapWSReniec.put ("21"  , 9991);
		ErrorRENIECHelper.mapWSReniec.put ("22"  , 9990);
		ErrorRENIECHelper.mapWSReniec.put ("23"  , 9989);
		ErrorRENIECHelper.mapWSReniec.put ("NTP" , 9988);
		ErrorRENIECHelper.mapWSReniec.put ("SINV", 9987);
		ErrorRENIECHelper.mapWSReniec.put ("DNE" , 9986);
		ErrorRENIECHelper.mapWSReniec.put ("DNV" , 9985);
		ErrorRENIECHelper.mapWSReniec.put ("UNL" , 9984);
	}
	
	public static void release() {
	
		mapWSReniec.clear();
		mapWSReniec = null;
		
	}
	
	
	public static ErrorType getError (String reniecErrorCode) {

		ErrorType et;
		
		try {
			int realErrorCode = ErrorRENIECHelper.mapWSReniec.get(reniecErrorCode);
			et = ErrorType.fromValue(realErrorCode);
		} catch (RQSInvalidErrorTypeException e) {
			et = ErrorType.__UNKNOW_RENIEC_ERROR_CODE_ERROR;
		}
		
		return et;
	}
}


