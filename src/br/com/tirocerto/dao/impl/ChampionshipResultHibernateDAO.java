package br.com.tirocerto.dao.impl;

import org.hibernate.Session;

import br.com.tirocerto.dao.ChampionshipResultDAO;
import br.com.tirocerto.model.ChampionshipResult;
import br.com.tirocerto.model.ChampionshipStage;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

public class ChampionshipResultHibernateDAO implements ChampionshipResultDAO {
	private Session session;
	
	public ChampionshipResultHibernateDAO(Session session) {
		this.session = session;
	}
	
	@Override
	public void save(ChampionshipResult championshipResult) {
		session.save(championshipResult);
	}

	@Override
	public void update(ChampionshipResult championshipResult) {
		session.update(championshipResult);
	}

	@Override
	public void delete(ChampionshipResult championshipResult) {
		session.delete(championshipResult);
	}

	@Override
	public Page<ChampionshipResult> paginateByChampionshipStage(
			PageRequest pageRequest, ChampionshipStage championshipStage) {
		// TODO Auto-generated method stub
		return null;
	}
}
