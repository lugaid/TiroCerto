package br.com.tirocerto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
public class ChampionshipRanking implements Serializable {
	private static final long serialVersionUID = -1487657636592211053L;

	@Id
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private ChampionshipEnrolled championshipEnrolled;
	
	@Column()
	@Min(value = 1)
	@NotNull
	private Integer position;

	public ChampionshipEnrolled getChampionshipEnrolled() {
		return championshipEnrolled;
	}

	public void setChampionshipEnrolled(ChampionshipEnrolled championshipEnrolled) {
		this.championshipEnrolled = championshipEnrolled;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
}
