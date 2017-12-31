/**
 *   Copyright 2018 Pascal Ammon, Gabriel Wyss
 * 
 * 	 Implementation eines anonymen Mobility Pricing Systems auf Basis eines Gruppensignaturschemas
 * 
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
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
