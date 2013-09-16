package br.com.tirocerto.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.ScoreboardDAO;
import br.com.tirocerto.model.Scoreboard;
import br.com.tirocerto.model.ScoreboardStage;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;
import br.com.tirocerto.util.datatable.PageResponse;
import static br.com.tirocerto.util.hibernate.PaginateCollumns.addSortedColumns;
import static br.com.tirocerto.util.hibernate.PaginateCollumns.addSearchColumns;

@Component
@RequestScoped
public class ScoreboardHibernateDAO implements ScoreboardDAO {
	private Session session;

	public ScoreboardHibernateDAO(Session session) {
		this.session = session;
	}

	@Override
	public void save(Scoreboard scoreboard) {
		fixScoreboardStage(scoreboard);
		session.save(scoreboard);
	}

	@Override
	public void update(Scoreboard scoreboard) {
		fixScoreboardStage(scoreboard);
		session.update(scoreboard);
	}

	@Override
	public void delete(Scoreboard scoreboard) {
		fixScoreboardStage(scoreboard);
		session.delete(scoreboard);
	}

	@Override
	public Scoreboard byId(Long id) {
		return (Scoreboard) session.get(Scoreboard.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Scoreboard> paginate(PageRequest pageRequest) {
		Criteria criteria = session.createCriteria(Scoreboard.class).setReadOnly(true);

		criteria.setFirstResult(pageRequest.getStart());
		criteria.setMaxResults(pageRequest.getSize());

		addSortedColumns(pageRequest, criteria);
		addSearchColumns(pageRequest, criteria);
		
		List<Scoreboard> resultList = criteria.list();
		
		Page<Scoreboard> page = new PageResponse<Scoreboard>(resultList,
				pageRequest, getRowCount());

		return page;
	}
	
	private Long getRowCount() {
		Long count = (Long)session.createQuery("select count(*) from Scoreboard").uniqueResult();
		return count == null ? 0 : count;
	}
	
	private void fixScoreboardStage(Scoreboard scoreboard) {
		if (scoreboard == null
				|| scoreboard.getScoreboardStage() == null) {
			
			return;
		}
		
		for(ScoreboardStage scoreboardStage : scoreboard.getScoreboardStage()) {
			scoreboardStage.setScoreboard(scoreboard);
		}
	}
}
