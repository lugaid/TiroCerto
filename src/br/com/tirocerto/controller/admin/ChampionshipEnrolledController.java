package br.com.tirocerto.controller.admin;

import static br.com.tirocerto.util.datatable.PagingResults.dataTablesPaging;
import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
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
	
	
	public ChampionshipEnrolledController(Result result,
			ChampionshipEnrolledDAO championshipEnrolledDAO,
			AssociateDAO associateDAO, ChampionshipDAO championshipDAO,
			Validator validator) {
		
		this.result = result;
		this.championshipEnrolledDAO = championshipEnrolledDAO;
		this.championshipDAO = championshipDAO;
		this.associateDAO = associateDAO;
	}

	@Get("/{championship.id}")
	public void list(Championship championship) {
		result.include("championship", championshipDAO.byId(championship.getId()));
	}
	
	@Get("/show")
	public void show() {

	}

	@Get("/edit/{championship.id}/{associate.id}")
	public void edit(Championship championship, Associate associate) {
		result.forwardTo(this).show();
	}

	@Get("/paginate/{championship.id}")
	public void paginate(Championship championship, PageRequest pageRequest) {
		Page<Associate> associatePage = this.associateDAO.paginate(pageRequest);

		result.include("associatePage", associatePage);
		result.include("championship", championship);
	}
}
