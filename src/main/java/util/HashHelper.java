package util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.mapping.Map;

import interfaces.HashValue;

public class HashHelper {
	private static final DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");

	private static MessageDigest digest;

	static {
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// this methods generate the hash value of an object based ond the @HashValue
	// Annotation
	// check the test case for more info
	public static byte[] getHash(Object value) {
		if (digest == null) {
			// todo error handling
		}
		return digest.digest(getBytes(value));
	}

	// this methods generate the hash value of an object based ond the @HashValue
	// Annotation
	// check the test case for more info
	public static byte[] getBytes(Object value) {
		StringBuilder builder = new StringBuilder();
		if (value != null) {

			List<Field> fields = new ArrayList<Field>();

			for (Field field : value.getClass().getDeclaredFields()) {
				fields.add(field);
			}

			fields.sort(new Comparator<Field>() {

				@Override
				public int compare(Field a, Field b) {
					// TODO Auto-generated method stub
					int res = String.CASE_INSENSITIVE_ORDER.compare(a.getName(), b.getName());
					if (res == 0) {
						res = a.getName().compareTo(b.getName());
					}
					return res;
				}

			});

			for (Field field : fields) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(HashValue.class)) {
					try {
						setValue(field.get(value), builder);

					} catch (Exception e) {
						// todo error handling!
						e.printStackTrace();
					}
				}
			}
		}
		return builder.toString().getBytes(StandardCharsets.UTF_8);
	}

	private static void setValue(Object obj, StringBuilder builder) {
				
		if (obj.getClass() == Date.class) {
			builder.append(dateFormat.format((Date) obj));
		} else if (obj.getClass() == BigDecimal.class) {
			builder.append(((BigDecimal) obj).toPlainString());
		} else if (obj.getClass() == HashMap.class) {

			HashMap<?, ?> map = ((HashMap<?, ?>) obj);
			map.forEach((k, v) -> {
				setValue(k, builder);
				setValue(v, builder);
			});
		} else {
			builder.append(obj.toString());
		}
	}

}
