package pe.gob.midis.sisfoh.tests;

import java.io.IOException;

import pe.gob.midis.sisfoh.bean.NamesResponse;
import pe.gob.midis.sisfoh.exception.RQSPropertyNotFoundException;
import pe.gob.midis.sisfoh.mock.GeneralEntitiesMock;
import pe.gob.midis.sisfoh.service.RENIECQueryService;
import pe.gob.midis.sisfoh.utils.PropertiesHelper;

public class SearchForByNames {

	public static void main(String[] args) 
			throws IOException, RQSPropertyNotFoundException  {
		
        RENIECQueryService adci = new RENIECQueryService();
 
		PropertiesHelper.load();
		GeneralEntitiesMock.load();
        
        try {
    		NamesResponse rr = adci.queryByNames("41945677", "ZAVALETA", "IBAÑEZ", "");
//    		NamesResponse rr = adci.queryByNames("41945677", "ALARCON", "GUTIERREZ", "");
    		
    		if ( rr != null )
    			System.out.println(rr.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
