package br.com.tirocerto.dao.impl;

import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.ScoreboardStageDAO;
import br.com.tirocerto.model.ScoreboardStage;

@Component
@RequestScoped
public class ScoreboardStageHibernateDAO implements ScoreboardStageDAO {
	private Session session;

	public ScoreboardStageHibernateDAO(Session session) {
		this.session = session;
	}
	
	@Override
	public void save(ScoreboardStage scoreboardStage) {
		session.save(scoreboardStage);
	}

	@Override
	public void update(ScoreboardStage scoreboardStage) {
		session.update(scoreboardStage);
	}

	@Override
	public void delete(ScoreboardStage scoreboardStage) {
		session.delete(scoreboardStage);
	}
}
