package br.com.tirocerto.dao.impl;

import static br.com.tirocerto.util.hibernate.PaginateCollumns.addSortedColumns;
import static br.com.tirocerto.util.hibernate.PaginateCollumns.addSearchColumns;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.ChampionshipDAO;
import br.com.tirocerto.model.Championship;
import br.com.tirocerto.model.ChampionshipStage;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;
import br.com.tirocerto.util.datatable.PageResponse;

@Component
@RequestScoped
public class ChampionshipHibernateDAO implements ChampionshipDAO {
	private Session session;
	private ChampionshipStageHibernateDAO championshipStageHibernateDAO;

	public ChampionshipHibernateDAO(
			Session session,
			ChampionshipStageHibernateDAO championshipStageHibernateDAO) {
		this.session = session;
		this.championshipStageHibernateDAO = championshipStageHibernateDAO;
	}

	@Override
	public void save(Championship championship) {
		fixChampionshipStage(championship);
		session.save(championship);
	}

	@Override
	public void update(Championship championship) {
		fixChampionshipStage(championship);
		removeChampionshipStages(championship);
		session.merge(championship);
		session.flush();
		session.clear();
	}

	@Override
	public void delete(Championship championship) {
		fixChampionshipStage(championship);
		session.delete(championship);
	}

	@Override
	public Championship byId(Long id) {
		return (Championship) session.get(Championship.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Championship> paginate(PageRequest pageRequest) {
		Criteria criteria = session.createCriteria(Championship.class)
				.setReadOnly(true);

		criteria.setFirstResult(pageRequest.getStart());
		criteria.setMaxResults(pageRequest.getSize());

		criteria.createAlias("modality", "modality");
		
		addSortedColumns(pageRequest, criteria);
		addSearchColumns(pageRequest, criteria);

		List<Championship> resultList = criteria.list();

		Page<Championship> page = new PageResponse<Championship>(resultList,
				pageRequest, getRowCount(), getRowCountRestriction(pageRequest));

		return page;
	}

	private Long getRowCount() {
		Criteria criteria = session.createCriteria(Championship.class); 
		criteria.setProjection(Projections.rowCount());
		return ((Long)criteria.list().get(0));
	}
	
	private Long getRowCountRestriction(PageRequest pageRequest) {
		Criteria criteria = session.createCriteria(Championship.class); 
		criteria.setProjection(Projections.rowCount());
		criteria.createAlias("modality", "modality");
		addSearchColumns(pageRequest, criteria);
		return ((Long)criteria.list().get(0));
	}
	
	private void fixChampionshipStage(Championship championship) {
		if (championship == null
				|| championship.getChampionshipStages() == null) {
			return;
		}

		for (ChampionshipStage championshipStages : championship
				.getChampionshipStages()) {
			championshipStages.setChampionship(championship);
		}
	}

	private void removeChampionshipStages(Championship championship) {
		if (championship == null) {
			return;
		}

		Championship championshipOld = byId(championship.getId());

		List<ChampionshipStage> oldChampinshipStages;

		if (championshipOld == null
				|| (oldChampinshipStages = championshipOld
						.getChampionshipStages()) == null) {
			return;
		}
		
		List<ChampionshipStage> removedChampinshipStages = new ArrayList<>();
		removedChampinshipStages.addAll(oldChampinshipStages);
		removedChampinshipStages.removeAll(championship.getChampionshipStages());

		for (ChampionshipStage removedChampionshipStage : removedChampinshipStages) {
			championshipStageHibernateDAO
					.delete(removedChampionshipStage);
		}		
	}

	@Override
	public boolean existsByModality(Long modalityId) {
		Query query = session.createQuery("select count(*) from Championship where modality.id = :mi");
		
		query.setLong("mi", modalityId);
		
		Long count = (Long)query.uniqueResult();
		return count == null || count == 0 ? false : true;
	}
}
