package pe.gob.midis.sisfoh.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.midis.sisfoh.model.SexPrefix;

@Repository
public interface SexPrefixRepository extends JpaRepository<SexPrefix, String> {
    
	public List<SexPrefix> findAll();
}
