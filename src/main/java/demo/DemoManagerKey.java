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
 * Empty Class, that is needed because we need different types
 * for serialization / transmission and hibernation (ORM mapping).
 */
package demo;

import java.math.BigInteger;

import keys.ManagerKey;

public class DemoManagerKey implements ManagerKey {
	private BigInteger Xg;
	private BigInteger p;
	private BigInteger q;

	public DemoManagerKey() {
	}

	public BigInteger getP() {
		return p;
	}

	public BigInteger getQ() {
		return q;
	}

	public BigInteger getXg() {
		return Xg;
	}

	public void setXg(BigInteger xg) {
		Xg = xg;
	}

	public void setP(BigInteger p) {
		this.p = p;
	}

	public void setQ(BigInteger q) {
		this.q = q;
	}
}
