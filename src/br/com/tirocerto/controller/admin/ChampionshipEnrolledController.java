package br.com.tirocerto.controller.admin;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.tirocerto.business.ChampionshipRankingBusiness;
import br.com.tirocerto.business.ChampionshipStageRankingBusiness;
import br.com.tirocerto.dao.AssociateDAO;
import br.com.tirocerto.dao.ChampionshipDAO;
import br.com.tirocerto.dao.ChampionshipEnrolledDAO;
import br.com.tirocerto.model.Associate;
import br.com.tirocerto.model.Championship;
import br.com.tirocerto.model.ChampionshipEnrolled;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

@Resource
@Path("/admin/championshipEnrolled")
@LoggedIn
public class ChampionshipEnrolledController {
	private Result result;
	private ChampionshipDAO championshipDAO;
	private ChampionshipEnrolledDAO championshipEnrolledDAO;
	private AssociateDAO associateDAO;
	private ChampionshipRankingBusiness championshipRankingBusiness;
	private ChampionshipStageRankingBusiness championshipStageRankingBusiness;
	
	public ChampionshipEnrolledController(Result result,
			ChampionshipEnrolledDAO championshipEnrolledDAO,
			AssociateDAO associateDAO, ChampionshipDAO championshipDAO,
			Validator validator,
			ChampionshipRankingBusiness championshipRankingBusiness,
			ChampionshipStageRankingBusiness championshipStageRankingBusiness) {

		this.result = result;
		this.championshipEnrolledDAO = championshipEnrolledDAO;
		this.championshipDAO = championshipDAO;
		this.associateDAO = associateDAO;
		this.championshipRankingBusiness = championshipRankingBusiness;
		this.championshipStageRankingBusiness = championshipStageRankingBusiness;
	}

	@Get("/{championship.id}")
	public void list(Championship championship) {
		result.include("championship",
				championshipDAO.byId(championship.getId()));
	}

	@Get("enrolledByChampionship/{championship.id}")
	public void listEnrolledByChampionship(Championship championship) {
		result.include("championship",
				championshipDAO.byId(championship.getId()));
	}

	@Get("/paginate/{championship.id}")
	public void paginate(Championship championship, PageRequest pageRequest) {
		Page<Associate> associatePage = this.associateDAO.paginate(pageRequest);

		result.include("associatePage", associatePage);
		result.include("championship", championship);
	}

	@Get("/paginateEnrolledByChampionship/{championship.id}")
	public void paginateEnrolledByChampionship(Championship championship,
			PageRequest pageRequest) {
		result.include("championshipEnrolledPage", championshipEnrolledDAO
				.paginateEnrolledByChampionship(championship, pageRequest));
	}

	@Put
	@Path("")
	public void save(final ChampionshipEnrolled championshipEnrolled) {
		championshipEnrolledDAO.save(championshipEnrolled);

		//recalc ranking
		recalcRankingByChanpionshipId(championshipEnrolled.getChampionship().getId());
		
		result.nothing();
	}

	@Delete
	@Path("")
	public void delete(final ChampionshipEnrolled championshipEnrolled) {
		ChampionshipEnrolled championshipEnrolledDelete = championshipEnrolledDAO
				.getById(championshipEnrolled);

		championshipEnrolledDAO.delete(championshipEnrolledDelete);
		
		//recalc ranking
		recalcRankingByChanpionshipId(championshipEnrolledDelete.getChampionship().getId());
		
		result.nothing();
	}
	
	private void recalcRankingByChanpionshipId(Long id) {
		championshipStageRankingBusiness.recalcAllStagesByChampionship(id);
		
		championshipRankingBusiness.recalcRanking(id);
	}
}
