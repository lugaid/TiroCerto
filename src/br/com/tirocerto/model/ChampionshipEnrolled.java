package br.com.tirocerto.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
public class ChampionshipEnrolled implements Serializable {
	private static final long serialVersionUID = -1275977111673169430L;

	@Id
	@ManyToOne(cascade = CascadeType.ALL , fetch=FetchType.EAGER)
	@JoinColumn(nullable=false)
	private Championship championship;
	
	@Id
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(nullable=false)
	private Associate associate;
	
	@Valid
	@OneToMany(mappedBy = "championshipEnrolled", targetEntity = ChampionshipResult.class, cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ChampionshipResult> championshipResult;
	
	@Valid
	@OneToMany(mappedBy = "championshipEnrolled", targetEntity = ChampionshipStageRanking.class, cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ChampionshipStageRanking> championshipStageRanking;
	
	@Valid
	@OneToMany(mappedBy = "championshipEnrolled", targetEntity = ChampionshipRanking.class, cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ChampionshipRanking> championshipRanking;
	
	public Championship getChampionship() {
		return championship;
	}

	public void setChampionship(Championship championship) {
		this.championship = championship;
	}

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
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

	public List<ChampionshipRanking> getChampionshipRanking() {
		return championshipRanking;
	}

	public void setChampionshipRanking(List<ChampionshipRanking> championshipRanking) {
		this.championshipRanking = championshipRanking;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((associate == null) ? 0 : associate.hashCode());
		result = prime * result
				+ ((championship == null) ? 0 : championship.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ChampionshipEnrolled))
			return false;
		ChampionshipEnrolled other = (ChampionshipEnrolled) obj;
		if (associate == null) {
			if (other.associate != null)
				return false;
		} else if (!associate.equals(other.associate))
			return false;
		if (championship == null) {
			if (other.championship != null)
				return false;
		} else if (!championship.equals(other.championship))
			return false;
		return true;
	}
}
