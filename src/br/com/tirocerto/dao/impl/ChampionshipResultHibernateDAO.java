package br.com.tirocerto.dao.impl;

import static br.com.tirocerto.util.hibernate.PaginateCollumns.addSortedColumns;
import static br.com.tirocerto.util.hibernate.PaginateCollumns.addSearchColumns;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.ChampionshipResultDAO;
import br.com.tirocerto.model.ChampionshipEnrolled;
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
		session.flush();
	}

	@Override
	public ChampionshipResult byId(Long id) {
		return (ChampionshipResult) session.get(ChampionshipResult.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<ChampionshipResult> paginateByChampionshipStage(
			PageRequest pageRequest, ChampionshipStage championshipStage) {

		Criteria criteria = session.createCriteria(ChampionshipResult.class)
				.setReadOnly(true);

		criteria.createAlias("championshipEnrolled", "championshipEnrolled");
		criteria.createAlias("championshipEnrolled.associate", "associate");
		
		criteria.add(Restrictions.eq("championshipStage.id",
				championshipStage.getId()));

		criteria.setFirstResult(pageRequest.getStart());
		criteria.setMaxResults(pageRequest.getSize());
		
		addSortedColumns(pageRequest, criteria);
		addSearchColumns(pageRequest, criteria);
		
		List<ChampionshipResult> resultList = criteria.list();

		Page<ChampionshipResult> page = new PageResponse<ChampionshipResult>(
				resultList, pageRequest,
				getRowCountByChampionshipStage(championshipStage),
				getRowCountByChampionshipStageRestriction(pageRequest,
						championshipStage));

		return page;
	}

	private Long getRowCountByChampionshipStage(
			ChampionshipStage championshipStage) {
		Criteria criteria = session.createCriteria(ChampionshipResult.class);
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq("championshipStage.id",
				championshipStage.getId()));
		return ((Long) criteria.list().get(0));
	}

	private Long getRowCountByChampionshipStageRestriction(
			PageRequest pageRequest, ChampionshipStage championshipStage) {
		Criteria criteria = session.createCriteria(ChampionshipResult.class);
		criteria.setProjection(Projections.rowCount());
		
		criteria.createAlias("championshipEnrolled", "championshipEnrolled");
		criteria.createAlias("championshipEnrolled.associate", "associate");
		
		criteria.add(Restrictions.eq("championshipStage.id",
				championshipStage.getId()));
		addSearchColumns(pageRequest, criteria);
		
		return ((Long) criteria.list().get(0));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChampionshipResult> byEnrolledAndStage(
			ChampionshipEnrolled championshipEnrolled,
			ChampionshipStage championshipStage) {
		Criteria criteria = session.createCriteria(ChampionshipResult.class);

		criteria.createAlias("championshipStage", "cs");
		criteria.createAlias("championshipEnrolled", "ce");

		criteria.add(Restrictions.eq("ce.championship.id", championshipEnrolled
				.getChampionship().getId()));
		criteria.add(Restrictions.eq("ce.associate.id", championshipEnrolled
				.getAssociate().getId()));
		criteria.add(Restrictions.eq("cs.id", championshipStage.getId()));

		return criteria.list();
	}

	@Override
	public boolean existResult(ChampionshipResult championshipResult) {
		Criteria criteria = session.createCriteria(ChampionshipResult.class);

		if (championshipResult.getId() != null) {
			criteria.add(Restrictions.ne("id", championshipResult.getId()));
		}

		criteria.createAlias("championshipStage", "cs");
		criteria.createAlias("championshipEnrolled", "ce");

		criteria.add(Restrictions.eq("ce.associate.id", championshipResult
				.getChampionshipEnrolled().getAssociate().getId()));
		criteria.add(Restrictions.eq("ce.championship.id", championshipResult
				.getChampionshipEnrolled().getChampionship().getId()));
		criteria.add(Restrictions.eq("cs.id", championshipResult
				.getChampionshipStage().getId()));
		criteria.add(Restrictions.eq("serie", championshipResult.getSerie()));

		List<?> list = criteria.list();

		return list != null && list.size() > 0;
	}
}
