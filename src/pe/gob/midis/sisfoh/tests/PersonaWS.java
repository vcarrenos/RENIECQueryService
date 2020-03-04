package pe.gob.midis.sisfoh.tests;

import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.util.List;

import org.reniec.rel.SRELServiceService;

import pe.gob.midis.sisfoh.utils.ProxyAuthenticator;

public class PersonaWS {
    
  public static List<String> consultarPersona(String dni)
  {

    String dniAppMEF = "20045993";
    String Usu = "SISFOHWS";
    String Pass = "NDk2MDk5Nzg=";
    String AppMEF = "MIDIS";  
    
    PersonaWS.setProxy();

    SRELServiceService ws = new SRELServiceService();

	System.out.println(">>> Calling service");
	return ws.getSRELServicePort().getRegIdentConsolidada2(
			ws.getSRELServicePort().getSession(Usu, Pass), Usu, AppMEF, dniAppMEF, dni);
  }
  
  private static void setProxy()
  {
      Authenticator.setDefault(new ProxyAuthenticator("MIDIS\\mvargas", "mvargas41317561"));
      System.setProperty("http.proxyHost", "proxy.midis.gob.pe"); 
      System.setProperty("http.proxyPort", "8080"); 
  }

	public static void main(String[] args) throws UnsupportedEncodingException 
	{
//		System.out.println(">>> Consultando DNI [" + args[0] +"]");
		List<String> lst = consultarPersona("32728789"); 
		System.out.println(">>> Datos Obtenidos:");
		for (String string : lst) {
			System.out.println(string);
		}
	}       
}
