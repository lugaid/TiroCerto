package br.com.tirocerto.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.ChampionshipDAO;
import br.com.tirocerto.dao.ChampionshipRankingDAO;
import br.com.tirocerto.dao.ChampionshipStageRankingDAO;
import br.com.tirocerto.model.Championship;
import br.com.tirocerto.model.ChampionshipEnrolled;
import br.com.tirocerto.model.ChampionshipRanking;
import br.com.tirocerto.model.ChampionshipStageRanking;

@Component
@RequestScoped
public class ChampionshipRankingBusiness {
	private ChampionshipDAO championshipDAO;
	private ChampionshipRankingDAO championshipRankingDAO;
	private ChampionshipStageRankingDAO championshipStageRankingDAO;

	private ChampionshipRankingBusiness(
			ChampionshipDAO championshipDAO,
			ChampionshipRankingDAO championshipRankingDAO,
			ChampionshipStageRankingDAO championshipStageRankingDAO) {
		this.championshipDAO = championshipDAO;
		this.championshipRankingDAO = championshipRankingDAO;
		this.championshipStageRankingDAO = championshipStageRankingDAO;
	}
	
	public void recalcRanking(Long id) {
		// remove all rankings by Stage
		championshipRankingDAO.deleteByChampionship(id);

		championshipRankingDAO.saveAll(calcRanking(id));
	}
	
	private List<ChampionshipRanking> calcRanking(Long id) {
		List<ChampionshipRanking> championshipRankings = new ArrayList<>();

		// get stage and modality
		Championship championship = championshipDAO.byId(id);

		// get all enrolleds
		List<ChampionshipEnrolled> championshipEnrolleds = championship.getChampionshipEnrolled();

		if(championshipEnrolleds == null) {
			return championshipRankings;
		}
		
		for (ChampionshipEnrolled championshipEnrolled : championshipEnrolleds) {

			// get the result and sort reverted to get the best results
			List<ChampionshipStageRanking> championshipStageRankings = championshipStageRankingDAO.byEnrolled(championshipEnrolled);
			Collections.sort(championshipStageRankings, Collections.reverseOrder());

			// get the quantity of objects based to qty of series setted on
			// modality
			int i = 0;
			for (ChampionshipStageRanking championshipStageRanking : championshipStageRankings) {
				if (i++ >= championship.getQtyStages()) {
					break;
				}

				//Initialize ranking
				ChampionshipRanking championshipRanking = new ChampionshipRanking(
						championshipEnrolled,
						championshipStageRanking);
				
				// add to the best result
				if (!championshipRankings
						.contains(championshipRanking)) {
					championshipRankings.add(championshipRanking);
				} else {
					championshipRankings.get(
							championshipRankings
									.indexOf(championshipRanking)).sum(
											championshipStageRanking);
				}
			}
		}

		// sort best results
		Collections.sort(championshipRankings, Collections.reverseOrder());
		
		//set ranking
		int position = 0;
		for(ChampionshipRanking championshipRanking : championshipRankings) {
			position += 1;
			
			championshipRanking.setPosition(position);
		}

		return championshipRankings;
	}
}
