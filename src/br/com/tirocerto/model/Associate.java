package br.com.tirocerto.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "cr" })) 
public class Associate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  
	private Long id;
	
	@Column(length=15)
	private String cr;
	
	@Column(length=50)
	private String name;
	
	@Column(length=50)
	private String email;
	
	@Column(length=50)
	private String password;
	
	@Column
	private AssociateType associateType;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public AssociateType getAssociateType() {
		return associateType;
	}

	public void setAssociateType(AssociateType associateType) {
		this.associateType = associateType;
	}

	public enum AssociateType {
		VISITOR, ASSOCIATE;
	}
}
