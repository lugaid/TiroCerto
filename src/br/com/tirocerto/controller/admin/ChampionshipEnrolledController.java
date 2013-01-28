package br.com.tirocerto.controller.admin;

import static br.com.tirocerto.util.datatable.PagingResults.dataTablesPaging;
import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.tirocerto.dao.AssociateDAO;
import br.com.tirocerto.dao.ChampionshipEnrolledDAO;
import br.com.tirocerto.dao.impl.ChampionshipEnrolledHibernateDAO;
import br.com.tirocerto.model.Associate;
import br.com.tirocerto.model.Championship;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

@Resource
@Path("/admin/championshipEnrolled")
@LoggedIn
public class ChampionshipEnrolledController {
	private Result result;
	private ChampionshipEnrolledDAO championshipEnrolledDAO;
	private AssociateDAO associateDAO;
	private Validator validator;

	public ChampionshipEnrolledController(Result result,
			ChampionshipEnrolledDAO championshipEnrolledDAO,
			AssociateDAO associateDAO, Validator validator) {
		this.result = result;
		this.championshipEnrolledDAO = championshipEnrolledDAO;
		this.associateDAO = associateDAO;
		this.validator = validator;
	}
	
	@Get("")
	public void show() {

	}

	@Get("/edit/{championship.id}")
	public void edit(Championship championship) {
		result.forwardTo(this).show();
	}
	
	@Get("/paginate")
	public void paginate(PageRequest pageRequest) {
		Page<Associate> associatePage = this.associateDAO.paginate(pageRequest);
		this.result.use(dataTablesPaging()).from(associatePage);
	}
}
