package br.com.tirocerto.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.tirocerto.dao.AssociateDAO;
import br.com.tirocerto.model.Associate;

@Resource
public class AssociateController {
	private Result result;
	private AssociateDAO associateDAO;
	
	public AssociateController(Result result, AssociateDAO associateDAO) {
		this.result = result;
		this.associateDAO = associateDAO;
	}
//	@Get
//	@Path("/associate/{page}")
//	public void list(int page) {
//		
//	}
	
	@Get
	@Path("/associate")
	public void form() {
		this.result.include("AssociateTypes", Associate.AssociateType.values());
	}
	
	@Post
	@Path("/associate")
	public void save(Associate associate) {
		System.out.println(associate.getCr());
		associateDAO.save(associate);
		result.redirectTo(AssociateController.class).form();
	}
}
