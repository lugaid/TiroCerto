package br.com.tirocerto.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.ChampionshipStageDAO;
import br.com.tirocerto.dao.ChampionshipStageRankingDAO;
import br.com.tirocerto.model.ChampionshipEnrolled;
import br.com.tirocerto.model.ChampionshipResult;
import br.com.tirocerto.model.ChampionshipStage;
import br.com.tirocerto.model.ChampionshipStageRanking;
import br.com.tirocerto.model.Modality;

@Component()
@RequestScoped
public class ChampionshipStageRankingBusiness {
	private ChampionshipStageDAO championshipStageDAO;
	private ChampionshipStageRankingDAO championshipStageRankingDAO;

	private ChampionshipStageRankingBusiness(
			ChampionshipStageDAO championshipStageDAO,
			ChampionshipStageRankingDAO championshipStageRankingDAO) {
		this.championshipStageDAO = championshipStageDAO;
		this.championshipStageRankingDAO = championshipStageRankingDAO;
	}

	public void recalcAllStagesByChampionship(Long id) {
		List<ChampionshipStage> championshipStages = championshipStageDAO.byChampionshipId(id);
		
		if(championshipStages != null && championshipStages.size() > 0) {
			for(ChampionshipStage championshipStage : championshipStages) {
				recalcRanking(championshipStage.getId());
			}
		}
	}
	
	public void recalcRanking(Long id) {
		// remove all rankings by Stage
		championshipStageRankingDAO.deleteByStage(id);

		championshipStageRankingDAO.saveAll(calcRanking(id));
	}

	private List<ChampionshipStageRanking> calcRanking(Long id) {
		List<ChampionshipStageRanking> championshipStageRankings = new ArrayList<>();

		// get stage and modality
		ChampionshipStage championshipStage = championshipStageDAO.byId(id);
		Modality modality = championshipStage.getChampionship().getModality();

		// get all enrolleds
		List<ChampionshipEnrolled> championshipEnrolleds = championshipStage
				.getChampionship().getChampionshipEnrolled();

		if(championshipEnrolleds == null) {
			return championshipStageRankings;
		}
		
		for (ChampionshipEnrolled championshipEnrolled : championshipEnrolleds) {

			// get the result and sort reverted to get the best results
			List<ChampionshipResult> championshipResults = championshipEnrolled.getChampionshipResult();
			
			//if there are no results insert one blank ranking
			if(championshipResults == null || championshipResults.size() == 0) {
				ChampionshipStageRanking championshipStageRanking = new ChampionshipStageRanking(
						championshipEnrolled, championshipStage);

				championshipStageRankings.add(championshipStageRanking);
				
				continue;
			}
			
			Collections.sort(championshipResults, Collections.reverseOrder());

			// get the quantity of objects based to qty of series setted on
			// modality
			int i = 0;
			boolean existResults = false;
			
			for (ChampionshipResult championshipResult : championshipResults) {
				if (!championshipResult.getChampionshipStage().equals(championshipStage)) {
					continue;
				}
				
				existResults = true;
				
				if (i++ >= modality.getQtySeries()) {
					break;
				}

				// Initialize ranking
				ChampionshipStageRanking championshipStageRanking = new ChampionshipStageRanking(
						championshipEnrolled, championshipStage,
						championshipResult);

				// add to the best result
				if (!championshipStageRankings
						.contains(championshipStageRanking)) {
					championshipStageRankings.add(championshipStageRanking);
				} else {
					championshipStageRankings.get(
							championshipStageRankings
									.indexOf(championshipStageRanking)).sum(
							championshipResult);
				}
			}
			
			//if there are no results to this stage insert blank
			if(existResults == false) {
				ChampionshipStageRanking championshipStageRanking = new ChampionshipStageRanking(
						championshipEnrolled, championshipStage);

				championshipStageRankings.add(championshipStageRanking);
				
				continue;
			}
		}

		// sort best results
		Collections.sort(championshipStageRankings, Collections.reverseOrder());

		// set ranking
		int position = 0;
		for (ChampionshipStageRanking championshipStageRanking : championshipStageRankings) {
			position += 1;

			championshipStageRanking.setPosition(position);
		}

		return championshipStageRankings;
	}
}
