package br.com.tirocerto.dao.impl;

import static br.com.tirocerto.util.hibernate.PaginateSortedCollumns.addSortedColumns;

import java.util.List;

import javax.persistence.FetchType;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.AssociateDAO;
import br.com.tirocerto.dao.ChampionshipEnrolledDAO;
import br.com.tirocerto.model.Associate;
import br.com.tirocerto.model.ChampionshipEnrolled;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;
import br.com.tirocerto.util.datatable.PageResponse;

@Component
@RequestScoped
public class ChampionshipEnrolledHibernateDAO implements
		ChampionshipEnrolledDAO {
	private Session session;

	public ChampionshipEnrolledHibernateDAO(Session session,
			AssociateDAO associateDAO) {
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

	@SuppressWarnings("unchecked")
	@Override
	public Page<ChampionshipEnrolled> paginate(PageRequest pageRequest,
			Long championshipId) {
		String nome = pageRequest.getSearch() == null ? "" : pageRequest
				.getSearch();

		Criteria criteria = session.createCriteria(Associate.class);
		criteria.createAlias("championshipEnrolled", "ChampionshipEnrolled");
		
		criteria.add(Restrictions.ilike("associateAlias.name", nome, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("ChampionshipEnrolled.championship.id", championshipId));

		criteria.setFirstResult(pageRequest.getStart());
		criteria.setMaxResults(pageRequest.getSize());

		addSortedColumns(pageRequest, criteria);

		List<ChampionshipEnrolled> resultList = criteria.list();

		
		Page<ChampionshipEnrolled> page = new PageResponse<ChampionshipEnrolled>(
				resultList, pageRequest, getRowCount());

		return page;
	}

	private Long getRowCount() {
		Long count = (Long) session.createQuery(
				"select count(*) from ChampionshipEnrolled").uniqueResult();
		return count == null ? 0 : count;
	}
}
