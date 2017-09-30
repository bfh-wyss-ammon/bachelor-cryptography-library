package signatures;

import java.math.BigInteger;
import com.google.gson.annotations.Expose;


public class Signature {
	@Expose
	private final BigInteger u;
	@Expose
	private final BigInteger bigU1;
	@Expose
	private final BigInteger bigU2;
	@Expose
	private final BigInteger bigU3;
	@Expose
	private final BigInteger zx;
	@Expose
	private final BigInteger zr;
	@Expose
	private final BigInteger ze;
	@Expose
	private final BigInteger zbigR;
	@Expose
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
