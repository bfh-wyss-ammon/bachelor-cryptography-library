package util;

import java.math.BigInteger;
import java.security.SecureRandom;

import keys.ManagerKey;
import keys.SecretKey;
import keys.PublicKey;
import requests.JoinRequest;
import responses.JoinResponse;
import settings.DefaultSettings;
import settings.Settings;

public class JoinHelper {

	public static JoinResponse join(PublicKey publicKey, ManagerKey managerKey, JoinRequest joinRequest) {
		return join(new DefaultSettings(), publicKey, managerKey, joinRequest);
	}

	public static JoinResponse join(Settings settings, PublicKey publicKey, ManagerKey managerKey,
			JoinRequest joinRequest) {
		SecureRandom random = new SecureRandom();

		BigInteger e = new BigInteger(settings.getle(), random);
		BigInteger twoToLE = new BigInteger("2").pow(settings.getlE() - 1);
		BigInteger bigE = twoToLE.add(e);

		boolean repeat = true;
		while (repeat) {
			e = new BigInteger(settings.getle(), random);
			bigE = twoToLE.add(e);
			if (e.bitLength() == settings.getle() && bigE.bitLength() == settings.getlE()
					&& bigE.isProbablePrime(settings.getPrimeCertainty()))
				repeat = false;
		}
		BigInteger ri = new BigInteger(e.bitLength() - 1, settings.getPrimeCertainty(), random);
		BigInteger commitment = joinRequest.getCommitment().multiply(publicKey.getH().modPow(ri, publicKey.getN()));
		BigInteger part = publicKey.getA().multiply(commitment).mod(publicKey.getN());

		BigInteger totient = managerKey.getP().subtract(BigInteger.ONE).multiply(managerKey.getQ().subtract(BigInteger.ONE));
		BigInteger privat = bigE.modInverse(totient);

		// encrypt res
		BigInteger yi = part.modPow(privat, publicKey.getN());
		BigInteger wi = publicKey.getW().modPow(privat, publicKey.getN());

		return new JoinResponse(wi, yi, bigE, ri, e);
	}

	public static void init(PublicKey publicKey, SecretKey secretKey) {
		init(new DefaultSettings(), publicKey, secretKey);
	}
	public static void init(Settings settings, PublicKey publicKey, SecretKey secretKey) {
		SecureRandom random = new SecureRandom();
		BigInteger xi = Math.RandValModP(random, settings.getModulus(), publicKey.getN());

		BigInteger bigY = publicKey.getBigG().modPow(xi, publicKey.getBigP());

		BigInteger ri = new BigInteger(settings.getModulus(), settings.getPrimeCertainty(), random);
		while (!ri.gcd(publicKey.getN()).equals(BigInteger.ONE)) {
			ri = new BigInteger(settings.getModulus(), settings.getPrimeCertainty(), random);
		}
		BigInteger commitment = publicKey.getG().modPow(xi, publicKey.getN())
				.multiply(publicKey.getH().modPow(ri, publicKey.getN())).mod(publicKey.getN());
		
		secretKey.setX(xi);
		secretKey.setR(ri);
		secretKey.setBigY(bigY);
		secretKey.setCommitment(commitment);
	}
}