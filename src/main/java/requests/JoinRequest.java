package requests;

import java.math.BigInteger;

import keys.SecretKey;

public class JoinRequest {

	private final BigInteger bigY;
	private final BigInteger commitment;

	public JoinRequest(BigInteger bigY, BigInteger commitment) {
		this.bigY = bigY;
		this.commitment = commitment;

	}

	public JoinRequest(SecretKey memberKey) {
		this.bigY = memberKey.getBigY();
		this.commitment = memberKey.getCommitment();
	}

	public BigInteger bigY() {
		return bigY;
	}

	public BigInteger getCommitment() {
		return commitment;
	}

}
