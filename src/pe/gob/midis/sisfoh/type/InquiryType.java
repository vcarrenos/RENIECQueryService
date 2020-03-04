package pe.gob.midis.sisfoh.type;

public enum InquiryType {
	
	__SEARCH_FOR_NAMES    (1),
	__SEARCH_FOR_ADULT_ID (2),
	__SEARCH_FOR_CHILD_ID (4);
	
	int inquiryType;

	private InquiryType(int inquiryType) {
		this.inquiryType = inquiryType;
	}

	public int getInquiryType() {
		return inquiryType;
	}
	
}
