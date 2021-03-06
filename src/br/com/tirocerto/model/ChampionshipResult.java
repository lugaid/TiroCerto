package br.com.tirocerto.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {
		"championshipEnrolled_championship_id",
		"championshipEnrolled_associate_id", "championshipStage_id", "serie" }) })
public class ChampionshipResult implements Serializable,
		Comparable<ChampionshipResult> {
	private static final long serialVersionUID = -3847523785697942107L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private ChampionshipEnrolled championshipEnrolled;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private ChampionshipStage championshipStage;

	@Min(value = 1)
	@Column(nullable = false)
	private Integer serie;

	@Min(value = 0)
	@Column()
	private Integer targetDivisionX;

	@Min(value = 0)
	@Column()
	private Integer targetDivision10;

	@Min(value = 0)
	@Column()
	private Integer targetDivision9;

	@Min(value = 0)
	@Column()
	private Integer targetDivision8;

	@Min(value = 0)
	@Column()
	private Integer targetDivision7;

	@Min(value = 0)
	@Column()
	private Integer targetDivision6;

	@Min(value = 0)
	@Column()
	private Integer targetDivision5;

	@Min(value = 0)
	@Column()
	private Integer targetDivision4;

	@Min(value = 0)
	@Column()
	private Integer targetDivision3;

	@Min(value = 0)
	@Column()
	private Integer targetDivision2;

	@Min(value = 0)
	@Column()
	private Integer targetDivision1;

	@Min(value = 0)
	@Column()
	@NotNull()
	private Integer points;

	@Min(value = 0)
	@Column()
	@NotNull()
	private Integer penalty;

	@Column()
	@NotNull()
	private Integer total;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ChampionshipEnrolled getChampionshipEnrolled() {
		return championshipEnrolled;
	}

	public void setChampionshipEnrolled(
			ChampionshipEnrolled championshipEnrolled) {
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
		if (!(obj instanceof ChampionshipResult))
			return false;
		ChampionshipResult other = (ChampionshipResult) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(ChampionshipResult championshipResult) {
		if (this.getTotal() != null && championshipResult.getTotal() == null) {
			return 1;
		}

		if (this.getTotal() == null && championshipResult.getTotal() != null) {
			return -1;
		}

		if (this.getTotal() != null && championshipResult.getTotal() != null
				&& this.getTotal() > championshipResult.getTotal()) {
			return 1;
		}

		if (this.getTotal() != null && championshipResult.getTotal() != null
				&& this.getTotal() < championshipResult.getTotal()) {
			return -1;
		}
		if (this.getTargetDivisionX() != null
				&& championshipResult.getTargetDivisionX() == null) {
			return 1;
		}

		if (this.getTargetDivisionX() == null
				&& championshipResult.getTargetDivisionX() != null) {
			return -1;
		}

		if (this.getTargetDivisionX() != null
				&& championshipResult.getTargetDivisionX() != null
				&& this.getTargetDivisionX() > championshipResult
						.getTargetDivisionX()) {
			return 1;
		}

		if (this.getTargetDivisionX() != null
				&& championshipResult.getTargetDivisionX() != null
				&& this.getTargetDivisionX() < championshipResult
						.getTargetDivisionX()) {
			return -1;
		}
		if (this.getTargetDivision10() != null
				&& championshipResult.getTargetDivision10() == null) {
			return 1;
		}

		if (this.getTargetDivision10() == null
				&& championshipResult.getTargetDivision10() != null) {
			return -1;
		}

		if (this.getTargetDivision10() != null
				&& championshipResult.getTargetDivision10() != null
				&& this.getTargetDivision10() > championshipResult
						.getTargetDivision10()) {
			return 1;
		}

		if (this.getTargetDivision10() != null
				&& championshipResult.getTargetDivision10() != null
				&& this.getTargetDivision10() < championshipResult
						.getTargetDivision10()) {
			return -1;
		}
		if (this.getTargetDivision9() != null
				&& championshipResult.getTargetDivision9() == null) {
			return 1;
		}

		if (this.getTargetDivision9() == null
				&& championshipResult.getTargetDivision9() != null) {
			return -1;
		}

		if (this.getTargetDivision9() != null
				&& championshipResult.getTargetDivision9() != null
				&& this.getTargetDivision9() > championshipResult
						.getTargetDivision9()) {
			return 1;
		}

		if (this.getTargetDivision9() != null
				&& championshipResult.getTargetDivision9() != null
				&& this.getTargetDivision9() < championshipResult
						.getTargetDivision9()) {
			return -1;
		}
		if (this.getTargetDivision8() != null
				&& championshipResult.getTargetDivision8() == null) {
			return 1;
		}

		if (this.getTargetDivision8() == null
				&& championshipResult.getTargetDivision8() != null) {
			return -1;
		}

		if (this.getTargetDivision8() != null
				&& championshipResult.getTargetDivision8() != null
				&& this.getTargetDivision8() > championshipResult
						.getTargetDivision8()) {
			return 1;
		}

		if (this.getTargetDivision8() != null
				&& championshipResult.getTargetDivision8() != null
				&& this.getTargetDivision8() < championshipResult
						.getTargetDivision8()) {
			return -1;
		}
		if (this.getTargetDivision7() != null
				&& championshipResult.getTargetDivision7() == null) {
			return 1;
		}

		if (this.getTargetDivision7() == null
				&& championshipResult.getTargetDivision7() != null) {
			return -1;
		}

		if (this.getTargetDivision7() != null
				&& championshipResult.getTargetDivision7() != null
				&& this.getTargetDivision7() > championshipResult
						.getTargetDivision7()) {
			return 1;
		}

		if (this.getTargetDivision7() != null
				&& championshipResult.getTargetDivision7() != null
				&& this.getTargetDivision7() < championshipResult
						.getTargetDivision7()) {
			return -1;
		}
		if (this.getTargetDivision6() != null
				&& championshipResult.getTargetDivision6() == null) {
			return 1;
		}

		if (this.getTargetDivision6() == null
				&& championshipResult.getTargetDivision6() != null) {
			return -1;
		}

		if (this.getTargetDivision6() != null
				&& championshipResult.getTargetDivision6() != null
				&& this.getTargetDivision6() > championshipResult
						.getTargetDivision6()) {
			return 1;
		}

		if (this.getTargetDivision6() != null
				&& championshipResult.getTargetDivision6() != null
				&& this.getTargetDivision6() < championshipResult
						.getTargetDivision6()) {
			return -1;
		}
		if (this.getTargetDivision5() != null
				&& championshipResult.getTargetDivision5() == null) {
			return 1;
		}

		if (this.getTargetDivision5() == null
				&& championshipResult.getTargetDivision5() != null) {
			return -1;
		}

		if (this.getTargetDivision5() != null
				&& championshipResult.getTargetDivision5() != null
				&& this.getTargetDivision5() > championshipResult
						.getTargetDivision5()) {
			return 1;
		}

		if (this.getTargetDivision5() != null
				&& championshipResult.getTargetDivision5() != null
				&& this.getTargetDivision5() < championshipResult
						.getTargetDivision5()) {
			return -1;
		}
		if (this.getTargetDivision4() != null
				&& championshipResult.getTargetDivision4() == null) {
			return 1;
		}

		if (this.getTargetDivision4() == null
				&& championshipResult.getTargetDivision4() != null) {
			return -1;
		}

		if (this.getTargetDivision4() != null
				&& championshipResult.getTargetDivision4() != null
				&& this.getTargetDivision4() > championshipResult
						.getTargetDivision4()) {
			return 1;
		}

		if (this.getTargetDivision4() != null
				&& championshipResult.getTargetDivision4() != null
				&& this.getTargetDivision4() < championshipResult
						.getTargetDivision4()) {
			return -1;
		}
		if (this.getTargetDivision3() != null
				&& championshipResult.getTargetDivision3() == null) {
			return 1;
		}

		if (this.getTargetDivision3() == null
				&& championshipResult.getTargetDivision3() != null) {
			return -1;
		}

		if (this.getTargetDivision3() != null
				&& championshipResult.getTargetDivision3() != null
				&& this.getTargetDivision3() > championshipResult
						.getTargetDivision3()) {
			return 1;
		}

		if (this.getTargetDivision3() != null
				&& championshipResult.getTargetDivision3() != null
				&& this.getTargetDivision3() < championshipResult
						.getTargetDivision3()) {
			return -1;
		}
		if (this.getTargetDivision2() != null
				&& championshipResult.getTargetDivision2() == null) {
			return 1;
		}

		if (this.getTargetDivision2() == null
				&& championshipResult.getTargetDivision2() != null) {
			return -1;
		}

		if (this.getTargetDivision2() != null
				&& championshipResult.getTargetDivision2() != null
				&& this.getTargetDivision2() > championshipResult
						.getTargetDivision2()) {
			return 1;
		}

		if (this.getTargetDivision2() != null
				&& championshipResult.getTargetDivision2() != null
				&& this.getTargetDivision2() < championshipResult
						.getTargetDivision2()) {
			return -1;
		}
		if (this.getTargetDivision1() != null
				&& championshipResult.getTargetDivision1() == null) {
			return 1;
		}

		if (this.getTargetDivision1() == null
				&& championshipResult.getTargetDivision1() != null) {
			return -1;
		}

		if (this.getTargetDivision1() != null
				&& championshipResult.getTargetDivision1() != null
				&& this.getTargetDivision1() > championshipResult
						.getTargetDivision1()) {
			return 1;
		}

		if (this.getTargetDivision1() != null
				&& championshipResult.getTargetDivision1() != null
				&& this.getTargetDivision1() < championshipResult
						.getTargetDivision1()) {
			return -1;
		}

		return 0;
	}
}
