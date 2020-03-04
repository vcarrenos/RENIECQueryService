package pe.gob.midis.sisfoh.utils;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CPXAContextHelper {
	
	private static ClassPathXmlApplicationContext context = null;
	
	public static void create() throws IOException {
		
		CPXAContextHelper.context = new ClassPathXmlApplicationContext("application-context.xml");
		
		if ( context == null ) {
			
		}
	}
	
	public static void release() {
		
		if ( context != null ) {
			
			CPXAContextHelper.context.close();
		}
		
	}
	
	public static ClassPathXmlApplicationContext getContext()
	{
		return CPXAContextHelper.context;
	}

}
