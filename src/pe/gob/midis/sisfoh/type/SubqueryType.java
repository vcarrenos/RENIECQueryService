package pe.gob.midis.sisfoh.type;

import pe.gob.midis.sisfoh.exception.RQSInvalidSubqueryTypeException;

public enum SubqueryType {
	
	__DATA_TYPE				(  1,   "1"),
	__PHOTOGRAPHY_TYPE		(  2,   "2"),
	__SIGNATURE_TYPE		(  3,   "3"),
	__DATA_PHOTO			( 12,  "12"),
	__DATA_SIGNATURE		( 13,  "13"),	
	__PHOTO_SIGNATURE		( 23,  "23"),	
	__FULL_INFO				(123, "123");

	private final int subqueryType;
	private final String subqueryTypeLabel;

	private SubqueryType(int subqueryType, String subqueryTypeLabel) {
		
		this.subqueryType = subqueryType;
		this.subqueryTypeLabel = subqueryTypeLabel;
	}

	public int getSubqueryType() {
		return subqueryType;
	}
	
	/**
	 * @return the subqueryTypeLabel
	 */
	public String getSubqueryTypeLabel() {
		return subqueryTypeLabel;
	}	
	
	static public SubqueryType fromValue(int value) 
		throws RQSInvalidSubqueryTypeException {
		
		String valueStr = String.valueOf(value);
		
		fromValue(valueStr);
		
		throw new RQSInvalidSubqueryTypeException(valueStr);
	}

	static public SubqueryType fromValue(String value) 
		throws RQSInvalidSubqueryTypeException {

		for (SubqueryType st : SubqueryType.values()) {
			if (st.getSubqueryTypeLabel().equals(value)) {
				return st;
			}
		}

		throw new RQSInvalidSubqueryTypeException(value);
	}
}
