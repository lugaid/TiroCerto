package br.com.tirocerto.util.hibernate;

import java.io.Serializable;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueValidator implements ConstraintValidator<Unique, Serializable> {

	private Session session;
	private Class<?> entityClass;
	private String uniqueField;
	
	public void initialize(Unique unique) {
		entityClass = unique.entity();
		uniqueField = unique.property();
	}

	public boolean isValid(Serializable property, ConstraintValidatorContext cvContext) {
		List<?> list = session.createCriteria(entityClass).add(Restrictions.eq(uniqueField, property)).list();
		
		return list != null && list.size() > 0;
	}
	
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }
}