package demo;

import java.io.Serializable;
import java.math.BigInteger;

import keys.PublicKey;

public class DemoPublicKey implements PublicKey, Serializable {
	/**
	 * default serial version id
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger n;
	private BigInteger a;
	private BigInteger g;
	private BigInteger h;
	private BigInteger w;
	private BigInteger bigQ;
	private BigInteger bigP;
	private BigInteger bigF;
	private BigInteger bigG;
	private BigInteger bigH;

	public DemoPublicKey(BigInteger n, BigInteger a, BigInteger g, BigInteger h, BigInteger bigQ, BigInteger bigP,
			BigInteger bigF, BigInteger bigG, BigInteger bigH, BigInteger w) {
		this.n = n;
		this.a = a;
		this.g = g;
		this.h = h;
		this.bigQ = bigQ;
		this.bigP = bigP;
		this.bigF = bigF;
		this.bigG = bigG;
		this.bigH = bigH;
		this.w = w;
	}

	public DemoPublicKey() {

	}

	public BigInteger getN() {
		return n;
	}

	public BigInteger getA() {
		return a;
	}

	public BigInteger getG() {
		return g;
	}

	public BigInteger getH() {
		return h;
	}

	public BigInteger getBigQ() {
		return bigQ;
	}

	public BigInteger getBigP() {
		return bigP;
	}

	public BigInteger getBigF() {
		return bigF;
	}

	public BigInteger getBigG() {
		return bigG;
	}

	public BigInteger getBigH() {
		return bigH;
	}

	public BigInteger getW() {
		return w;
	}

	public void setN(BigInteger n) {
		this.n = n;
	}

	public void setA(BigInteger a) {
		this.a = a;
	}

	public void setG(BigInteger g) {
		this.g = g;
	}

	public void setH(BigInteger h) {
		this.h = h;
	}

	public void setW(BigInteger w) {
		this.w = w;
	}

	public void setBigQ(BigInteger bigQ) {
		this.bigQ = bigQ;
	}

	public void setBigP(BigInteger bigP) {
		this.bigP = bigP;
	}

	public void setBigF(BigInteger bigF) {
		this.bigF = bigF;
	}

	public void setBigG(BigInteger bigG) {
		this.bigG = bigG;
	}

	public void setBigH(BigInteger bigH) {
		this.bigH = bigH;
	}

}