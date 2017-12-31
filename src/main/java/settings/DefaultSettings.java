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
 * Contains the Default Key Length settings for this scheme
 * Used only if none are given to the generator.
 */
package settings;

public class DefaultSettings implements Settings {

	private final int modulus;

	private final int lE;
	
	private final int lQ;

	// length of c
	private final int lc;

	// length of e and s
	private final int le;

	// sets how many rounds of the miller rabin test are run
	private final int prime_certainty;	
	
	public DefaultSettings() {
		this.modulus = 2048;
		this.lE = 696;
		this.lQ = 378;
		this.lc = 256;
		this.le = 60;
		this.prime_certainty = 40;
	}

	
	public int getModulus() {
		return modulus;
	}

	public int getlE() {
		return lE;
	}

	public int getlQ() {
		return lQ;
	}

	public int getlc() {
		return lc;
	}

	public int getle() {
		return le;
	}

	public int getPrimeCertainty() {
		return prime_certainty;
	}
}
