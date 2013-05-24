package br.com.tirocerto.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
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
	}

	@Override
	public void update(ChampionshipRanking championshipRanking) {
		session.update(championshipRanking);
	}

	@Override
	public void delete(ChampionshipRanking championshipRanking) {
		session.delete(championshipRanking);
	}

	@Override
	public void deleteByChampionship(Long championshipId) {
		Query query = session
				.createQuery("delete ChampionshipRanking where championshipEnrolled.championship.id = :id");
		query.setLong("id", championshipId);
		query.executeUpdate();
	}

}
