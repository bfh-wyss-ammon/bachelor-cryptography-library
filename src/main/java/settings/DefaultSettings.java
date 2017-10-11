/**
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
		this.lE = 504;
		this.lQ = 282;
		this.lc = 160;
		this.le = 60;
		this.prime_certainty = 100;
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
