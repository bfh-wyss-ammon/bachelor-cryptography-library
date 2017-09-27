package signatures;

import java.math.BigInteger;


public class Signature {

	private final BigInteger u;
	private final BigInteger bigU1;
	private final BigInteger bigU2;
	private final BigInteger bigU3;
	private final BigInteger zx;
	private final BigInteger zr;
	private final BigInteger ze;
	private final BigInteger zbigR;
	private final BigInteger c;

	public Signature(BigInteger u, BigInteger bigU1, BigInteger bigU2, BigInteger bigU3, BigInteger zx,
			BigInteger zr, BigInteger ze, BigInteger zbigR, BigInteger c) {
		this.u = u;
		this.bigU1 = bigU1;
		this.bigU2 = bigU2;
		this.bigU3 = bigU3;
		this.zx = zx;
		this.zr = zr;
		this.ze = ze;
		this.zbigR = zbigR;
		this.c = c;
	}

	public BigInteger getU() {
		return u;
	}

	public BigInteger getBigU1() {
		return bigU1;
	}

	public BigInteger getBigU2() {
		return bigU2;
	}

	public BigInteger getBigU3() {
		return bigU3;
	}

	public BigInteger getZx() {
		return zx;
	}

	public BigInteger getZr() {
		return zr;
	}

	public BigInteger getZe() {
		return ze;
	}

	public BigInteger getZbigR() {
		return zbigR;
	}

	public BigInteger getC() {
		return c;
	}
}
