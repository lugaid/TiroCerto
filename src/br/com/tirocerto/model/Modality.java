package br.com.tirocerto.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
public class Modality {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Length(min = 10, max = 50)
	@Column(length = 50)
	private String description;

	@Column
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private ModalityPointType modalityPointType;

	@Column
	@OneToMany(mappedBy = "modality", targetEntity = ModalityTargetDivision.class, cascade = CascadeType.ALL)
	private List<ModalityTargetDivision> modalityTargetDivisions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ModalityPointType getModalityPointType() {
		return modalityPointType;
	}

	public void setModalityPointType(ModalityPointType modalityPointType) {
		this.modalityPointType = modalityPointType;
	}

	public List<ModalityTargetDivision> getModalityTargetDivisions() {
		return modalityTargetDivisions;
	}

	public void setModalityTargetDivisions(
			List<ModalityTargetDivision> modalityTargetDivisions) {
		this.modalityTargetDivisions = modalityTargetDivisions;
	}

	public enum ModalityPointType {
		TARGET, POINT;
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
		if (!(obj instanceof Modality))
			return false;
		Modality other = (Modality) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
