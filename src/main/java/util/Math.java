/**
 * This class contains some methods used by the Generator.
 * These methods concern:
 * hashing, finding random values with fixed length and primes with fixed length.
 */

package util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Math {
	public static BigInteger GetHash(ArrayList<byte[]> values) {
		try {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		BigInteger last = new BigInteger("0");
		for (byte[] value : values){
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
			try {
				outputStream.write(last.toByteArray());
				outputStream.write(value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte toBeHashedValue[] = outputStream.toByteArray();
			md.update(toBeHashedValue, 0, toBeHashedValue.length);
			last = new BigInteger(1, md.digest());
		}
		return last;
		}
		catch(Exception ex) {
			//todo: error handling
		}
		return null;
	}
		
	public static BigInteger RandValModP(SecureRandom rand, int maxlength, BigInteger p) {
		BigInteger ret = new BigInteger(maxlength, rand).mod(p);
		while (ret.bitLength() != maxlength) {
			ret = new BigInteger(maxlength, rand).mod(p);
		}
		return ret;
	}
	
	public static BigInteger RandomElementOfQRn(SecureRandom rand, int modulus, BigInteger n, BigInteger p, BigInteger q) {
		BigInteger a = RandValModP(rand, modulus, n);

		while (!QuadraticResidue(a, p, q)) {
			a = RandValModP(rand, modulus, n);
		}
		return a;
	}
	
	public static boolean QuadraticResidue(BigInteger a, BigInteger p, BigInteger q) {
		BigInteger two = BigInteger.ONE.add(BigInteger.ONE);

		BigInteger test1 = p.subtract(BigInteger.ONE).divide(two);
		BigInteger test2 = q.subtract(BigInteger.ONE).divide(two);

		return a.mod(p).modPow(test1, p).equals(BigInteger.ONE)
				&& a.mod(q).modPow(test2, q).equals(BigInteger.ONE);
	}
	
	public static byte[] ConvertToBytes(Object object) throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(bos)) {
			out.writeObject(object);
			return bos.toByteArray();
		}
	}
	
	public static BigInteger totient(BigInteger n) {
		BigInteger phi = new BigInteger("1");
		BigInteger i = new BigInteger("2");
		while (i.compareTo(n) < 0) {
			if ((i.gcd(n)).equals(BigInteger.ONE))
				phi = phi.add(BigInteger.ONE);
			i = i.add(BigInteger.ONE);
		}
		return phi;
	}
	
	public static BigInteger randValModP(SecureRandom rand, int maxlength, BigInteger p) {
		BigInteger ret = new BigInteger(maxlength, rand).mod(p);
		while (ret.bitLength() != maxlength) {
			ret = new BigInteger(maxlength, rand).mod(p);
		}
		return ret;
	}

	public static BigInteger randVal(SecureRandom rand, int length) {
		BigInteger ret = new BigInteger(length, rand);
		while (ret.bitLength() != length) {
			ret = new BigInteger(length, rand);
		}
		return ret;
	}
}
