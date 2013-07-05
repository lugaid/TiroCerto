package br.com.tirocerto.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.tirocerto.dao.ChampionshipStageDAO;
import br.com.tirocerto.model.ChampionshipStage;

@Resource
@Path("/championshipStageRanking")
public class ChampionshipStageRankingController {
	private Result result;
	private ChampionshipStageDAO championshipStageDAO;
	
	public ChampionshipStageRankingController(Result result, ChampionshipStageDAO championshipStageDAO) {
		this.result = result;
		this.championshipStageDAO = championshipStageDAO;
	}
	
	@Get("/{championshipStage.id}")
	public void list(ChampionshipStage championshipStage) {
		result.include("championshipStage",
				championshipStageDAO.byId(championshipStage.getId()));
	}
}
