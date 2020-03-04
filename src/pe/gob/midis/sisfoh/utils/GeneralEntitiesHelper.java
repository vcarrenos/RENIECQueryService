package pe.gob.midis.sisfoh.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.gob.midis.sisfoh.action.IndexAction;
import pe.gob.midis.sisfoh.exception.RQSParameterNotFoundException;
import pe.gob.midis.sisfoh.model.AddressPrefix;
import pe.gob.midis.sisfoh.model.AuditLog;
import pe.gob.midis.sisfoh.model.BlockOrChaletPrefix;
import pe.gob.midis.sisfoh.model.DptoFloorInternalPrefix;
import pe.gob.midis.sisfoh.model.InstructionLevelPrefix;
import pe.gob.midis.sisfoh.model.MaritalStatusPrefix;
import pe.gob.midis.sisfoh.model.Parameter;
import pe.gob.midis.sisfoh.model.RestrictionPrefix;
import pe.gob.midis.sisfoh.model.SexPrefix;
import pe.gob.midis.sisfoh.model.UrbCondomResidPrefix;
import pe.gob.midis.sisfoh.model.User;
import pe.gob.midis.sisfoh.model.repository.AddressPrefixRepository;
import pe.gob.midis.sisfoh.model.repository.AuditLogRepository;
import pe.gob.midis.sisfoh.model.repository.BlockOrChaletPrefixRepository;
import pe.gob.midis.sisfoh.model.repository.DptoFloorInternalPrefixRepository;
import pe.gob.midis.sisfoh.model.repository.InstructionLevelPrefixRepository;
import pe.gob.midis.sisfoh.model.repository.MaritalStatusPrefixRepository;
import pe.gob.midis.sisfoh.model.repository.ParameterRepository;
import pe.gob.midis.sisfoh.model.repository.RestrictionRepository;
import pe.gob.midis.sisfoh.model.repository.SexPrefixRepository;
import pe.gob.midis.sisfoh.model.repository.UrbCondomResidPrefixRepository;
import pe.gob.midis.sisfoh.model.repository.UserRepository;

public class GeneralEntitiesHelper {

	private static final Logger LOG = LoggerFactory.getLogger(IndexAction.class);
	
	static private Map<String, String> addressPrefixes;
	static private Map<String, String> blockOrChaletPrefixes;
	static private Map<String, String> dptoFloorInternalPrefixes;
	static private Map<String, String> instructionLevelPrefixes;
	static private Map<String, String> maritalStatusPrefixes;
	static private Map<String, Parameter> parameters;
	static private Map<String, String> restrictionPrefixes;
	static private Map<String, String> sexPrefixes;
	static private Map<String, String> urbCondomResidPrefixes;
	static private Map<String, User> users;
	static private Map<Integer, AuditLog> auditLog;

	static public void load() throws RQSParameterNotFoundException {

		GeneralEntitiesHelper.release();
		GeneralEntitiesHelper.loadAddressPrefixes();
		GeneralEntitiesHelper.loadBlockOrChaletPrefixes();
		GeneralEntitiesHelper.loadDptoFloorInternalPrefixes();
		GeneralEntitiesHelper.loadInstructionLevelPrefixes();
		GeneralEntitiesHelper.loadMaritalStatusPrefixes();
		GeneralEntitiesHelper.loadParameters();
		GeneralEntitiesHelper.loadRestrictions();
		GeneralEntitiesHelper.loadSexPrefixes();
		GeneralEntitiesHelper.loadUrbCondomResidPrefixes();
		GeneralEntitiesHelper.loadUsers();
	}
	
