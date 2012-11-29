package br.com.tirocerto.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tirocerto.dao.AssociateDAO;
import br.com.tirocerto.model.Associate;

@Resource
@Path("/associate")
public class AssociateController {
	private Result result;
	private AssociateDAO associateDAO;
	private Validator validator;
	
	public AssociateController(Result result, AssociateDAO associateDAO, Validator validator) {
		this.result = result;
		this.associateDAO = associateDAO;
		this.validator = validator;
	}
	
	@Get
	@Path("")
	public void form() {
		
		for(Object map : result.included().values()) {
			System.out.println(map);
		}
		result.include("AssociateTypes", Associate.AssociateType.values());
	}
	
	@Get
	@Path("/edit/{id}")
	public void formEdit(Long id) {
		result.include("associate", associateDAO.byId(id));
		result.include("AssociateTypes", Associate.AssociateType.values());
		result.include("mode", "post");
		result.forwardTo(this).form();
	}
	
	@Get
	@Path("/delete/{id}")
	public void formDelete(Long id) {
		result.include("associate", associateDAO.byId(id));
		result.include("AssociateTypes", Associate.AssociateType.values());
		result.include("mode", "delete");
		result.forwardTo(this).form();
	}
	
	@Put
	@Path("")
	public void save(final Associate associate) {
		//bean validator
		validator.validate(associate);
		validator.checking(new Validations() {{
			that(!associateDAO.existsEmail(associate), "email", "already.exists", associate.getEmail());
		}});
		validator.onErrorForwardTo(this).form();

		associateDAO.save(associate);
		result.forwardTo(this).form();
	}
	
	@Post
	@Path("")
	public void update(final Associate associate) {
		associateDAO.update(associate);
		result.forwardTo(this).form();
	}
	
	@Delete
	@Path("")
	public void delete(final Associate associate) {
		associateDAO.delete(associate);
		result.forwardTo(this).form();
	}
}
