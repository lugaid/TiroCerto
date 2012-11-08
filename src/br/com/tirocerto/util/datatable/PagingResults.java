package br.com.tirocerto.util.datatable;

import static br.com.caelum.vraptor.view.Results.representation;
import static br.com.caelum.vraptor.view.Results.json;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.http.FormatResolver;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.serialization.HTMLSerialization;

@Component
public class PagingResults implements PagingResult {

	private Result result;
	private FormatResolver formatResolver;
	private HTMLSerialization html;

	public PagingResults(Result result, FormatResolver formatResolver,
			HTMLSerialization html) {
		this.result = result;
		this.formatResolver = formatResolver;
		this.html = html;
	}

	@Override
	public void from(Page<?> page) {

		if (html.accepts(formatResolver.getAcceptFormat())) {
			html.from(page);
		} else {
			DataTable dataTable = new DataTable(page);
			
			if(formatResolver.getAcceptFormat().equals("json")) {
				this.result.use(json()).withoutRoot().from(dataTable).include("aaData")
				.serialize();
			} else {
				this.result.use(representation()).from(dataTable).include("aaData")
					.serialize();
			}
		}

	}

	public static Class<PagingResults> dataTablesPaging() {
		return PagingResults.class;
	}

}
