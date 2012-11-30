package br.com.tirocerto.dao.impl;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.tirocerto.dao.ModalityTargetDivisionDAO;
import br.com.tirocerto.model.ModalityTargetDivision;

@Component
@RequestScoped
public class ModalityTargetDivisionHibernateDAO implements ModalityTargetDivisionDAO {
	private Session session;

	public ModalityTargetDivisionHibernateDAO(Session session) {
		this.session = session;
	}

	@Override
	public void save(ModalityTargetDivision modalityTargetDivision) {
		session.save(modalityTargetDivision);
	}

	@Override
	public void update(ModalityTargetDivision modalityTargetDivision) {
		session.update(modalityTargetDivision);
	}

	@Override
	public void delete(ModalityTargetDivision modalityTargetDivision) {
		session.delete(modalityTargetDivision);
	}

	@Override
	public ModalityTargetDivision byId(Long id) {
		return (ModalityTargetDivision) session.get(ModalityTargetDivision.class, id);
	}
}
