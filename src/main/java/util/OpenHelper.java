/**
 * This class contains the method the group manager can use to open a signature.
 * For this, the group manager needs a public key, a signature, a message
 * and a manager key.
 */
package util;

import java.math.BigInteger;

import keys.ManagerKey;
import keys.PublicKey;
import signatures.Signature;

public class OpenHelper {

	public static int open(PublicKey publicKey, ManagerKey managerKey, byte[] message, Signature signature,
			BigInteger[] YList) {
		if (!VerifyHelper.verify(publicKey, signature, message))
			return -1;
		BigInteger bigU1 = signature.getBigU1().modPow(managerKey.getXg(), publicKey.getBigP());

		int i = 0;
		for (BigInteger bigY : YList) {
			if (bigU1.multiply(bigY).mod(publicKey.getBigP()).equals(signature.getBigU2())) {
				return i;
			}
			i++;
		}
		return -1;
	}
}
