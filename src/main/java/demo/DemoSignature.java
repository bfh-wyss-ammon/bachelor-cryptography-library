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

import com.google.gson.annotations.Expose;

import interfaces.HashValue;
import signatures.Signature;

public class DemoSignature implements Signature {
	@Expose
	@HashValue
	private BigInteger u;
	@Expose
	@HashValue
	private BigInteger bigU1;
	@Expose
	@HashValue
	private BigInteger bigU2;
	@Expose
	@HashValue
	private BigInteger bigU3;
	@Expose
	@HashValue
	private BigInteger zx;
	@Expose
	@HashValue
	private BigInteger zr;
	@Expose
	@HashValue
	private BigInteger ze;
	@Expose
	@HashValue
	private BigInteger zbigR;	
	@Expose
	@HashValue
	private BigInteger c;
	
	public DemoSignature() {
		
	}



	public BigInteger getU() {
		return u;
	}

	public BigInteger getBigU1() {
		return bigU1;
	}

	public BigInteger getBigU2() {
		return bigU2;
	}

	public BigInteger getBigU3() {
		return bigU3;
	}

	public BigInteger getZx() {
		return zx;
	}

	public BigInteger getZr() {
		return zr;
	}

	public BigInteger getZe() {
		return ze;
	}

	public BigInteger getZbigR() {
		return zbigR;
	}

	public BigInteger getC() {
		return c;
	}

	public void setU(BigInteger u) {
		this.u = u;
	}

	public void setBigU1(BigInteger bigU1) {
		this.bigU1 = bigU1;
	}

	public void setBigU2(BigInteger bigU2) {
		this.bigU2 = bigU2;
	}

	public void setBigU3(BigInteger bigU3) {
		this.bigU3 = bigU3;
	}

	public void setZx(BigInteger zx) {
		this.zx = zx;
	}

	public void setZr(BigInteger zr) {
		this.zr = zr;
	}

	public void setZe(BigInteger ze) {
		this.ze = ze;
	}

	public void setZbigR(BigInteger zbigR) {
		this.zbigR = zbigR;
	}

	public void setC(BigInteger c) {
		this.c = c;
	}

}
