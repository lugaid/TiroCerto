package br.com.tirocerto.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
public class Championship implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7238566883027860110L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	@NotNull
	@Length(min = 10, max = 50)
	@Column(length = 50)
	private String description;

	@Valid
	@OneToMany(mappedBy = "championship", targetEntity = ChampionshipStage.class, cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ChampionshipStage> championshipStages;

	@NotNull
	@OneToOne
	@JoinColumn
	private Modality modality;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="championship")
	private Set<ChampionshipEnrolled> championshipEnrolled;
	
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

	public List<ChampionshipStage> getChampionshipStages() {
		return championshipStages;
	}

	public void setChampionshipStages(List<ChampionshipStage> championshipStages) {
		this.championshipStages = championshipStages;
	}

	public Modality getModality() {
		return modality;
	}

	public void setModality(Modality modality) {
		this.modality = modality;
	}
	
	public Set<ChampionshipEnrolled> getChampionshipEnrolled() {
		return championshipEnrolled;
	}

	public void setChampionshipEnrolled(Set<ChampionshipEnrolled> championshipEnrolled) {
		this.championshipEnrolled = championshipEnrolled;
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
		if (!(obj instanceof Championship))
			return false;
		Championship other = (Championship) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