	static public void release() {

		if (GeneralEntitiesHelper.addressPrefixes == null
				|| GeneralEntitiesHelper.blockOrChaletPrefixes == null
				|| GeneralEntitiesHelper.dptoFloorInternalPrefixes == null
				|| GeneralEntitiesHelper.instructionLevelPrefixes == null
				|| GeneralEntitiesHelper.maritalStatusPrefixes == null
				|| GeneralEntitiesHelper.parameters == null
				|| GeneralEntitiesHelper.restrictionPrefixes == null
				|| GeneralEntitiesHelper.sexPrefixes == null
				|| GeneralEntitiesHelper.urbCondomResidPrefixes == null
				|| GeneralEntitiesHelper.users == null) 
			return;
			
		GeneralEntitiesHelper.addressPrefixes.clear();
		GeneralEntitiesHelper.addressPrefixes = null;

		GeneralEntitiesHelper.blockOrChaletPrefixes.clear();
		GeneralEntitiesHelper.blockOrChaletPrefixes = null;
		
		GeneralEntitiesHelper.dptoFloorInternalPrefixes.clear();
		GeneralEntitiesHelper.dptoFloorInternalPrefixes = null;
		
		GeneralEntitiesHelper.instructionLevelPrefixes.clear();
		GeneralEntitiesHelper.instructionLevelPrefixes = null;
		
		GeneralEntitiesHelper.maritalStatusPrefixes.clear();
		GeneralEntitiesHelper.maritalStatusPrefixes = null;

		GeneralEntitiesHelper.parameters.clear();
		GeneralEntitiesHelper.parameters = null;
		
		GeneralEntitiesHelper.restrictionPrefixes.clear();
		GeneralEntitiesHelper.restrictionPrefixes = null;

		GeneralEntitiesHelper.sexPrefixes.clear();
		GeneralEntitiesHelper.sexPrefixes = null;

		GeneralEntitiesHelper.urbCondomResidPrefixes.clear();
		GeneralEntitiesHelper.urbCondomResidPrefixes = null;

		GeneralEntitiesHelper.users.clear();
		GeneralEntitiesHelper.users = null;
	}

	static private void loadAddressPrefixes() {

		LOG.debug("Reading AddressPrefixes");

		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		AddressPrefixRepository repository = context.getBean(AddressPrefixRepository.class);
		GeneralEntitiesHelper.addressPrefixes = new HashMap<String, String>();

		for (AddressPrefix element: repository.findAll()) 
			GeneralEntitiesHelper.addressPrefixes.put(element.getCode(), element.getDescription());	
		
	}
	
	static private void loadBlockOrChaletPrefixes() {

		LOG.info("Reading BlockOrChaletPrefixes");

		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		BlockOrChaletPrefixRepository repository = context.getBean(BlockOrChaletPrefixRepository.class);
		GeneralEntitiesHelper.blockOrChaletPrefixes = new HashMap<String, String>();

		for (BlockOrChaletPrefix element: repository.findAll()) 
			GeneralEntitiesHelper.blockOrChaletPrefixes.put(element.getCode(), element.getDescription());
		
	}
	
	static private void loadDptoFloorInternalPrefixes() {

		LOG.info("Reading DptoFloorInternalPrefixes");

		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		DptoFloorInternalPrefixRepository repository = context.getBean(DptoFloorInternalPrefixRepository.class);
		GeneralEntitiesHelper.dptoFloorInternalPrefixes = new HashMap<String, String>();

		for (DptoFloorInternalPrefix element: repository.findAll()) 
			GeneralEntitiesHelper.dptoFloorInternalPrefixes.put(element.getCode(), element.getDescription());
		
	}

	static private void loadInstructionLevelPrefixes() {

		LOG.info("Reading InstructionLevelPrefixes");

		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		InstructionLevelPrefixRepository repository = context.getBean(InstructionLevelPrefixRepository.class);
		GeneralEntitiesHelper.instructionLevelPrefixes = new HashMap<String, String>();

		for (InstructionLevelPrefix element: repository.findAll()) 
			GeneralEntitiesHelper.instructionLevelPrefixes.put(element.getCode(), element.getDescription());
		
	}
	
	static private void loadMaritalStatusPrefixes() {

		LOG.info("Reading MaritalStatusPrefixes");

		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		MaritalStatusPrefixRepository repository = context.getBean(MaritalStatusPrefixRepository.class);
		GeneralEntitiesHelper.maritalStatusPrefixes = new HashMap<String, String>();

		for (MaritalStatusPrefix element: repository.findAll()) 
			GeneralEntitiesHelper.maritalStatusPrefixes.put(element.getCode(), element.getDescription());
		
	}
	

