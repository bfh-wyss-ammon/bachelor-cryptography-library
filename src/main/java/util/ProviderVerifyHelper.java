package util;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class ProviderVerifyHelper {
	
	public static boolean verify(byte[] message, byte[] signature, String base64publickey) {
		try {
			byte[] publicKeyEncoded = Base64.getDecoder().decode(base64publickey);
			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(publicKeyEncoded);
			KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
			PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
			Signature sig = Signature.getInstance("SHA256withDSA", "SUN");
			sig.initVerify(pubKey);
			sig.update(message);
			return sig.verify(signature);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
