package br.com.tirocerto.controller.admin;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.tirocerto.dao.ChampionshipDAO;
import br.com.tirocerto.dao.ChampionshipResultDAO;
import br.com.tirocerto.dao.ChampionshipStageDAO;
import br.com.tirocerto.dao.ModalityDAO;
import br.com.tirocerto.model.Championship;
import br.com.tirocerto.model.ChampionshipStage;

@Resource
@Path("/admin/championshipResult")
@LoggedIn
public class ChampionshipResultController {
	private Result result;
	private ChampionshipStageDAO championshipStageDAO;
	private ChampionshipResultDAO championshipResultDAO;
	private Validator validator;

	public ChampionshipResultController(Result result,
			ChampionshipResultDAO championshipResultDAO,
			ChampionshipStageDAO championshipStageDAO, Validator validator) {
		this.result = result;
		this.championshipResultDAO = championshipResultDAO;
		this.championshipStageDAO = championshipStageDAO;
		this.validator = validator;
	}

	@Get("/{championshipStage.id}")
	public void list(ChampionshipStage championshipStage) {
		result.include("championshipStage",
				championshipStageDAO.byId(championshipStage.getId()));
	}

	@Get("paginateByChampionshipStage/{championshipStage.id}")
	public void paginateByChampionshipStage(ChampionshipStage championshipStage) {
		result.include("championshipStage",
				championshipStageDAO.byId(championshipStage.getId()));
	}
}
