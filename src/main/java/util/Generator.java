/**
 * This is the Key Generator of this Group Signature Scheme.
 * It generates a new group.
 * It takes an empty Public Key, empty Manager Key and a class that contains the
 * bit length settings of the generated key material.
 */
package util;

import java.math.*;
import java.security.*;
import keys.ManagerKey;
import keys.PublicKey;
import settings.DefaultSettings;
import settings.Settings;

public class Generator {

	public static void generate(PublicKey emptyPublicKey, ManagerKey managerKey) {
		generate(new DefaultSettings(), emptyPublicKey, managerKey);
	}

	public static void generate(Settings settings, PublicKey emptyPublicKey, ManagerKey managerKey) {
		SecureRandom random = new SecureRandom();

		while (true) {
			BigInteger p = (new BigInteger((settings.getModulus() / 2), settings.getPrimeCertainty(), random));
			BigInteger q = (new BigInteger((settings.getModulus() / 2), settings.getPrimeCertainty(), random));
			BigInteger n = p.multiply(q);

			// some easy checks
			if (n.bitLength() == settings.getModulus() && p.isProbablePrime(settings.getPrimeCertainty())
					&& q.isProbablePrime(settings.getPrimeCertainty())) {

				BigInteger alpha = new BigInteger(settings.getModulus(), settings.getPrimeCertainty(), random);
				BigInteger a = Math.RandomElementOfQRn(random, settings.getModulus(), n, p, q);
				BigInteger h = Math.RandomElementOfQRn(random, settings.getModulus(), n, p, q);
				BigInteger g = h.modPow(alpha, n);
				BigInteger w = Math.RandomElementOfQRn(random, settings.getModulus(), n, p, q);

				BigInteger bigQ = new BigInteger(settings.getlQ(), settings.getPrimeCertainty(), random);
				BigInteger multiplicator = new BigInteger("2").pow(settings.getModulus() - settings.getlQ());
				BigInteger bigP = bigQ.multiply(multiplicator).add(BigInteger.ONE);

				if (bigP.bitLength() == settings.getModulus()) {
					while (true) {
						if (bigP.bitLength() == settings.getModulus()) {
							if (bigP.isProbablePrime(1) && bigP.isProbablePrime(settings.getPrimeCertainty()))
								break;
						}
						multiplicator = multiplicator.add(BigInteger.ONE);
						bigP = bigQ.multiply(multiplicator).add(BigInteger.ONE);
					}

					// checks
					if (bigP.isProbablePrime(settings.getPrimeCertainty())
							&& bigQ.isProbablePrime(settings.getPrimeCertainty())) {

						BigInteger bigF = new BigInteger(settings.getModulus(), random).mod(bigP);
						bigF = bigF.modPow((bigP.subtract(BigInteger.ONE)).divide(bigQ), bigP);

						BigInteger Xg = Math.randValModP(random, settings.getlQ(), bigQ);
						BigInteger Xh = Math.randValModP(random, settings.getlQ(), bigQ);

						BigInteger bigG = bigF.modPow(Xg, bigP);
						BigInteger bigH = bigF.modPow(Xh, bigP);

						emptyPublicKey.setN(n);
						emptyPublicKey.setA(a);
						emptyPublicKey.setG(g);
						emptyPublicKey.setH(h);
						emptyPublicKey.setBigQ(bigQ);
						emptyPublicKey.setBigP(bigP);
						emptyPublicKey.setBigF(bigF);
						emptyPublicKey.setBigG(bigG);
						emptyPublicKey.setBigH(bigH);
						emptyPublicKey.setW(w);

						managerKey.setP(p);
						managerKey.setQ(q);
						managerKey.setXg(Xg);

						break;
					}
				}
			}

		}
	}
}
