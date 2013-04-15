package br.com.tirocerto.util.hibernate;

import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import br.com.tirocerto.util.datatable.PageRequest;

public class PaginateSortedCollumns {
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
}
