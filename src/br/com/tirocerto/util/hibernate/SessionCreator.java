package br.com.tirocerto.util.hibernate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class SessionCreator implements ComponentFactory<Session> {
	private final SessionFactoryCreator sessionFactoryCreator;
	private Session session;
	
	public SessionCreator(SessionFactoryCreator sessionFactoryCreator) {
		this.sessionFactoryCreator = sessionFactoryCreator;
	}
	
	@PostConstruct
	public void create() {
		session = sessionFactoryCreator.getInstance().openSession();
	}
	
	public Session getInstance() {
		return session;
	}
	
	@PreDestroy
	public void destroy() {
		session.close();
	}
}
