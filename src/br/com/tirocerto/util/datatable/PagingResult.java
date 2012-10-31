package br.com.tirocerto.util.datatable;

import br.com.caelum.vraptor.View;

public interface PagingResult extends View {

public void from(Page<?> page);

}