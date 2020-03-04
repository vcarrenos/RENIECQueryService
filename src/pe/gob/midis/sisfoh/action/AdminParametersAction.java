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
import pe.gob.midis.sisfoh.model.Parameter;
import pe.gob.midis.sisfoh.type.EventType;
import pe.gob.midis.sisfoh.type.PropertiesType;
import pe.gob.midis.sisfoh.utils.GeneralEntitiesHelper;
import pe.gob.midis.sisfoh.utils.PropertiesHelper;

import com.opensymphony.xwork2.ActionSupport;

public class AdminParametersAction extends ActionSupport 
	implements SessionAware {

	private static final Logger LOG = LoggerFactory.getLogger(AdminParametersAction.class);
	private static final long serialVersionUID = -7143272536998100967L;
	
	private String parameterCode;
	private String parameterDescription;
	private String parameterValue;
	private String parameterEnabled;
	private String parameterEditable;
	private String jsonTableData;
	private String jsonErrorMessage;

	private Map<String,Object> session;

	public AdminParametersAction() {

		session = new HashMap<String, Object>();
	}

	public String execute() {
		
		return ActionSupport.SUCCESS;
	}
	
	@SuppressWarnings("rawtypes")
	public String loadAllParameters() {
		
		jsonErrorMessage = null;

		try {
			// Getting the bin data
			List<Map> arrayTable = new ArrayList<Map>(); 
			Map<String, Object> data;
			
			for (Map.Entry<String, Parameter> param : GeneralEntitiesHelper.getParameters().entrySet())
			{
			    
				// Add objects to table
				data = new HashMap<String, Object>();
				data.put("codigo", URLDecoder.decode(param.getValue().getCode(), "UTF-8"));
				data.put("descripcion", URLDecoder.decode(param.getValue().getDescription(), "UTF-8"));
				data.put("valor", URLDecoder.decode(param.getValue().getValue(), "UTF-8"));
				data.put("activo", param.getValue().isEnabled());
				data.put("visible", param.getValue().isVisible());
				data.put("eliminable", param.getValue().isRemovable());
				arrayTable.add(data);
			}
			
			jsonTableData = JSONUtil.serialize(arrayTable);

			arrayTable = null;
			data = null;
			
			LOG.debug("jsonTableData: " + jsonTableData);
			
		} catch (Exception e) {
			
			jsonErrorMessage = getText("error.parameters.cannot.load");
			LOG.error(jsonErrorMessage, e);
		}
		
		return ActionSupport.SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String saveParameter() {

		jsonErrorMessage = null;

		try {

			Parameter paramCurrentKEY = (Parameter)GeneralEntitiesHelper.getParameters().get("APP_COMPANY_KEY");
			Parameter paramCurrentMQEnvActive = (Parameter)GeneralEntitiesHelper.getParameters().get("APP_MQ_ENVIRONMENTQUERY_ACTIVE");
			
			GeneralEntitiesHelper.saveParameter(new Parameter(getParameterCode(),
					getParameterDescription(), getParameterValue(), true, true, true));
			GeneralEntitiesHelper.loadParameters(); // Reload the parameters

			HashMap<String,Object> userMap = (HashMap<String,Object>)JSONUtil.deserialize((String)session.get("user"));
			String user = (String)userMap.get("user");	
			
			GeneralEntitiesHelper.saveAuditLog(new AuditLog(EventType.__SAVE,
					user, String.format(getText("parameters.action.save"), getParameterCode())));			
			
			// Se verifica si se ha cambiado la llave de cifrado
			if (getParameterCode().equals("APP_COMPANY_KEY") 
				&& !paramCurrentKEY.getValue().equals(getParameterValue())) {

				// Se actualiza la contraseña con la nueva llave 
				GeneralEntitiesHelper.updatePasswordUsers(
						paramCurrentKEY.getValue(), getParameterValue());
				
				GeneralEntitiesHelper.saveAuditLog(new AuditLog(EventType.__SAVE,
						user, getText("users.action.update")));			
			}
			
			// Se verifica si se ha cambiado el ambiente de consultas de la RENIEC
			if (getParameterCode().equals("APP_MQ_ENVIRONMENTQUERY_ACTIVE") 
					&& !paramCurrentMQEnvActive.getValue().equals(getParameterValue())) {
				
				PropertiesType props = PropertiesHelper.getProperties();
				
				// Se actualiza que ambiente usar 
				props.setApplMQEnvironmentActive(Integer.parseInt(getParameterValue()));
								
			}
			
			
		} catch (Exception e) {

			jsonErrorMessage = getText("error.parameters.cannot.save");
			LOG.error(jsonErrorMessage, e);
		}
		
		return ActionSupport.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String removeParameter() {

		jsonErrorMessage = null;

		try {
			GeneralEntitiesHelper.deleteParameter(getParameterCode());
			GeneralEntitiesHelper.loadParameters();

			HashMap<String,Object> userMap = (HashMap<String,Object>)JSONUtil.deserialize((String)session.get("user"));
			String user = (String)userMap.get("user");			

			GeneralEntitiesHelper.saveAuditLog(new AuditLog(EventType.__DELETE,
					user, String.format(getText("parameters.action.delete "), getParameterCode())));			
		} catch (Exception e) {

			jsonErrorMessage = getText("error.parameters.cannot.remove");
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

	public String getParameterCode() {
		return parameterCode;
	}

	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}

	public String getParameterDescription() {
		return parameterDescription;
	}

	public void setParameterDescription(String parameterDescription) {
		this.parameterDescription = parameterDescription;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public String getParameterEnabled() {
		return parameterEnabled;
	}

	public void setParameterEnabled(String parameterEnabled) {
		this.parameterEnabled = parameterEnabled;
	}

	public String getParameterEditable() {
		return parameterEditable;
	}

	public void setParameterEditable(String parameterEditable) {
		this.parameterEditable = parameterEditable;
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
