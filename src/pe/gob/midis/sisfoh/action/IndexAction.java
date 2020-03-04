package pe.gob.midis.sisfoh.action;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaSystemException;

import pe.gob.midis.sisfoh.model.AuditLog;
import pe.gob.midis.sisfoh.model.User;
import pe.gob.midis.sisfoh.type.EventType;
import pe.gob.midis.sisfoh.utils.GeneralEntitiesHelper;
import pe.gob.midis.sisfoh.utils.StringUtil;

import com.opensymphony.xwork2.ActionSupport;


public class IndexAction extends ActionSupport 
	implements SessionAware {

	private static final Logger LOG = LoggerFactory.getLogger(IndexAction.class);
	private static final long serialVersionUID = 8424750786095800806L;
	
	private String user;
	private String password;
	private String newPassword;
	private String newRePassword;
	private boolean accessGranted;
	private String jsonErrorMessage;
	private Map<String,Object> session;
	
	public IndexAction () 	{
		
		session = new HashMap<String, Object>();
	}
	
	public String home() {
		return ActionSupport.SUCCESS;
	}

	// Log Out user
	@SuppressWarnings("unchecked")
	public String logOut() throws JSONException {

		if (session.get("user") != null) {
			
			HashMap<String,Object> userMap = (HashMap<String,Object>)JSONUtil.deserialize((String)session.get("user"));
			String user = (String)userMap.get("user");			
			
			session.remove("user");
			
			jsonErrorMessage = String.format(getText("login.access.loggedout"), user);
			LOG.info(jsonErrorMessage);
			
			GeneralEntitiesHelper.saveAuditLog(new AuditLog(EventType.__LOGOUT, user, jsonErrorMessage));
		}
		
		return ActionSupport.SUCCESS;
	}
	
	public String verifyLogonAuthorization() throws JSONException {
		
		if (StringUtil.isNullOrEmpty(getUser()))
			return ActionSupport.SUCCESS;
		
		User user = GeneralEntitiesHelper.getUsers().get(getUser());
		String CipherKey = GeneralEntitiesHelper.getParameters().get("APP_COMPANY_KEY").getValue();
		
		if (user == null) 
			jsonErrorMessage = String.format(getText("login.invalid.user"), getUser());
		else {
		
			String webUser = user.getUser().trim(),
				   webPassword = user.getPassword().trim();
			
			accessGranted = false;
			
			try {
	
				setPassword ( StringUtil.paddingRigth(getPassword(), (int) Math.ceil(getPassword().length() / 8.0) * 8) );
				setPassword ( StringUtil.encrypt(password, CipherKey) );

				if ( !StringUtil.isNullOrEmpty(webUser) && !StringUtil.isNullOrEmpty(webPassword) )
				{
					// Validates the user
					if ( !getUser().equals(webUser))
						jsonErrorMessage = String.format(getText("login.invalid.user"), getUser());
					else
						// Validates the password
						if ( !getPassword().equals(webPassword))
							jsonErrorMessage = getText("login.invalid.password");
						else 	{
							// Credentials are correct
							accessGranted = true;
							
							session.put("user",  JSONUtil.serialize(user));		
							session.put("downloadFinish", "0");
		
							jsonErrorMessage = String.format(getText("login.access.granted"), getUser());
							
						}
				} 
				else 
					jsonErrorMessage = getText("login.no.parameters");

			} catch (NoSuchAlgorithmException 
					| NoSuchPaddingException
					| InvalidKeyException 
					| IllegalBlockSizeException
					| BadPaddingException e) {
				
				jsonErrorMessage = String.format(getText("error.cipher.general "), getUser());
			}
		}

		try {
			
			GeneralEntitiesHelper.saveAuditLog(new AuditLog(EventType.__LOGIN, getUser(), jsonErrorMessage));
		} catch (JpaSystemException e) {
			jsonErrorMessage = getText("error.database.general");
			accessGranted = false;
		}
		LOG.info(StringUtil.getMessageId() + jsonErrorMessage);
		
		return ActionSupport.SUCCESS;
	}
	
	public String changePassword() {
		User user = GeneralEntitiesHelper.getUsers().get(getUser());
		String CipherKey = GeneralEntitiesHelper.getParameters().get("APP_COMPANY_KEY").getValue();
		
		if (user == null) 
			jsonErrorMessage = getText("login.invalid.user");
		else {
		
			String webUser = user.getUser().trim(),
				   webPassword = user.getPassword().trim();
			
			accessGranted = false;
			
			try {
	
				setPassword ( StringUtil.paddingRigth(getPassword(), (int) Math.ceil(getPassword().length() / 8.0) * 8) );
				setPassword ( StringUtil.encrypt(password, CipherKey) );
			
			// Validates the user
			if ( !getUser().equals(webUser))
				jsonErrorMessage = String.format(getText("login.invalid.user"), getUser());
			else
				// Validates the password
				if ( !getPassword().equals(webPassword))
					jsonErrorMessage = getText("login.invalid.password");
				else
					// Validates that the new password is not equal to previous
					if ( getPassword().equals(getNewPassword()))
						jsonErrorMessage = getText("login.change.newpasswd"); 
					else
						// Validates the re-entry of the new password
						if ( !getNewPassword().equals(getNewRePassword()))
							jsonErrorMessage = getText("login.change.repasswd");
						else
						{
							// The password change is correct
							try {
								// Save the new password
								newPassword = StringUtil.paddingRigth(newPassword, (int) Math.ceil(newPassword.length() / 8.0) * 8);
								newPassword = StringUtil.encrypt(newPassword, CipherKey).trim();
								
								user.setPassword(newPassword);
								
								GeneralEntitiesHelper.saveUser(user);
								GeneralEntitiesHelper.loadUsers();
	
								accessGranted = true;
								jsonErrorMessage = getText("login.password.changed");
								
							} catch (Exception e) {
								jsonErrorMessage = getText("error.save.password");
								LOG.error(jsonErrorMessage, e);
							}
						}
			} catch (NoSuchAlgorithmException 
					| NoSuchPaddingException
					| InvalidKeyException 
					| IllegalBlockSizeException
					| BadPaddingException e) {
				
				jsonErrorMessage = String.format(getText("error.cipher.general "), getUser());
			}
		}

		try {
			
			GeneralEntitiesHelper.saveAuditLog(new AuditLog(EventType.__CHANGEPASSWORD, getUser(), jsonErrorMessage));
		} catch (JpaSystemException e) {
			jsonErrorMessage = getText("error.database.general");
			accessGranted = false;
		}
		LOG.info(StringUtil.getMessageId() + jsonErrorMessage);
		
		return ActionSupport.SUCCESS;
	}

	
	public String close() {
		return ActionSupport.INPUT;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the accessGranted
	 */
	public boolean isAccessGranted() {
		return accessGranted;
	}

	/**
	 * @param accessGranted the accessGranted to set
	 */
	public void setAccessGranted(boolean accessGranted) {
		this.accessGranted = accessGranted;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewRePassword() {
		return newRePassword;
	}

	public void setNewRePassword(String newRePassword) {
		this.newRePassword = newRePassword;
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
