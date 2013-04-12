package br.com.tirocerto.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.ChampionshipResultDAO;
import br.com.tirocerto.model.ChampionshipResult;
import br.com.tirocerto.model.ChampionshipStage;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;
import br.com.tirocerto.util.datatable.PageResponse;

@Component
@RequestScoped
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

	@SuppressWarnings("unchecked")
	@Override
	public Page<ChampionshipResult> paginateByChampionshipStage(
			PageRequest pageRequest, ChampionshipStage championshipStage) {
		
		Criteria criteria = session.createCriteria(ChampionshipResult.class).setReadOnly(true);
		
		criteria.add(Restrictions.eq("championshipStage.id", championshipStage.getId()));
				
		criteria.setFirstResult(pageRequest.getStart());
		criteria.setMaxResults(pageRequest.getSize());
		
		List<ChampionshipResult> resultList = criteria.list();
		
		Page<ChampionshipResult> page = new PageResponse<ChampionshipResult>(resultList,
				pageRequest, getRowCountByChampionshipStage(championshipStage));

		return page;
	}
	
	private Long getRowCountByChampionshipStage(ChampionshipStage championshipStage) {
		Long count = (Long)session.createQuery("select count(*) from ChampionshipResult").uniqueResult();
		return count == null ? 0 : count;
	}
}
