package br.com.tirocerto.dao.impl;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.AssociateDAO;
import br.com.tirocerto.factory.SessionCreator;
import br.com.tirocerto.model.Associate;

@Component
@RequestScoped
public class AssociateHibernateDAO implements AssociateDAO {
	private SessionCreator sessionCreator;
	private Session session;
	
	public AssociateHibernateDAO(SessionCreator sessionCreator) {
		this.sessionCreator = sessionCreator;
		this.session = sessionCreator.getInstance();
	}

	@Override
	public void save(Associate associate) {
		session.save(associate);
	}
}
