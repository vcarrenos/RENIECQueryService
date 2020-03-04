package pe.gob.midis.sisfoh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grl_user")
public class User {

    @Id
    @Column(name = "dni", nullable = false)
	String dni;

    @Column(name = "ap_first", nullable = false)
	String apFirst;

    @Column(name = "ap_last", nullable = false)
	String apLast;

    @Column(name = "names", nullable = false)
	String names;

    @Column(name = "user", nullable = false)
	String user;

    @Column(name = "password", nullable = false)
	String password;

    @Column(name = "birth_date", nullable = false)
    String birthDate;
    
    @Column(name = "max_quota", nullable = false)
    Integer maxQuota;    
    
    @Column(name = "expiration_date", nullable = false)
    String expirationDate;


    @Column(name = "enabled", nullable = false)
	boolean enabled;
    
    public User() throws Exception {
    	this("", "", "", "", 0, "", "", false);
    }

	public User(String dni, String apFirst, String apLast, String names,
			Integer maxQuota, String expirationDate, String birthDate,
			boolean enabled) throws Exception {
		super();
		this.dni = dni;
		this.apFirst = apFirst;
		this.apLast = apLast;
		this.names = names;
		this.maxQuota = maxQuota;
		this.expirationDate = expirationDate;
		this.birthDate = birthDate;
		this.enabled = enabled;
		
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApFirst() {
		return apFirst;
	}

	public void setApFirst(String apFirst) {
		this.apFirst = apFirst;
	}

	public String getApLast() {
		return apLast;
	}

	public void setApLast(String apLast) {
		this.apLast = apLast;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(Integer maxQuota) {
		this.maxQuota = maxQuota;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

    
}
