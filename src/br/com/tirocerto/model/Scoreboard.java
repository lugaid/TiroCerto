package br.com.tirocerto.model;

import java.io.Serializable;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
public class Scoreboard implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	@Length(min = 10, max = 50)
	@Column(length = 50)
	private String description;
	
	@Min(value = 1)
	@Max(value = 99999999)
	@Column()
	private Integer timeToRefresh;
	
	@Min(value = 1)
	@Max(value = 99999999)
	@Column()
	private Integer qtyRows;
	
	@Valid
	@OneToMany(mappedBy = "scoreboard", targetEntity = ScoreboardStage.class, cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ScoreboardStage> scoreboardStage;
	
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

	public Integer getTimeToRefresh() {
		return timeToRefresh;
	}

	public void setTimeToRefresh(Integer timeToRefresh) {
		this.timeToRefresh = timeToRefresh;
	}

	public List<ScoreboardStage> getScoreboardStage() {
		return scoreboardStage;
	}

	public void setScoreboardStage(List<ScoreboardStage> scoreboardStage) {
		this.scoreboardStage = scoreboardStage;
	}
	
	public Integer getQtyRows() {
		return qtyRows;
	}

	public void setQtyRows(Integer qtyRows) {
		this.qtyRows = qtyRows;
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
		if (getClass() != obj.getClass())
			return false;
		Scoreboard other = (Scoreboard) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
