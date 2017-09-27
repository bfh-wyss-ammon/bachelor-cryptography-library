package util;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

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
				&& signature.getZx().bitLength() != (settings.getlQ() + settings.getlc() + settings.getle()))
			return false;

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
		try {
			input.add(Math.ConvertToBytes(publicKey));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
