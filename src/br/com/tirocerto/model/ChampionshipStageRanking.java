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
public class ChampionshipStageRanking implements Serializable, Comparable<ChampionshipStageRanking> {
	private static final long serialVersionUID = 4325252866205951566L;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private ChampionshipEnrolled championshipEnrolled;

	@Id
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private ChampionshipStage championshipStage;
	
	@Column()
	@Min(value = 1)
	@NotNull
	private Integer position;

	@Min(value = 0)
	@Column()
	private Integer targetDivisionX = 0;

	@Min(value = 0)
	@Column()
	private Integer targetDivision10 = 0;

	@Min(value = 0)
	@Column()
	private Integer targetDivision9 = 0;

	@Min(value = 0)
	@Column()
	private Integer targetDivision8 = 0;

	@Min(value = 0)
	@Column()
	private Integer targetDivision7 = 0;

	@Min(value = 0)
	@Column()
	private Integer targetDivision6 = 0;

	@Min(value = 0)
	@Column()
	private Integer targetDivision5 = 0;

	@Min(value = 0)
	@Column()
	private Integer targetDivision4 = 0;

	@Min(value = 0)
	@Column()
	private Integer targetDivision3 = 0;

	@Min(value = 0)
	@Column()
	private Integer targetDivision2 = 0;

	@Min(value = 0)
	@Column()
	private Integer targetDivision1 = 0;

	@Min(value = 0)
	@Column()
	@NotNull()
	private Integer points = 0;
	
	@Min(value = 0)
	@Column()
	@NotNull()
	private Integer penalty = 0;

	@Column()
	@NotNull()
	private Integer total = 0;
	
	public ChampionshipStageRanking() {
		super();
	}
	
	public ChampionshipStageRanking(ChampionshipEnrolled championshipEnrolled,
			ChampionshipStage championshipStage, ChampionshipResult championshipResult) {
		super();
		this.championshipEnrolled = championshipEnrolled;
		this.championshipStage = championshipStage;
		sum(championshipResult);
	}

	
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

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getTargetDivisionX() {
		return targetDivisionX;
	}

	public void setTargetDivisionX(Integer targetDivisionX) {
		this.targetDivisionX = targetDivisionX;
	}

	public Integer getTargetDivision10() {
		return targetDivision10;
	}

	public void setTargetDivision10(Integer targetDivision10) {
		this.targetDivision10 = targetDivision10;
	}

	public Integer getTargetDivision9() {
		return targetDivision9;
	}

	public void setTargetDivision9(Integer targetDivision9) {
		this.targetDivision9 = targetDivision9;
	}

	public Integer getTargetDivision8() {
		return targetDivision8;
	}

	public void setTargetDivision8(Integer targetDivision8) {
		this.targetDivision8 = targetDivision8;
	}

	public Integer getTargetDivision7() {
		return targetDivision7;
	}

	public void setTargetDivision7(Integer targetDivision7) {
		this.targetDivision7 = targetDivision7;
	}

	public Integer getTargetDivision6() {
		return targetDivision6;
	}

	public void setTargetDivision6(Integer targetDivision6) {
		this.targetDivision6 = targetDivision6;
	}

	public Integer getTargetDivision5() {
		return targetDivision5;
	}

	public void setTargetDivision5(Integer targetDivision5) {
		this.targetDivision5 = targetDivision5;
	}

	public Integer getTargetDivision4() {
		return targetDivision4;
	}

	public void setTargetDivision4(Integer targetDivision4) {
		this.targetDivision4 = targetDivision4;
	}

	public Integer getTargetDivision3() {
		return targetDivision3;
	}

	public void setTargetDivision3(Integer targetDivision3) {
		this.targetDivision3 = targetDivision3;
	}

	public Integer getTargetDivision2() {
		return targetDivision2;
	}

	public void setTargetDivision2(Integer targetDivision2) {
		this.targetDivision2 = targetDivision2;
	}

	public Integer getTargetDivision1() {
		return targetDivision1;
	}

