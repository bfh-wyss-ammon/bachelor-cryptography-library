package signatures;

import java.math.BigInteger;
import com.google.gson.annotations.Expose;


public interface Signature {
	
	public BigInteger getU();

	public BigInteger getBigU1();

	public BigInteger getBigU2();

	public BigInteger getBigU3();

	public BigInteger getZx();

	public BigInteger getZr();

	public BigInteger getZe();

	public BigInteger getZbigR();

	public BigInteger getC();
	
	public void setU(BigInteger u);

	public void setBigU1(BigInteger bigU1);

	public void setBigU2(BigInteger bigU2);

	public void setBigU3(BigInteger bigU3);

	public void setZx(BigInteger zx);

	public void setZr(BigInteger zr);

	public void setZe(BigInteger ze);

	public void setZbigR(BigInteger zbigR);

	public void setC(BigInteger c);
}
