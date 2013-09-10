package br.com.tirocerto.dao;

import br.com.tirocerto.model.Championship;
import br.com.tirocerto.model.ChampionshipEnrolled;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

public interface ChampionshipEnrolledDAO {
	void save(ChampionshipEnrolled championshipEnrolled);
	void update(ChampionshipEnrolled championshipEnrolled);
	void delete(ChampionshipEnrolled championshipEnrolled);
	ChampionshipEnrolled getById(ChampionshipEnrolled championshipEnrolled);
	Page<ChampionshipEnrolled> paginateEnrolledByChampionship(Championship championship, PageRequest pageRequest);
}