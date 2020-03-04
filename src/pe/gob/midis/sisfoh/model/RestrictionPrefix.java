package pe.gob.midis.sisfoh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "grl_restriction_prefix")
public class RestrictionPrefix {
	
	@Id
	@Column(name = "code", nullable = false)
	private String code;
	
	@Column(name = "description", nullable = false)
	private String description;

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


}
