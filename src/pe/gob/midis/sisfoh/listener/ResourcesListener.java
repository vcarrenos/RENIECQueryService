package pe.gob.midis.sisfoh.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.midis.sisfoh.exception.RQSParameterNotFoundException;
import pe.gob.midis.sisfoh.exception.RQSPropertyNotFoundException;
import pe.gob.midis.sisfoh.utils.CPXAContextHelper;
import pe.gob.midis.sisfoh.utils.ErrorRENIECHelper;
import pe.gob.midis.sisfoh.utils.GeneralEntitiesHelper;
import pe.gob.midis.sisfoh.utils.PropertiesHelper;
import pe.gob.midis.sisfoh.utils.SystemConfigHelper;

public class ResourcesListener implements ServletContextListener{
	private static final Logger LOG = LoggerFactory.getLogger(ResourcesListener.class);
	
	public void contextDestroyed(ServletContextEvent arg0) {
		liberarRecursos();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		try {
			iniciarRecursos();
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static void iniciarRecursos() 
			throws Exception {
        LOG.info("starting server...");

        try {
            cargarDatosConfiguracionProceso();

        } catch (Exception e) {
            LOG.error( "Error starting resources", e);
            
            throw e;
        }
    }

    private static void cargarDatosConfiguracionProceso() 
    		throws IOException, RQSParameterNotFoundException, RQSPropertyNotFoundException {

        LOG.info("Loading server config...");

        CPXAContextHelper.create();
        GeneralEntitiesHelper.load();
        PropertiesHelper.load();
        ErrorRENIECHelper.create();
        SystemConfigHelper.create();
    }
    
    public static void liberarRecursos() {
        LOG.info("Stopping Server");

        try {

        	CPXAContextHelper.release();
        	GeneralEntitiesHelper.release();
        	ErrorRENIECHelper.release();
        } catch (Exception e) {
            LOG.error("Failed to release resources", e);
        }
    }

}
