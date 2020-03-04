package pe.gob.midis.sisfoh.type;

public enum EventType {
	
	__LOGIN			 ("LOGIN"),
	__LOGOUT         ("LOGOUT"),
	__CHANGEPASSWORD ("CHANGEPASSWORD"),
	__QUERYBYADULTID ("QUERYBYADULTID"),
	__QUERYBYNAMES   ("QUERYBYNAMES"),
	__QUERYBYCHILDID ("QUERYBYCHILDID"),
	__QUERYBYBATCH   ("QUERYBYBATCH"),
	__QUERYPERSON    ("QUERYPERSON"),
	__SAVE           ("SAVE"),
	__DELETE         ("DELETE"),
	__EDIT           ("EDIT"),
	__EXPORT         ("EXPORT");
	
	String eventCode;

	private EventType(String eventCode) {

		this.eventCode = eventCode;
	}

	public String getEventCode() {

		return eventCode;
	}
	
}
