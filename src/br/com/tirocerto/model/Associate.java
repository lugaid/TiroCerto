package br.com.tirocerto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
=======
import javax.validation.constraints.NotNull;
>>>>>>> dd1f53e657aa583b099ddeb5cd701e426f4a33db

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
public class Associate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Length(min = 1, max = 15)
	@Column(length = 15)
	private String cr;

	@NotEmpty
	@Length(min = 10, max = 50)
	@Column(length = 50)
	private String name;

	@Email
	@NotEmpty
	@Length(min = 5, max = 50)
	@Column(unique = true, length = 50)
	private String email;

	@Column
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private AssociateType associateType;

	@Column
	private Boolean adminAccess;

	@Length(min = 5, max = 50)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Associate))
			return false;
		Associate other = (Associate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
