package pe.gob.midis.sisfoh.service;

import java.util.List;

import pe.gob.midis.sisfoh.bean.AdultDNIResponse;
import pe.gob.midis.sisfoh.bean.ChildDNIResponse;
import pe.gob.midis.sisfoh.bean.NamesResponse;
import pe.gob.midis.sisfoh.exception.RQSException;

public interface IRENIECQueryService {

	public AdultDNIResponse queryByAdultId(String usuarioInstitucionApli,
			String numeroDNI, String tipoSubConsulta)
			throws Exception;
	
	public NamesResponse queryByNames(String usuarioInstitucionApli,
			String apellidoPaterno, String apellidoMaterno, String nombres)
			throws Exception;

	public ChildDNIResponse queryByChildId(String usuarioInstitucionApli,
			String numeroDNI, String numeroDNIApoderado, int tipoVinculo,
			String tipoSubConsulta)
			throws Exception;
	
	public List<String> consultarPersona(String dni, String usuario, String password)
				throws RQSException;
	
}
