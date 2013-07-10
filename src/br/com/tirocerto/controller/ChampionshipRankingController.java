package br.com.tirocerto.controller;

import java.util.Collections;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.tirocerto.business.SortChampionshipRankingByPosition;
import br.com.tirocerto.dao.ChampionshipDAO;
import br.com.tirocerto.dao.ChampionshipRankingDAO;
import br.com.tirocerto.model.Championship;
import br.com.tirocerto.model.ChampionshipRanking;

@Resource
@Path("/championshipRanking")
public class ChampionshipRankingController {
	private Result result;
	private ChampionshipDAO championshipDAO;
	private ChampionshipRankingDAO championshipRankingDAO;
	
	public ChampionshipRankingController(Result result, ChampionshipDAO championshipDAO, ChampionshipRankingDAO championshipRankingDAO) {
		this.result = result;
		this.championshipDAO = championshipDAO;
		this.championshipRankingDAO = championshipRankingDAO;
	}
	
	@Get("/{championship.id}")
	public void list(Championship championship) {
		championship = championshipDAO.byId(championship.getId());

		List<ChampionshipRanking> championshipRanking = championshipRankingDAO.byChampionship(championship.getId());

		Collections.sort(championshipRanking, new SortChampionshipRankingByPosition());
		
		result.include("championship", championship);
		
		result.include("championshipRanking", championshipRanking);
	}
}
