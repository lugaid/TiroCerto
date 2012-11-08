package br.com.tirocerto.dao.impl;

import java.util.List;
<<<<<<< HEAD

import org.hibernate.Session;
=======
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
>>>>>>> dd1f53e657aa583b099ddeb5cd701e426f4a33db
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
<<<<<<< HEAD
	
	@Override
	public boolean existsEmail(Associate associate) {
		List<?> list = session.createCriteria(Associate.class).add(Restrictions.eq("email", associate.getEmail())).list();
		
		return list != null && list.size() > 0;
	}
=======

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
		String nome = pageRequest.getSearch() == null ? "" : pageRequest
				.getSearch();

		Criteria criteria = session.createCriteria(Associate.class).setReadOnly(true);

		criteria.add(Restrictions.ilike("name", nome, MatchMode.ANYWHERE));

		criteria.setFirstResult(pageRequest.getStart());
		criteria.setMaxResults(pageRequest.getSize());

		addSortedColumns(pageRequest, criteria);

		List<Associate> resultList = criteria.list();
		
		fillBlankPasswords(resultList);
		
		Page<Associate> page = new PageResponse<Associate>(resultList,
				pageRequest, getRowCount());

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
		Long count = (Long)session.createQuery("select count(*) from Associate").uniqueResult();
		return count == null ? 0 : count;
	}

	private void addSortedColumns(PageRequest pageRequest, Criteria criteria) {
		for (Entry<String, String> sort : pageRequest.getSort().entrySet()) {
			switch (sort.getValue()) {
			case "desc":
				criteria.addOrder(Order.desc(sort.getKey()));
				break;
			case "asc":
				criteria.addOrder(Order.asc(sort.getKey()));
				break;
			default:
				throw new IllegalArgumentException("expected asc or des found " + sort.getValue());
			}
		}
	}
	
	private void fillBlankPasswords(List<Associate> resultList) {
		if(resultList != null) {
			for(Associate associate : resultList) {
				associate.setPassword(null);
			}
		}
	}
>>>>>>> dd1f53e657aa583b099ddeb5cd701e426f4a33db
}
