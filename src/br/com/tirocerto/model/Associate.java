package br.com.tirocerto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
public class Associate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Length(min=5, max=15)
	@Column(unique=true, length = 15)
	private String cr;

	@NotEmpty
	@Length(min=5, max=15)
	@Column(length = 50)
	private String name;

	@Email
	@NotEmpty
	@Column(unique=true, length = 50)
	private String email;

	@Column
	private AssociateType associateType;

	@Column
	private Boolean adminAccess;

	@Length(min=5, max=50)
	@Column(length = 50)
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCr() {
		return cr;
	}

	public void setCr(String cr) {
		this.cr = cr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AssociateType getAssociateType() {
		return associateType;
	}

	public void setAssociateType(AssociateType associateType) {
		this.associateType = associateType;
	}

	public Boolean getAdminAccess() {
		return adminAccess;
	}

	public void setAdminAccess(Boolean adminAccess) {
		this.adminAccess = adminAccess;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public enum AssociateType {
		VISITOR, ASSOCIATE;
	}
}
