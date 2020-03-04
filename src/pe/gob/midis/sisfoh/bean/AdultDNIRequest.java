package pe.gob.midis.sisfoh.bean;

import pe.gob.midis.sisfoh.type.InquiryType;
import pe.gob.midis.sisfoh.type.SubqueryType;

public class AdultDNIRequest extends Header {
	
	// Default adult DNI request values 
	static int __ADULT_DNI_REQUEST_LENGTH = 30;
	
	// Adult DNI request fields
	String idNumber;				// Número de DNI
	SubqueryType  subqueryType;		// Tipo de sub consulta
	int    signatureType;			// Formato de firma
	String reserved;				// Reservado para uso futuro
	
	public AdultDNIRequest() {
	}
	
	public AdultDNIRequest(InquiryType inquiryType, String applicantInstitutionCode,
			String applicantInstitution, String applicantInstitutionUser, String applicantInstitutionHost,
			String idNumber, SubqueryType subqueryType, int signatureType) {
		
		super(inquiryType, applicantInstitutionCode, applicantInstitution,
				applicantInstitutionUser, applicantInstitutionHost);
		
		this.idNumber = idNumber;
		this.subqueryType = subqueryType;
		this.signatureType = signatureType;
		this.reserved = Header.__SPACE;
	}

	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public SubqueryType getSubqueryType() {
		return subqueryType;
	}
	public void setSubqueryType(SubqueryType subqueryType) {
		this.subqueryType = subqueryType;
	}
	public int getSignatureType() {
		return signatureType;
	}
	public void setSignatureType(int signatureType) {
		this.signatureType = signatureType;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	
	public String toString() {
		String format = "%s%8.8s%-5d%d%16s";
		
		setRequestLength(AdultDNIRequest.__ADULT_DNI_REQUEST_LENGTH);
		
		return String.format(format, super.toString(), 
				getIdNumber(),
				getSubqueryType().getSubqueryType(), 
				getSignatureType(), 
				getReserved());
	}	

}
