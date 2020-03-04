package pe.gob.midis.sisfoh.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.midis.sisfoh.model.Parameter;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, String> {
    
	public List<Parameter> findAll();
	
	Long deleteByCode(String id);
	
	@Override
	public Parameter findOne(String id);
	
	@Override
	<S extends Parameter> S saveAndFlush(S parameter);
	
	@Override
	public <S extends Parameter> List<S> save(Iterable<S> parameters);
}

