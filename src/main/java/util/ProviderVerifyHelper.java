/**
 *  Copyright 2018 Pascal Ammon, Gabriel Wyss
 * 
 * 	 Implementation eines anonymen Mobility Pricing Systems auf Basis eines Gruppensignaturschemas
 * 
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

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
