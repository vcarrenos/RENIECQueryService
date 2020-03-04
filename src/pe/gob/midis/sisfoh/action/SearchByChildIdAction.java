package pe.gob.midis.sisfoh.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.midis.sisfoh.bean.ChildDNIResponse;
import pe.gob.midis.sisfoh.mock.RENIECQueryMock;
import pe.gob.midis.sisfoh.model.AuditLog;
import pe.gob.midis.sisfoh.service.RENIECQueryService;
import pe.gob.midis.sisfoh.type.EventType;
import pe.gob.midis.sisfoh.type.PropertiesType;
import pe.gob.midis.sisfoh.utils.GeneralEntitiesHelper;
import pe.gob.midis.sisfoh.utils.PropertiesHelper;

import com.opensymphony.xwork2.ActionSupport;

public class SearchByChildIdAction extends ActionSupport 
	implements SessionAware {
	
	private static final Logger LOG = LoggerFactory.getLogger(SearchByChildIdAction.class);
	private static final long serialVersionUID = -7143272536998100967L;
	
	private String numberIdFather;
	private String numberId;
	private int    familyTies;
	private String jsonData;
	private String jsonErrorMessage;
	
	private Map<String, Object> session;
	
	public SearchByChildIdAction() {

		session = new HashMap<String, Object>();
	}

	public String execute() {
		
		return ActionSupport.SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String queryByChildId() {
		
		jsonErrorMessage = null;

		try {
			PropertiesType props = PropertiesHelper.getProperties();
			ChildDNIResponse childDNI = null;
			
			HashMap<String,Object> userMap = (HashMap<String,Object>)JSONUtil.deserialize((String)session.get("user"));
			String user = (String)userMap.get("user");		
			
			GeneralEntitiesHelper.saveAuditLog(new AuditLog(EventType.__QUERYBYCHILDID,
					user, String.format(getText("querybychild.action.event"), getNumberId())));			
			
			if (!props.isApplMock()) {
				RENIECQueryService service = new RENIECQueryService();

				childDNI = service.queryByChildId(user, getNumberId(),
						getNumberIdFather(), getFamilyTies(), "12");
			} else {
				RENIECQueryMock mock = new RENIECQueryMock();

				childDNI = mock.queryByChildId(user, getNumberId(),
						getNumberIdFather(), getFamilyTies(), "12");
			}
			
			if ( childDNI != null )
				LOG.info(childDNI.toString());

			// Add objects to table
			Map<String, Object> data = new HashMap<String, Object>();

			data.put ("data", childDNI);
			
			jsonData = JSONUtil.serialize(data);
			
			data = null;
			
//			LOG.debug("jsonTableData: " + jsonTableData);
			
		} catch (Exception e) {
			jsonErrorMessage = e.getMessage();
			LOG.error(jsonErrorMessage, e);
		}
		
		return ActionSupport.SUCCESS;
	}

	public int getFamilyTies() {
		return familyTies;
	}

	public void setFamilyTies(int familyTies) {
		this.familyTies = familyTies;
	}

	public String getNumberIdFather() {
		return numberIdFather;
	}

	public void setNumberIdFather(String numberIdFather) {
		this.numberIdFather = numberIdFather;
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
