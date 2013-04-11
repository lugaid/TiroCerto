package br.com.tirocerto.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
public class Modality implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6111862081872364561L;

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
	
	@Min(value = 1)
	@Max(value = 100)
	@Column()
	private Integer maxSeries;
	
	@Min(value = 1)
	@Column()
	private Integer targetXValue;
	
	@Min(value = 1)
	@Max(value = 10)
	@Column()
	private Integer targetQtyDivisions;

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
		this.description = description == null ? null : description.toUpperCase();
	}

	public ModalityPointType getModalityPointType() {
		return modalityPointType;
	}

	public void setModalityPointType(ModalityPointType modalityPointType) {
		this.modalityPointType = modalityPointType;
	}

	public Integer getMaxSeries() {
		return maxSeries;
	}

	public void setMaxSeries(Integer maxSeries) {
		this.maxSeries = maxSeries;
	}

	public Integer getTargetXValue() {
		return targetXValue;
	}

	public void setTargetXValue(Integer targetXValue) {
		this.targetXValue = targetXValue;
	}

	public Integer getTargetQtyDivisions() {
		return targetQtyDivisions;
	}

	public void setTargetQtyDivisions(Integer targetQtyDivisions) {
		this.targetQtyDivisions = targetQtyDivisions;
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
