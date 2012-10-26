package br.com.tirocerto.dao;

import br.com.tirocerto.model.Associate;

public interface AssociateDAO {
	void save(Associate associate);
	void update(Associate associate);
	void delete(Associate associate);
	Associate byId(Long id);
}
