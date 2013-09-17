package br.com.tirocerto.dao;

import br.com.tirocerto.model.ScoreboardStage;

public interface ScoreboardStageDAO {
	void save(ScoreboardStage scoreboardStage);
	void update(ScoreboardStage scoreboardStage);
	void delete(ScoreboardStage scoreboardStage);
}