	static private void loadRestrictions() {

		LOG.info("Reading Restrictions");

		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		RestrictionRepository repository = context.getBean(RestrictionRepository.class);
		GeneralEntitiesHelper.restrictionPrefixes = new HashMap<String, String>();

		for (RestrictionPrefix element: repository.findAll()) 
			GeneralEntitiesHelper.restrictionPrefixes.put(element.getCode(), element.getDescription());
		
	}
	
	static private void loadSexPrefixes() {

		LOG.info("Reading SexPrefixes");

		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		SexPrefixRepository repository = context.getBean(SexPrefixRepository.class);
		GeneralEntitiesHelper.sexPrefixes = new HashMap<String, String>();

		for (SexPrefix element: repository.findAll()) 
			GeneralEntitiesHelper.sexPrefixes.put(element.getCode(), element.getDescription());
		
	}

	static private void loadUrbCondomResidPrefixes() {

		LOG.info("Reading UrbCondomResidPrefixes");

		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		UrbCondomResidPrefixRepository repository = context.getBean(UrbCondomResidPrefixRepository.class);
		GeneralEntitiesHelper.urbCondomResidPrefixes = new HashMap<String, String>();

		for (UrbCondomResidPrefix element: repository.findAll()) 
			GeneralEntitiesHelper.urbCondomResidPrefixes.put(element.getCode(), element.getDescription());
		
	}
	
	static public void loadParameters() throws RQSParameterNotFoundException {

		LOG.info("Reading Parameters");

		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		ParameterRepository repository = context.getBean(ParameterRepository.class);
		GeneralEntitiesHelper.parameters = new HashMap<String, Parameter>();
		
		for (Parameter param: repository.findAll()) 
			GeneralEntitiesHelper.parameters.put(param.getCode(), param);
		
		/*
		 *  Validate if exists essential parameters
		 */
		
		// MQ Producction parameters
		
		if (GeneralEntitiesHelper.parameters.get("APP_MQ_ENVIRONMENTQUERY_ACTIVE") == null)
			throw new RQSParameterNotFoundException("APP_MQ_ENVIRONMENTQUERY_ACTIVE");

		if (GeneralEntitiesHelper.parameters.get("APP_PROD_MQ_HOST") == null)
			throw new RQSParameterNotFoundException("APP_PROD_MQ_HOST");

		if (GeneralEntitiesHelper.parameters.get("APP_PROD_MQ_PORT_NUMBER") == null)
			throw new RQSParameterNotFoundException("APP_PROD_MQ_PORT_NUMBER");
			
		if (GeneralEntitiesHelper.parameters.get("APP_PROD_MQ_CHANNEL_NAME") == null)
			throw new RQSParameterNotFoundException("APP_PROD_MQ_CHANNEL_NAME");

		if (GeneralEntitiesHelper.parameters.get("APP_PROD_MQ_MANAGER_NAME") == null)
			throw new RQSParameterNotFoundException("APP_PROD_MQ_MANAGER_NAME");

		if (GeneralEntitiesHelper.parameters.get("APP_PROD_MQ_QUEUE_REQUEST") == null)
			throw new RQSParameterNotFoundException("APP_PROD_MQ_QUEUE_REQUEST");

		if (GeneralEntitiesHelper.parameters.get("APP_PROD_MQ_QUEUE_RESPONSE") == null)
			throw new RQSParameterNotFoundException("APP_PROD_MQ_QUEUE_RESPONSE");

		if (GeneralEntitiesHelper.parameters.get("APP_PROD_MQ_INSTITUTION_CODE") == null)
			throw new RQSParameterNotFoundException("APP_PROD_MQ_INSTITUTION_CODE");
			
		if (GeneralEntitiesHelper.parameters.get("APP_PROD_MQ_EXPIRATION_TIME") == null)
			throw new RQSParameterNotFoundException("APP_PROD_MQ_EXPIRATION_TIME");
		
		
		// MQ Testing parameters
		
		if (GeneralEntitiesHelper.parameters.get("APP_TEST_MQ_HOST") == null)
			throw new RQSParameterNotFoundException("APP_TEST_MQ_HOST");

		if (GeneralEntitiesHelper.parameters.get("APP_TEST_MQ_PORT_NUMBER") == null)
			throw new RQSParameterNotFoundException("APP_TEST_MQ_PORT_NUMBER");
			
		if (GeneralEntitiesHelper.parameters.get("APP_TEST_MQ_CHANNEL_NAME") == null)
			throw new RQSParameterNotFoundException("APP_TEST_MQ_CHANNEL_NAME");

		if (GeneralEntitiesHelper.parameters.get("APP_TEST_MQ_MANAGER_NAME") == null)
			throw new RQSParameterNotFoundException("APP_TEST_MQ_MANAGER_NAME");

		if (GeneralEntitiesHelper.parameters.get("APP_TEST_MQ_QUEUE_REQUEST") == null)
			throw new RQSParameterNotFoundException("APP_TEST_MQ_QUEUE_REQUEST");

		if (GeneralEntitiesHelper.parameters.get("APP_TEST_MQ_QUEUE_RESPONSE") == null)
			throw new RQSParameterNotFoundException("APP_TEST_MQ_QUEUE_RESPONSE");

		if (GeneralEntitiesHelper.parameters.get("APP_PROD_MQ_INSTITUTION_CODE") == null)
			throw new RQSParameterNotFoundException("APP_PROD_MQ_INSTITUTION_CODE");
			
		if (GeneralEntitiesHelper.parameters.get("APP_PROD_MQ_EXPIRATION_TIME") == null)
			throw new RQSParameterNotFoundException("APP_PROD_MQ_EXPIRATION_TIME");


		// PROXY parameters
		if (GeneralEntitiesHelper.parameters.get("APP_PROXY_SET") == null)
			throw new RQSParameterNotFoundException("APP_PROXY_SET");
		
		if (GeneralEntitiesHelper.parameters.get("APP_PROXY_SET").equals("1")) {

			if (GeneralEntitiesHelper.parameters.get("APP_PROXY_HOST") == null)
				throw new RQSParameterNotFoundException("APP_PROXY_HOST");
				
			if (GeneralEntitiesHelper.parameters.get("APP_PROXY_PORT") == null)
				throw new RQSParameterNotFoundException("APP_PROXY_PORT");
	
			if (GeneralEntitiesHelper.parameters.get("APP_PROXY_USER") == null)
				throw new RQSParameterNotFoundException("APPL_PROXY_USER");
	
			if (GeneralEntitiesHelper.parameters.get("APP_PROXY_PASSWORD") == null)
				throw new RQSParameterNotFoundException("APP_PROXY_PASSWORD");
		}
		
	}

