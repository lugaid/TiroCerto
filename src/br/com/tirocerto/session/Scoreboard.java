package br.com.tirocerto.session;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.tirocerto.model.ChampionshipStage;

@Component
@SessionScoped
public class Scoreboard implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int qtyEnrolledToShow;
	
	private List<ChampionshipStage> championshipStages;
	
	private boolean nextEnrolled;
	
	private boolean nextStage;

	public int getQtyEnrolledToShow() {
		return qtyEnrolledToShow;
	}

	public void setQtyEnrolledToShow(int qtyEnrolledToShow) {
		this.qtyEnrolledToShow = qtyEnrolledToShow;
	}

	public List<ChampionshipStage> getChampionshipStages() {
		return championshipStages;
	}

	public void setChampionshipStages(List<ChampionshipStage> championshipStages) {
		this.championshipStages = championshipStages;
	}

	public boolean hasNextEnrolled() {
		return nextEnrolled;
	}

	public boolean hasNextStage() {
		return nextStage;
	}
	
	
}
