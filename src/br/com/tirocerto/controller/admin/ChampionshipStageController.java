package br.com.tirocerto.controller.admin;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.tirocerto.dao.ChampionshipDAO;
import br.com.tirocerto.model.Championship;

@Resource
@Path("/admin/championshipStage")
@LoggedIn
public class ChampionshipStageController {
	private Result result;
	private ChampionshipDAO championshipDAO;
	
	public ChampionshipStageController(Result result,
			ChampionshipDAO championshipDAO,
			Validator validator) {
		
		this.result = result;
		this.championshipDAO = championshipDAO;
	}
	
	@Get("/{championship.id}")
	public void list(Championship championship) {
		result.include("championship", championshipDAO.byId(championship.getId()));
	}
}
