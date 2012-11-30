package br.com.tirocerto.dao;

import br.com.tirocerto.model.ModalityTargetDivision;

public interface ModalityTargetDivisionDAO {
	void save(ModalityTargetDivision modalityTargetDivision);
	void update(ModalityTargetDivision modalityTargetDivision);
	void delete(ModalityTargetDivision modalityTargetDivision);
	ModalityTargetDivision byId(Long id);
}
