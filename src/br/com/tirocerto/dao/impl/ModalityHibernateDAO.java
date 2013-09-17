package br.com.tirocerto.dao.impl;

import java.util.List;

import static br.com.tirocerto.util.hibernate.PaginateCollumns.addSearchColumns;
import static br.com.tirocerto.util.hibernate.PaginateCollumns.addSortedColumns;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.ModalityDAO;
import br.com.tirocerto.model.Modality;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;
import br.com.tirocerto.util.datatable.PageResponse;

@Component
@RequestScoped
public class ModalityHibernateDAO implements ModalityDAO {
	private Session session;

	public ModalityHibernateDAO(Session session) {
		this.session = session;
	}

	@Override
	public void save(Modality modality) {
		session.save(modality);
	}

	@Override
	public void update(Modality modality) {
		session.merge(modality);
	}

	@Override
	public void updateDescription(Modality modality) {
		Query query = session
				.createQuery("update Modality set description = :description where id = :id");
		query.setLong("id", modality.getId());
		query.setString("description", modality.getDescription());
		query.executeUpdate();
	}

	@Override
	public void delete(Modality modality) {
		session.delete(modality);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Modality> listAll() {
		return session.createCriteria(Modality.class).setReadOnly(true).list();
	}

	@Override
	public Modality byId(Long id) {
		return (Modality) session.get(Modality.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Modality> paginate(PageRequest pageRequest) {
		Criteria criteria = session.createCriteria(Modality.class).setReadOnly(
				true);

		criteria.setFirstResult(pageRequest.getStart());
		criteria.setMaxResults(pageRequest.getSize());

		addSortedColumns(pageRequest, criteria);
		addSearchColumns(pageRequest, criteria);

		List<Modality> resultList = criteria.list();

		Page<Modality> page = new PageResponse<Modality>(resultList,
				pageRequest, getRowCount(), getRowCountRestriction(pageRequest));

		return page;
	}

	private Long getRowCount() {
		Criteria criteria = session.createCriteria(Modality.class);
		criteria.setProjection(Projections.rowCount());
		return ((Long) criteria.list().get(0));
	}

	private Long getRowCountRestriction(PageRequest pageRequest) {
		Criteria criteria = session.createCriteria(Modality.class);
		criteria.setProjection(Projections.rowCount());
		addSearchColumns(pageRequest, criteria);
		return ((Long) criteria.list().get(0));
	}
}
