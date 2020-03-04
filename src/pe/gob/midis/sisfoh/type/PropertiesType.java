package pe.gob.midis.sisfoh.type;

import java.util.Map;
import java.util.Properties;

import pe.gob.midis.sisfoh.model.Parameter;
import pe.gob.midis.sisfoh.utils.GeneralEntitiesHelper;

public class PropertiesType {

	private boolean applMock;

	private Integer   applMQEnvironmentActive; 
	private String [] applMQHostName;
	private Integer[] applMQPortNumber;
	private String [] applMQQueueManager;
	private String [] applMQChannel;
	private String [] applMQQueueNameRequest;
	private String [] applMQQueueNameResponse;
	private Integer[] applMQMessageExpiration;
	
	private String [] applWebInstitutionCode; 
	private String  applWebInstitutionName; 
	private String  applWebInstitutionHost;
	private Integer applWebMatchesNumber;
	private String  applWSUser;
	private String  applWSPassword;
	private Integer appDelayQueryBatch; 
	private Integer appQuantityQueryBatch; 

	private String  applReniecWSEntityUser; 
	private String  applReniecWSUser; 
	private String  applReniecWSPassword; 
	private String  applReniecWSTraceCode;
	private Integer applReniecWSMaxRetries;
	
	private boolean applProxySet;
	private String  applProxyHost; 
	private String  applProxyPort;
	private String  applProxyUser; 
	private String  applProxyPassword; 	
	
	final static private int __PRODUCCION = 0;
	final static private int __DESARROLLO = 1;

	public PropertiesType(Properties props) {
		
		applMQHostName          = new String[2];
		applMQPortNumber        = new Integer[2];
		applMQQueueManager      = new String[2];
		applMQChannel           = new String[2];
		applMQQueueNameRequest  = new String[2];
		applMQQueueNameResponse = new String[2];
		applMQMessageExpiration = new Integer[2];
		applWebInstitutionCode  = new String[2];
		
		Map<String, Parameter>parameters = GeneralEntitiesHelper.getParameters();
		
		this.applMQEnvironmentActive = Integer.parseInt(parameters.get("APP_MQ_ENVIRONMENTQUERY_ACTIVE").getValue());
		
		this.applMQHostName         [__PRODUCCION] = parameters.get("APP_PROD_MQ_HOST").getValue();
		this.applMQQueueManager     [__PRODUCCION] = parameters.get("APP_PROD_MQ_MANAGER_NAME").getValue();
		this.applMQPortNumber       [__PRODUCCION] = Integer.parseInt(parameters.get("APP_PROD_MQ_PORT_NUMBER").getValue());
		this.applMQChannel          [__PRODUCCION] = parameters.get("APP_PROD_MQ_CHANNEL_NAME").getValue();
		this.applMQQueueNameRequest [__PRODUCCION] = parameters.get("APP_PROD_MQ_QUEUE_REQUEST").getValue();
		this.applMQQueueNameResponse[__PRODUCCION] = parameters.get("APP_PROD_MQ_QUEUE_RESPONSE").getValue();
		this.applWebInstitutionCode [__PRODUCCION] = parameters.get("APP_PROD_MQ_INSTITUTION_CODE").getValue();
		this.applMQMessageExpiration[__PRODUCCION] = Integer.parseInt(parameters.get("APP_PROD_MQ_EXPIRATION_TIME").getValue());

		if(this.applMQMessageExpiration[__PRODUCCION] <= 0)
			this.applMQMessageExpiration[__PRODUCCION]  = 2500;
		
		this.applMQHostName         [__DESARROLLO] = parameters.get("APP_TEST_MQ_HOST").getValue();
		this.applMQQueueManager     [__DESARROLLO] = parameters.get("APP_TEST_MQ_MANAGER_NAME").getValue();
		this.applMQPortNumber       [__DESARROLLO] = Integer.parseInt(parameters.get("APP_TEST_MQ_PORT_NUMBER").getValue());
		this.applMQChannel          [__DESARROLLO] = parameters.get("APP_TEST_MQ_CHANNEL_NAME").getValue();
		this.applMQQueueNameRequest [__DESARROLLO] = parameters.get("APP_TEST_MQ_QUEUE_REQUEST").getValue();
		this.applMQQueueNameResponse[__DESARROLLO] = parameters.get("APP_TEST_MQ_QUEUE_RESPONSE").getValue();
		this.applWebInstitutionCode [__DESARROLLO] = parameters.get("APP_TEST_MQ_INSTITUTION_CODE").getValue();
		this.applMQMessageExpiration[__DESARROLLO] = Integer.parseInt(parameters.get("APP_TEST_MQ_EXPIRATION_TIME").getValue());

		if(this.applMQMessageExpiration[__DESARROLLO] <= 0)
			this.applMQMessageExpiration[__DESARROLLO]  = 2500;
		
		if (parameters.get("APP_DELAY_QUERY_BATCH") != null)
			this.appDelayQueryBatch = Integer.parseInt(parameters.get("APP_DELAY_QUERY_BATCH").getValue()) * 1000;
		else
			this.appDelayQueryBatch = 1 * 1000;

		if (parameters.get("APP_QUANTITY_QUERY_BATCH") != null)
			this.appQuantityQueryBatch = Integer.parseInt(parameters.get("APP_QUANTITY_QUERY_BATCH").getValue());
		else
			this.appQuantityQueryBatch = 500;
		
		this.applMock                 = props.getProperty("appl.mock").equals("1");

		this.applWebInstitutionName   = props.getProperty("appl.web.institution.name");
		this.applWebInstitutionHost   = props.getProperty("appl.web.institution.host");
		this.applWebMatchesNumber     = Integer.valueOf(props.getProperty("appl.web.matches.number"));
		this.applWSUser               = props.getProperty("appl.ws.user");
		this.applWSPassword           = props.getProperty("appl.ws.password");		
		
		this.applReniecWSEntityUser   = props.getProperty("appl.reniec.ws.entity.user");
		this.applReniecWSUser         = props.getProperty("appl.reniec.ws.user");
		this.applReniecWSPassword     = props.getProperty("appl.reniec.ws.password");
		this.applReniecWSTraceCode    = props.getProperty("appl.reniec.ws.trace.code");
		
		if(props.containsKey("appl.reniec.ws.max.retries"))
			this.applReniecWSMaxRetries = Integer.valueOf(props.getProperty("appl.reniec.ws.max.retries"));
		else
			this.applReniecWSMaxRetries = 3;
		
		this.applProxySet             = parameters.get("APP_PROXY_SET").getValue().equals("1");
		this.applProxyHost            = parameters.get("APP_PROXY_HOST").getValue();
		this.applProxyPort            = parameters.get("APP_PROXY_PORT").getValue();
		this.applProxyUser            = parameters.get("APP_PROXY_USER").getValue();
		this.applProxyPassword        = parameters.get("APP_PROXY_PASSWORD").getValue();
	}

