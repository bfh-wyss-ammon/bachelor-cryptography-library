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

import java.math.*;
import java.security.SecureRandom;

public class Paillier {
	// sets the size of the key material
	public final int modulus = 2048;

	// sets how many rounds of the miller rabin test are run
	public final int prime_certainty = 100;

	// the public key and the generator (is equal to n+1)
	private BigInteger n;
	private BigInteger nsquared;
	private BigInteger g;

	// the random generator used for cryptographic operations
	private SecureRandom rand;

	// the private key
	private BigInteger lambda;
	private BigInteger p;
	private BigInteger q;

	// some decryption step that can be precalculated
	private BigInteger preCalcDecrypt;

	/**
	 * create a new instance of this cryptosystem with new keys
	 */
	public Paillier() {
		rand = new SecureRandom();
		boolean keysAreValid = false;
		while (!keysAreValid) {
			keysAreValid = keyGen();
		}
	}

	/**
	 * @param n
	 *            part of the public key
	 * @param p
	 *            part of the private key
	 * @param q
	 *            part of the private key
	 * @param lambda
	 *            part of the private key create a new instance of this cryptosystem
	 *            with known keys public key must be a hex string with 2048 bits
	 *            private key must be a hex string p and hex string q separated with
	 *            _ p and q are each 1024 bit
	 */
	public Paillier(String publickey_n, String privatekey_p_q) {

		if (publickey_n != null) {
			this.n = new BigInteger(publickey_n, 16);
			this.g = new BigInteger("2");
			this.nsquared = this.n.multiply(this.n);
		}

		if (privatekey_p_q != null) {
			String pq = privatekey_p_q;
			String[] splitted = pq.split("_");
			String pstring = splitted[0];
			String qstring = splitted[1];
			BigInteger p = new BigInteger(pstring, 16);
			BigInteger q = new BigInteger(qstring, 16);
			this.p = p;
			this.q = q;
			this.lambda = this.p.subtract(BigInteger.ONE).multiply(this.q.subtract(BigInteger.ONE))
					.divide(this.p.subtract(BigInteger.ONE).gcd(this.q.subtract(BigInteger.ONE)));
			this.preCalcDecrypt = this.g.modPow(this.lambda, this.nsquared).subtract(BigInteger.ONE).divide(this.n)
					.modInverse(this.n);
		}

		rand = new SecureRandom();
	}

	private boolean keyGen() {

		// find the public key
		p = new BigInteger(this.modulus / 2, this.prime_certainty, this.rand);
		q = new BigInteger(this.modulus / 2, this.prime_certainty, this.rand);
		this.n = this.p.multiply(this.q);
		this.nsquared = this.n.multiply(this.n);
		this.g = new BigInteger("2");

		// find and check the secret key
		this.lambda = this.p.subtract(BigInteger.ONE).multiply(this.q.subtract(BigInteger.ONE))
				.divide(this.p.subtract(BigInteger.ONE).gcd(this.q.subtract(BigInteger.ONE)));
		this.preCalcDecrypt = this.g.modPow(this.lambda, this.nsquared).subtract(BigInteger.ONE).divide(this.n)
				.modInverse(this.n);

		// check if we have generated a valid key pair
		BigInteger check = this.g.modPow(this.lambda, this.nsquared).subtract(BigInteger.ONE).divide(this.n);
		if (!relPrime(check, n))
			return false;
		return true;
	}

	/**
	 * @param m
	 *            takes BigInt m as message
	 * @return returns the encrypted ciphertext of m
	 */
	public BigInteger encryption(BigInteger m) {

		// make sure we have the public key and that the message m < n
		if (this.nsquared == null || !isLess(m, n))
			return null;

		// make sure we have a random number r < n
		BigInteger r = null;
		while (r == null || !isLess(r, n)) {
			r = new BigInteger(this.modulus, this.rand);
		}

		return this.g.modPow(m, this.nsquared).multiply(r.modPow(this.n, this.nsquared)).mod(this.nsquared);

	}

	/**
	 * @param c
	 *            takes BigInt c as ciphertext
	 * @return returns the decrypted cleartext of c
	 */
	public BigInteger decryption(BigInteger c) {
		// make sure we have the private and public key and c < n^2
		if (this.n == null || this.lambda == null || this.nsquared == null || this.g == null || (!isLess(c, nsquared))
				|| this.preCalcDecrypt == null)
			return null;
		return c.modPow(this.lambda, this.nsquared).subtract(BigInteger.ONE).divide(this.n)
				.multiply(this.preCalcDecrypt).mod(this.n);
	}

	/**
	 * @param c1
	 *            ciphertext to be added
	 * @param c2
	 *            ciphertext to be added
	 * @param n
	 *            the public key
	 * @return the encrypted result of the addition
	 */
	public BigInteger homomorphicAdd(BigInteger c1, BigInteger c2) {
		if (nsquared == null)
			return null;
		return c1.multiply(c2).mod(this.nsquared);
	}

	public String getPrivateKey() {
		if (p == null || q == null)
			return null;
		return p.toString(16) + "_" + q.toString(16);
	}

	public String getPublicKey() {
		return n.toString(16);
	}

	private boolean isLess(BigInteger a, BigInteger b) {
		return a.compareTo(b) == -1;

	}

	private boolean relPrime(BigInteger a, BigInteger b) {
		return a.gcd(b).intValue() == 1;

	}
}