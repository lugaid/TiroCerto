package br.com.tirocerto.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.ChampionshipStageRankingDAO;
import br.com.tirocerto.model.ChampionshipStageRanking;

@Component
@RequestScoped
public class ChampionshipStageRankingHibernateDAO implements
		ChampionshipStageRankingDAO {

	private Session session;
	
	private ChampionshipStageRankingHibernateDAO(Session session) {
		this.session = session;
	}

	@Override
	public void save(ChampionshipStageRanking championshipStageRanking) {
		session.save(championshipStageRanking);
	}

	@Override
	public void saveAll(List<ChampionshipStageRanking> championshipStageRankings) {
		if(championshipStageRankings == null){
			return;
		}
		
		for(ChampionshipStageRanking championshipStageRanking : championshipStageRankings) {
			save(championshipStageRanking);
		}
	}
	
	@Override
	public void update(ChampionshipStageRanking championshipStageRanking) {
		session.update(championshipStageRanking);
	}

	@Override
	public void delete(ChampionshipStageRanking championshipStageRanking) {
		session.delete(championshipStageRanking);
	}

	@Override
	public void deleteByStage(Long stageId) {
		Query query = session
				.createQuery("delete ChampionshipStageRanking where championshipStage.id = :id");
		query.setLong("id", stageId);
		query.executeUpdate();
	}
}
