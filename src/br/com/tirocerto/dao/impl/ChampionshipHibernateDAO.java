package br.com.tirocerto.dao.impl;

import static br.com.tirocerto.util.hibernate.PaginateSortedCollumns.addSortedColumns;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

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
	private Session session;;

	public ChampionshipHibernateDAO(
			Session session,
			ModalityTargetDivisionHibernateDAO modalityTargetDivisionHibernateDAO) {
		this.session = session;
	}

	@Override
	public void save(Championship championship) {
		fixChampionshipStage(championship);
		session.save(championship);
	}

	@Override
	public void update(Championship championship) {
		fixChampionshipStage(championship);
		session.merge(championship);
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
		String nome = pageRequest.getSearch() == null ? "" : pageRequest
				.getSearch();

		Criteria criteria = session.createCriteria(Championship.class)
				.setReadOnly(true);

		criteria.add(Restrictions
				.ilike("description", nome, MatchMode.ANYWHERE));

		criteria.setFirstResult(pageRequest.getStart());
		criteria.setMaxResults(pageRequest.getSize());

		addSortedColumns(pageRequest, criteria);

		List<Championship> resultList = criteria.list();

		Page<Championship> page = new PageResponse<Championship>(resultList,
				pageRequest, getRowCount());

		return page;
	}

	private Long getRowCount() {
		Long count = (Long) session.createQuery(
				"select count(*) from Championship").uniqueResult();
		return count == null ? 0 : count;
	}

	private void fixChampionshipStage(Championship championship) {
		if (championship == null
				|| championship.getChampionshipStages() == null) {
			return;
		}

		for (ChampionshipStage championshipStages : championship
				.getChampionshipStages()) {
			championshipStages.setChanpionship(championship);
		}
	}
}
