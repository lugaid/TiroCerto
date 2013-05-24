package br.com.tirocerto.dao;

import java.util.List;

import br.com.tirocerto.model.ChampionshipRanking;

public interface ChampionshipRankingDAO {
	void save(ChampionshipRanking championshipRanking);
	void saveAll(List<ChampionshipRanking> championshipRankings);
	void update(ChampionshipRanking championshipRanking);
	void delete(ChampionshipRanking championshipRanking);
	void deleteByChampionship(Long championshipId);
}
