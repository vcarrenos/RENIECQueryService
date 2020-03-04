package pe.gob.midis.sisfoh.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import pe.gob.midis.sisfoh.exception.RQSException;
import pe.gob.midis.sisfoh.exception.RQSInvalidErrorTypeException;
import pe.gob.midis.sisfoh.utils.PropertiesHelper;

public class ChildDNIResponse extends BaseDNIResponse {

	private static final Logger LOG = Logger.getLogger(ChildDNIResponse.class);

	// Default child DNI response values 
	static int __CHILD_DNI_RESPONSE_LENGTH   = AdultDNIResponse.__ADULT_DNI_RESPONSE_LENGTH;
	
	// Response child DNI fields length
	final static int __BYTES_RESERVADO_2	= 27;
	final static int __BYTES_RESERVADO_3	= 14;
	
	// Response child DNI fields 
	private String reservado_2;				// Reservado
	private String reservado_3;				// Reservado
	
	public ChildDNIResponse() {

		super();
	}

	public ChildDNIResponse(String response, String subqueryType) 
			throws RQSException, RQSInvalidErrorTypeException, UnsupportedEncodingException {

		super();
		valueOf(response, subqueryType);
	}

	/**
	 * @return the reservado_2
	 */
	@SuppressWarnings("unused")
	private String getReservado_2() {
		return reservado_2;
	}

	/**
	 * @param reservado_2 the reservado_2 to set
	 */
	private void setReservado_2(String reservado_2) {
		this.reservado_2 = reservado_2;
	}

	/**
	 * @return the reservado_3
	 */
	@SuppressWarnings("unused")
	private String getReservado_3() {
		return reservado_3;
	}

	/**
	 * @param reservado_3 the reservado_3 to set
	 */
	private void setReservado_3(String reservado_3) {
		this.reservado_3 = reservado_3;
	}
		
	public ChildDNIResponse valueOf (String response, String subqueryType) 
			throws RQSException, RQSInvalidErrorTypeException, UnsupportedEncodingException {
		int index = Header.__HEADER_LENGTH;
		
		index = BaseDNIResponse.valueOf(response, this, subqueryType);
		
		// if the photo is present ...
		if ( subqueryType.contains("2") )
		{
			this.setLongitudFoto(Integer.parseInt(response.substring(index, index + BaseDNIResponse.__BYTES_LONGITUD_FOTO).trim()));
			index += BaseDNIResponse.__BYTES_LONGITUD_FOTO;
			
			this.setReservado_2(response.substring(index, index + __BYTES_RESERVADO_2));
			index += __BYTES_RESERVADO_2;
			
			this.setReservado_3(response.substring(index, index + __BYTES_RESERVADO_3));
			index += __BYTES_RESERVADO_3;
			
			// Convert message text to binary
			byte[] binaryResponse = response.getBytes("ISO-8859-1");
			
			if ( ActionContext.getContext() == null )
				return this;
			
			if (this.getLongitudFoto() > 0)
			{
				
				try {
					ServletContext ctx = ServletActionContext.getServletContext();
					
					String filePath = String.format(
							"resources/download/%s/%s/%s", this
							.getNumeroDNI().substring(0, 3), this
							.getNumeroDNI().substring(3, 6), this
							.getNumeroDNI());

					String fileRealPath = ctx.getRealPath(filePath);
					new File(fileRealPath).mkdirs();
					
					filePath = filePath + '/' + this.getNumeroDNI() + ".foto.base64"; 
					
					String fileRealFileName = ctx.getRealPath(filePath);

					if ( !PropertiesHelper.getProperties().isApplMock() )
					{
						// Assign memory to save the photography
						byte[] fotografiaJPG = new byte[this.getLongitudFoto()];
						
						// Copy the photography
						System.arraycopy(binaryResponse, index, fotografiaJPG, 0, this.getLongitudFoto());
						index += this.getLongitudFoto();
						
						String fotografiaBase64 = new String(Base64.encodeBase64(fotografiaJPG));
						setFotografiaBase64(fotografiaBase64);
						setLongitudFoto(fotografiaBase64.length());
						
						FileOutputStream stream = new FileOutputStream(fileRealFileName);

						stream.write(fotografiaBase64.getBytes());
					    stream.close();
					}
					else
					{
						FileInputStream stream = new FileInputStream(fileRealFileName);
						// Assign memory to save the photography
						byte[] fotografiaBase64 = new byte[stream.available()];

						setLongitudFoto(stream.available());

						stream.read(fotografiaBase64);
						setFotografiaBase64(new String(fotografiaBase64));
					    stream.close();
					}
					
				} catch (Exception e) {

					e.printStackTrace();
				}
				
			}
			else
				LOG.info(String.format("El DNI '%s' no cuenta con fotogtafia", this.getNumeroDNI()));
		}
		
		return this;
	}

}
