package br.com.tirocerto.dao;

import br.com.tirocerto.model.ChampionshipSerieResult;

public interface ChampionshipSerieResultDAO {
	void save(ChampionshipSerieResult championshipSerieResult);
	void update(ChampionshipSerieResult championshipSerieResult);
	void delete(ChampionshipSerieResult championshipSerieResult);
	//ChampionshipSerieResult byId(Long id);
	//Page<Championship> paginate(PageRequest pageRequest);
}
