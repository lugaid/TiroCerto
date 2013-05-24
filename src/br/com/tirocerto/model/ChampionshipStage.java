package br.com.tirocerto.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
public class ChampionshipStage implements Serializable {
	private static final long serialVersionUID = -1538408257290639939L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional=false)
	@JoinColumn
	private Championship championship;
	
	@NotEmpty
	@NotNull
	@Length(min = 10, max = 50)
	@Column(length = 50)
	private String description;
	
	@NotNull
	private Calendar date;

	@Valid
	@OneToMany(mappedBy = "championshipStage", targetEntity = ChampionshipResult.class, cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ChampionshipResult> championshipResult;
	
	@Valid
	@OneToMany(mappedBy = "championshipStage", targetEntity = ChampionshipStageRanking.class, cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ChampionshipStageRanking> championshipStageRanking;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Championship getChampionship() {
		return championship;
	}

	public void setChampionship(Championship championship) {
		this.championship = championship;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ChampionshipResult> getChampionshipResult() {
		return championshipResult;
	}

	public void setChampionshipResult(List<ChampionshipResult> championshipResult) {
		this.championshipResult = championshipResult;
	}

	public List<ChampionshipStageRanking> getChampionshipStageRanking() {
		return championshipStageRanking;
	}

	public void setChampionshipStageRanking(
			List<ChampionshipStageRanking> championshipStageRanking) {
		this.championshipStageRanking = championshipStageRanking;
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
		if (!(obj instanceof ChampionshipStage))
			return false;
		ChampionshipStage other = (ChampionshipStage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
