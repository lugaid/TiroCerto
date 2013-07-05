package br.com.tirocerto.dao.impl;

import static br.com.tirocerto.util.hibernate.PaginateCollumns.addSortedColumns;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.business.ChampionshipRankingBusiness;
import br.com.tirocerto.business.ChampionshipStageRankingBusiness;
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
	private ChampionshipStageRankingBusiness championshipStageRankingBusiness;
	private ChampionshipRankingBusiness championshipRankingBusiness;

	public ChampionshipResultHibernateDAO(Session session,
			ChampionshipStageRankingBusiness championshipStageRankingBusiness,
			ChampionshipRankingBusiness championshipRankingBusiness) {
		this.session = session;
		this.championshipStageRankingBusiness = championshipStageRankingBusiness;
		this.championshipRankingBusiness = championshipRankingBusiness;
	}

	@Override
	public void save(ChampionshipResult championshipResult) {
		session.save(championshipResult);
		championshipStageRankingBusiness.recalcRanking(championshipResult
				.getChampionshipStage().getId());
		championshipRankingBusiness.recalcRanking(championshipResult
				.getChampionshipEnrolled().getChampionship().getId());
	}

	@Override
	public void update(ChampionshipResult championshipResult) {
		session.update(championshipResult);
		championshipStageRankingBusiness.recalcRanking(championshipResult
				.getChampionshipStage().getId());
		championshipRankingBusiness.recalcRanking(championshipResult
				.getChampionshipEnrolled().getChampionship().getId());
	}

	@Override
	public void delete(ChampionshipResult championshipResult) {
		session.delete(championshipResult);
		session.flush();
		championshipStageRankingBusiness.recalcRanking(championshipResult
				.getChampionshipStage().getId());
		championshipRankingBusiness.recalcRanking(championshipResult
				.getChampionshipEnrolled().getChampionship().getId());
	}

	@Override
	public ChampionshipResult byId(Long id) {
		return (ChampionshipResult) session.get(ChampionshipResult.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<ChampionshipResult> paginateByChampionshipStage(
			PageRequest pageRequest, ChampionshipStage championshipStage) {

		String nome = pageRequest.getSearch() == null ? "" : pageRequest
				.getSearch();

		Criteria criteria = session.createCriteria(ChampionshipResult.class)
				.setReadOnly(true);

		criteria.createAlias("championshipEnrolled", "ce");
		criteria.createAlias("ce.associate", "as");

		criteria.add(Restrictions.ilike("as.name", nome, MatchMode.ANYWHERE));

		criteria.add(Restrictions.eq("championshipStage.id",
				championshipStage.getId()));

		criteria.setFirstResult(pageRequest.getStart());
		criteria.setMaxResults(pageRequest.getSize());

		List<ChampionshipResult> resultList = criteria.list();

		addSortedColumns(pageRequest, criteria);

		Page<ChampionshipResult> page = new PageResponse<ChampionshipResult>(
				resultList, pageRequest,
				getRowCountByChampionshipStage(championshipStage));

		return page;
	}

	private Long getRowCountByChampionshipStage(
			ChampionshipStage championshipStage) {
		Long count = (Long) session
				.createQuery(
						"select count(*) from ChampionshipResult where championshipStage.id = :id")
				.setLong("id", championshipStage.getId()).uniqueResult();
		return count == null ? 0 : count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChampionshipResult> byEnrolledAndStage(ChampionshipEnrolled championshipEnrolled, ChampionshipStage championshipStage) {
		Criteria criteria = session.createCriteria(ChampionshipResult.class);

		criteria.createAlias("championshipStage", "cs");
		criteria.createAlias("championshipEnrolled", "ce");
		
		criteria.add(Restrictions.eq("ce.championship.id", championshipEnrolled.getChampionship().getId()));
		criteria.add(Restrictions.eq("ce.associate.id", championshipEnrolled.getAssociate().getId()));
		criteria.add(Restrictions.eq("cs.id", championshipStage.getId()));

		return criteria.list();
	}
}
