package br.com.tirocerto.dao.impl;


import static br.com.tirocerto.util.hibernate.PaginateCollumns.addSearchColumns;
import static br.com.tirocerto.util.hibernate.PaginateCollumns.addSortedColumns;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
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
		session.flush();
	}

	@Override
	public void update(ChampionshipEnrolled championshipEnrolled) {
		session.merge(championshipEnrolled);
		session.flush();
	}

	@Override
	public void delete(ChampionshipEnrolled championshipEnrolled) {
		session.delete(championshipEnrolled);
		session.flush();
	}
	
	@Override
	public ChampionshipEnrolled getById(ChampionshipEnrolled championshipEnrolled) {
		Criteria criteria = session.createCriteria(ChampionshipEnrolled.class);

		criteria.add(Restrictions.eq("championship.id", championshipEnrolled.getChampionship().getId()));
		criteria.add(Restrictions.eq("associate.id", championshipEnrolled.getAssociate().getId()));
		
		return (ChampionshipEnrolled)criteria.uniqueResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Page<ChampionshipEnrolled> paginateEnrolledByChampionship(
			Championship championship, PageRequest pageRequest) {
		
		Criteria criteria = session.createCriteria(ChampionshipEnrolled.class);
		
		criteria.createAlias("championship", "championship");
		criteria.createAlias("associate", "associate");
		
		criteria.add(Restrictions.eq("championship.id", championship.getId()));

		criteria.setFirstResult(pageRequest.getStart());
		criteria.setMaxResults(pageRequest.getSize());

		addSortedColumns(pageRequest, criteria);
		addSearchColumns(pageRequest, criteria);
		
		List<ChampionshipEnrolled> resultList = criteria.list();

		Page<ChampionshipEnrolled> page = new PageResponse<ChampionshipEnrolled>(
				resultList, pageRequest,
				getRowCountByChampionship(championship), getRowCountByChampionshipRestriction(pageRequest, championship));

		return page;
	}

	private Long getRowCountByChampionship(Championship championship) {
		Criteria criteria = session.createCriteria(ChampionshipEnrolled.class); 
		criteria.setProjection(Projections.rowCount());
		criteria.createAlias("championship", "championship");
		criteria.add(Restrictions.eq("championship.id", championship.getId()));
		return ((Long)criteria.list().get(0));
	}
	
	private Long getRowCountByChampionshipRestriction(PageRequest pageRequest, Championship championship) {
		Criteria criteria = session.createCriteria(ChampionshipEnrolled.class); 
		criteria.setProjection(Projections.rowCount());
		criteria.createAlias("championship", "championship");
		criteria.createAlias("associate", "associate");
		criteria.add(Restrictions.eq("championship.id", championship.getId()));
		addSearchColumns(pageRequest, criteria);
		return ((Long)criteria.list().get(0));
	}
}
