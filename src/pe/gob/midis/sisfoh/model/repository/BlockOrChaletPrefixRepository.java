package pe.gob.midis.sisfoh.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.midis.sisfoh.model.BlockOrChaletPrefix;

@Repository
public interface BlockOrChaletPrefixRepository extends JpaRepository<BlockOrChaletPrefix, String> {
    
	public List<BlockOrChaletPrefix> findAll();
}
