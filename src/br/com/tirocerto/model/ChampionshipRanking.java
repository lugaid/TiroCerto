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
public class ChampionshipRanking implements Serializable, Comparable<ChampionshipRanking> {
	private static final long serialVersionUID = -1487657636592211053L;

	@Id
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private ChampionshipEnrolled championshipEnrolled;

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

	public ChampionshipRanking() {
		super();
	}

	public ChampionshipRanking(ChampionshipEnrolled championshipEnrolled,
			ChampionshipStageRanking championshipStageRanking) {
		super();
		this.championshipEnrolled = championshipEnrolled;
		sum(championshipStageRanking);
	}
	
	public ChampionshipEnrolled getChampionshipEnrolled() {
		return championshipEnrolled;
	}

	public void setChampionshipEnrolled(
			ChampionshipEnrolled championshipEnrolled) {
		this.championshipEnrolled = championshipEnrolled;
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
	
	public void sum(ChampionshipStageRanking championshipStageRanking) {
		if(championshipStageRanking.getTargetDivisionX() != null) {
		    this.targetDivisionX += championshipStageRanking.getTargetDivisionX();
		}

		if(championshipStageRanking.getTargetDivision10() != null) {
		    this.targetDivision10 += championshipStageRanking.getTargetDivision10();
		}

		if(championshipStageRanking.getTargetDivision9() != null) {
		    this.targetDivision9 += championshipStageRanking.getTargetDivision9();
		}

		if(championshipStageRanking.getTargetDivision8() != null) {
		    this.targetDivision8 += championshipStageRanking.getTargetDivision8();
		}

		if(championshipStageRanking.getTargetDivision7() != null) {
		    this.targetDivision7 += championshipStageRanking.getTargetDivision7();
		}

		if(championshipStageRanking.getTargetDivision6() != null) {
		    this.targetDivision6 += championshipStageRanking.getTargetDivision6();
		}

		if(championshipStageRanking.getTargetDivision5() != null) {
		    this.targetDivision5 += championshipStageRanking.getTargetDivision5();
		}

		if(championshipStageRanking.getTargetDivision4() != null) {
		    this.targetDivision4 += championshipStageRanking.getTargetDivision4();
		}

		if(championshipStageRanking.getTargetDivision3() != null) {
		    this.targetDivision3 += championshipStageRanking.getTargetDivision3();
		}

		if(championshipStageRanking.getTargetDivision2() != null) {
		    this.targetDivision2 += championshipStageRanking.getTargetDivision2();
		}

		if(championshipStageRanking.getTargetDivision1() != null) {
		    this.targetDivision1 += championshipStageRanking.getTargetDivision1();
		}

		if(championshipStageRanking.getPoints() != null) {
		    this.points += championshipStageRanking.getPoints();
		}

		if(championshipStageRanking.getPenalty() != null) {
		    this.penalty += championshipStageRanking.getPenalty();
		}

		if(championshipStageRanking.getTotal() != null) {
		    this.total += championshipStageRanking.getTotal();
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
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ChampionshipRanking))
			return false;
		ChampionshipRanking other = (ChampionshipRanking) obj;
		if (championshipEnrolled == null) {
			if (other.championshipEnrolled != null)
				return false;
		} else if (!championshipEnrolled.equals(other.championshipEnrolled))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(ChampionshipRanking championshipRanking) {
		if (this.getTotal() != null && championshipRanking.getTotal() == null) {
			return 1;
		}

		if (this.getTotal() == null && championshipRanking.getTotal() != null) {
			return -1;
		}

		if (this.getTotal() != null && championshipRanking.getTotal() != null
				&& this.getTotal() > championshipRanking.getTotal()) {
			return 1;
		}

		if (this.getTotal() != null && championshipRanking.getTotal() != null
				&& this.getTotal() < championshipRanking.getTotal()) {
			return -1;
		}
		if (this.getTargetDivisionX() != null
				&& championshipRanking.getTargetDivisionX() == null) {
			return 1;
		}

		if (this.getTargetDivisionX() == null
				&& championshipRanking.getTargetDivisionX() != null) {
			return -1;
		}

		if (this.getTargetDivisionX() != null
				&& championshipRanking.getTargetDivisionX() != null
				&& this.getTargetDivisionX() > championshipRanking
						.getTargetDivisionX()) {
			return 1;
		}

		if (this.getTargetDivisionX() != null
				&& championshipRanking.getTargetDivisionX() != null
				&& this.getTargetDivisionX() < championshipRanking
						.getTargetDivisionX()) {
			return -1;
		}
		if (this.getTargetDivision10() != null
				&& championshipRanking.getTargetDivision10() == null) {
			return 1;
		}

		if (this.getTargetDivision10() == null
				&& championshipRanking.getTargetDivision10() != null) {
			return -1;
		}

		if (this.getTargetDivision10() != null
				&& championshipRanking.getTargetDivision10() != null
				&& this.getTargetDivision10() > championshipRanking
						.getTargetDivision10()) {
			return 1;
		}

		if (this.getTargetDivision10() != null
				&& championshipRanking.getTargetDivision10() != null
				&& this.getTargetDivision10() < championshipRanking
						.getTargetDivision10()) {
			return -1;
		}
		if (this.getTargetDivision9() != null
				&& championshipRanking.getTargetDivision9() == null) {
			return 1;
		}

		if (this.getTargetDivision9() == null
				&& championshipRanking.getTargetDivision9() != null) {
			return -1;
		}

		if (this.getTargetDivision9() != null
				&& championshipRanking.getTargetDivision9() != null
				&& this.getTargetDivision9() > championshipRanking
						.getTargetDivision9()) {
			return 1;
		}

		if (this.getTargetDivision9() != null
				&& championshipRanking.getTargetDivision9() != null
				&& this.getTargetDivision9() < championshipRanking
						.getTargetDivision9()) {
			return -1;
		}
		if (this.getTargetDivision8() != null
				&& championshipRanking.getTargetDivision8() == null) {
			return 1;
		}

		if (this.getTargetDivision8() == null
				&& championshipRanking.getTargetDivision8() != null) {
			return -1;
		}

		if (this.getTargetDivision8() != null
				&& championshipRanking.getTargetDivision8() != null
				&& this.getTargetDivision8() > championshipRanking
						.getTargetDivision8()) {
			return 1;
		}

		if (this.getTargetDivision8() != null
				&& championshipRanking.getTargetDivision8() != null
				&& this.getTargetDivision8() < championshipRanking
						.getTargetDivision8()) {
			return -1;
		}
		if (this.getTargetDivision7() != null
				&& championshipRanking.getTargetDivision7() == null) {
			return 1;
		}

		if (this.getTargetDivision7() == null
				&& championshipRanking.getTargetDivision7() != null) {
			return -1;
		}

		if (this.getTargetDivision7() != null
				&& championshipRanking.getTargetDivision7() != null
				&& this.getTargetDivision7() > championshipRanking
						.getTargetDivision7()) {
			return 1;
		}

		if (this.getTargetDivision7() != null
				&& championshipRanking.getTargetDivision7() != null
				&& this.getTargetDivision7() < championshipRanking
						.getTargetDivision7()) {
			return -1;
		}
		if (this.getTargetDivision6() != null
				&& championshipRanking.getTargetDivision6() == null) {
			return 1;
		}

		if (this.getTargetDivision6() == null
				&& championshipRanking.getTargetDivision6() != null) {
			return -1;
		}

		if (this.getTargetDivision6() != null
				&& championshipRanking.getTargetDivision6() != null
				&& this.getTargetDivision6() > championshipRanking
						.getTargetDivision6()) {
			return 1;
		}

		if (this.getTargetDivision6() != null
				&& championshipRanking.getTargetDivision6() != null
				&& this.getTargetDivision6() < championshipRanking
						.getTargetDivision6()) {
			return -1;
		}
		if (this.getTargetDivision5() != null
				&& championshipRanking.getTargetDivision5() == null) {
			return 1;
		}

		if (this.getTargetDivision5() == null
				&& championshipRanking.getTargetDivision5() != null) {
			return -1;
		}

		if (this.getTargetDivision5() != null
				&& championshipRanking.getTargetDivision5() != null
				&& this.getTargetDivision5() > championshipRanking
						.getTargetDivision5()) {
			return 1;
		}

		if (this.getTargetDivision5() != null
				&& championshipRanking.getTargetDivision5() != null
				&& this.getTargetDivision5() < championshipRanking
						.getTargetDivision5()) {
			return -1;
		}
		if (this.getTargetDivision4() != null
				&& championshipRanking.getTargetDivision4() == null) {
			return 1;
		}

		if (this.getTargetDivision4() == null
				&& championshipRanking.getTargetDivision4() != null) {
			return -1;
		}

		if (this.getTargetDivision4() != null
				&& championshipRanking.getTargetDivision4() != null
				&& this.getTargetDivision4() > championshipRanking
						.getTargetDivision4()) {
			return 1;
		}

		if (this.getTargetDivision4() != null
				&& championshipRanking.getTargetDivision4() != null
				&& this.getTargetDivision4() < championshipRanking
						.getTargetDivision4()) {
			return -1;
		}
		if (this.getTargetDivision3() != null
				&& championshipRanking.getTargetDivision3() == null) {
			return 1;
		}

		if (this.getTargetDivision3() == null
				&& championshipRanking.getTargetDivision3() != null) {
			return -1;
		}

		if (this.getTargetDivision3() != null
				&& championshipRanking.getTargetDivision3() != null
				&& this.getTargetDivision3() > championshipRanking
						.getTargetDivision3()) {
			return 1;
		}

		if (this.getTargetDivision3() != null
				&& championshipRanking.getTargetDivision3() != null
				&& this.getTargetDivision3() < championshipRanking
						.getTargetDivision3()) {
			return -1;
		}
		if (this.getTargetDivision2() != null
				&& championshipRanking.getTargetDivision2() == null) {
			return 1;
		}

		if (this.getTargetDivision2() == null
				&& championshipRanking.getTargetDivision2() != null) {
			return -1;
		}

		if (this.getTargetDivision2() != null
				&& championshipRanking.getTargetDivision2() != null
				&& this.getTargetDivision2() > championshipRanking
						.getTargetDivision2()) {
			return 1;
		}

		if (this.getTargetDivision2() != null
				&& championshipRanking.getTargetDivision2() != null
				&& this.getTargetDivision2() < championshipRanking
						.getTargetDivision2()) {
			return -1;
		}
		if (this.getTargetDivision1() != null
				&& championshipRanking.getTargetDivision1() == null) {
			return 1;
		}

		if (this.getTargetDivision1() == null
				&& championshipRanking.getTargetDivision1() != null) {
			return -1;
		}

		if (this.getTargetDivision1() != null
				&& championshipRanking.getTargetDivision1() != null
				&& this.getTargetDivision1() > championshipRanking
						.getTargetDivision1()) {
			return 1;
		}

		if (this.getTargetDivision1() != null
				&& championshipRanking.getTargetDivision1() != null
				&& this.getTargetDivision1() < championshipRanking
						.getTargetDivision1()) {
			return -1;
		}

		return 0;
	}
}
