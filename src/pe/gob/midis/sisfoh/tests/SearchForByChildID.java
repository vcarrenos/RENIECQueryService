package pe.gob.midis.sisfoh.tests;

import java.io.IOException;

import pe.gob.midis.sisfoh.bean.ChildDNIResponse;
import pe.gob.midis.sisfoh.exception.RQSPropertyNotFoundException;
import pe.gob.midis.sisfoh.mock.GeneralEntitiesMock;
import pe.gob.midis.sisfoh.service.RENIECQueryService;
import pe.gob.midis.sisfoh.utils.PropertiesHelper;

public class SearchForByChildID {

	public static void main(String[] args) 
			throws IOException, RQSPropertyNotFoundException {
		
		String[] childDNINumber     = {"93849347", "70624786", "70640411", "70757530", "91177856", "91181012", "91182184", "91187286", "91180999"};
		String[] fatherDNINumber    = {"45454555", "10516206", "02644752", "15693711", "16597500", "16597501", "16597503", "16597504", "16597505"};
		String[] motherDNINumber    = {"41081874", "43320846", "02872892", "16597509", "80653355", "44020371", "42551225", "09444829", "05410604"};
		String[] declarantDNINumber = {"41081874", "43320846", "02644752", "16597509", "80653355", "44020371", "42551225", "09444829", "05410604"};
		
        RENIECQueryService adci = new RENIECQueryService();

        PropertiesHelper.load();
		GeneralEntitiesMock.load();
        
        for (int i = 0; i < childDNINumber.length; i++) {
        	
        	try {
        		ChildDNIResponse rr = adci.queryByChildId("08715701", childDNINumber[i], declarantDNINumber[i], 3, "12");

        		if ( rr != null )
        			System.out.println(rr.toString());
        		
        		rr =  null;
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
