package pe.gob.midis.sisfoh.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class StringUtil {
	
	public static String getDecimalFormat(double mount) {
		Locale locale  = new Locale("pe", "PE");
		String pattern = "###,##0.00";
		
		DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(locale);
		decimalFormat.applyPattern(pattern);
		
		return decimalFormat.format(mount);
	}
	
	public static String getMessageId() {
		String idTxn = "" + System.currentTimeMillis();
		
		return String.format("$M{%s} ", idTxn);
	}
	
	/**
	 * Adiciona l espacios al lado derecho en una cadena de caracteres
	 */
	public static String paddingRigth(String s, int l)
	{
		String p = new String();

		if (s.length() > l)
			p = s.substring(0, l);
		else if (s.length() < l)
		{
			int i = l - s.length();
			int j = 0;

			for (j = 0; j < i; j++)
				p = p + " ";

			p = s + p;
		}
		else
			p = s;

		return p;
	}	
	
	public static boolean isNullOrEmpty(String s) {
		return (s == null || s.trim().length() == 0);
	}
	
	public static String encrypt(String message, String secretKey)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
					InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		SecretKey key = new SecretKeySpec(secretKey.getBytes(), "TripleDES");
		Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] plainTextBytes = message.getBytes();
		byte[] buf = cipher.doFinal(plainTextBytes);
	    
	    return new String(Hex.encodeHex(buf)).toUpperCase();
	}
 
	public static String decrypt(String encryptedText, String secretKey)
		throws NoSuchAlgorithmException, NoSuchPaddingException,
				InvalidKeyException, IllegalBlockSizeException, BadPaddingException, DecoderException {
	
		byte[] message = Hex.decodeHex(encryptedText.toCharArray());
		SecretKey key = new SecretKeySpec(secretKey.getBytes(), "TripleDES");
		
		Cipher decipher = Cipher.getInstance("DESede/ECB/NoPadding");
		decipher.init(Cipher.DECRYPT_MODE, key);
		byte[] plainText = decipher.doFinal(message);
		
		return new String(plainText);
	}	
}


