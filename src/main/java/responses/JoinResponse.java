package responses;

import java.math.BigInteger;

import com.google.gson.annotations.Expose;

public class JoinResponse {
	@Expose
	private final BigInteger wi;
	@Expose
	private final BigInteger yi;
	@Expose
	private final BigInteger Ei;
	@Expose
	private final BigInteger ri;
	@Expose
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
