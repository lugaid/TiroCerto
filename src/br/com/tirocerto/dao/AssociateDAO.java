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
<<<<<<< HEAD
=======
	Page<Associate> paginate(PageRequest pageRequest);
	Associate findByEmailAndPassword(Associate associate);
>>>>>>> dd1f53e657aa583b099ddeb5cd701e426f4a33db
}