	public static void saveParameter(Parameter parameter) throws Exception {

		LOG.info("Updating parameter [" + parameter.getCode() + "]");
		
		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		ParameterRepository repository = context.getBean(ParameterRepository.class);
				
		// if update cipher key ...
		if (parameter.getCode().equals("APP_COMPANY_KEY")) {

			Parameter currentParameter = repository.findOne(parameter.getCode()); 
			
			// ... update password for all users
			if ( !parameter.getValue().equals(currentParameter.getValue())) {
				UserRepository userRepository = context.getBean(UserRepository.class);
				
				for (User user: userRepository.findAll()) {
					
					String oldPassword = StringUtil.decrypt(user.getPassword(), currentParameter.getValue());
					String newPassword = StringUtil.encrypt(oldPassword, parameter.getValue());
					
					user.setPassword(newPassword);
					
					userRepository.saveAndFlush(user);
				}
				
				GeneralEntitiesHelper.loadParameters();
			}
		}
		
		repository.saveAndFlush(parameter);
	}
	
	public static void deleteParameter(String code) {

		LOG.info("Deleting parameter id[" + code + "]");

		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		ParameterRepository repository = context.getBean(ParameterRepository.class);
		
		repository.delete(code);
	}		
	
	static public void loadUsers() {

		LOG.info("Reading all users");

		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		UserRepository repository = context.getBean(UserRepository.class);
		GeneralEntitiesHelper.users = new HashMap<String, User>();

		for (User element: repository.findAllByOrderByApFirstAsc()) 
			GeneralEntitiesHelper.users.put(element.getUser(), element);
		
	}

