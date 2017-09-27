package keys;

import java.math.BigInteger;

import responses.JoinResponse;

public interface SecretKey {
	public BigInteger getBigY();

	public BigInteger getCommitment();

	public BigInteger getBigE();

	public BigInteger getX();

	public BigInteger getY();

	public BigInteger getE();

	public BigInteger getR();

	public BigInteger getW();
	
	public void setX(BigInteger x);

	public void setW(BigInteger w);

	public void setY(BigInteger y);

	public void setE(BigInteger e);

	public void setR(BigInteger r);

	public void setBigE(BigInteger bigE);

	public void setBigY(BigInteger bigY);

	public void setCommitment(BigInteger commitment);

	public void maintainResponse(JoinResponse joinResponse);
}
