package br.com.tirocerto.dao;

import br.com.tirocerto.model.ChampionshipStage;

public interface ChampionshipStageDAO {
	void save(ChampionshipStage championshipStage);
	void update(ChampionshipStage championshipStage);
	void delete(ChampionshipStage championshipStage);
	ChampionshipStage byId(Long id);
}
