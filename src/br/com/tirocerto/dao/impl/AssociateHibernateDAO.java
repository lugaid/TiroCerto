package br.com.tirocerto.dao.impl;

import org.hibernate.Session;

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
		session.beginTransaction();
		session.save(associate);
		session.getTransaction().commit();
	}
	
	@Override
	public void update(Associate associate) {
		session.beginTransaction();
		session.update(associate);
		session.getTransaction().commit();
	}
	
	@Override
	public void delete(Associate associate) {
		session.beginTransaction();
		session.delete(associate);
		session.getTransaction().commit();
	}
	
	@Override
	public Associate byId(Long id) {
		return (Associate) session.get(Associate.class, id);
	}
}