	public boolean isApplMock() {
		return applMock;
	}

	public void setApplMock(boolean applMock) {
		this.applMock = applMock;
	}

	public Integer getApplMQEnvironmentActive() {
		return applMQEnvironmentActive;
	}

	public void setApplMQEnvironmentActive(Integer applMQEnvironmentActive) {
		this.applMQEnvironmentActive = applMQEnvironmentActive;
	}

	public String getApplMQHostName() {
		return applMQHostName[this.applMQEnvironmentActive];
	}

//	public void setApplMQHostName(String applMQHostName) {
//		this.applMQHostName[this.applMQEnvironmentActive] = applMQHostName;
//	}

	public Integer getApplMQPortNumber() {
		return applMQPortNumber[this.applMQEnvironmentActive];
	}

//	public void setApplMQPortNumber(Integer applMQPortNumber) {
//		this.applMQPortNumber[this.applMQEnvironmentActive] = applMQPortNumber;
//	}

	public String getApplMQQueueManager() {
		return applMQQueueManager[this.applMQEnvironmentActive];
	}

//	public void setApplMQQueueManager(String applMQQueueManager) {
//		this.applMQQueueManager[this.applMQEnvironmentActive] = applMQQueueManager;
//	}

	public String getApplMQChannel() {
		return applMQChannel[this.applMQEnvironmentActive];
	}

//	public void setApplMQChannel(String applMQChannel) {
//		this.applMQChannel[this.applMQEnvironmentActive] = applMQChannel;
//	}

	public String getApplMQQueueNameRequest() {
		return applMQQueueNameRequest[this.applMQEnvironmentActive];
	}

//	public void setApplMQQueueNameRequest(String applMQQueueNameRequest) {
//		this.applMQQueueNameRequest[this.applMQEnvironmentActive] = applMQQueueNameRequest;
//	}

