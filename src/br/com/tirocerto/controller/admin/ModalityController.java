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
import br.com.tirocerto.dao.ModalityDAO;
import br.com.tirocerto.model.Modality;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;


@Resource
@Path("/admin/modality")
@LoggedIn
public class ModalityController {
	private Result result;
	private ModalityDAO modalityDAO;
	private Validator validator;
	
	public ModalityController(Result result, ModalityDAO modalityDAO, Validator validator) {
		this.result = result;
		this.modalityDAO = modalityDAO;
		this.validator = validator;
	}
	
	@Get("")
	public void list() {
		
	}
	
	@Get("/show")
	public void show() {
		
		for(Object map : result.included().values()) {
			System.out.println(map);
		}
		result.include("ModalityPointTypes", Modality.ModalityPointType.values());
	}
	
	@Get("/paginate")
    public void paginate(PageRequest pageRequest) {
        Page<Modality> modalityPage = this.modalityDAO.paginate(pageRequest);
        this.result.use(dataTablesPaging()).from(modalityPage);
        
    }
	
	@Get("/new")
	public void formNew() {
		result.forwardTo(this).show();
	}
	
	@Get
	@Path("/edit/{id}")
	public void formEdit(Long id) {
		Modality modality = loadById(id);
		
		result.include("modality", modality);
		result.include("mode", "post");
		result.forwardTo(this).show();
	}
	
	@Get
	@Path("/delete/{id}")
	public void formDelete(Long id) {
		Modality modality = loadById(id);
		
		result.include("modality", modality);
		result.include("mode", "delete");
		result.forwardTo(this).show();
	}
	
	@Put
	@Path("")
	public void save(final Modality modality) {
		//bean validator
		validator.validate(modality);
		validator.onErrorRedirectTo(this).formNew();
		
		modalityDAO.save(modality);
		result.include("success", "new");
		result.forwardTo(this).list();
	}
	
	@Post
	@Path("")
	public void update(final Modality modality) {
		//bean validator
		validator.validate(modality);
		validator.onErrorRedirectTo(this).formEdit(modality.getId());
		
		modalityDAO.update(modality);
		result.include("success", "update");
		result.forwardTo(this).list();
	}
	
	@Delete
	@Path("")
	public void delete(final Modality modality) {
		modalityDAO.delete(modality);
		result.include("success", "delete");
		result.forwardTo(this).list();
	}
	
	
	private Modality loadById(Long id) {
		final Modality modality = modalityDAO.byId(id);
		
		validator.checking(new Validations() {{
			that(modality != null, "modality", "not.found");
		}});
		
		validator.onErrorRedirectTo(this).list();
		
		return modality;
	}
}
