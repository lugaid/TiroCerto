package br.com.tirocerto.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
public class ScoreboardStage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(nullable=false)
	private Scoreboard scoreboard;
	
	@Id
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(nullable=false)
	private ChampionshipStage championshipStage;

	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
	}

	public ChampionshipStage getChampionshipStage() {
		return championshipStage;
	}

	public void setChampionshipStage(ChampionshipStage championshipStage) {
		this.championshipStage = championshipStage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((championshipStage == null) ? 0 : championshipStage
						.hashCode());
		result = prime * result
				+ ((scoreboard == null) ? 0 : scoreboard.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreboardStage other = (ScoreboardStage) obj;
		if (championshipStage == null) {
			if (other.championshipStage != null)
				return false;
		} else if (!championshipStage.equals(other.championshipStage))
			return false;
		if (scoreboard == null) {
			if (other.scoreboard != null)
				return false;
		} else if (!scoreboard.equals(other.scoreboard))
			return false;
		return true;
	}
}