	public void setTargetDivision1(Integer targetDivision1) {
		this.targetDivision1 = targetDivision1;
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public void sum(ChampionshipResult championshipResult) {
		if(championshipResult.getTargetDivisionX() != null) {
		    this.targetDivisionX += championshipResult.getTargetDivisionX();
		}

		if(championshipResult.getTargetDivision10() != null) {
		    this.targetDivision10 += championshipResult.getTargetDivision10();
		}

		if(championshipResult.getTargetDivision9() != null) {
		    this.targetDivision9 += championshipResult.getTargetDivision9();
		}

		if(championshipResult.getTargetDivision8() != null) {
		    this.targetDivision8 += championshipResult.getTargetDivision8();
		}

		if(championshipResult.getTargetDivision7() != null) {
		    this.targetDivision7 += championshipResult.getTargetDivision7();
		}

		if(championshipResult.getTargetDivision6() != null) {
		    this.targetDivision6 += championshipResult.getTargetDivision6();
		}

		if(championshipResult.getTargetDivision5() != null) {
		    this.targetDivision5 += championshipResult.getTargetDivision5();
		}

		if(championshipResult.getTargetDivision4() != null) {
		    this.targetDivision4 += championshipResult.getTargetDivision4();
		}

		if(championshipResult.getTargetDivision3() != null) {
		    this.targetDivision3 += championshipResult.getTargetDivision3();
		}

		if(championshipResult.getTargetDivision2() != null) {
		    this.targetDivision2 += championshipResult.getTargetDivision2();
		}

		if(championshipResult.getTargetDivision1() != null) {
		    this.targetDivision1 += championshipResult.getTargetDivision1();
		}

		if(championshipResult.getPoints() != null) {
		    this.points += championshipResult.getPoints();
		}

		if(championshipResult.getPenalty() != null) {
		    this.penalty += championshipResult.getPenalty();
		}

		if(championshipResult.getTotal() != null) {
		    this.total += championshipResult.getTotal();
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((championshipEnrolled == null) ? 0 : championshipEnrolled
						.hashCode());
		result = prime
				* result
				+ ((championshipStage == null) ? 0 : championshipStage
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ChampionshipStageRanking))
			return false;
		ChampionshipStageRanking other = (ChampionshipStageRanking) obj;
		if (championshipEnrolled == null) {
			if (other.championshipEnrolled != null)
				return false;
		} else if (!championshipEnrolled.equals(other.championshipEnrolled))
			return false;
		if (championshipStage == null) {
			if (other.championshipStage != null)
				return false;
		} else if (!championshipStage.equals(other.championshipStage))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(ChampionshipStageRanking championshipStageRanking) {
		if (this.getTotal() != null && championshipStageRanking.getTotal() == null) {
			return 1;
		}

		if (this.getTotal() == null && championshipStageRanking.getTotal() != null) {
			return -1;
		}

		if (this.getTotal() != null && championshipStageRanking.getTotal() != null
				&& this.getTotal() > championshipStageRanking.getTotal()) {
			return 1;
		}

		if (this.getTotal() != null && championshipStageRanking.getTotal() != null
				&& this.getTotal() < championshipStageRanking.getTotal()) {
			return -1;
		}
		if (this.getTargetDivisionX() != null
				&& championshipStageRanking.getTargetDivisionX() == null) {
			return 1;
		}

		if (this.getTargetDivisionX() == null
				&& championshipStageRanking.getTargetDivisionX() != null) {
			return -1;
		}

		if (this.getTargetDivisionX() != null
				&& championshipStageRanking.getTargetDivisionX() != null
				&& this.getTargetDivisionX() > championshipStageRanking
						.getTargetDivisionX()) {
			return 1;
		}

		if (this.getTargetDivisionX() != null
				&& championshipStageRanking.getTargetDivisionX() != null
				&& this.getTargetDivisionX() < championshipStageRanking
						.getTargetDivisionX()) {
			return -1;
		}
		if (this.getTargetDivision10() != null
				&& championshipStageRanking.getTargetDivision10() == null) {
			return 1;
		}

		if (this.getTargetDivision10() == null
				&& championshipStageRanking.getTargetDivision10() != null) {
			return -1;
		}

		if (this.getTargetDivision10() != null
				&& championshipStageRanking.getTargetDivision10() != null
				&& this.getTargetDivision10() > championshipStageRanking
						.getTargetDivision10()) {
			return 1;
		}

		if (this.getTargetDivision10() != null
				&& championshipStageRanking.getTargetDivision10() != null
				&& this.getTargetDivision10() < championshipStageRanking
						.getTargetDivision10()) {
			return -1;
		}
		if (this.getTargetDivision9() != null
				&& championshipStageRanking.getTargetDivision9() == null) {
			return 1;
		}

		if (this.getTargetDivision9() == null
				&& championshipStageRanking.getTargetDivision9() != null) {
			return -1;
		}

		if (this.getTargetDivision9() != null
				&& championshipStageRanking.getTargetDivision9() != null
				&& this.getTargetDivision9() > championshipStageRanking
						.getTargetDivision9()) {
			return 1;
		}

		if (this.getTargetDivision9() != null
				&& championshipStageRanking.getTargetDivision9() != null
				&& this.getTargetDivision9() < championshipStageRanking
						.getTargetDivision9()) {
			return -1;
		}
		if (this.getTargetDivision8() != null
				&& championshipStageRanking.getTargetDivision8() == null) {
			return 1;
		}

		if (this.getTargetDivision8() == null
				&& championshipStageRanking.getTargetDivision8() != null) {
			return -1;
		}

		if (this.getTargetDivision8() != null
				&& championshipStageRanking.getTargetDivision8() != null
				&& this.getTargetDivision8() > championshipStageRanking
						.getTargetDivision8()) {
			return 1;
		}

		if (this.getTargetDivision8() != null
				&& championshipStageRanking.getTargetDivision8() != null
				&& this.getTargetDivision8() < championshipStageRanking
						.getTargetDivision8()) {
			return -1;
		}
		if (this.getTargetDivision7() != null
				&& championshipStageRanking.getTargetDivision7() == null) {
			return 1;
		}

		if (this.getTargetDivision7() == null
				&& championshipStageRanking.getTargetDivision7() != null) {
			return -1;
		}

		if (this.getTargetDivision7() != null
				&& championshipStageRanking.getTargetDivision7() != null
				&& this.getTargetDivision7() > championshipStageRanking
						.getTargetDivision7()) {
			return 1;
		}

		if (this.getTargetDivision7() != null
				&& championshipStageRanking.getTargetDivision7() != null
				&& this.getTargetDivision7() < championshipStageRanking
						.getTargetDivision7()) {
			return -1;
		}
		if (this.getTargetDivision6() != null
				&& championshipStageRanking.getTargetDivision6() == null) {
			return 1;
		}

		if (this.getTargetDivision6() == null
				&& championshipStageRanking.getTargetDivision6() != null) {
			return -1;
		}

		if (this.getTargetDivision6() != null
				&& championshipStageRanking.getTargetDivision6() != null
				&& this.getTargetDivision6() > championshipStageRanking
						.getTargetDivision6()) {
			return 1;
		}

		if (this.getTargetDivision6() != null
				&& championshipStageRanking.getTargetDivision6() != null
				&& this.getTargetDivision6() < championshipStageRanking
						.getTargetDivision6()) {
			return -1;
		}
		if (this.getTargetDivision5() != null
				&& championshipStageRanking.getTargetDivision5() == null) {
			return 1;
		}

		if (this.getTargetDivision5() == null
				&& championshipStageRanking.getTargetDivision5() != null) {
			return -1;
		}

		if (this.getTargetDivision5() != null
				&& championshipStageRanking.getTargetDivision5() != null
				&& this.getTargetDivision5() > championshipStageRanking
						.getTargetDivision5()) {
			return 1;
		}

		if (this.getTargetDivision5() != null
				&& championshipStageRanking.getTargetDivision5() != null
				&& this.getTargetDivision5() < championshipStageRanking
						.getTargetDivision5()) {
			return -1;
		}
		if (this.getTargetDivision4() != null
				&& championshipStageRanking.getTargetDivision4() == null) {
			return 1;
		}

		if (this.getTargetDivision4() == null
				&& championshipStageRanking.getTargetDivision4() != null) {
			return -1;
		}

		if (this.getTargetDivision4() != null
				&& championshipStageRanking.getTargetDivision4() != null
				&& this.getTargetDivision4() > championshipStageRanking
						.getTargetDivision4()) {
			return 1;
		}

		if (this.getTargetDivision4() != null
				&& championshipStageRanking.getTargetDivision4() != null
				&& this.getTargetDivision4() < championshipStageRanking
						.getTargetDivision4()) {
			return -1;
		}
		if (this.getTargetDivision3() != null
				&& championshipStageRanking.getTargetDivision3() == null) {
			return 1;
		}

		if (this.getTargetDivision3() == null
				&& championshipStageRanking.getTargetDivision3() != null) {
			return -1;
		}

		if (this.getTargetDivision3() != null
				&& championshipStageRanking.getTargetDivision3() != null
				&& this.getTargetDivision3() > championshipStageRanking
						.getTargetDivision3()) {
			return 1;
		}

		if (this.getTargetDivision3() != null
				&& championshipStageRanking.getTargetDivision3() != null
				&& this.getTargetDivision3() < championshipStageRanking
						.getTargetDivision3()) {
			return -1;
		}
		if (this.getTargetDivision2() != null
				&& championshipStageRanking.getTargetDivision2() == null) {
			return 1;
		}

		if (this.getTargetDivision2() == null
				&& championshipStageRanking.getTargetDivision2() != null) {
			return -1;
		}

		if (this.getTargetDivision2() != null
				&& championshipStageRanking.getTargetDivision2() != null
				&& this.getTargetDivision2() > championshipStageRanking
						.getTargetDivision2()) {
			return 1;
		}

		if (this.getTargetDivision2() != null
				&& championshipStageRanking.getTargetDivision2() != null
				&& this.getTargetDivision2() < championshipStageRanking
						.getTargetDivision2()) {
			return -1;
		}
		if (this.getTargetDivision1() != null
				&& championshipStageRanking.getTargetDivision1() == null) {
			return 1;
		}

		if (this.getTargetDivision1() == null
				&& championshipStageRanking.getTargetDivision1() != null) {
			return -1;
		}

		if (this.getTargetDivision1() != null
				&& championshipStageRanking.getTargetDivision1() != null
				&& this.getTargetDivision1() > championshipStageRanking
						.getTargetDivision1()) {
			return 1;
		}

		if (this.getTargetDivision1() != null
				&& championshipStageRanking.getTargetDivision1() != null
				&& this.getTargetDivision1() < championshipStageRanking
						.getTargetDivision1()) {
			return -1;
		}

		return 0;
	}
}
