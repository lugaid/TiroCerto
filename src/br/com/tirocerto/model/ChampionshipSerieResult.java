package br.com.tirocerto.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
public class ChampionshipSerieResult implements Serializable {
	private static final long serialVersionUID = 3659419224600254133L;
	
	@Id
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({@JoinColumn(name="championship_id", referencedColumnName="championship_id", nullable=false), 
			@JoinColumn(name="associate_id", referencedColumnName="associate_id", nullable=false)})
	private ChampionshipEnrolled championshipEnrolled;
	
	@Id
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(nullable=false)
	private ChampionshipStage championshipStage;
	
	@Id
	@Min(value = 1)
	@Max(value = 99999999)
	private Integer serie;
	
	@Column
	@Min(value = 1)
	@Max(value = 99999999)
	private Integer points;
	
	@Column
	@Min(value = 1)
	@Max(value = 99999999)
	private Integer penalty;

	public ChampionshipEnrolled getChampionshipEnrolled() {
		return championshipEnrolled;
	}

	public void setChampionshipEnrolled(ChampionshipEnrolled championshipEnrolled) {
		this.championshipEnrolled = championshipEnrolled;
	}

	public ChampionshipStage getChampionshipStage() {
		return championshipStage;
	}

	public void setChampionshipStage(ChampionshipStage championshipStage) {
		this.championshipStage = championshipStage;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getPenalty() {
		return penalty;
	}

	public void setPenalty(Integer penalty) {
		this.penalty = penalty;
	}
}
