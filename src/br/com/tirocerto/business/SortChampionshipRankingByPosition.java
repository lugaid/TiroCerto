package br.com.tirocerto.business;

import java.util.Comparator;

import br.com.tirocerto.model.ChampionshipRanking;

public class SortChampionshipRankingByPosition implements Comparator<ChampionshipRanking> {

	@Override
	public int compare(ChampionshipRanking arg0, ChampionshipRanking arg1) {
		return arg0.getPosition().compareTo(arg1.getPosition());
	}
}
