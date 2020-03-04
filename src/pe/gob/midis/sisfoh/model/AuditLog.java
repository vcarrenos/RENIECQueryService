package pe.gob.midis.sisfoh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import pe.gob.midis.sisfoh.type.EventType;
import pe.gob.midis.sisfoh.utils.DateUtil;
import pe.gob.midis.sisfoh.utils.SystemConfigHelper;


@Entity
@Table(name = "grl_audit_log")
public class AuditLog {
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "event", nullable = false)
	private String event;
	
	@Column(name = "user_name", nullable = false)
	private String userName;
	
	@Column(name = "event_date", nullable = false)
	private String eventDate;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "user_os", nullable = false)
	private String userOS;
	
	@Column(name = "ip", nullable = false)
	private String ip;

	public AuditLog() {
		this(EventType.__LOGIN, "", "");
	}

	public AuditLog(EventType event, String userName, String description) {
		super();
		this.event = event.getEventCode();
		this.userName = userName;
		this.eventDate = DateUtil.getDateTime();
		this.description = description;
		this.userOS = SystemConfigHelper.getOsUser();
		this.ip = SystemConfigHelper.getHostAddress();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserOS() {
		return userOS;
	}

	public void setUserOS(String userOS) {
		this.userOS = userOS;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
