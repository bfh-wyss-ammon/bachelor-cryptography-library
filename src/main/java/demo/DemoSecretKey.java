/**
 * Empty Class, that is needed because we need different types
 * for serialization / transmission and hibernation (ORM mapping).
 */
package demo;

import java.math.BigInteger;
import keys.SecretKey;
import responses.JoinResponse;

public class DemoSecretKey implements SecretKey {
	private BigInteger x;
	private BigInteger w;
	private BigInteger y;
	private BigInteger e;
	private BigInteger r;
	private BigInteger bigE;
	private BigInteger bigY;
	private BigInteger commitment;
	
	public DemoSecretKey() {
		
	}

	public BigInteger getBigY() {
		return bigY;
	}

	public BigInteger getCommitment() {
		return commitment;
	}

	public BigInteger getBigE() {
		return bigE;
	}
	
	public BigInteger getX() {
		return x;
	}

	public BigInteger getY() {
		return y;
	}

	public BigInteger getE() {
		return e;
	}

	public BigInteger getR() {
		return r;
	}

	public BigInteger getW() {
		return w;
	}
	
	public void setX(BigInteger x) {
		this.x = x;
	}

	public void setW(BigInteger w) {
		this.w = w;
	}

	public void setY(BigInteger y) {
		this.y = y;
	}

	public void setE(BigInteger e) {
		this.e = e;
	}

	public void setR(BigInteger r) {
		this.r = r;
	}

	public void setBigE(BigInteger bigE) {
		this.bigE = bigE;
	}

	public void setBigY(BigInteger bigY) {
		this.bigY = bigY;
	}

	public void setCommitment(BigInteger commitment) {
		this.commitment = commitment;
	}

	public void maintainResponse(JoinResponse joinResponse) {
		this.bigE = joinResponse.getEi();
		this.r = joinResponse.getRi().add(getR());
		this.e = joinResponse.getE();
		this.y = joinResponse.getYi();
		this.w = joinResponse.getWi();

	}
}
