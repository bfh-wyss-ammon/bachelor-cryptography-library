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
 * Interfaces that define getter and setter methods for the Public Key. 
 */
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
