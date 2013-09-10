package br.com.tirocerto.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.ChampionshipStageDAO;
import br.com.tirocerto.model.ChampionshipStage;

@Component
@RequestScoped
public class ChampionshipStageHibernateDAO implements ChampionshipStageDAO {
	private Session session;

	public ChampionshipStageHibernateDAO(Session session) {
		this.session = session;
	}
	
	@Override
	public void save(ChampionshipStage championshipStage) {
		session.save(championshipStage);
	}

	@Override
	public void update(ChampionshipStage championshipStage) {
		session.update(championshipStage);
	}

	@Override
	public void delete(ChampionshipStage championshipStage) {
		session.delete(championshipStage);
	}

	@Override
	public ChampionshipStage byId(Long id) {
		return (ChampionshipStage) session.get(ChampionshipStage.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChampionshipStage> byChampionshipId(Long id) {
		Criteria criteria = session.createCriteria(ChampionshipStage.class)
				.setReadOnly(true);

		criteria.createAlias("championship", "ca");
		
		criteria.add(Restrictions.eq("ca.id", id));
		
		return (List<ChampionshipStage>) criteria.list();
	}
}
