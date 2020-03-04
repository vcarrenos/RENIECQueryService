package pe.gob.midis.sisfoh.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.midis.sisfoh.model.RestrictionPrefix;

@Repository
public interface RestrictionRepository extends JpaRepository<RestrictionPrefix, String> {
    
	public List<RestrictionPrefix> findAll();
}
