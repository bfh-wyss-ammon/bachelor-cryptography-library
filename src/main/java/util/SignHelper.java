package util;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;


import keys.SecretKey;
import keys.PublicKey;
import settings.DefaultSettings;
import settings.Settings;
import signatures.Signature;

public class SignHelper {
	
	public static Signature sign(SecretKey memberKey, PublicKey publicKey, byte[] message) {
		return sign(new DefaultSettings(), memberKey, publicKey, message);
	}
	
	public static Signature sign(Settings settings, SecretKey memberKey, PublicKey publicKey, byte[] message) {
		SecureRandom random = new SecureRandom();
		// all the variables we need
		BigInteger r = Math.randVal(random, settings.getModulus() / 2);
		BigInteger bigR = Math.randValModP(random, settings.getlQ(), publicKey.getBigQ());
		BigInteger u = publicKey.getH().modPow(r, publicKey.getN()).multiply(memberKey.getY()).mod(publicKey.getN()).multiply(memberKey.getW()).mod(publicKey.getN());
		
		
		
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
		try {
			input.add(Math.ConvertToBytes(publicKey));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		return new Signature(u, bigU1, bigU2, bigU3, zx, zr, ze, zbigR, c);

	}
}
