package pe.gob.midis.sisfoh.type;

import pe.gob.midis.sisfoh.exception.RQSInvalidFamilyTiesTypeException;
import pe.gob.midis.sisfoh.exception.RQSInvalidSubqueryTypeException;

public enum FamilyTiesType {
	
	__FATHER		(1, "Padre"),
	__MOTHER		(2, "Madre"),
	__DECLARANT		(3, "Declarante"),
	__SHELTERLESS	(4, "Desamparado");


	int familyTiesType;
	String familyTiesTypeLabel;
	
	private FamilyTiesType(int familyTiesType, String familyTiesTypeLabel) {
		this.familyTiesType = familyTiesType;
		this.familyTiesTypeLabel = familyTiesTypeLabel;
	}

	/**
	 * @return the familyTiesType
	 */
	public int getFamilyTiesType() {
		return familyTiesType;
	}

	/**
	 * @return the familyTiesTypeLabel
	 */
	public String getFamilyTiesTypeLabel() {
		return familyTiesTypeLabel;
	}
	
	static public FamilyTiesType fromValue(int value) 
			throws RQSInvalidSubqueryTypeException, RQSInvalidFamilyTiesTypeException {

			for (FamilyTiesType ftt : FamilyTiesType.values()) {
				if (ftt.getFamilyTiesType() == value) {
					return ftt;
				}
			}

			throw new RQSInvalidFamilyTiesTypeException(value);
		}	
	
}
