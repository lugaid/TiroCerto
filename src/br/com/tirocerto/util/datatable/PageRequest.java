//this code was found on https://github.com/dennysfredericci/vraptor-datatables

package br.com.tirocerto.util.datatable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PageRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_PAGE_SIZE = 5;

	private int size;
	private int start;
	private String search;
	private Map<String, String> sort;
	private List<String> searchOn;

	public PageRequest() {
		this.start = 0;
		this.size = DEFAULT_PAGE_SIZE;
	}

	public int getSize() {

		return size;
	}

	public int getPageNumber() {

		int page = 0;

		if (start >= this.getSize()) {
			page = start / this.getSize();
		}

		return page;
	}

	public int getOffset() {

		return getPageNumber() * size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getStart() {
		return this.start;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Map<String, String> getSort() {
		return sort;
	}

	public void setSort(Map<String, String> sort) {
		this.sort = sort;
	}

	public List<String> getSearchOn() {
		return searchOn;
	}

	public void setSearchOn(List<String> searchOn) {
		this.searchOn = searchOn;
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PageRequest)) {
			return false;
		}

		PageRequest that = (PageRequest) obj;

		boolean pageEqual = this.getPageNumber() == that.getPageNumber();
		boolean sizeEqual = this.getSize() == that.getSize();

		return pageEqual && sizeEqual;
	}

	@Override
	public int hashCode() {

		int result = 17;

		result = 31 * result + getPageNumber();
		result = 31 * result + getSize();

		return result;
	}
}
