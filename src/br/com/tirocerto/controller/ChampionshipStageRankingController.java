package br.com.tirocerto.controller;

import java.util.Collections;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.tirocerto.business.SortChampionshipStageRankingByPosition;
import br.com.tirocerto.dao.ChampionshipStageDAO;
import br.com.tirocerto.model.ChampionshipStage;

@Resource
@Path("/championshipStageRanking")
public class ChampionshipStageRankingController {
	private Result result;
	private ChampionshipStageDAO championshipStageDAO;

	public ChampionshipStageRankingController(Result result,
			ChampionshipStageDAO championshipStageDAO) {
		this.result = result;
		this.championshipStageDAO = championshipStageDAO;
	}

	@Get("/{championshipStage.id}")
	public void list(ChampionshipStage championshipStage) {
		championshipStage = championshipStageDAO.byId(championshipStage.getId());
		
		Collections.sort(championshipStage.getChampionshipStageRanking(), new SortChampionshipStageRankingByPosition());
		
		result.include("championshipStage", championshipStage);
	}
}
