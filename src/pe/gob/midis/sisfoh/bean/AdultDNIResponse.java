package pe.gob.midis.sisfoh.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import pe.gob.midis.sisfoh.exception.RQSException;
import pe.gob.midis.sisfoh.exception.RQSInvalidErrorTypeException;
import pe.gob.midis.sisfoh.utils.PropertiesHelper;

import com.opensymphony.xwork2.ActionContext;

public class AdultDNIResponse extends BaseDNIResponse {

	private static final Logger LOG = Logger.getLogger(AdultDNIResponse.class);
	
	// Default DNI response values 
	static int __ADULT_DNI_RESPONSE_LENGTH   = 991;

	// Response DNI body fields length
	final static int __BYTES_LONGITUD_FIRMA				=  9;
	final static int __BYTES_RESERVADO_2				= 18;
	final static int __BYTES_RESERVADO_3				= 14;
	

	// Response DNI fields 
	private int    longitudFirma;			// Longitud de la firma
	private String firmaBase64;				// Firma en formato Base64
	private String reservado_2;				// Reservado
	private String reservado_3;				// Reservado
	
	public AdultDNIResponse() {
		
		super();
	}

	public AdultDNIResponse(String response, String subqueryType) 
			throws RQSException, RQSInvalidErrorTypeException, UnsupportedEncodingException {
		
		super();
		valueOf(response, subqueryType);
	}
	
	/**
	 * @return the longitudFirma
	 */
	public int getLongitudFirma() {
		return longitudFirma;
	}

	/**
	 * @param longitudFirma the longitudFirma to set
	 */
	public void setLongitudFirma(int longitudFirma) {
		this.longitudFirma = longitudFirma;
	}
	
	public String getFirmaBase64() {
		return firmaBase64;
	}

	public void setFirmaBase64(String firmaBase64) {
		this.firmaBase64 = firmaBase64;
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
	
	public AdultDNIResponse valueOf (String response, String subqueryType) 
			throws RQSException, RQSInvalidErrorTypeException, UnsupportedEncodingException {
		
		int index = Header.__HEADER_LENGTH;
		
		index = BaseDNIResponse.valueOf(response, this, subqueryType);
		
		if ( subqueryType.contains("2") || subqueryType.contains("3") )
		{
			this.setLongitudFoto(Integer.parseInt(response.substring(index, index + BaseDNIResponse.__BYTES_LONGITUD_FOTO).trim()));
			index += BaseDNIResponse.__BYTES_LONGITUD_FOTO;
			
			this.setLongitudFirma(Integer.parseInt(response.substring(index, index + __BYTES_LONGITUD_FIRMA)));
			index += __BYTES_LONGITUD_FIRMA;
			
			this.setReservado_2(response.substring(index, index + __BYTES_RESERVADO_2));
			index += __BYTES_RESERVADO_2;
			
			this.setReservado_3(response.substring(index, index + __BYTES_RESERVADO_3));
			index += __BYTES_RESERVADO_3;
			
			// Convert message text to binary
			byte[] binaryResponse = response.getBytes("ISO-8859-1");
			
			if ( ActionContext.getContext() == null )
				return this;
			
			// if the photo is present ...
			if (subqueryType.contains("2") )
			{
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
			else {
				setFotografiaBase64("");
				setLongitudFoto(0);
			}
			
			// if the signature is present ...
			if (subqueryType.contains("3"))
			{
				if (this.getLongitudFirma() > 0)
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

						filePath = filePath + '/' + this.getNumeroDNI() + ".firma.base64"; 
						
						String fileRealFileName = ctx.getRealPath(filePath);
						
						if ( !PropertiesHelper.getProperties().isApplMock() )
						{
							// Assign memory to save the signature
							byte[] firmaJPG  = new byte[this.getLongitudFirma()];
							
							// Copy the signature
							System.arraycopy(binaryResponse, index, firmaJPG, 0, this.getLongitudFirma());
							index += this.getLongitudFirma();
							
							String firmaBase64 = new String(Base64.encodeBase64(firmaJPG));
							setFirmaBase64(firmaBase64);
							setLongitudFirma(firmaBase64.length());
							
							FileOutputStream stream = new FileOutputStream(fileRealFileName);
		
							stream.write(firmaBase64.getBytes());
						    stream.close();
						}
						else
						{
							FileInputStream stream = new FileInputStream(fileRealFileName);
							
							// Assign memory to save the photography
							byte[] firmaBase64 = new byte[stream.available()];

							setLongitudFirma(stream.available());

							stream.read(firmaBase64);
							setFirmaBase64(new String(firmaBase64));
						    stream.close();
						}
						
					} catch (Exception e) {
	
						e.printStackTrace();
					}
					
				}			
				else
					LOG.info(String.format("El DNI '%s' no cuenta con firma", this.getNumeroDNI()));
			}
			else {
				setFirmaBase64("");
				setLongitudFirma(0);
			}
		}
		
		return this;
	}
	
	public String toString() {
		String result = super.toString();
		
		result += 	"longitudFirma            : [" + getLongitudFirma()   + "]\n";
 	
		return result;
	}
	
	public String toStringLine() {
		String formatSpec = "%s[%s]";
		String result;

		result = String.format(formatSpec, super.toStringLine(), getFirmaBase64());

		return result;
	}
}