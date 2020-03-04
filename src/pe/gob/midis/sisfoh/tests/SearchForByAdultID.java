package pe.gob.midis.sisfoh.tests;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.midis.sisfoh.action.IndexAction;
import pe.gob.midis.sisfoh.bean.AdultDNIResponse;
import pe.gob.midis.sisfoh.exception.RQSPropertyNotFoundException;
import pe.gob.midis.sisfoh.mock.GeneralEntitiesMock;
import pe.gob.midis.sisfoh.service.RENIECQueryService;
import pe.gob.midis.sisfoh.utils.PropertiesHelper;

public class SearchForByAdultID {

	private static final Logger LOG = LoggerFactory.getLogger(IndexAction.class);

	public static void main(String[] args) 
			throws IOException, RQSPropertyNotFoundException {
		
		RENIECQueryService adci = new RENIECQueryService();

		GeneralEntitiesMock.load();
		PropertiesHelper.load();

		// Datos para pruebas en Producción 
//		String user = "10808968";
//		String[] dni = { 
//				"10808968",
//				"42077317",
//				"42716939",
//				"40538336",
//				"09803918",
//				"41466664"
//		};

//		// Datos para pruebas en Desarrollo 
		String user = "08715701";
		String[] dni = { 
				"10526062",
				"08845622",
				"43942930",
				"43937252",
				"40547390",
				"33404155",
				"43873223",
				"40040990",
				"01889246",
				"43865838",
				"40225629",
				"42424933",
				"80364512"
		};
		
		
		for (int i = 0; i < dni.length; i++) {
			try {

				AdultDNIResponse rr = adci.queryByAdultId(user, dni[i], "1");

				if (rr != null)	{
					LOG.debug((i + 1) + ": DNI" + rr.toString());
					System.out.println(String.format(">>> #%05d: DNI Encontrado...\n%s", i + 1, rr.toString()));
				}

			} catch (Exception e) {

				System.out.println(String.format(">>> #%05d: DNI [%s] NO Encontrado...\n", i + 1, dni[i]));
				e.printStackTrace();
			}
		}
	}
}
