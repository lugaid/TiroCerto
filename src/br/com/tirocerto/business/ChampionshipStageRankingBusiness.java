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

@Component
@RequestScoped
public class ChampionshipStageRankingBusiness {
	private ChampionshipStageDAO championshipStageDAO;
	private ChampionshipStageRankingDAO championshipStageRankingDAO;
	private List<BestStageResult> bestStageResults = new ArrayList<>();

	private ChampionshipStageRankingBusiness(
			ChampionshipStageDAO championshipStageDAO,
			ChampionshipStageRankingDAO championshipStageRankingDAO) {
		this.championshipStageDAO = championshipStageDAO;
		this.championshipStageRankingDAO = championshipStageRankingDAO;
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

		for (ChampionshipEnrolled championshipEnrolled : championshipEnrolleds) {

			// get the result and sort reverted to get the best results
			List<ChampionshipResult> championshipResults = championshipEnrolled
					.getChampionshipResult();
			Collections.sort(championshipResults, Collections.reverseOrder());

			// get the quantity of objects based to qty of series setted on
			// modality
			int i = 0;
			for (ChampionshipResult championshipResult : championshipResults) {
				if (i++ >= modality.getQtySeries()) {
					break;
				}

				// add to the best result
				addBestResult(championshipEnrolled, championshipResult);
			}
		}

		//sort best results
		Collections.sort(bestStageResults, Collections.reverseOrder());
		
		int position = 1;
		for(BestStageResult bestStageResult : bestStageResults) {
			ChampionshipStageRanking championshipStageRanking = new ChampionshipStageRanking();
			
			championshipStageRanking.setChampionshipEnrolled(bestStageResult.getChampionshipEnrolled());
			championshipStageRanking.setChampionshipStage(championshipStage);
			championshipStageRanking.setPosition(position);
			
			championshipStageRankings.add(championshipStageRanking);
			
			position++;
		}
			
		
		return championshipStageRankings;
	}

	private void addBestResult(ChampionshipEnrolled championshipEnrolled,
			ChampionshipResult championshipResult) {
		BestStageResult newBestStageResult = new BestStageResult(
				championshipEnrolled, championshipResult);

		if (!bestStageResults.contains(newBestStageResult)) {
			bestStageResults.add(newBestStageResult);
		} else {
			bestStageResults.get(bestStageResults.indexOf(newBestStageResult)).sum(championshipResult);
		}
	}

	class BestStageResult implements Comparable<BestStageResult> {
		private ChampionshipEnrolled championshipEnrolled;
		
		private Integer targetDivisionX = 0;

		private Integer targetDivision10 = 0;

		private Integer targetDivision9 = 0;

		private Integer targetDivision8 = 0;

		private Integer targetDivision7 = 0;

		private Integer targetDivision6 = 0;

		private Integer targetDivision5 = 0;

		private Integer targetDivision4 = 0;

		private Integer targetDivision3 = 0;

		private Integer targetDivision2 = 0;

		private Integer targetDivision1 = 0;

		private Integer points = 0;

		private Integer penalty = 0;

		private Integer total = 0;

		public BestStageResult(ChampionshipEnrolled championshipEnrolled,
				ChampionshipResult championshipResult) {
			this.championshipEnrolled = championshipEnrolled;
			
			sum(championshipResult);
		}

		public ChampionshipEnrolled getChampionshipEnrolled() {
			return championshipEnrolled;
		}

		public void setChampionshipEnrolled(
				ChampionshipEnrolled championshipEnrolled) {
			this.championshipEnrolled = championshipEnrolled;
		}

		public Integer getTargetDivisionX() {
			return targetDivisionX;
		}

		public void setTargetDivisionX(Integer targetDivisionX) {
			this.targetDivisionX = targetDivisionX;
		}

		public Integer getTargetDivision10() {
			return targetDivision10;
		}

		public void setTargetDivision10(Integer targetDivision10) {
			this.targetDivision10 = targetDivision10;
		}

		public Integer getTargetDivision9() {
			return targetDivision9;
		}

		public void setTargetDivision9(Integer targetDivision9) {
			this.targetDivision9 = targetDivision9;
		}

