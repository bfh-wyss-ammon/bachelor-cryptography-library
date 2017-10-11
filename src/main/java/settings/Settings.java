/**
 * Getter and Setter Interface for the bit length settings of the group
 * signature scheme.
 */

package settings;

public interface Settings {
	public int getModulus();
	public int getlE();
	public int getlQ();
	public int getlc();
	public int getle();
	public int getPrimeCertainty();
}
