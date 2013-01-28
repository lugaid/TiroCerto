package br.com.tirocerto.dao;

import br.com.tirocerto.model.ChampionshipEnrolled;

public interface ChampionshipEnrolledDAO {
	void save(ChampionshipEnrolled championshipEnrolled);
	void update(ChampionshipEnrolled championshipEnrolled);
	void delete(ChampionshipEnrolled championshipEnrolled);
}