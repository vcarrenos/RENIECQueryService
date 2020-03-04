package pe.gob.midis.sisfoh.bean;

import pe.gob.midis.sisfoh.type.FamilyTiesType;
import pe.gob.midis.sisfoh.type.InquiryType;
import pe.gob.midis.sisfoh.type.SubqueryType;

public class ChildDNIRequest extends Header {

	// Default child DNI request values 
	static int __CHILD_DNI_RESPONSE_LENGTH = 40;
	
	// Response child DNI fields length
	final static int __BYTES_LONGITUD_FIRMA				=  9;
	final static int __BYTES_RESERVADO_2				= 18;
	final static int __BYTES_RESERVADO_3				= 14;	
	
	// Child DNI request fields
	String idTutorNumber;				// Número de DNI del padre o apoderado.  
	String idNumber;					// Número  de  DNI del menor 
	FamilyTiesType  familyTiesType;		// Tipo de vínculo
	SubqueryType  subqueryType;			// Tipo de sub consulta
	String reserved;					// Reservado para uso futuro
	
	public ChildDNIRequest() {
	}
	
	public ChildDNIRequest(InquiryType inquiryType, String applicantInstitutionCode, String applicantInstitution,
			String applicantInstitutionUser, String applicantInstitutionHost, String idTutorNumber, String idNumber,
			FamilyTiesType familyTiesType, SubqueryType subqueryType) {
		
		super(inquiryType, applicantInstitutionCode, applicantInstitution,
				applicantInstitutionUser, applicantInstitutionHost);
		
		this.idTutorNumber = idTutorNumber;
		this.idNumber = idNumber;
		this.familyTiesType = familyTiesType;
		this.subqueryType = subqueryType;
		this.reserved = Header.__SPACE;
	}

	/**
	 * @return the idTutorNumber
	 */
	public String getIdTutorNumber() {
		return idTutorNumber;
	}

	/**
	 * @param idTutorNumber the idTutorNumber to set
	 */
	public void setIdTutorNumber(String idTutorNumber) {
		this.idTutorNumber = idTutorNumber;
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return the familyTiesType
	 */
	public FamilyTiesType getFamilyTiesType() {
		return familyTiesType;
	}

	/**
	 * @param familyTiesType the familyTiesType to set
	 */
	public void setFamilyTiesType(FamilyTiesType familyTiesType) {
		this.familyTiesType = familyTiesType;
	}

	/**
	 * @return the subqueryType
	 */
	public SubqueryType getSubqueryType() {
		return subqueryType;
	}

	/**
	 * @param subqueryType the subqueryType to set
	 */
	public void setSubqueryType(SubqueryType subqueryType) {
		this.subqueryType = subqueryType;
	}

	/**
	 * @return the reserved
	 */
	public String getReserved() {
		return reserved;
	}

	/**
	 * @param reserved the reserved to set
	 */
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}	
	
	public String toString() {
		String format = "%s%8.8s%8.8s%d%-5d%18s";
		
		setRequestLength(ChildDNIRequest.__CHILD_DNI_RESPONSE_LENGTH);
		
		return String.format(format, super.toString(),
				getIdTutorNumber(),
				getIdNumber(),
				getFamilyTiesType().getFamilyTiesType(),
				getSubqueryType().getSubqueryType(), 
				getReserved());
	}	

}
