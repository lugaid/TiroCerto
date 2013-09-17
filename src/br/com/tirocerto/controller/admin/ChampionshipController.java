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
import br.com.tirocerto.dao.ChampionshipDAO;
import br.com.tirocerto.dao.ModalityDAO;
import br.com.tirocerto.model.Championship;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

@Resource
@Path("/admin/championship")
@LoggedIn
public class ChampionshipController {
	private Result result;
	private ChampionshipDAO championshipDAO;
	private ModalityDAO modalityDAO;
	private Validator validator;
	private ChampionshipStageRankingBusiness championshipStageRankingBusiness;
	private ChampionshipRankingBusiness championshipRankingBusiness;

	public ChampionshipController(Result result,
			ChampionshipDAO championshipDAO, ModalityDAO modalityDAO,
			Validator validator,
			ChampionshipStageRankingBusiness championshipStageRankingBusiness,
			ChampionshipRankingBusiness championshipRankingBusiness) {
		this.result = result;
		this.championshipDAO = championshipDAO;
		this.modalityDAO = modalityDAO;
		this.validator = validator;
		this.championshipStageRankingBusiness = championshipStageRankingBusiness;
		this.championshipRankingBusiness = championshipRankingBusiness;
	}

	@Get("")
	public void list() {

	}

	@Get("/show")
	public void show() {
		result.include("Modalities", modalityDAO.listAll());
	}

	@Get("/paginate")
	public void paginate(PageRequest pageRequest) {
		Page<Championship> championshipPage = this.championshipDAO
				.paginate(pageRequest);

		result.include("championshipPage", championshipPage);
	}

	@Get("/new")
	public void formNew() {
		result.forwardTo(this).show();
	}

	@Get
	@Path("/edit/{id}")
	public void formEdit(Long id) {
		Championship championship = loadById(id);

		result.include("championship", championship);
		result.include("mode", "post");
		result.forwardTo(this).show();
	}

	@Get
	@Path("/delete/{id}")
	public void formDelete(Long id) {
		Championship championship = loadById(id);

		result.include("championship", championship);
		result.include("mode", "delete");
		result.forwardTo(this).show();
	}

	@Put
	@Path("")
	public void save(final Championship championship) {
		// bean validator
		validator.validate(championship);
		validator.onErrorRedirectTo(this).formNew();

		championshipDAO.save(championship);
		
		//recalc ranking
		recalcRankingByChanpionshipId(championship.getId());
		
		result.include("success", "new");
		result.forwardTo(this).list();
	}

	@Post
	@Path("")
	public void update(final Championship championship) {
		// bean validator
		validator.validate(championship);
		validator.onErrorRedirectTo(this).formEdit(championship.getId());

		championshipDAO.update(championship);
		
		//recalc ranking
		recalcRankingByChanpionshipId(championship.getId());
		
		result.include("success", "update");
		result.forwardTo(this).list();
	}

	@Delete
	@Path("")
	public void delete(final Championship championship) {
		championshipDAO.delete(championship);
		result.include("success", "delete");
		result.forwardTo(this).list();
	}

	private Championship loadById(Long id) {
		final Championship modality = championshipDAO.byId(id);

		validator.checking(new Validations() {
			{
				that(modality != null, "championship", "not.found");
			}
		});

		validator.onErrorRedirectTo(this).list();

		return modality;
	}
	
	private void recalcRankingByChanpionshipId(Long id) {
		championshipStageRankingBusiness.recalcAllStagesByChampionship(id);
		
		championshipRankingBusiness.recalcRanking(id);
	}
}
