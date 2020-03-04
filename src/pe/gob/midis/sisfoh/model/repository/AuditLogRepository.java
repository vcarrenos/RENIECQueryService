package pe.gob.midis.sisfoh.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.midis.sisfoh.model.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
	
	public List<AuditLog> findAll();
	
	public List<AuditLog> findDistinctByEvent(String event);
	
	public List<AuditLog> findByEvent(String event);
	
	public List<AuditLog> findByUserName(String userName);
	
	public List<AuditLog> findByEventDateBetween(String beginDate, String endDate);

	public List<AuditLog> findByEventAndUserName(String event, String userName);

	public List<AuditLog> findByEventAndEventDateBetween(String userName,
			String beginDate, String endDate);
	
	public List<AuditLog> findByUserNameAndEventDateBetween(
			String userName, String beginDate, String endDate);

	public List<AuditLog> findByEventAndUserNameAndEventDateBetween(
			String event, String userName, String beginDate, String endDate);
	
	@Override
	<S extends AuditLog> S saveAndFlush(S auditLog);
	
}

