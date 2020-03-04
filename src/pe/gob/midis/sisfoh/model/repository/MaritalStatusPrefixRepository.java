package pe.gob.midis.sisfoh.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.midis.sisfoh.model.MaritalStatusPrefix;

@Repository
public interface MaritalStatusPrefixRepository extends JpaRepository<MaritalStatusPrefix, String> {
    
	public List<MaritalStatusPrefix> findAll();
}
