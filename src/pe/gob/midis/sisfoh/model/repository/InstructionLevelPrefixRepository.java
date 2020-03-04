package pe.gob.midis.sisfoh.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.midis.sisfoh.model.InstructionLevelPrefix;

@Repository
public interface InstructionLevelPrefixRepository extends JpaRepository<InstructionLevelPrefix, String> {
    
	public List<InstructionLevelPrefix> findAll();
}
