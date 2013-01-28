package br.com.tirocerto.dao.impl;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.ChampionshipEnrolledDAO;
import br.com.tirocerto.model.ChampionshipEnrolled;

@Component
@RequestScoped
public class ChampionshipEnrolledHibernateDAO implements ChampionshipEnrolledDAO {
	private Session session;
	
	public ChampionshipEnrolledHibernateDAO(
			Session session) {
		this.session = session;
	}
	
	@Override
	public void save(ChampionshipEnrolled championshipEnrolled) {
		session.save(championshipEnrolled);
	}

	@Override
	public void update(ChampionshipEnrolled championshipEnrolled) {
		session.merge(championshipEnrolled);
	}

	@Override
	public void delete(ChampionshipEnrolled championshipEnrolled) {
		session.delete(championshipEnrolled);
	}
}
