//this code was found on https://github.com/dennysfredericci/vraptor-datatables

package br.com.tirocerto.util.datatable;

import static com.google.common.base.Objects.firstNonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Convert(PageRequest.class)
@RequestScoped
public class DatatableRequestConverter implements Converter<PageRequest> {
	private PageRequest pageRequest = new PageRequest();
	private final HttpServletRequest request;

	public DatatableRequestConverter(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public PageRequest convert(String value, Class<? extends PageRequest> type,
			ResourceBundle bundle) {

		convertDefault();
		convertSort();

		return pageRequest;
	}
	
	private void convertDefault() {
		String iDisplayStart = this.request.getParameter("iDisplayStart");
		String iDisplayLength = this.request.getParameter("iDisplayLength");

		Integer start = Integer.valueOf(firstNonNull(iDisplayStart, "0"));
		Integer size = Integer.valueOf(firstNonNull(iDisplayLength, "10"));
		String search = this.request.getParameter("sSearch");
		
		pageRequest.setStart(start);
		pageRequest.setSize(size);
		pageRequest.setSearch(search);
	}
	
	private void convertSort() {
		Map<String, String> sort = new HashMap<>();
		
		//find teh iSortCol entries
		for(Entry<String, String[]> entry : this.request.getParameterMap().entrySet()) {
			if(entry.getKey().startsWith("iSortCol_")) {
				String sortDirection = findSortDirection(entry.getKey());
				String column = findColumnName(entry.getValue()[0]);
				
				sort.put(column, sortDirection);
			}
		}
		
		pageRequest.setSort(sort);
	}
	
	private String findSortDirection(String sort) {
		String entryKey = sort.replace("iSortCol_", "sSortDir_");
		
		//find teh sSortDir_ entries
		for(Entry<String, String[]> entry : this.request.getParameterMap().entrySet()) {
			if(entry.getKey().startsWith(entryKey)) {
				return entry.getValue()[0];
			}
		}
		
		return null;
	}
	
	private String findColumnName(String column){
		String entryKey = "mDataProp_" + column.trim();
		
		//find teh mDataProp_ entries
		for(Entry<String, String[]> entry : this.request.getParameterMap().entrySet()) {
			if(entry.getKey().startsWith(entryKey)) {
				return entry.getValue()[0];
			}
		}
		
		return null;
	}
}