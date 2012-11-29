package br.com.tirocerto.dao.impl;

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
		session.update(modality);
	}

	@Override
	public void delete(Modality modality) {
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

		Criteria criteria = session.createCriteria(Modality.class).setReadOnly(true);

		criteria.add(Restrictions.ilike("description", nome, MatchMode.ANYWHERE));

		criteria.setFirstResult(pageRequest.getStart());
		criteria.setMaxResults(pageRequest.getSize());

		addSortedColumns(pageRequest, criteria);

		List<Modality> resultList = criteria.list();
		
		Page<Modality> page = new PageResponse<Modality>(resultList,
				pageRequest, getRowCount());

		return page;
	}
	
	private Long getRowCount() {
		Long count = (Long)session.createQuery("select count(*) from Modality").uniqueResult();
		return count == null ? 0 : count;
	}
}
