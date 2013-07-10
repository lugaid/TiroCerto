package br.com.tirocerto.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.ChampionshipRankingDAO;
import br.com.tirocerto.model.ChampionshipRanking;

@Component
@RequestScoped
public class ChampionshipRankingHibernateDAO implements ChampionshipRankingDAO {

	private Session session;

	public ChampionshipRankingHibernateDAO(Session session) {
		this.session = session;
	}

	@Override
	public void save(ChampionshipRanking championshipRanking) {
		session.save(championshipRanking);
	}

	@Override
	public void saveAll(List<ChampionshipRanking> championshipRankings) {
		if (championshipRankings == null) {
			return;
		}

		for (ChampionshipRanking championshipRanking : championshipRankings) {
			save(championshipRanking);
		}
		session.flush();
	}

	@Override
	public void update(ChampionshipRanking championshipRanking) {
		session.update(championshipRanking);
	}

	@Override
	public void delete(ChampionshipRanking championshipRanking) {
		session.delete(championshipRanking);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteByChampionship(Long championshipId) {
		Criteria criteria = session.createCriteria(ChampionshipRanking.class)
				.setReadOnly(true);

		criteria.add(Restrictions
				.eq("championshipEnrolled.championship.id", championshipId));
		
		List<ChampionshipRanking> resultList = criteria.list();
		
		for(ChampionshipRanking championshipRanking : resultList) {
			delete(championshipRanking);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChampionshipRanking> byChampionship(Long championshipId) {
		Criteria criteria = session.createCriteria(ChampionshipRanking.class)
				.setReadOnly(true);

		criteria.add(Restrictions
				.eq("championshipEnrolled.championship.id", championshipId));
		
		List<ChampionshipRanking> resultList = criteria.list();
		
		return resultList;
	}

}
