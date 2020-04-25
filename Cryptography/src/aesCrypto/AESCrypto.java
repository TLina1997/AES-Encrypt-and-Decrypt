package aesCrypto;

import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class AESCrypto {
	
	private static final String Algo ="AES";
	private byte[] keyValue;
	
	
	public AESCrypto (String key) {
		
		keyValue = key.getBytes();
	}
	
	private Key generateKey() throws Exception{
		
		Key key = new SecretKeySpec(keyValue, Algo);
		
		return key;
	}
	
	public String encrypt(String Data) throws Exception{
		
		Key key = generateKey();
		Cipher c = Cipher.getInstance(Algo);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encVal);
		
		return encryptedValue;
			
	}
	
	public String decrypt(String encryptedData) throws Exception{
		
		Key key = generateKey();
		Cipher c = Cipher.getInstance(Algo);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String (decValue);
		
		return decryptedValue;
			
	}
	
	
	public static void main (String args[]) {
		
		try {
			
			AESCrypto aes = new AESCrypto("QueZxsweqqER39zZ");
			String encData = aes.encrypt("TLina");

			System.out.println("Encrypted Data: " + encData);
			
			String decData = aes.decrypt(encData);
			
			System.out.println("Decrypted Data: " + decData);
		}
		
		catch (Exception ex) {
			// TODO: handle exception
			
			Logger.getLogger(AESCrypto.class.getName()).log(Level.SEVERE,null,ex);
			
		}
	}
	
}