		public Integer getTargetDivision8() {
			return targetDivision8;
		}

		public void setTargetDivision8(Integer targetDivision8) {
			this.targetDivision8 = targetDivision8;
		}

		public Integer getTargetDivision7() {
			return targetDivision7;
		}

		public void setTargetDivision7(Integer targetDivision7) {
			this.targetDivision7 = targetDivision7;
		}

		public Integer getTargetDivision6() {
			return targetDivision6;
		}

		public void setTargetDivision6(Integer targetDivision6) {
			this.targetDivision6 = targetDivision6;
		}

		public Integer getTargetDivision5() {
			return targetDivision5;
		}

		public void setTargetDivision5(Integer targetDivision5) {
			this.targetDivision5 = targetDivision5;
		}

		public Integer getTargetDivision4() {
			return targetDivision4;
		}

		public void setTargetDivision4(Integer targetDivision4) {
			this.targetDivision4 = targetDivision4;
		}

		public Integer getTargetDivision3() {
			return targetDivision3;
		}

		public void setTargetDivision3(Integer targetDivision3) {
			this.targetDivision3 = targetDivision3;
		}

		public Integer getTargetDivision2() {
			return targetDivision2;
		}

		public void setTargetDivision2(Integer targetDivision2) {
			this.targetDivision2 = targetDivision2;
		}

		public Integer getTargetDivision1() {
			return targetDivision1;
		}

		public void setTargetDivision1(Integer targetDivision1) {
			this.targetDivision1 = targetDivision1;
		}

		public Integer getPoints() {
			return points;
		}

		public void setPoints(Integer points) {
			this.points = points;
		}

		public Integer getPenalty() {
			return penalty;
		}

		public void setPenalty(Integer penalty) {
			this.penalty = penalty;
		}

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public void sum(ChampionshipResult championshipResult) {
			if(championshipResult.getTargetDivisionX() != null) {
			    this.targetDivisionX += championshipResult.getTargetDivisionX();
			}

			if(championshipResult.getTargetDivision10() != null) {
			    this.targetDivision10 += championshipResult.getTargetDivision10();
			}

			if(championshipResult.getTargetDivision9() != null) {
			    this.targetDivision9 += championshipResult.getTargetDivision9();
			}

			if(championshipResult.getTargetDivision8() != null) {
			    this.targetDivision8 += championshipResult.getTargetDivision8();
			}

			if(championshipResult.getTargetDivision7() != null) {
			    this.targetDivision7 += championshipResult.getTargetDivision7();
			}

			if(championshipResult.getTargetDivision6() != null) {
			    this.targetDivision6 += championshipResult.getTargetDivision6();
			}

			if(championshipResult.getTargetDivision5() != null) {
			    this.targetDivision5 += championshipResult.getTargetDivision5();
			}

			if(championshipResult.getTargetDivision4() != null) {
			    this.targetDivision4 += championshipResult.getTargetDivision4();
			}

			if(championshipResult.getTargetDivision3() != null) {
			    this.targetDivision3 += championshipResult.getTargetDivision3();
			}

			if(championshipResult.getTargetDivision2() != null) {
			    this.targetDivision2 += championshipResult.getTargetDivision2();
			}

			if(championshipResult.getTargetDivision1() != null) {
			    this.targetDivision1 += championshipResult.getTargetDivision1();
			}

			if(championshipResult.getPoints() != null) {
			    this.points += championshipResult.getPoints();
			}

			if(championshipResult.getPenalty() != null) {
			    this.penalty += championshipResult.getPenalty();
			}

			if(championshipResult.getTotal() != null) {
			    this.total += championshipResult.getTotal();
			}
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime
					* result
					+ ((championshipEnrolled == null) ? 0
							: championshipEnrolled.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof BestStageResult))
				return false;
			BestStageResult other = (BestStageResult) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (championshipEnrolled == null) {
				if (other.championshipEnrolled != null)
					return false;
			} else if (!championshipEnrolled.equals(other.championshipEnrolled))
				return false;
			return true;
		}

