package br.com.tirocerto.dao.impl;

import org.hibernate.Session;

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
}
