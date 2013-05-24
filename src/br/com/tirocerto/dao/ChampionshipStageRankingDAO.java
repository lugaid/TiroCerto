package br.com.tirocerto.dao;

import java.util.List;

import br.com.tirocerto.model.ChampionshipStageRanking;

public interface ChampionshipStageRankingDAO {
	void save(ChampionshipStageRanking championshipStageRanking);
	void saveAll(List<ChampionshipStageRanking> championshipStageRankings);
	void update(ChampionshipStageRanking championshipStageRanking);
	void delete(ChampionshipStageRanking championshipStageRanking);
	void deleteByStage(Long stageId);
}
