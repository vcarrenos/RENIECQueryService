package pe.gob.midis.sisfoh.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.midis.sisfoh.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
	@Override
	<S extends User> S saveAndFlush(S user);

	public List<User> findAllByOrderByApFirstAsc();
}
