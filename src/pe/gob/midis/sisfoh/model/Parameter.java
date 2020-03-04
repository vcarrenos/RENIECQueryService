package pe.gob.midis.sisfoh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grl_parameter")
public class Parameter {
	
	@Id
	@Column(name = "code", nullable = false)
	private String code;
	
	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "value", nullable = false)
	private String value;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "visible", nullable = false)
	private boolean visible;

	@Column(name = "removable", nullable = false)
	private boolean removable;

	public Parameter() {
		this("", "", "", false, false, false);
	}

	public Parameter(String code, String description, String value,
			boolean enabled, boolean visible, boolean removable) {
		
		this.code        = code;
		this.description = description;
		this.value       = value;
		this.enabled     = enabled;
		this.visible     = visible;
		this.removable   = removable;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isRemovable() {
		return removable;
	}

	public void setRemovable(boolean removable) {
		this.removable = removable;
	}


	
	
}
