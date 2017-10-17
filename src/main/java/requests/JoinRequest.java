/**
 * After generating its member secret, the member who wants to join 
 * a group sends a Join Request to the Server. The Join Request contains
 * bigY (which the server uses to identify the member) and a commitment,
 * which is proof that the member is in possession of its secret.
 */
package requests;

import java.math.BigInteger;

import com.google.gson.annotations.Expose;

import keys.SecretKey;

public class JoinRequest {
	@Expose
	private final BigInteger bigY;
	@Expose
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
	
	public boolean IsComplete() {
		return bigY != null && commitment != null;
	}

}