	public static void saveUser(User user) throws Exception {

		LOG.info("Updating user [" + user.getDni() + "]");
		
		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		UserRepository repository = context.getBean(UserRepository.class);
		User userSearch = repository.getOne(user.getDni());
		
		if ( userSearch == null ) {
			
			user.setUser(user.getDni());

			// Encrypt the new password
			String CipherKey = GeneralEntitiesHelper.getParameters().get("APP_COMPANY_KEY").getValue();
			
			String newPassword = StringUtil.paddingRigth(user.getDni(), (int) Math.ceil(user.getDni().length() / 8.0) * 8);
			user.setPassword (StringUtil.encrypt(newPassword, CipherKey).trim());
			
		}
			
		repository.saveAndFlush(user);
	}
	
	static public void updatePasswordUsers(String currentKey, String newKey) {

		LOG.info("Updating all users password");

		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		UserRepository repository = context.getBean(UserRepository.class);

		for (User user: repository.findAll()) {
			
			try {

				String currentPassword = StringUtil.decrypt(user.getPassword(), currentKey);
				String newPassword =  StringUtil.paddingRigth(currentPassword, (int) Math.ceil(currentPassword.length() / 8.0) * 8);
				
				user.setPassword ( StringUtil.encrypt(newPassword, newKey) );
				
				saveUser(user);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		GeneralEntitiesHelper.loadUsers();
		
	}
	
	
	static public void loadAuditLog(String eventType,
			String userName, String beginDate, String endDate) {

		LOG.info("Reading Audit LOG");

		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		AuditLogRepository repository = context.getBean(AuditLogRepository.class);
		GeneralEntitiesHelper.auditLog = new HashMap<Integer, AuditLog>();
		List<AuditLog> log = null;
		
		// Buscar tipo de evento
		if (!StringUtil.isNullOrEmpty(eventType)
				&& StringUtil.isNullOrEmpty(userName)
				&& StringUtil.isNullOrEmpty(beginDate)
				&& StringUtil.isNullOrEmpty(endDate))
			log = repository.findByEvent(eventType);
		else
			// Buscar nombre de usuario
			if (StringUtil.isNullOrEmpty(eventType)
					&& !StringUtil.isNullOrEmpty(userName)
					&& StringUtil.isNullOrEmpty(beginDate)
					&& StringUtil.isNullOrEmpty(endDate))
				log = repository.findByUserName(userName);
			else
				// Buscar por tipo de evento y nombre de usuario
				if (!StringUtil.isNullOrEmpty(eventType)
						&& !StringUtil.isNullOrEmpty(userName)
						&& StringUtil.isNullOrEmpty(beginDate)
						&& StringUtil.isNullOrEmpty(endDate))
					log = repository.findByEventAndUserName(eventType, userName);
				else
					// Buscar por tipo de evento y fecha del evento
					if (!StringUtil.isNullOrEmpty(eventType)
							&& StringUtil.isNullOrEmpty(userName)
							&& !StringUtil.isNullOrEmpty(beginDate)
							&& !StringUtil.isNullOrEmpty(endDate))
						log = repository.findByEventAndEventDateBetween(eventType, beginDate, endDate);
					else
						// Buscar por nombre de usuario y fecha del evento
						if (StringUtil.isNullOrEmpty(eventType)
								&& !StringUtil.isNullOrEmpty(userName)
								&& !StringUtil.isNullOrEmpty(beginDate)
								&& !StringUtil.isNullOrEmpty(endDate))
							log = repository.findByUserNameAndEventDateBetween(userName, beginDate, endDate);
						else
							// Buscar con todos los criterios
							if (!StringUtil.isNullOrEmpty(eventType)
									&& !StringUtil.isNullOrEmpty(userName)
									&& !StringUtil.isNullOrEmpty(beginDate)
									&& !StringUtil.isNullOrEmpty(endDate))
								log = repository.findByEventAndUserNameAndEventDateBetween(eventType, userName, beginDate, endDate);
							else
								log = repository.findAll();
			
		for (AuditLog element: log) 
			GeneralEntitiesHelper.auditLog.put(element.getId(), element);
		
	}
	
	public static void deleteAllAuditLog() {

		LOG.info("Cleaning Audit LOG");

		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		AuditLogRepository repository = context.getBean(AuditLogRepository.class);
		
		repository.deleteAll();
	}	
	
	public static void saveAuditLog(AuditLog auditLog) {

		LOG.info(String.format("Updating Audit LOG [%-15s%s]", auditLog.getEvent(), auditLog.getDescription()));
		
		ClassPathXmlApplicationContext context = CPXAContextHelper.getContext(); 
		AuditLogRepository repository = context.getBean(AuditLogRepository.class);
		
		repository.saveAndFlush(auditLog);
	}

	public static Map<String, String> getAddressPrefixes() {
		return GeneralEntitiesHelper.addressPrefixes;
	}

	public static void setAddressPrefixes(Map<String, String> addressPrefixes) {
		GeneralEntitiesHelper.addressPrefixes = addressPrefixes;
	}

	public static Map<String, String> getBlockOrChaletPrefixes() {
		return GeneralEntitiesHelper.blockOrChaletPrefixes;
	}

	public static void setBlockOrChaletPrefixes(
			Map<String, String> blockOrChaletPrefixes) {
		GeneralEntitiesHelper.blockOrChaletPrefixes = blockOrChaletPrefixes;
	}

	public static Map<String, String> getDptoFloorInternalPrefixes() {
		return GeneralEntitiesHelper.dptoFloorInternalPrefixes;
	}

	public static void setDptoFloorInternalPrefixes(
			Map<String, String> dptoFloorInternalPrefixes) {
		GeneralEntitiesHelper.dptoFloorInternalPrefixes = dptoFloorInternalPrefixes;
	}

	public static Map<String, String> getInstructionLevelPrefixes() {
		return GeneralEntitiesHelper.instructionLevelPrefixes;
	}

	public static void setInstructionLevelPrefixes(
			Map<String, String> instructionLevelPrefixes) {
		GeneralEntitiesHelper.instructionLevelPrefixes = instructionLevelPrefixes;
	}

	public static Map<String, String> getMaritalStatusPrefixes() {
		return GeneralEntitiesHelper.maritalStatusPrefixes;
	}

	public static void setMaritalStatusPrefixes(
			Map<String, String> maritalStatusPrefixes) {
		GeneralEntitiesHelper.maritalStatusPrefixes = maritalStatusPrefixes;
	}

	public static Map<String, Parameter> getParameters() {
		return GeneralEntitiesHelper.parameters;
	}

	public static void setParameters(Map<String, Parameter> parameters) {
		GeneralEntitiesHelper.parameters = parameters;
	}

	public static Map<String, String> getRestrictionPrefixes() {
		return GeneralEntitiesHelper.restrictionPrefixes;
	}

	public static void setRestrictionPrefixes(
			Map<String, String> restrictionPrefixes) {
		GeneralEntitiesHelper.restrictionPrefixes = restrictionPrefixes;
	}

	public static Map<String, String> getSexPrefixes() {
		return GeneralEntitiesHelper.sexPrefixes;
	}

	public static void setSexPrefixes(Map<String, String> sexPrefixes) {
		GeneralEntitiesHelper.sexPrefixes = sexPrefixes;
	}

	public static Map<String, String> getUrbCondomResidPrefixes() {
		return GeneralEntitiesHelper.urbCondomResidPrefixes;
	}

	public static void setUrbCondomResidPrefixes(
			Map<String, String> urbCondomResidPrefixes) {
		GeneralEntitiesHelper.urbCondomResidPrefixes = urbCondomResidPrefixes;
	}

	public static Map<String, User> getUsers() {
		return GeneralEntitiesHelper.users;
	}

	public static void setUsers(Map<String, User> users) {
		GeneralEntitiesHelper.users = users;
	}

	public static Map<Integer, AuditLog> getAuditLog() {
		return GeneralEntitiesHelper.auditLog;
	}

	public static void setAuditLog(Map<Integer, AuditLog> auditLog) {
		GeneralEntitiesHelper.auditLog = auditLog;
	}
}

