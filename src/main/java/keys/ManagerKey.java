package keys;

import java.math.BigInteger;

public interface ManagerKey {

	public BigInteger getP();

	public BigInteger getQ();

	public BigInteger getXg();

	public void setXg(BigInteger xg);

	public void setP(BigInteger p);

	public void setQ(BigInteger q);

}
