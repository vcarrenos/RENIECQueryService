package pe.gob.midis.sisfoh.bean;

import java.util.ArrayList;
import java.util.List;

import pe.gob.midis.sisfoh.exception.RQSException;
import pe.gob.midis.sisfoh.exception.RQSInvalidErrorTypeException;
import pe.gob.midis.sisfoh.type.ErrorType;

public class NamesResponse {

	// Default names response values 
	static int __NAMES_RESPONSE_LENGTH = 16;
	
	// Names response fields length
	static int __BYTES_CODIGO_ERROR			=  4;
	static int __BYTES_TOTAL_COINCIDENCIAS	=  4;
	static int __BYTES_RESERVADO 			=  8;
	
	// Names response fields 
	private int    codigoError; 									// Código de error
	private int    totalCoincidencias;								// Número total de coincidencias. 
	private String reservado; 										// Reservado para uso futuro
	private List<NamesSubResponse>	listaPersonasEncontradas;		// Lista de personas		
	
	public NamesResponse() {
	}

	public NamesResponse(String response)
			throws RQSException, RQSInvalidErrorTypeException {
		listaPersonasEncontradas = new ArrayList<NamesSubResponse>();
		NamesResponse.valueOf(response, this);
	}

	public int getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(int codigoError) {
		this.codigoError = codigoError;
	}

	public int getTotalCoincidencias() {
		return totalCoincidencias;
	}

	public void setTotalCoincidencias(int totalCoincidencias) {
		this.totalCoincidencias = totalCoincidencias;
	}

	public String getReservado() {
		return reservado;
	}

	public void setReservado(String reservado) {
		this.reservado = reservado;
	}

	public List<NamesSubResponse> getListaPersonasEncontradas() {
		return listaPersonasEncontradas;
	}

	public void setListaPersonasEncontradas(List<NamesSubResponse> listaPersonasEncontradas) {
		this.listaPersonasEncontradas = listaPersonasEncontradas;
	}

	static public NamesResponse valueOf (String response, NamesResponse responseBody)
			throws RQSException, RQSInvalidErrorTypeException {
		
		int index = Header.__HEADER_LENGTH;
		
		responseBody.setCodigoError(Integer.parseInt(response.substring(index, index + NamesResponse.__BYTES_CODIGO_ERROR)));
		index += NamesResponse.__BYTES_CODIGO_ERROR;
		
		if ( ErrorType.fromValue(responseBody.getCodigoError()) != ErrorType.__OK_SUCCESSFUL)
			throw new RQSException(responseBody.getCodigoError()); 
		
		responseBody.setTotalCoincidencias(Integer.parseInt(response.substring(index, index + NamesResponse.__BYTES_TOTAL_COINCIDENCIAS)));
		index += NamesResponse.__BYTES_TOTAL_COINCIDENCIAS;
		
		responseBody.setReservado(response.substring(index, index + NamesResponse.__BYTES_RESERVADO));
		index += NamesResponse.__BYTES_RESERVADO;
		
		for (int i = 0; i < responseBody.getTotalCoincidencias()
				&& response.length() >= index + NamesSubResponse.__NAMES_SUB_RESPONSE_LENGTH; 
				i++) {
			NamesSubResponse nbsr = new NamesSubResponse(
					response.substring(index, index + NamesSubResponse.__NAMES_SUB_RESPONSE_LENGTH));
			
			responseBody.listaPersonasEncontradas.add(nbsr);
			
			index += NamesSubResponse.__NAMES_SUB_RESPONSE_LENGTH;
		}
		
		return responseBody;
	}

}
