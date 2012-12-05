package br.com.tirocerto.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
public class Championship {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	@Length(min = 10, max = 50)
	@Column(length = 50)
	private String description;

	@Valid
	@OneToMany(mappedBy = "championship", targetEntity = ChampionshipStage.class, cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ChampionshipStage> championshipStage;
	
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

	public List<ChampionshipStage> getChampionshipStage() {
		return championshipStage;
	}

	public void setChampionshipStage(List<ChampionshipStage> championshipStage) {
		this.championshipStage = championshipStage;
	}
}
