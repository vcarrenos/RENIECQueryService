package pe.gob.midis.sisfoh.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.midis.sisfoh.model.AddressPrefix;


@Repository
public interface AddressPrefixRepository extends JpaRepository<AddressPrefix, String> {
	
	public List<AddressPrefix> findAll();
}
