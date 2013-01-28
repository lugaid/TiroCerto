package br.com.tirocerto.dao;

import br.com.tirocerto.model.ChampionshipEnrolled;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

public interface ChampionshipEnrolledDAO {
	void save(ChampionshipEnrolled championshipEnrolled);
	void update(ChampionshipEnrolled championshipEnrolled);
	void delete(ChampionshipEnrolled championshipEnrolled);
	Page<ChampionshipEnrolled> paginate(PageRequest pageRequest, Long championshipId);
}