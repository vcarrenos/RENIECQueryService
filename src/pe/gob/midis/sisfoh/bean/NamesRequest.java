package pe.gob.midis.sisfoh.bean;

import pe.gob.midis.sisfoh.type.InquiryType;

public class NamesRequest extends Header {

	// Default names request values 
	static int __NAMES_REQUEST_LENGTH = 153;
	
	// Names request fields
	int    matchesNumber;		// Numero de coincidencias solicitadas.
	int    groupHome;			// Inicio de grupo.
	String fatherLastName;		// Apellido paterno
	String motherLastName; 		// Apellido materno
	String preNames; 			// Prenombres
	String reserved; 			// Reservado
	
	public NamesRequest() {
		
	}
	
	public NamesRequest(InquiryType inquiryType, String applicantInstitutionCode, String applicantInstitutionName,
			String applicantInstitutionUser, String applicantInstitutionHost, int matchesNumber, 
			String fatherLastName, String motherLastName,
			String preNames) {
		super(inquiryType, applicantInstitutionCode, applicantInstitutionName,
				applicantInstitutionUser, applicantInstitutionHost);
		this.matchesNumber = matchesNumber;
		this.fatherLastName = fatherLastName.trim();
		this.motherLastName = motherLastName.trim();
		this.preNames = preNames.trim();
		this.reserved = Header.__SPACE;
	}

	public int getMatchesNumber() {
		return matchesNumber;
	}

	public void setMatchesNumber(int matchesNumber) {
		this.matchesNumber = matchesNumber;
	}

	public int getGroupHome() {
		return groupHome;
	}

	public void setGroupHome(int groupHome) {
		this.groupHome = groupHome;
	}

	public String getFatherLastName() {
		return fatherLastName;
	}

	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}

	public String getMotherLastName() {
		return motherLastName;
	}

	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}

	public String getPreNames() {
		return preNames;
	}

	public void setPreNames(String preNames) {
		this.preNames = preNames;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	
	public String toString() {
		String format = "%s%04d%04d%-40.40s%-40.40s%-60.60s%5s";
		
		setRequestLength(NamesRequest.__NAMES_REQUEST_LENGTH);
		
		return String.format(format, super.toString(), 
				getMatchesNumber(),
				getGroupHome(), 
				getFatherLastName(),
				getMotherLastName(),
				getPreNames(),
				getReserved());
	}		
}
