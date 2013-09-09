package br.com.tirocerto.dao;

import br.com.tirocerto.model.Championship;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

public interface ChampionshipDAO {
	void save(Championship championship);
	void update(Championship championship);
	void delete(Championship championship);
	Championship byId(Long id);
	Page<Championship> paginate(PageRequest pageRequest);
	boolean existsByModality(Long modalityId);
}
