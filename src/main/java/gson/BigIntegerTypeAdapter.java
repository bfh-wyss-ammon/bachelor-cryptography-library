/**
 * This class handles the google JSON parser (doesn't allow BigInteger type).
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
