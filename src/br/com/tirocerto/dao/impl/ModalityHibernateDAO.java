package br.com.tirocerto.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static br.com.tirocerto.util.hibernate.PaginateSortedCollumns.addSortedColumns;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.ModalityDAO;
import br.com.tirocerto.model.Modality;
import br.com.tirocerto.model.Modality.ModalityPointType;
import br.com.tirocerto.model.ModalityTargetDivision;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;
import br.com.tirocerto.util.datatable.PageResponse;

@Component
@RequestScoped
public class ModalityHibernateDAO implements ModalityDAO {
	private Session session;
	private ModalityTargetDivisionHibernateDAO modalityTargetDivisionHibernateDAO;

	public ModalityHibernateDAO(
			Session session,
			ModalityTargetDivisionHibernateDAO modalityTargetDivisionHibernateDAO) {
		this.session = session;
		this.modalityTargetDivisionHibernateDAO = modalityTargetDivisionHibernateDAO;
	}

	@Override
	public void save(Modality modality) {
		fixModalityTargetDivision(modality);
		session.save(modality);
	}

	@Override
	public void update(Modality modality) {
		fixModalityTargetDivision(modality);

		removeModalityTargetDivision(modality);

		session.merge(modality);
	}

	@Override
	public void delete(Modality modality) {
		fixModalityTargetDivision(modality);
		session.delete(modality);
	}

	@Override
	public Modality byId(Long id) {
		return (Modality) session.get(Modality.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Modality> paginate(PageRequest pageRequest) {
		String nome = pageRequest.getSearch() == null ? "" : pageRequest
				.getSearch();

		Criteria criteria = session.createCriteria(Modality.class).setReadOnly(
				true);

		criteria.add(Restrictions
				.ilike("description", nome, MatchMode.ANYWHERE));

		criteria.setFirstResult(pageRequest.getStart());
		criteria.setMaxResults(pageRequest.getSize());

		addSortedColumns(pageRequest, criteria);

		List<Modality> resultList = criteria.list();

		Page<Modality> page = new PageResponse<Modality>(resultList,
				pageRequest, getRowCount());

		return page;
	}

	private Long getRowCount() {
		Long count = (Long) session
				.createQuery("select count(*) from Modality").uniqueResult();
		return count == null ? 0 : count;
	}

	private void fixModalityTargetDivision(Modality modality) {
		if (modality == null || modality.getModalityTargetDivisions() == null) {
			return;
		}

		for (ModalityTargetDivision modalityTargetDivision : modality
				.getModalityTargetDivisions()) {
			modalityTargetDivision.setModality(modality);
		}
	}

	private void removeModalityTargetDivision(Modality modality) {
		Modality modalityOld = byId(modality.getId());

		List<ModalityTargetDivision> oldModalityTargetDivision = modalityOld
				.getModalityTargetDivisions();

		if (modalityOld == null || oldModalityTargetDivision == null) {
			return;
		}

		List<ModalityTargetDivision> removedModalityTargetDivisions = new ArrayList<>();

		removedModalityTargetDivisions.addAll(oldModalityTargetDivision);

		if (modality != null && modality.getModalityTargetDivisions() != null
				&& modality.getModalityPointType() == ModalityPointType.TARGET) {
			removedModalityTargetDivisions.removeAll(modality
					.getModalityTargetDivisions());
		}

		if (modality != null && modality.getModalityTargetDivisions() != null
				&& modality.getModalityPointType() != ModalityPointType.TARGET) {
			modality.getModalityTargetDivisions().clear();
		}

		for (ModalityTargetDivision removedModalityTargetDivision : removedModalityTargetDivisions) {
			session.delete(removedModalityTargetDivision);
		}
	}
}
