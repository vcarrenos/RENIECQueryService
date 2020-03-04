package pe.gob.midis.sisfoh.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.midis.sisfoh.model.AuditLog;
import pe.gob.midis.sisfoh.type.EventType;
import pe.gob.midis.sisfoh.utils.DateUtil;
import pe.gob.midis.sisfoh.utils.GeneralEntitiesHelper;

import com.opensymphony.xwork2.ActionSupport;

public class AdminAuditLogAction extends ActionSupport
	implements SessionAware {

	private static final Logger LOG = LoggerFactory.getLogger(AdminAuditLogAction.class);
	private static final long serialVersionUID = -7143272536998100967L;

	private String eventType;
	private String userName;
	private String beginDate;
	private String endDate;

	private String jsonTableData;
	private String jsonErrorMessage;
	
	private Map<String, Object> session;
	
	public AdminAuditLogAction() {

		super();
		session = new HashMap<String, Object>();
	}

	public String execute() {
		
		return ActionSupport.SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes" })
	public String loadAllAuditLog() {
		
		jsonErrorMessage = null;
		
		if (getEventType() != null) {

			try {
				// Getting the bin data
				List<Map> arrayTable = new ArrayList<Map>(); 
				Map<String, Object> data;

				GeneralEntitiesHelper.loadAuditLog(
						(eventType.equalsIgnoreCase("TODOS") ? null : eventType),
						userName, beginDate, endDate);
				
				for (Map.Entry<Integer, AuditLog> log : GeneralEntitiesHelper.getAuditLog().entrySet())
				{
					
					java.sql.Timestamp fecha = DateUtil.toDateTime(log.getValue().getEventDate());
				    
					// Add objects to table
					data = new HashMap<String, Object>();
					data.put("id", log.getValue().getId());
					data.put("evento", log.getValue().getEvent());
					data.put("usuario", log.getValue().getUserName());
					data.put("fecha", DateUtil.getDateTime(fecha));
					data.put("descipcion", URLDecoder.decode(log.getValue().getDescription(), "UTF-8"));
					data.put("usuario_so", log.getValue().getUserOS());
					data.put("ip", log.getValue().getIp());
	
					arrayTable.add(data);
				}
				
				jsonTableData = JSONUtil.serialize(arrayTable);
				
				arrayTable = null;
				data = null;
				
				LOG.debug("jsonTableData: " + jsonTableData);
				
			} catch (Exception e) {
				
				jsonErrorMessage = getText("error.auditlog.cannot.load");
				LOG.error(jsonErrorMessage, e);
			}
		}
		
		return ActionSupport.SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String clearAuditLog() {
		
		jsonErrorMessage = null;

		try {
			HashMap<String,Object> userMap = (HashMap<String,Object>)JSONUtil.deserialize((String)session.get("user"));
			String user = (String)userMap.get("user");
			
			GeneralEntitiesHelper.deleteAllAuditLog();
			
			GeneralEntitiesHelper.saveAuditLog(new AuditLog(EventType.__DELETE,
					user, getText("audit.clearall.event")));			
			
			LOG.debug("jsonTableData: " + jsonTableData);
			
		} catch (Exception e) {
			
			jsonErrorMessage = getText("error.auditlog.cannot.load");
			LOG.error(jsonErrorMessage, e);
		}
		
		return ActionSupport.SUCCESS;
	}	

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getJsonTableData() {
		return jsonTableData;
	}

	public void setJsonTableData(String jsonTableData) {
		this.jsonTableData = jsonTableData;
	}

	public String getJsonErrorMessage() {
		return jsonErrorMessage;
	}

	public void setJsonErrorMessage(String jsonErrorMessage) {
		this.jsonErrorMessage = jsonErrorMessage;
	}

    @Override
    public void setSession(Map<String, Object> session) {

		this.session = session;
    }

}