	public String getApplMQQueueNameResponse() {
		return applMQQueueNameResponse[this.applMQEnvironmentActive];
	}

//	public void setApplMQQueueNameResponse(String applMQQueueNameResponse) {
//		this.applMQQueueNameResponse[this.applMQEnvironmentActive] = applMQQueueNameResponse;
//	}

	public Integer getApplMQMessageExpiration() {
		return applMQMessageExpiration[this.applMQEnvironmentActive];
	}

//	public void setApplMQMessageExpiration(Integer applMQMessageExpiration) {
//		this.applMQMessageExpiration[this.applMQEnvironmentActive] = applMQMessageExpiration;
//	}

	public String getApplWebInstitutionCode() {
		return applWebInstitutionCode[this.applMQEnvironmentActive];
	}

//	public void setApplWebInstitutionCode(String applWebInstitutionCode) {
//		this.applWebInstitutionCode[this.applMQEnvironmentActive] = applWebInstitutionCode;
//	}

	public String getApplWebInstitutionName() {
		return applWebInstitutionName;
	}

	public void setApplWebInstitutionName(String applWebInstitutionName) {
		this.applWebInstitutionName = applWebInstitutionName;
	}

	public String getApplWebInstitutionHost() {
		return applWebInstitutionHost;
	}

	public void setApplWebInstitutionHost(String applWebInstitutionHost) {
		this.applWebInstitutionHost = applWebInstitutionHost;
	}

	public Integer getApplWebMatchesNumber() {
		return applWebMatchesNumber;
	}

	public void setApplWebMatchesNumber(Integer applWebMatchesNumber) {
		this.applWebMatchesNumber = applWebMatchesNumber;
	}

	public String getApplWSUser() {
		return applWSUser;
	}

	public void setApplWSUser(String applWSUser) {
		this.applWSUser = applWSUser;
	}

	public String getApplWSPassword() {
		return applWSPassword;
	}

	public void setApplWSPassword(String applWSPassword) {
		this.applWSPassword = applWSPassword;
	}

	public String getApplReniecWSEntityUser() {
		return applReniecWSEntityUser;
	}

	public void setApplReniecWSEntityUser(String applReniecWSEntityUser) {
		this.applReniecWSEntityUser = applReniecWSEntityUser;
	}

	public String getApplReniecWSUser() {
		return applReniecWSUser;
	}

	public void setApplReniecWSUser(String applReniecWSUser) {
		this.applReniecWSUser = applReniecWSUser;
	}

	public String getApplReniecWSPassword() {
		return applReniecWSPassword;
	}

	public void setApplReniecWSPassword(String applReniecWSPassword) {
		this.applReniecWSPassword = applReniecWSPassword;
	}

	public String getApplReniecWSTraceCode() {
		return applReniecWSTraceCode;
	}

	public void setApplReniecWSTraceCode(String applReniecWSTraceCode) {
		this.applReniecWSTraceCode = applReniecWSTraceCode;
	}

	public Integer getApplReniecWSMaxRetries() {
		return applReniecWSMaxRetries;
	}

	public void setApplReniecWSMaxRetries(Integer applReniecWSMaxRetries) {
		this.applReniecWSMaxRetries = applReniecWSMaxRetries;
	}

	public boolean isApplProxySet() {
		return applProxySet;
	}


	public void setApplProxySet(boolean applProxySet) {
		this.applProxySet = applProxySet;
	}

	public String getApplProxyHost() {
		return applProxyHost;
	}

	public void setApplProxyHost(String applProxyHost) {
		this.applProxyHost = applProxyHost;
	}

	public String getApplProxyPort() {
		return applProxyPort;
	}

	public void setApplProxyPort(String applProxyPort) {
		this.applProxyPort = applProxyPort;
	}

	public String getApplProxyUser() {
		return applProxyUser;
	}

	public void setApplProxyUser(String applProxyUser) {
		this.applProxyUser = applProxyUser;
	}

	public String getApplProxyPassword() {
		return applProxyPassword;
	}

	public void setApplProxyPassword(String applProxyPassword) {
		this.applProxyPassword = applProxyPassword;
	}

	public Integer getAppDelayQueryBatch() {
		return appDelayQueryBatch;
	}

//	public void setAppDelayQueryBatch(Integer appDelayQueryBatch) {
//		this.appDelayQueryBatch = appDelayQueryBatch;
//	}

	public Integer getAppQuantityQueryBatch() {
		return appQuantityQueryBatch;
	}

//	public void setAppQuantityQueryBatch(Integer appQuantityQueryBatch) {
//		this.appQuantityQueryBatch = appQuantityQueryBatch;
//	}
}
