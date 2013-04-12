package br.com.tirocerto.controller.admin;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.tirocerto.dao.ChampionshipDAO;
import br.com.tirocerto.dao.ChampionshipResultDAO;
import br.com.tirocerto.dao.ChampionshipStageDAO;
import br.com.tirocerto.dao.ModalityDAO;
import br.com.tirocerto.model.Associate;
import br.com.tirocerto.model.Championship;
import br.com.tirocerto.model.ChampionshipResult;
import br.com.tirocerto.model.ChampionshipStage;
import br.com.tirocerto.model.Modality;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

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

	@Get("/show")
	public void show() {
		//result.include("ModalityPointType", Modality.ModalityPointType);
	}
	
	@Get("/paginateByChampionshipStage/{championshipStage.id}")
	public void paginateByChampionshipStage(
			ChampionshipStage championshipStage, PageRequest pageRequest) {
		
		Page<ChampionshipResult> championshipResultPage = this.championshipResultDAO
				.paginateByChampionshipStage(pageRequest, championshipStage);
		
		result.include("championshipResultPage", championshipResultPage);
	}
	
	@Get("/new/{championshipStage.id}")
	public void formNew(ChampionshipStage championshipStage) {
		result.include("championshipStage",
				championshipStageDAO.byId(championshipStage.getId()));
		
		result.forwardTo(this).show();
	}
	
	@Put("")
	public void save(final ChampionshipResult championshipResult) {
		//bean validator
		validator.validate(championshipResult);
		validator.onErrorRedirectTo(this).formNew(championshipResult.getChampionshipStage());
		
		championshipResultDAO.save(championshipResult);
		result.include("success", "new");
		result.forwardTo(this).list(championshipResult.getChampionshipStage());
	}
}
