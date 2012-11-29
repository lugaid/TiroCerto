package br.com.tirocerto.controller.admin;

import br.com.bronx.vraptor.restrictrex.annotation.LoginPage;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;
import br.com.tirocerto.controller.IndexController;
import br.com.tirocerto.controller.admin.IndexAdminController;
import br.com.tirocerto.dao.AssociateDAO;
import br.com.tirocerto.model.Associate;
import br.com.tirocerto.session.AssociateLogged;

@Resource
@Path("/admin/associate")
public class AssociateLoginController {
	private Result result;
	private AssociateDAO associateDAO;
	private Validator validator;
	private AssociateLogged associateLogged;

	public AssociateLoginController(Result result, AssociateDAO associateDAO,
			Validator validator, AssociateLogged associateLogged) {
		this.result = result;
		this.associateDAO = associateDAO;
		this.validator = validator;
		this.associateLogged = associateLogged;
	}

	@LoginPage
	@Get("/login")
	public void login() {
		
	}
	
	@Get("/logout")
	public void logout() {
		associateLogged.logout();
		
		result.redirectTo(IndexController.class).index();
	}
	
	@Post("/login")
	public void loginPost(final Associate associate) {
		validator.checking(new Validations() {{
			that(associate != null, "associate", "required");
			that(associate.getEmail() != null && !associate.getEmail().trim().isEmpty(), "associate.email", "required");
			that(associate.getPassword() != null && !associate.getPassword().trim().isEmpty(), "associate.password", "required");
		}});
		
		validator.onErrorUse(Results.logic()).redirectTo(AssociateLoginController.class).login();
		
		final Associate associateLoaded = associateDAO.findByEmailAndPassword(associate);
		
		validator.checking(new Validations() {{
			that(associateLoaded != null, "associate", "login.invalid");
		}});
		
		validator.onErrorUse(Results.logic()).redirectTo(AssociateLoginController.class).login();

		this.associateLogged.login(associateLoaded);
		
		result.redirectTo(IndexAdminController.class).index();
	}
}
