package pe.gob.midis.sisfoh.mock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.midis.sisfoh.action.IndexAction;
import pe.gob.midis.sisfoh.model.Parameter;
import pe.gob.midis.sisfoh.model.User;
import pe.gob.midis.sisfoh.utils.GeneralEntitiesHelper;

public class GeneralEntitiesMock {

	private static final Logger LOG = LoggerFactory.getLogger(IndexAction.class);
	
	static Connection connection = null;
	static ResultSet resultSet = null;
	static Statement statement = null;

	static public void load() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite::resource:database/generals.db");

			GeneralEntitiesHelper.setAddressPrefixes (GeneralEntitiesMock.loadAddressPrefixes());
			GeneralEntitiesHelper.setBlockOrChaletPrefixes (GeneralEntitiesMock.loadBlockOrChaletPrefixes());
			GeneralEntitiesHelper.setDptoFloorInternalPrefixes (GeneralEntitiesMock.loadDptoFloorInternalPrefixes());
			GeneralEntitiesHelper.setInstructionLevelPrefixes (GeneralEntitiesMock.loadInstructionLevelPrefixes());
			GeneralEntitiesHelper.setMaritalStatusPrefixes (GeneralEntitiesMock.loadMaritalStatusPrefixes());
			GeneralEntitiesHelper.setParameters (GeneralEntitiesMock.loadParameters());
			GeneralEntitiesHelper.setRestrictionPrefixes (GeneralEntitiesMock.loadRestrictions());
			GeneralEntitiesHelper.setSexPrefixes (GeneralEntitiesMock.loadSexPrefixes());
			GeneralEntitiesHelper.setUrbCondomResidPrefixes ( GeneralEntitiesMock.loadUrbCondomResidPrefixes());
			GeneralEntitiesHelper.setUsers (GeneralEntitiesMock.loadUsers());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	static public void release() {

		GeneralEntitiesHelper.release();
	}
	
	
	static private Map<String, String> loadAddressPrefixes() throws SQLException {

		Map<String, String> map = new HashMap<String, String>();

		LOG.debug("Reading AddressPrefixes");

		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from grl_address_prefix");
		while (resultSet.next()) {
			map.put(resultSet.getString("code"), resultSet.getString("description"));
		}
		resultSet.close();
		statement.close();
		
		return map;
	}
	
	static private Map<String, String> loadBlockOrChaletPrefixes() throws SQLException {

		Map<String, String> map = new HashMap<String, String>();

		LOG.info("Reading BlockOrChaletPrefixes");

		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from grl_block_or_chalet_prefix");
		while (resultSet.next()) {
			map.put(resultSet.getString("code"), resultSet.getString("description"));
		}
		resultSet.close();
		statement.close();
		
		return map;
	}

	static private Map<String, String> loadDptoFloorInternalPrefixes() throws SQLException {

		Map<String, String> map = new HashMap<String, String>();

		LOG.info("Reading DptoFloorInternalPrefixes");

		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from grl_dpto_floor_internal_prefix");
		while (resultSet.next()) {
			map.put(resultSet.getString("code"), resultSet.getString("description"));
		}
		resultSet.close();
		statement.close();
		
		return map;
	}

	static private Map<String, String> loadInstructionLevelPrefixes() throws SQLException {

		Map<String, String> map = new HashMap<String, String>();

		LOG.info("Reading InstructionLevelPrefixes");

		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from grl_instruction_level_prefix");
		while (resultSet.next()) {
			map.put(resultSet.getString("code"), resultSet.getString("description"));
		}
		resultSet.close();
		statement.close();
		
		return map;
	}
	
	static private Map<String, String> loadMaritalStatusPrefixes() throws SQLException {

		Map<String, String> map = new HashMap<String, String>();

		LOG.info("Reading MaritalStatusPrefixes");

		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from grl_marital_status_prefix");
		while (resultSet.next()) {
			map.put(resultSet.getString("code"), resultSet.getString("description"));
		}
		resultSet.close();
		statement.close();
		
		return map;
	}	
	
	static private Map<String, String> loadRestrictions() throws SQLException {

		Map<String, String> map = new HashMap<String, String>();

		LOG.info("Reading Restrictions");

		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from grl_restriction_prefix");
		while (resultSet.next()) {
			map.put(resultSet.getString("code"), resultSet.getString("description"));
		}
		resultSet.close();
		statement.close();
		
		return map;
	}
	
	static private Map<String, String> loadSexPrefixes() throws SQLException {

		Map<String, String> map = new HashMap<String, String>();

		LOG.info("Reading SexPrefixes");

		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from grl_sex_prefix");
		while (resultSet.next()) {
			map.put(resultSet.getString("code"), resultSet.getString("description"));
		}
		resultSet.close();
		statement.close();
		
		return map;
	}

	static private Map<String, String> loadUrbCondomResidPrefixes() throws SQLException {

		Map<String, String> map = new HashMap<String, String>();

		LOG.info("Reading UrbCondomResidPrefixes");

		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from grl_urb_condom_resid_prefix");
		while (resultSet.next()) {
			map.put(resultSet.getString("code"), resultSet.getString("description"));
		}
		resultSet.close();
		statement.close();
		
		return map;
	}	
	
	static private Map<String, Parameter> loadParameters() throws SQLException {

		Map<String, Parameter> map = new HashMap<String, Parameter>();

		LOG.info("Reading Parameters");
		
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from grl_parameter");
		while (resultSet.next()) {
			Parameter param = new Parameter(resultSet.getString("code"),
					resultSet.getString("description"),
					resultSet.getString("value"),
					(resultSet.getInt("enabled") == 1),
					(resultSet.getInt("visible") == 1),
					(resultSet.getInt("removable") == 1));
			
			map.put(param.getCode(), param);
			
		}
		resultSet.close();
		statement.close();

		return map;
	}
	
	static private Map<String, User> loadUsers() throws Exception {

		Map<String, User> map = new HashMap<String, User>();
		LOG.info("Reading loadUsers");


		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from grl_user");
		while (resultSet.next()) {
			User param = new User(resultSet.getString("dni"),
					resultSet.getString("ap_first"),
					resultSet.getString("ap_last"),
					resultSet.getString("names"), 
					resultSet.getInt("max_quota"),
					resultSet.getString("expiration_date"),
					resultSet.getString("birth_date"),
					(resultSet.getInt("enabled") == 1));
			param.setPassword(resultSet.getString("password"));
			
			map.put(param.getUser(), param);
			
		}
		resultSet.close();
		statement.close();
		
		return map;
	}
	


}
