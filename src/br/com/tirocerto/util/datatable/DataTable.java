//this code was found on https://github.com/dennysfredericci/vraptor-datatables

package br.com.tirocerto.util.datatable;

import java.util.Date;

public class DataTable {

	private String sEcho;
	private long iTotalRecords;
	private long iTotalDisplayRecords;

	private Object[] aaData;

	public DataTable(Page<?> page) {

		this.sEcho = String.valueOf(new Date().getTime());
		this.iTotalDisplayRecords = page.getTotalElements();
		this.iTotalRecords = page.getTotalElements();
		this.aaData = page.getContent().toArray();

	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public Object[] getAaData() {
		return aaData;
	}

	public void setAaData(Object[] aaData) {
		this.aaData = aaData;
	}
}