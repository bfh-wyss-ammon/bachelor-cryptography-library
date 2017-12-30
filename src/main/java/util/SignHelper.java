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
 * This class contains the helper methods needed to sign a message.
 * The sign method takes a public key, a message, a member secret key and an empty siganture.
 * 
 */
package util;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

import keys.SecretKey;
import keys.PublicKey;
import settings.DefaultSettings;
import settings.Settings;
import signatures.Signature;

public class SignHelper {

	public static void sign(SecretKey memberKey, PublicKey publicKey, byte[] message, Signature emptySignature) {
		sign(new DefaultSettings(), memberKey, publicKey, message, emptySignature);
	}

	public static void sign(Settings settings, SecretKey memberKey, PublicKey publicKey, byte[] message,
			Signature emptySignature) {
		SecureRandom random = new SecureRandom();
		// all the variables we need
		BigInteger r = Math.randVal(random, settings.getModulus() / 2);
		BigInteger bigR = Math.randValModP(random, settings.getlQ(), publicKey.getBigQ());
		BigInteger u = publicKey.getH().modPow(r, publicKey.getN()).multiply(memberKey.getY()).mod(publicKey.getN())
				.multiply(memberKey.getW()).mod(publicKey.getN());

		BigInteger bigU1 = publicKey.getBigF().modPow(bigR, publicKey.getBigP());
		BigInteger bigU2 = publicKey.getBigG().modPow(bigR.add(memberKey.getX()), publicKey.getBigP());
		BigInteger bigU3 = publicKey.getBigH().modPow(bigR.add(memberKey.getE()), publicKey.getBigP());

		BigInteger rx = Math.randVal(random, settings.getlQ() + settings.getlc() + settings.getle());
		BigInteger rr = Math.randVal(random, settings.getModulus() / 2 + settings.getlc() + settings.getle());
		BigInteger re = Math.randVal(random, settings.getle() + settings.getlc() + settings.getle());
		BigInteger bigRr = Math.randValModP(random, settings.getlQ(), publicKey.getBigQ());

		BigInteger v = u.modPow(re, publicKey.getN()).multiply(publicKey.getG().modPow(rx.negate(), publicKey.getN()))
				.multiply(publicKey.getH().modPow(rr, publicKey.getN())).mod(publicKey.getN());
		BigInteger bigV1 = publicKey.getBigF().modPow(bigRr, publicKey.getBigP());
		BigInteger bigV2 = publicKey.getBigG().modPow(bigRr.add(rx), publicKey.getBigP());
		BigInteger bigV3 = publicKey.getBigH().modPow(bigRr.add(re), publicKey.getBigP());

		// generate hashing challenge
		ArrayList<byte[]> input = new ArrayList<byte[]>();
		input.add(HashHelper.getHash(publicKey));
		input.add(u.toByteArray());
		input.add(v.toByteArray());
		input.add(bigU1.toByteArray());
		input.add(bigU2.toByteArray());
		input.add(bigU3.toByteArray());
		input.add(bigV1.toByteArray());
		input.add(bigV2.toByteArray());
		input.add(bigV3.toByteArray());
		input.add(message);

		BigInteger c = Math.GetHash(input);
		BigInteger zx = rx.add(c.multiply(memberKey.getX()));

		BigInteger res = memberKey.getR().negate().subtract(r.multiply(memberKey.getBigE()));
		BigInteger zr = rr.add(c.multiply(res));

		res = c.multiply(memberKey.getE());
		BigInteger ze = re.add(res);

		BigInteger zbigR = bigRr.add(c.multiply(bigR).mod(publicKey.getBigQ()));

		// return the new signature
		emptySignature.setU(u);
		emptySignature.setBigU1(bigU1);
		emptySignature.setBigU2(bigU2);
		emptySignature.setBigU3(bigU3);
		emptySignature.setZx(zx);
		emptySignature.setZr(zr);
		emptySignature.setZe(ze);
		emptySignature.setZbigR(zbigR);
		emptySignature.setC(c);

	}
}
