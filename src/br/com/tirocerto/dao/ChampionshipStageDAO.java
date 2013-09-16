package br.com.tirocerto.dao;

import java.util.List;

import br.com.tirocerto.model.ChampionshipStage;

public interface ChampionshipStageDAO {
	void save(ChampionshipStage championshipStage);
	void update(ChampionshipStage championshipStage);
	void delete(ChampionshipStage championshipStage);
	ChampionshipStage byId(Long id);
	List<ChampionshipStage> byChampionshipId(Long id);
	List<ChampionshipStage> allStages();
}
