package pe.gob.midis.sisfoh.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import pe.gob.midis.sisfoh.bean.NamesResponse;
import pe.gob.midis.sisfoh.bean.NamesSubResponse;
import pe.gob.midis.sisfoh.mock.RENIECQueryMock;
import pe.gob.midis.sisfoh.model.AuditLog;
import pe.gob.midis.sisfoh.service.RENIECQueryService;
import pe.gob.midis.sisfoh.type.EventType;
import pe.gob.midis.sisfoh.type.PropertiesType;
import pe.gob.midis.sisfoh.utils.GeneralEntitiesHelper;
import pe.gob.midis.sisfoh.utils.PropertiesHelper;

public class SearchByNamesAction extends ActionSupport 
	implements SessionAware {
	
	private static final Logger LOG = LoggerFactory.getLogger(SearchByNamesAction.class);
	private static final long serialVersionUID = -7143272536998100967L;

	private String names;
	private String fatherLastName;
	private String motherLastName;
	private String jsonData;
	private String jsonErrorMessage;
	
	private Map<String, Object> session;
	
	public SearchByNamesAction() {

		session = new HashMap<String, Object>();
	}

	public String execute() {
		
		return ActionSupport.SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String queryByNames() {
		
		jsonErrorMessage = null;

		try {
			PropertiesType props = PropertiesHelper.getProperties();
			NamesResponse names = null;
			
			HashMap<String,Object> userMap = (HashMap<String,Object>)JSONUtil.deserialize((String)session.get("user"));
			String user = (String)userMap.get("user");
			
			GeneralEntitiesHelper.saveAuditLog(new AuditLog(EventType.__QUERYBYNAMES, user, 
					String.format(getText("querybyname.action.event"),
							getFatherLastName(), getMotherLastName(),
							getNames())));			
			
			if (!props.isApplMock()) {
				RENIECQueryService service = new RENIECQueryService();

				names = service.queryByNames(user, getFatherLastName(),
						getMotherLastName(), getNames());
			} else {
				RENIECQueryMock mock = new RENIECQueryMock();

				names = mock.queryByNames(user, getFatherLastName(),
						getMotherLastName(), getNames());
			}
			
			if ( names != null )
				LOG.info(names.toString());

			// Add objects to table
			List<NamesSubResponse> arrayTable = new ArrayList<NamesSubResponse>(); 

			arrayTable.addAll(names.getListaPersonasEncontradas());
			
			jsonData = JSONUtil.serialize(arrayTable);
			
			arrayTable = null;
			
//			LOG.debug("jsonTableData: " + jsonData);
			
		} catch (Exception e) {
			jsonErrorMessage = e.getMessage();
			LOG.error(jsonErrorMessage, e);
		}
		
		return ActionSupport.SUCCESS;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
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
