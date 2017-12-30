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
 */

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
