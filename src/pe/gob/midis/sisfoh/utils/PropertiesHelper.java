package pe.gob.midis.sisfoh.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import pe.gob.midis.sisfoh.exception.RQSPropertyNotFoundException;
import pe.gob.midis.sisfoh.type.PropertiesType;

public class PropertiesHelper {

	private static PropertiesType properties;
	
	static public void load() 
			throws IOException, RQSPropertyNotFoundException {
		
		// Getting the properties values for MQ connection
		Properties props;
		
		props = PropertiesHelper.getProperties("midis-reniec.properties");
		properties = PropertiesHelper.validateProperties(props);
	};
	
	static public Properties getProperties(String fileName) throws IOException {
		InputStream inStream = PropertiesHelper.class.getClassLoader().getResourceAsStream(fileName);
		
		Properties props = new Properties();
		props.load(inStream);
		
		inStream.close();
		
		return props;
	}
	
	static public PropertiesType validateProperties(Properties props) 
				throws RQSPropertyNotFoundException {
		
		if(!props.containsKey("appl.web.institution.name"))
			throw new RQSPropertyNotFoundException("appl.web.institution.name");

		if(!props.containsKey("appl.web.institution.host"))
			throw new RQSPropertyNotFoundException("appl.web.institution.host");

		if(!props.containsKey("appl.web.matches.number"))
			throw new RQSPropertyNotFoundException("appl.web.matches.number");

		if(!props.containsKey("appl.ws.user"))
			throw new RQSPropertyNotFoundException("appl.ws.user'");

		if(!props.containsKey("appl.ws.password"))
			throw new RQSPropertyNotFoundException("appl.ws.password");

		
		if(!props.containsKey("appl.reniec.ws.entity.user"))
			throw new RQSPropertyNotFoundException("appl.reniec.ws.entity.user");

		if(!props.containsKey("appl.reniec.ws.user"))
			throw new RQSPropertyNotFoundException("appl.reniec.ws.user");

		if(!props.containsKey("appl.reniec.ws.password"))
			throw new RQSPropertyNotFoundException("appl.reniec.ws.password");

		if(!props.containsKey("appl.reniec.ws.trace.code"))
			throw new RQSPropertyNotFoundException("appl.reniec.ws.trace.code");

		PropertiesType properties = new PropertiesType(props); 
		
		return properties;
	}

	public static PropertiesType getProperties() {
		return properties;
	}
	
}
