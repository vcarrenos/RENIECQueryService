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
import pe.gob.midis.sisfoh.model.User;
import pe.gob.midis.sisfoh.type.EventType;
import pe.gob.midis.sisfoh.utils.GeneralEntitiesHelper;

import com.opensymphony.xwork2.ActionSupport;

public class AdminUsersAction extends ActionSupport 
	implements SessionAware {

	private static final Logger LOG = LoggerFactory.getLogger(AdminUsersAction.class);
	private static final long serialVersionUID = -7143272536998100967L;
	

	private String userCode;
	private String userNameFirst;
	private String userNameLast;
	private String userNames;
	private String userLogin;
	private Integer userQuota;
	private String userBirthDate;
	private String userExpirationDate;
	private boolean userEnabled;
	
	private String jsonTableData;
	private String jsonErrorMessage;

	private Map<String,Object> session;
	
	public AdminUsersAction() {

		session = new HashMap<String, Object>();
	}

	public String execute() {
		
		return ActionSupport.SUCCESS;
	}
	
	@SuppressWarnings("rawtypes")
	public String loadAllUsers() {
		
		jsonErrorMessage = null;

		try {
			// Getting the bin data
			List<Map> arrayTable = new ArrayList<Map>(); 
			Map<String, Object> data;
			
			for (Map.Entry<String, User> user : GeneralEntitiesHelper.getUsers().entrySet())
			{
			    
				// Add objects to table
				data = new HashMap<String, Object>();
				data.put("codigo", user.getValue().getDni());
				data.put("paterno", URLDecoder.decode(user.getValue().getApFirst(), "UTF-8"));
				data.put("materno", URLDecoder.decode(user.getValue().getApLast(), "UTF-8"));
				data.put("nombres", URLDecoder.decode(user.getValue().getNames(), "UTF-8"));
				data.put("nacimiento", user.getValue().getBirthDate());
				data.put("cuota", String.valueOf(user.getValue().getMaxQuota()));
				data.put("expiracion", user.getValue().getExpirationDate());
				data.put("activo", user.getValue().isEnabled());
				arrayTable.add(data);
			}
			
			jsonTableData = JSONUtil.serialize(arrayTable);
			
			arrayTable = null;
			data = null;
			
			LOG.debug("jsonTableData: " + jsonTableData);
			
		} catch (Exception e) {
			
			jsonErrorMessage = getText("error.users.cannot.load");
			LOG.error(jsonErrorMessage, e);
		}
		
		return ActionSupport.SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String saveUser() {

		jsonErrorMessage = null;

		try {

			GeneralEntitiesHelper.saveUser(new User(getUserCode(),
					getUserNameFirst(), getUserNameLast(), getUserNames(),
					getUserQuota(), getUserExpirationDate(),
					getUserBirthDate(), isUserEnabled()));
			GeneralEntitiesHelper.loadUsers();

			HashMap<String, Object> userMap = (HashMap<String, Object>) JSONUtil
					.deserialize((String) session.get("user"));
			String user = (String) userMap.get("user");

			GeneralEntitiesHelper.saveAuditLog(new AuditLog(EventType.__SAVE,
					user, String.format(getText("users.action.save"),
							getUserCode(), (isUserEnabled() ? "ACTIVO"
									: "INACTIVO"))));

		} catch (Exception e) {

			jsonErrorMessage = getText("error.users.cannot.save");
			LOG.error(jsonErrorMessage, e);
		}
		
		return ActionSupport.SUCCESS;
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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserNameFirst() {
		return userNameFirst;
	}

	public void setUserNameFirst(String userNameFirst) {
		this.userNameFirst = userNameFirst;
	}

	public String getUserNameLast() {
		return userNameLast;
	}

	public void setUserNameLast(String userNameLast) {
		this.userNameLast = userNameLast;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	
	public Integer getUserQuota() {
		return userQuota;
	}

	public void setUserQuota(Integer userQuota) {
		this.userQuota = userQuota;
	}

	public String getUserExpirationDate() {
		return userExpirationDate;
	}

	public void setUserExpirationDate(String userExpirationDate) {
		this.userExpirationDate = userExpirationDate;
	}

	public String getUserBirthDate() {
		return userBirthDate;
	}

	public void setUserBirthDate(String userBirthDate) {
		this.userBirthDate = userBirthDate;
	}

	public boolean isUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

	@Override
	public void setSession(Map<String, Object> session) {

		this.session = session;
	}
}
