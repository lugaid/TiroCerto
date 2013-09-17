package br.com.tirocerto.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.AssociateDAO;
import br.com.tirocerto.model.Associate;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;
import br.com.tirocerto.util.datatable.PageResponse;
import static br.com.tirocerto.util.hibernate.PaginateCollumns.addSortedColumns;
import static br.com.tirocerto.util.hibernate.PaginateCollumns.addSearchColumns;

@Component
@RequestScoped
public class AssociateHibernateDAO implements AssociateDAO {
	private Session session;

	public AssociateHibernateDAO(Session session) {
		this.session = session;
	}

	@Override
	public void save(Associate associate) {
		session.save(associate);
	}

	@Override
	public void update(Associate associate) {
		session.update(associate);
	}

	@Override
	public void delete(Associate associate) {
		session.delete(associate);
	}

	@Override
	public Associate byId(Long id) {
		return (Associate) session.get(Associate.class, id);
	}

	@Override
	public boolean existsEmail(Associate associate) {
		Criteria criteria = session.createCriteria(Associate.class);
		
		if(associate != null && associate.getId() != null){
			criteria.add(Restrictions.ne("id", associate.getId()));
		}
		
		criteria.add(Restrictions.eq("email", associate.getEmail()));
		
		List<?> list = criteria.list();

		return list != null && list.size() > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Associate> paginate(PageRequest pageRequest) {
		Criteria criteria = session.createCriteria(Associate.class).setReadOnly(true);

		criteria.setFirstResult(pageRequest.getStart());
		criteria.setMaxResults(pageRequest.getSize());

		addSortedColumns(pageRequest, criteria);
		addSearchColumns(pageRequest, criteria);
		
		List<Associate> resultList = criteria.list();
		
		fillBlankPasswords(resultList);
		
		Page<Associate> page = new PageResponse<Associate>(resultList,
				pageRequest, getRowCount(), getRowCountRestriction(pageRequest));

		return page;
	}
	
	@Override
	public Associate findByEmailAndPassword(Associate associate) {
		Criteria criteria = session.createCriteria(Associate.class);
		
		criteria.add(Restrictions.eq("email", associate.getEmail()));
		criteria.add(Restrictions.eq("password", associate.getPassword()));
		criteria.add(Restrictions.eq("adminAccess", true));
		
		return (Associate)criteria.uniqueResult();
	}
	
	private Long getRowCount() {
		Criteria criteria = session.createCriteria(Associate.class); 
		criteria.setProjection(Projections.rowCount());
		return ((Long)criteria.list().get(0));
	}
	
	private Long getRowCountRestriction(PageRequest pageRequest) {
		Criteria criteria = session.createCriteria(Associate.class); 
		criteria.setProjection(Projections.rowCount());
		addSearchColumns(pageRequest, criteria);
		return ((Long)criteria.list().get(0));
	}
	
	private void fillBlankPasswords(List<Associate> resultList) {
		if(resultList != null) {
			for(Associate associate : resultList) {
				associate.setPassword(null);
			}
		}
	}
}
