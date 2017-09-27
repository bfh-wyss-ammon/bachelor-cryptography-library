package demo;

import java.math.BigInteger;

import keys.ManagerKey;

public class DemoManagerKey implements ManagerKey {
	private BigInteger Xg;
	private BigInteger p;
	private BigInteger q;

	public DemoManagerKey() {
	}

	public BigInteger getP() {
		return p;
	}

	public BigInteger getQ() {
		return q;
	}

	public BigInteger getXg() {
		return Xg;
	}

	public void setXg(BigInteger xg) {
		Xg = xg;
	}

	public void setP(BigInteger p) {
		this.p = p;
	}

	public void setQ(BigInteger q) {
		this.q = q;
	}
}
