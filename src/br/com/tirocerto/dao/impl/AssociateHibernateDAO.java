package br.com.tirocerto.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.AssociateDAO;
import br.com.tirocerto.model.Associate;

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
}
