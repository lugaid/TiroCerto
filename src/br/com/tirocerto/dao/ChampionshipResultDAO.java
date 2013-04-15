package br.com.tirocerto.dao;

import br.com.tirocerto.model.ChampionshipResult;
import br.com.tirocerto.model.ChampionshipStage;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

public interface ChampionshipResultDAO {
	void save(ChampionshipResult championshipResult);
	void update(ChampionshipResult championshipResult);
	void delete(ChampionshipResult championshipResult);
	ChampionshipResult byId(Long id);
	Page<ChampionshipResult> paginateByChampionshipStage(PageRequest pageRequest, ChampionshipStage championshipStage);
}
