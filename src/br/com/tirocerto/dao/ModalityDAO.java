package br.com.tirocerto.dao;

import br.com.tirocerto.model.Modality;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

public interface ModalityDAO {
	void save(Modality associate);
	void update(Modality associate);
	void delete(Modality associate);
	Modality byId(Long id);
	Page<Modality> paginate(PageRequest pageRequest);
}
