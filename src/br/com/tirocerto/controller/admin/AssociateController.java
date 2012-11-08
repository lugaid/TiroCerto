package br.com.tirocerto.controller.admin;

import static br.com.tirocerto.util.datatable.PagingResults.dataTablesPaging;
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
import br.com.tirocerto.dao.AssociateDAO;
import br.com.tirocerto.model.Associate;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

@Resource
@Path("/admin/associate")
@LoggedIn
public class AssociateController {
	private Result result;
	private AssociateDAO associateDAO;
	private Validator validator;
	
	public AssociateController(Result result, AssociateDAO associateDAO, Validator validator) {
		this.result = result;
		this.associateDAO = associateDAO;
		this.validator = validator;
	}
	
	@Get("")
	public void list() {
		
	}
	
	@Get("/new")
	public void add() {
		result.forwardTo(this).show();
	}
	
	@Get("/show")
	public void show() {
		
		for(Object map : result.included().values()) {
			System.out.println(map);
		}
		result.include("AssociateTypes", Associate.AssociateType.values());
	}
	
    @Get("/paginate")
    public void paginate(PageRequest pageRequest) {
        Page<Associate> associatePage = this.associateDAO.paginate(pageRequest);
        this.result.use(dataTablesPaging()).from(associatePage);
        
    }
    
	@Get
	@Path("/edit/{id}")
	public void formEdit(Long id) {
		Associate associate = loadById(id);
		
		result.include("associate", associate);
		result.include("AssociateTypes", Associate.AssociateType.values());
		result.include("mode", "post");
		result.forwardTo(this).show();
	}
	
	@Get
	@Path("/delete/{id}")
	public void formDelete(Long id) {
		Associate associate = loadById(id);
		
		result.include("associate", associate);
		result.include("AssociateTypes", Associate.AssociateType.values());
		result.include("mode", "delete");
		result.forwardTo(this).show();
	}
	
	@Put
	@Path("")
	public void save(final Associate associate) {
		//bean validator
		validator.validate(associate);
		validator.checking(new Validations() {{
			that(!associateDAO.existsEmail(associate), "email", "already.exists", associate.getEmail());
		}});
		validator.onErrorForwardTo(this).show();

		associateDAO.save(associate);
		result.forwardTo(this).show();
	}
	
	@Post
	@Path("")
	public void update(final Associate associate) {
		//bean validator
		validator.validate(associate);
		validator.checking(new Validations() {{
			that(!associateDAO.existsEmail(associate), "email", "already.exists", associate.getEmail());
		}});
		validator.onErrorForwardTo(this).show();
				
		associateDAO.update(associate);
		result.forwardTo(this).show();
	}
	
	@Delete
	@Path("")
	public void delete(final Associate associate) {
		associateDAO.delete(associate);
		
		result.forwardTo(this).show();
	}
	
	
	private Associate loadById(Long id) {
		final Associate associate = associateDAO.byId(id);
		
		validator.checking(new Validations() {{
			that(associate != null, "associate", "not.found");
		}});
		
		validator.onErrorForwardTo(this).show();
		
		return associate;
	}
}
