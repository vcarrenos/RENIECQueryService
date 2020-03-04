package pe.gob.midis.sisfoh.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.midis.sisfoh.model.UrbCondomResidPrefix;

@Repository
public interface UrbCondomResidPrefixRepository extends JpaRepository<UrbCondomResidPrefix, String> {
    
	public List<UrbCondomResidPrefix> findAll();
}
