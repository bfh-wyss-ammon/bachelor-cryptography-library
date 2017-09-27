package responses;

import java.math.BigInteger;

public class JoinResponse {

	private final BigInteger wi;
	private final BigInteger yi;
	private final BigInteger Ei;
	private final BigInteger ri;
	private final BigInteger e;

	public JoinResponse(BigInteger wi, BigInteger yi, BigInteger Ei, BigInteger ri, BigInteger e) {
		this.wi = wi;
		this.yi = yi;
		this.Ei = Ei;
		this.ri = ri;
		this.e = e;

	}

	public BigInteger getE() {
		return e;
	}

	public BigInteger getWi() {
		return wi;
	}

	public BigInteger getYi() {
		return yi;
	}

	public BigInteger getEi() {
		return Ei;
	}

	public BigInteger getRi() {
		return ri;
	}

}
