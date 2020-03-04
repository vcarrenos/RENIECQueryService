package pe.gob.midis.sisfoh.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.midis.sisfoh.model.DptoFloorInternalPrefix;

@Repository
public interface DptoFloorInternalPrefixRepository extends JpaRepository<DptoFloorInternalPrefix, String> {
    
	public List<DptoFloorInternalPrefix> findAll();
}
