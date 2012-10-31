package br.com.tirocerto.dao;

import br.com.tirocerto.model.Associate;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

public interface AssociateDAO {
	void save(Associate associate);
	void update(Associate associate);
	void delete(Associate associate);
	Associate byId(Long id);
	boolean existsEmail(Associate associate);
	Page<Associate> paginate(PageRequest pageRequest);
}
