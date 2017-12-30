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
