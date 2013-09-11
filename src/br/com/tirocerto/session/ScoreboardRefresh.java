package br.com.tirocerto.session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.tirocerto.model.ScoreboardStage;

@Component
@SessionScoped
public class ScoreboardRefresh {
	public ScoreboardStage lastScoreboardStage;

	public ScoreboardStage getLastScoreboardStage() {
		return lastScoreboardStage;
	}

	public void setLastScoreboardStage(ScoreboardStage lastScoreboardStage) {
		this.lastScoreboardStage = lastScoreboardStage;
	}
}
