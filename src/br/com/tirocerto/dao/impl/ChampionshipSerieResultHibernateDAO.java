package br.com.tirocerto.dao.impl;

import org.hibernate.Session;

import br.com.tirocerto.dao.ChampionshipSerieResultDAO;
import br.com.tirocerto.model.ChampionshipSerieResult;

public class ChampionshipSerieResultHibernateDAO implements
		ChampionshipSerieResultDAO {

	private Session session;
	
	public ChampionshipSerieResultHibernateDAO(Session session) {
		this.session = session;
	}
	
	@Override
	public void save(ChampionshipSerieResult championshipSerieResult) {
		session.save(championshipSerieResult);
	}

	@Override
	public void update(ChampionshipSerieResult championshipSerieResult) {
		session.save(championshipSerieResult);
	}

	@Override
	public void delete(ChampionshipSerieResult championshipSerieResult) {
		session.save(championshipSerieResult);
	}

}
