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
 * Interfaces that define getter and setter methods for the Secret Key. 
 */

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