		private ChampionshipStageRankingBusiness getOuterType() {
			return ChampionshipStageRankingBusiness.this;
		}
		
		@Override
		public int compareTo(BestStageResult bestStageResult) {
			if (this.getTotal() != null && bestStageResult.getTotal() == null) {
				return 1;
			}

			if (this.getTotal() == null && bestStageResult.getTotal() != null) {
				return -1;
			}

			if (this.getTotal() != null && bestStageResult.getTotal() != null
					&& this.getTotal() > bestStageResult.getTotal()) {
				return 1;
			}

			if (this.getTotal() != null && bestStageResult.getTotal() != null
					&& this.getTotal() < bestStageResult.getTotal()) {
				return -1;
			}
			if (this.getTargetDivisionX() != null
					&& bestStageResult.getTargetDivisionX() == null) {
				return 1;
			}

			if (this.getTargetDivisionX() == null
					&& bestStageResult.getTargetDivisionX() != null) {
				return -1;
			}

			if (this.getTargetDivisionX() != null
					&& bestStageResult.getTargetDivisionX() != null
					&& this.getTargetDivisionX() > bestStageResult
							.getTargetDivisionX()) {
				return 1;
			}

			if (this.getTargetDivisionX() != null
					&& bestStageResult.getTargetDivisionX() != null
					&& this.getTargetDivisionX() < bestStageResult
							.getTargetDivisionX()) {
				return -1;
			}
			if (this.getTargetDivision10() != null
					&& bestStageResult.getTargetDivision10() == null) {
				return 1;
			}

			if (this.getTargetDivision10() == null
					&& bestStageResult.getTargetDivision10() != null) {
				return -1;
			}

			if (this.getTargetDivision10() != null
					&& bestStageResult.getTargetDivision10() != null
					&& this.getTargetDivision10() > bestStageResult
							.getTargetDivision10()) {
				return 1;
			}

			if (this.getTargetDivision10() != null
					&& bestStageResult.getTargetDivision10() != null
					&& this.getTargetDivision10() < bestStageResult
							.getTargetDivision10()) {
				return -1;
			}
			if (this.getTargetDivision9() != null
					&& bestStageResult.getTargetDivision9() == null) {
				return 1;
			}

			if (this.getTargetDivision9() == null
					&& bestStageResult.getTargetDivision9() != null) {
				return -1;
			}

			if (this.getTargetDivision9() != null
					&& bestStageResult.getTargetDivision9() != null
					&& this.getTargetDivision9() > bestStageResult
							.getTargetDivision9()) {
				return 1;
			}

			if (this.getTargetDivision9() != null
					&& bestStageResult.getTargetDivision9() != null
					&& this.getTargetDivision9() < bestStageResult
							.getTargetDivision9()) {
				return -1;
			}
			if (this.getTargetDivision8() != null
					&& bestStageResult.getTargetDivision8() == null) {
				return 1;
			}

			if (this.getTargetDivision8() == null
					&& bestStageResult.getTargetDivision8() != null) {
				return -1;
			}

			if (this.getTargetDivision8() != null
					&& bestStageResult.getTargetDivision8() != null
					&& this.getTargetDivision8() > bestStageResult
							.getTargetDivision8()) {
				return 1;
			}

			if (this.getTargetDivision8() != null
					&& bestStageResult.getTargetDivision8() != null
					&& this.getTargetDivision8() < bestStageResult
							.getTargetDivision8()) {
				return -1;
			}
			if (this.getTargetDivision7() != null
					&& bestStageResult.getTargetDivision7() == null) {
				return 1;
			}

			if (this.getTargetDivision7() == null
					&& bestStageResult.getTargetDivision7() != null) {
				return -1;
			}

			if (this.getTargetDivision7() != null
					&& bestStageResult.getTargetDivision7() != null
					&& this.getTargetDivision7() > bestStageResult
							.getTargetDivision7()) {
				return 1;
			}

			if (this.getTargetDivision7() != null
					&& bestStageResult.getTargetDivision7() != null
					&& this.getTargetDivision7() < bestStageResult
							.getTargetDivision7()) {
				return -1;
			}
			if (this.getTargetDivision6() != null
					&& bestStageResult.getTargetDivision6() == null) {
				return 1;
			}

			if (this.getTargetDivision6() == null
					&& bestStageResult.getTargetDivision6() != null) {
				return -1;
			}

			if (this.getTargetDivision6() != null
					&& bestStageResult.getTargetDivision6() != null
					&& this.getTargetDivision6() > bestStageResult
							.getTargetDivision6()) {
				return 1;
			}

			if (this.getTargetDivision6() != null
					&& bestStageResult.getTargetDivision6() != null
					&& this.getTargetDivision6() < bestStageResult
							.getTargetDivision6()) {
				return -1;
			}
			if (this.getTargetDivision5() != null
					&& bestStageResult.getTargetDivision5() == null) {
				return 1;
			}

			if (this.getTargetDivision5() == null
					&& bestStageResult.getTargetDivision5() != null) {
				return -1;
			}

			if (this.getTargetDivision5() != null
					&& bestStageResult.getTargetDivision5() != null
					&& this.getTargetDivision5() > bestStageResult
							.getTargetDivision5()) {
				return 1;
			}

			if (this.getTargetDivision5() != null
					&& bestStageResult.getTargetDivision5() != null
					&& this.getTargetDivision5() < bestStageResult
							.getTargetDivision5()) {
				return -1;
			}
			if (this.getTargetDivision4() != null
					&& bestStageResult.getTargetDivision4() == null) {
				return 1;
			}

			if (this.getTargetDivision4() == null
					&& bestStageResult.getTargetDivision4() != null) {
				return -1;
			}

			if (this.getTargetDivision4() != null
					&& bestStageResult.getTargetDivision4() != null
					&& this.getTargetDivision4() > bestStageResult
							.getTargetDivision4()) {
				return 1;
			}

			if (this.getTargetDivision4() != null
					&& bestStageResult.getTargetDivision4() != null
					&& this.getTargetDivision4() < bestStageResult
							.getTargetDivision4()) {
				return -1;
			}
			if (this.getTargetDivision3() != null
					&& bestStageResult.getTargetDivision3() == null) {
				return 1;
			}

			if (this.getTargetDivision3() == null
					&& bestStageResult.getTargetDivision3() != null) {
				return -1;
			}

			if (this.getTargetDivision3() != null
					&& bestStageResult.getTargetDivision3() != null
					&& this.getTargetDivision3() > bestStageResult
							.getTargetDivision3()) {
				return 1;
			}

			if (this.getTargetDivision3() != null
					&& bestStageResult.getTargetDivision3() != null
					&& this.getTargetDivision3() < bestStageResult
							.getTargetDivision3()) {
				return -1;
			}
			if (this.getTargetDivision2() != null
					&& bestStageResult.getTargetDivision2() == null) {
				return 1;
			}

			if (this.getTargetDivision2() == null
					&& bestStageResult.getTargetDivision2() != null) {
				return -1;
			}

			if (this.getTargetDivision2() != null
					&& bestStageResult.getTargetDivision2() != null
					&& this.getTargetDivision2() > bestStageResult
							.getTargetDivision2()) {
				return 1;
			}

			if (this.getTargetDivision2() != null
					&& bestStageResult.getTargetDivision2() != null
					&& this.getTargetDivision2() < bestStageResult
							.getTargetDivision2()) {
				return -1;
			}
			if (this.getTargetDivision1() != null
					&& bestStageResult.getTargetDivision1() == null) {
				return 1;
			}

			if (this.getTargetDivision1() == null
					&& bestStageResult.getTargetDivision1() != null) {
				return -1;
			}

			if (this.getTargetDivision1() != null
					&& bestStageResult.getTargetDivision1() != null
					&& this.getTargetDivision1() > bestStageResult
							.getTargetDivision1()) {
				return 1;
			}

			if (this.getTargetDivision1() != null
					&& bestStageResult.getTargetDivision1() != null
					&& this.getTargetDivision1() < bestStageResult
							.getTargetDivision1()) {
				return -1;
			}

			return 0;
		}
	}
}
