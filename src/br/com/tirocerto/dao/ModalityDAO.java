package br.com.tirocerto.dao;

import java.util.List;

import br.com.tirocerto.model.Modality;
import br.com.tirocerto.util.datatable.Page;
import br.com.tirocerto.util.datatable.PageRequest;

public interface ModalityDAO {
	void save(Modality modality);
	void update(Modality modality);
	void updateDescription(Modality modality);
	void delete(Modality modality);
	List<Modality> listAll();
	Modality byId(Long id);
	Page<Modality> paginate(PageRequest pageRequest);
}
