package br.com.tirocerto.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.AssociateDAO;
import br.com.tirocerto.model.Associate;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;
import br.com.tirocerto.util.datatable.PageResponse;

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
		List<?> list = session.createCriteria(Associate.class).add(Restrictions.eq("email", associate.getEmail())).list();
		
		return list != null && list.size() > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Associate> paginate(PageRequest pageRequest) {
        String nome = pageRequest.getSearch() == null ? "" : pageRequest.getSearch();

        Criteria criteria = session.createCriteria(Associate.class);

        criteria.add(Restrictions.ilike("name", nome, MatchMode.ANYWHERE));
        
        criteria.setFirstResult(pageRequest.getStart());
        criteria.setMaxResults(pageRequest.getSize());

        
		List<Associate> resultList = criteria.list();
        Page<Associate> page = new PageResponse<Associate>(resultList, pageRequest, getRowCount());

        return page;
	}
	
	private Long getRowCount() {
		return (long)session.createCriteria(Associate.class).list().size();
	}
}
