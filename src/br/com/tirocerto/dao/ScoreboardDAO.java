package br.com.tirocerto.dao;

import br.com.tirocerto.model.Scoreboard;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

public interface ScoreboardDAO {
	void save(Scoreboard scoreboard);
	void update(Scoreboard scoreboard);
	void delete(Scoreboard scoreboard);
	Scoreboard byId(Long id);
	Page<Scoreboard> paginate(PageRequest pageRequest);
}
