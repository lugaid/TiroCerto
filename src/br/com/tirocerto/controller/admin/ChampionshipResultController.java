package br.com.tirocerto.controller.admin;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tirocerto.business.ChampionshipRankingBusiness;
import br.com.tirocerto.business.ChampionshipStageRankingBusiness;
import br.com.tirocerto.dao.ChampionshipResultDAO;
import br.com.tirocerto.dao.ChampionshipStageDAO;
import br.com.tirocerto.model.ChampionshipResult;
import br.com.tirocerto.model.ChampionshipStage;
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
	private ChampionshipStageRankingBusiness championshipStageRankingBusiness;
	private ChampionshipRankingBusiness championshipRankingBusiness;

	public ChampionshipResultController(Result result,
			ChampionshipResultDAO championshipResultDAO,
			ChampionshipStageDAO championshipStageDAO, Validator validator,
			ChampionshipStageRankingBusiness championshipStageRankingBusiness,
			ChampionshipRankingBusiness championshipRankingBusiness) {
		
		this.result = result;
		this.championshipResultDAO = championshipResultDAO;
		this.championshipStageDAO = championshipStageDAO;
		this.validator = validator;
		this.championshipStageRankingBusiness = championshipStageRankingBusiness;
		this.championshipRankingBusiness = championshipRankingBusiness;
	}

	@Get("/{championshipStage.id}")
	public void list(ChampionshipStage championshipStage) {
		result.include("championshipStage",
				championshipStageDAO.byId(championshipStage.getId()));
	}

	@Get("/show")
	public void show() {

	}

	@Get("/paginateByChampionshipStage/{championshipStage.id}")
	public void paginateByChampionshipStage(
			ChampionshipStage championshipStage, PageRequest pageRequest) {

		Page<ChampionshipResult> championshipResultPage = this.championshipResultDAO
				.paginateByChampionshipStage(pageRequest, championshipStage);

		result.include("championshipResultPage", championshipResultPage);
	}

	@Get("/{championshipStage.id}/new")
	public void formNew(ChampionshipStage championshipStage) {
		result.include("championshipStage",
				championshipStageDAO.byId(championshipStage.getId()));

		result.forwardTo(this).show();
	}

	@Get("/{championshipStage.id}/edit/{championshipResult.id}")
	public void formEdit(ChampionshipStage championshipStage,
			ChampionshipResult championshipResult) {
		result.include("championshipStage",
				championshipStageDAO.byId(championshipStage.getId()));
		result.include("championshipResult",
				championshipResultDAO.byId(championshipResult.getId()));
		result.include("mode", "update");

		result.forwardTo(this).show();
	}

	@Get("/{championshipStage.id}/delete/{championshipResult.id}")
	public void formDelete(ChampionshipStage championshipStage,
			ChampionshipResult championshipResult) {
		result.include("championshipStage",
				championshipStageDAO.byId(championshipStage.getId()));
		result.include("championshipResult",
				championshipResultDAO.byId(championshipResult.getId()));
		result.include("mode", "delete");

		result.forwardTo(this).show();
	}

	@Put("")
	public void save(final ChampionshipResult championshipResult) {
		// bean validator
		validator.validate(championshipResult);
		validator.checking(new Validations() {
			{
				that(!championshipResultDAO.existResult(championshipResult),
						"associate_serie", "already.exists.noparm");
			}
		});
		validator.onErrorRedirectTo(this).formNew(
				championshipResult.getChampionshipStage());

		championshipResultDAO.save(championshipResult);
		recalcRanking(championshipResult);
		result.include("success", "new");
		result.forwardTo(this).list(championshipResult.getChampionshipStage());
	}

	@Post("")
	public void update(final ChampionshipResult championshipResult) {
		// bean validator
		validator.validate(championshipResult);
		validator.checking(new Validations() {
			{
				that(!championshipResultDAO.existResult(championshipResult),
						"associate_serie", "already.exists.noparm");
			}
		});
		validator.onErrorRedirectTo(this).formEdit(
				championshipResult.getChampionshipStage(), championshipResult);

		championshipResultDAO.update(championshipResult);
		recalcRanking(championshipResult);
		result.include("success", "update");
		result.forwardTo(this).list(championshipResult.getChampionshipStage());
	}

	@Delete("")
	public void delete(final ChampionshipResult championshipResult) {
		// bean validator
		validator.validate(championshipResult);
		validator.onErrorRedirectTo(this).formDelete(
				championshipResult.getChampionshipStage(), championshipResult);

		championshipResultDAO.delete(championshipResult);
		recalcRanking(championshipResult);
		result.include("success", "delete");
		result.forwardTo(this).list(championshipResult.getChampionshipStage());
	}
	
	private void recalcRanking(ChampionshipResult championshipResult) {
		championshipStageRankingBusiness.recalcRanking(championshipResult
				.getChampionshipStage().getId());
		championshipRankingBusiness.recalcRanking(championshipResult
				.getChampionshipEnrolled().getChampionship().getId());
	}
}
