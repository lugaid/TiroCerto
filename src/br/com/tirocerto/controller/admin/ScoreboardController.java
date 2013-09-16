/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.tirocerto.controller.admin;

import static br.com.tirocerto.util.datatable.PagingResults.dataTablesPaging;
import java.util.Collections;
import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tirocerto.business.SortChampionshipStageRankingByPosition;
import br.com.tirocerto.dao.ChampionshipStageDAO;
import br.com.tirocerto.dao.ScoreboardDAO;
import br.com.tirocerto.model.Scoreboard;
import br.com.tirocerto.session.ScoreboardRefresh;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

@Resource
@LoggedIn
@Path("/admin/scoreboard")
public class ScoreboardController {
	private Result result;
	private ScoreboardDAO scoreboardDAO;
	private Validator validator;
	private ScoreboardRefresh scoreboardRefresh;
	private ChampionshipStageDAO championshipStageDAO;

	public ScoreboardController(Result result, ScoreboardDAO scoreboardDAO,
			Validator validator, ScoreboardRefresh scoreboardRefresh, ChampionshipStageDAO championshipStageDAO) {
		this.result = result;
		this.scoreboardDAO = scoreboardDAO;
		this.validator = validator;
		this.scoreboardRefresh = scoreboardRefresh;
		this.championshipStageDAO = championshipStageDAO;
	}

	@Get("")
	public void list() {

	}

	@Path("/show")
	public void show() {
		result.include("championshipStages", championshipStageDAO.allStages());
	}

	@Path("/showPanel/{id}")
	public void showPanel(Long id) {
		result.include("scoreboard_id", id);
	}

	@Get("/paginate")
	public void paginate(PageRequest pageRequest) {
		Page<Scoreboard> scoreboardPage = this.scoreboardDAO
				.paginate(pageRequest);
		this.result.use(dataTablesPaging()).from(scoreboardPage);
	}

	@Get("/new")
	public void formNew() {
		result.forwardTo(this).show();
	}

	@Get("/edit/{id}")
	public void formEdit(Long id) {
		Scoreboard scoreboard = loadById(id);

		result.include("scoreboard", scoreboard);
		result.include("mode", "post");
		result.forwardTo(this).show();
	}

	@Get("/delete/{id}")
	public void formDelete(Long id) {
		Scoreboard scoreboard = loadById(id);

		result.include("scoreboard", scoreboard);
		result.include("mode", "delete");
		result.forwardTo(this).show();
	}

	@Put("")
	public void save(final Scoreboard scoreboard) {
		// bean validator
		validator.validate(scoreboard);
		validator.onErrorRedirectTo(this).formNew();

		scoreboardDAO.save(scoreboard);
		result.include("success", "new");
		result.forwardTo(this).list();
	}

	@Post("")
	public void update(final Scoreboard scoreboard) {
		// bean validator
		validator.validate(scoreboard);
		validator.onErrorRedirectTo(this).formEdit(scoreboard.getId());

		scoreboardDAO.update(scoreboard);
		result.include("success", "update");
		result.forwardTo(this).list();
	}

	@Delete("")
	public void delete(final Scoreboard scoreboard) {
		scoreboardDAO.delete(scoreboard);
		result.include("success", "delete");
		result.forwardTo(this).list();
	}

	@Path("/refresh/{id}")
	public void refresh(Long id) {
		int nextStage = 0;
		Scoreboard scoreboard = scoreboardDAO.byId(id);

		if (scoreboardRefresh.getLastScoreboardStage() != null
				&& scoreboard.getScoreboardStage().contains(
						scoreboardRefresh.getLastScoreboardStage())) {

			nextStage = scoreboard.getScoreboardStage().indexOf(
					scoreboardRefresh.getLastScoreboardStage()) + 1;

			if (nextStage > (scoreboard.getScoreboardStage().size() - 1)) {
				nextStage = 0;
			}
		}

		scoreboardRefresh.setLastScoreboardStage(scoreboard
				.getScoreboardStage().get(nextStage));

		Collections.sort(scoreboard.getScoreboardStage().get(nextStage)
				.getChampionshipStage().getChampionshipStageRanking(),
				new SortChampionshipStageRankingByPosition());

		result.include("scoreboard", scoreboard);
		result.include("championshipStage", scoreboard.getScoreboardStage()
				.get(nextStage).getChampionshipStage());
	}

	private Scoreboard loadById(Long id) {
		final Scoreboard scoreboard = scoreboardDAO.byId(id);

		validator.checking(new Validations() {
			{
				that(scoreboard != null, "scoreboard", "not.found");
			}
		});

		validator.onErrorRedirectTo(this).list();

		return scoreboard;
	}
}
