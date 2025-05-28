package Archivos;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtil {
	public static KeyPair generarParDeClaves() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(2048); // tamaño de la clave 
			return keyGen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String cifrarConClavePublica(String texto, PublicKey clavePublica) {
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, clavePublica);
			
			byte[] byteCifrado = cipher.doFinal(texto.getBytes("UTF-8"));
			
			return Base64.getEncoder().encodeToString(byteCifrado);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return texto;
	}
	
	public static String descifrarConClavePrivada(String textoCifrado, PrivateKey clavePrivada) {
			Cipher cipher;
			try {
				cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
				cipher.init(Cipher.DECRYPT_MODE, clavePrivada);
				byte[] byteDecifrados = cipher.doFinal(Base64.getDecoder().decode(textoCifrado));
				return new String(byteDecifrados, "UTF-8");
			} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		return null;
	}
}
