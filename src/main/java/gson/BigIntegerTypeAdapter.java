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
 * This class handles the google JSON parser (doesn't parse BigInteger type by default).
 */

package gson;

import java.io.IOException;
import java.math.BigInteger;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class BigIntegerTypeAdapter extends TypeAdapter<BigInteger> {

	@Override
	public BigInteger read(JsonReader reader) throws IOException {
		// TODO Auto-generated method stub
		return new BigInteger(reader.nextString());
	}

	@Override
	public void write(JsonWriter writer, BigInteger bigInteger) throws IOException {
		// TODO Auto-generated method stub
		writer.value(bigInteger.toString());
	}

}
