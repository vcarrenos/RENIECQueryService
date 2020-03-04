package pe.gob.midis.sisfoh.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.midis.sisfoh.bean.AdultDNIResponse;
import pe.gob.midis.sisfoh.mock.RENIECQueryMock;
import pe.gob.midis.sisfoh.model.AuditLog;
import pe.gob.midis.sisfoh.service.RENIECQueryService;
import pe.gob.midis.sisfoh.type.EventType;
import pe.gob.midis.sisfoh.type.PropertiesType;
import pe.gob.midis.sisfoh.utils.GeneralEntitiesHelper;
import pe.gob.midis.sisfoh.utils.PropertiesHelper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SearchByAdultIdAction extends ActionSupport 
	implements SessionAware {
	
	private static final Logger LOG = LoggerFactory.getLogger(SearchByAdultIdAction.class);
	private static final long serialVersionUID = -7143272536998100967L;
	
	private String numberId;
	private String jsonData;
	private String jsonErrorMessage;
	
	private Map<String, Object> session;
	
	public SearchByAdultIdAction() {

		session = new HashMap<String, Object>();
	}

	public String execute() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		if ( request.getParameter("id") != null )
			setNumberId(request.getParameter("id"));
		
		return ActionSupport.SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String queryByAdultId() {
		
		jsonErrorMessage = null;
		
		try {
			PropertiesType props = PropertiesHelper.getProperties();
			AdultDNIResponse adultDNI = null;
			
			HashMap<String,Object> userMap = (HashMap<String,Object>)JSONUtil.deserialize((String)session.get("user"));
			String user = (String)userMap.get("user");
			
			GeneralEntitiesHelper.saveAuditLog(new AuditLog(EventType.__QUERYBYADULTID,
					user, String.format(getText("querybyid.action.event"), getNumberId())));			
			
			if (!props.isApplMock()) {
				RENIECQueryService service = new RENIECQueryService();

				adultDNI = service.queryByAdultId(user, getNumberId(), "123");
			} else {
				RENIECQueryMock mock = new RENIECQueryMock();

				adultDNI = mock.queryByAdultId(user, getNumberId(), "123");
			}
			
			if ( adultDNI != null )
				LOG.info(adultDNI.toString());

			// Add objects to table
			Map<String, Object> data = new HashMap<String, Object>();

			data.put ("data", adultDNI);
			
			jsonData = JSONUtil.serialize(data);
			
			data = null;
			
//			LOG.debug("jsonTableData: " + jsonTableData);
			
		} catch (Exception e) {
			jsonErrorMessage = e.getMessage();
			LOG.error(jsonErrorMessage, e);
		}
		
		return ActionSupport.SUCCESS;
	}

	public String getNumberId() {
		return numberId;
	}

	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
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
