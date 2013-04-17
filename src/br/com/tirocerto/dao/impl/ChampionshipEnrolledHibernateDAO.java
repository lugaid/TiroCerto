package br.com.tirocerto.dao.impl;


import static br.com.tirocerto.util.hibernate.PaginateSortedCollumns.addSortedColumns;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.AssociateDAO;
import br.com.tirocerto.dao.ChampionshipEnrolledDAO;
import br.com.tirocerto.model.Championship;
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

	@Override
	@SuppressWarnings("unchecked")
	public Page<ChampionshipEnrolled> paginateEnrolledByChampionship(
			Championship championship, PageRequest pageRequest) {
		String nome = pageRequest.getSearch() == null ? "" : pageRequest
				.getSearch();
		
		Criteria criteria = session.createCriteria(ChampionshipEnrolled.class);
		
		criteria.createAlias("championship", "championship");
		criteria.createAlias("associate", "associate");
		
		criteria.add(Restrictions.eq("championship.id", championship.getId()));
		
		criteria.add(Restrictions.ilike("associate.name", nome, MatchMode.ANYWHERE));
		
		criteria.setFirstResult(pageRequest.getStart());
		criteria.setMaxResults(pageRequest.getSize());

		addSortedColumns(pageRequest, criteria);

		List<ChampionshipEnrolled> resultList = criteria.list();

		Page<ChampionshipEnrolled> page = new PageResponse<ChampionshipEnrolled>(
				resultList, pageRequest,
				getRowCountByChampionship(championship));

		return page;
	}

	@SuppressWarnings("unused")
	private Long getRowCount() {
		Long count = (Long) session.createQuery(
				"select count(*) from ChampionshipEnrolled").uniqueResult();
		return count == null ? 0 : count;
	}

	private Long getRowCountByChampionship(Championship championship) {
		Long count = (Long) session
				.createQuery(
						"select count(*) from ChampionshipEnrolled where championship.id = :id")
				.setLong("id", championship.getId()).uniqueResult();
		return count == null ? 0 : count;
	}
}
