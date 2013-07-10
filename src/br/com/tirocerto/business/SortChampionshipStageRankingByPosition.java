package br.com.tirocerto.business;

import java.util.Comparator;

import br.com.tirocerto.model.ChampionshipStageRanking;

public class SortChampionshipStageRankingByPosition implements Comparator<ChampionshipStageRanking> {

	@Override
	public int compare(ChampionshipStageRanking arg0, ChampionshipStageRanking arg1) {
		return arg0.getPosition().compareTo(arg1.getPosition());
	}
}
