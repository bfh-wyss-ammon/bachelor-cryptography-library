/**
 * This is a interface class we use for the HashValue annotations (decide which fields of an objects for generating it's hash value).
 */

package interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface HashValue {
}
