package br.com.tirocerto.util.hibernate;


import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.tirocerto.util.datatable.PageRequest;

public class PaginateCollumns {
	public static void addSortedColumns(PageRequest pageRequest, Criteria criteria) {
		for (Entry<String, String> sort : pageRequest.getSort().entrySet()) {
			switch (sort.getValue()) {
			case "desc":
				criteria.addOrder(Order.desc(sort.getKey().replace("_", ".")));
				break;
			case "asc":
				criteria.addOrder(Order.asc(sort.getKey().replace("_", ".")));
				break;
			default:
				throw new IllegalArgumentException("expected asc or des found " + sort.getValue());
			}
		}
	}
	
	public static void addSearchColumns(PageRequest pageRequest, Criteria criteria) {
		if(pageRequest.getSearchOn() == null) {
			return;
		}
		
		Criterion criterion = null;
				
		for (String search : pageRequest.getSearchOn()) {
			String value = pageRequest.getSearch() == null ? "" : pageRequest.getSearch();
			Criterion criterionLocal = Restrictions.ilike(search.replace("_", "."), value, MatchMode.ANYWHERE);

			if(criterion == null) {
				criterion = Restrictions.or(criterionLocal);
			} else {
				criterion = Restrictions.or(criterionLocal, criterion);
			}
		}

		if(criterion != null) {
			criteria.add(criterion);
		}
	}
}
