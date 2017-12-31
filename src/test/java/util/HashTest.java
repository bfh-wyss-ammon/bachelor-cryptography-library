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

package util;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import org.junit.Test;

import com.google.gson.Gson;

import data.Tuple;
import demo.DemoPublicKey;
import demo.DemoSecretKey;
import demo.DemoSignature;
import interfaces.HashValue;
import keys.PublicKey;
import keys.SecretKey;

public class HashTest {
	
	class A {
		@HashValue
		private String test;

		public String getTest() {
			return test;
		}

		public void setTest(String test) {
			this.test = test;
		}
		
		@HashValue 
		private BigInteger number;

		public BigInteger getNumber() {
			return number;
		}

		public void setNumber(BigInteger number) {
			this.number = number;
		}
		@HashValue
		private BigDecimal decimal;

		public BigDecimal getDecimal() {
			return decimal;
		}

		public void setDecimal(BigDecimal decimal) {
			this.decimal = decimal;
		}
		
		@HashValue
		private HashMap<String, String> map;

		public HashMap<String, String> getMap() {
			return map;
		}

		public void setMap(HashMap<String, String> map) {
			this.map = map;
		}
		
		
		
		
		
	}
	
	@Test
	public void hashTest() {
		
		A a = new A();
		A b = new A();
		
		b.setTest("blabla");
		b.setNumber(new BigInteger("2"));
		a.setTest("blabla");	
		a.setNumber(new BigInteger("3"));
		
		
		
		a.setDecimal(new BigDecimal("10.12121"));
		
		a.setMap(new HashMap<>());
		a.getMap().put("z", "1");
		a.getMap().put("b", "2");
		a.getMap().put("d", "3");
		
		b.setDecimal(new BigDecimal("10.12121"));
		b.setMap(new HashMap<>());
		b.getMap().put("y", "1");
		b.getMap().put("x", "2");
		b.getMap().put("a", "3");
		
		
		String strA = Base64.getEncoder().encodeToString(HashHelper.getHash(a));
		String strB = Base64.getEncoder().encodeToString(HashHelper.getHash(b));
		
		assertFalse(strA.equals(strB));
	}
}
