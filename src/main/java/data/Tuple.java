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
 * This class stores the data that belongs to a position tuple of the driving protocol. we had to put this in the crypto library because we don't want to install the common lib on the mobile application.
 * This class is also used for serializing, parsing and hashing the position tuple and its signature.
 */

package data;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.Expose;

import demo.DemoSignature;
import interfaces.HashValue;
import signatures.Signature;

public class Tuple {
	@Expose
	private int groupId;
	@Expose
	@HashValue
	private BigDecimal longitude;
	@Expose
	@HashValue
	private BigDecimal latitude;
	@Expose
	@HashValue
	private Date created;
	@Expose
	private DemoSignature signature;
	
	public Tuple()
	{
		
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Signature getSignature() {
		return signature;
	}

	public void setSignature(DemoSignature signature) {
		this.signature = signature;
	}

}
