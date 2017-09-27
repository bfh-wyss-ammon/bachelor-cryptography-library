package keys;

import java.math.BigInteger;

public interface PublicKey {
	public BigInteger getN();

	public BigInteger getA();

	public BigInteger getG();

	public BigInteger getH();

	public BigInteger getBigQ();

	public BigInteger getBigP();

	public BigInteger getBigF();

	public BigInteger getBigG();

	public BigInteger getBigH();

	public BigInteger getW();

	public void setN(BigInteger n);

	public void setA(BigInteger a);

	public void setG(BigInteger g);

	public void setH(BigInteger h);

	public void setW(BigInteger w);

	public void setBigQ(BigInteger bigQ);

	public void setBigP(BigInteger bigP);

	public void setBigF(BigInteger bigF);

	public void setBigG(BigInteger bigG);

	public void setBigH(BigInteger bigH);

}
