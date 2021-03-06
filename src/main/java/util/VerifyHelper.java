/**
 *   Copyright 2018 Pascal Ammon, Gabriel Wyss
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
 * This class contains the verify helper method.
 * It allows anybody to verify a signature on a message.
 * The verify method takes a public key, signature and a message.
 * It returns true if the signature is valid.
 * It returns false if the signature is invalid.
 */

package util;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import demo.DemoPublicKey;
import keys.PublicKey;
import settings.DefaultSettings;
import settings.Settings;
import signatures.Signature;

public class VerifyHelper {

	public static boolean verify(PublicKey publicKey, Signature signature, byte[] message) {
		return verify(new DefaultSettings(), publicKey, signature, message);
	}

	public static boolean verify(Settings settings, PublicKey publicKey, Signature signature, byte[] message) {

		if (signature.getZe().bitLength() != (settings.getle() + settings.getlc() + settings.getle())
				&& signature.getZx().bitLength() != (settings.getlQ() + settings.getlc() + settings.getle())) {

			System.out.println("def false");
			;
			return false;
		}
		boolean isValid = false;
		BigInteger vPart1 = publicKey.getA().multiply(publicKey.getW()).modPow(signature.getC().negate(),
				publicKey.getN());
		BigInteger vPart2 = publicKey.getG().modPow(signature.getZx().negate(), publicKey.getN());
		BigInteger vPart3 = publicKey.getH().modPow(signature.getZr(), publicKey.getN());
		BigInteger vPart4 = signature.getC().multiply(new BigInteger("2").pow(settings.getlE() - 1))
				.add(signature.getZe());
		BigInteger vPart5 = signature.getU().modPow(vPart4, publicKey.getN());

		BigInteger v = vPart1.multiply(vPart2).mod(publicKey.getN()).multiply(vPart3).mod(publicKey.getN())
				.multiply(vPart5).mod(publicKey.getN());

		BigInteger bigV1 = signature.getBigU1().modPow(signature.getC().negate(), publicKey.getBigP())
				.multiply(publicKey.getBigF().modPow(signature.getZbigR(), publicKey.getBigP()))
				.mod(publicKey.getBigP());
		BigInteger bigV2 = signature.getBigU2().modPow(signature.getC().negate(), publicKey.getBigP())
				.multiply(publicKey.getBigG().modPow(signature.getZbigR().add(signature.getZx()), publicKey.getBigP()))
				.mod(publicKey.getBigP());
		BigInteger bigV3 = signature.getBigU3().modPow(signature.getC().negate(), publicKey.getBigP())
				.multiply(publicKey.getBigH().modPow(signature.getZbigR().add(signature.getZe()), publicKey.getBigP()))
				.mod(publicKey.getBigP());

		ArrayList<byte[]> input = new ArrayList<byte[]>();
		input.add(HashHelper.getHash(publicKey));
		input.add(signature.getU().toByteArray());
		input.add(v.toByteArray());
		input.add(signature.getBigU1().toByteArray());
		input.add(signature.getBigU2().toByteArray());
		input.add(signature.getBigU3().toByteArray());
		input.add(bigV1.toByteArray());
		input.add(bigV2.toByteArray());
		input.add(bigV3.toByteArray());
		input.add(message);
		BigInteger c = Math.GetHash(input);
		isValid = c.equals(signature.getC());

		return isValid;
	}
}
