package pw.ry4n.enigma.util;

import pw.ry4n.enigma.Reflector;
import pw.ry4n.enigma.ReflectorImpl;
import pw.ry4n.enigma.Rotor;
import pw.ry4n.enigma.RotorImpl;

/**
 * A proof-of-concept wiring for a Base64
 * <a>https://en.wikipedia.org/wiki/Base64</a> enigma machine.
 * 
 * @author Ryan Powell
 */
public class RotorWiringBase64 {
	/**
	 * Private constructor to prevent instantiation of this class.
	 */
	private RotorWiringBase64() {
		// empty method
	}

	// ALPHABET per https://en.wikipedia.org/wiki/Base64
	public static final String[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
			.split("");

	public static Reflector<String> reflector() {
		// reflector cannot be random, it must be wired in pairs
		return new ReflectorImpl<>(ALPHABET,
				"h0cUtk8+qKJfeQn3N=r5DpbimxgWC9MLaAXoF1YOjVIS/E7v6Z42BlzPyTwuGdHsR".split(""), ALPHABET);
	}

	public static Rotor<String> rotorI() {
		return new RotorImpl<>(ALPHABET, "bAPp7ZRnMge5=StoHL+iXITDxJYdjkB3QFh96N8syWuCKUOVEqvw40l/rmzfG1ac2".split(""),
				ALPHABET, "0".split(""));
	}

	public static Rotor<String> rotorII() {
		return new RotorImpl<>(ALPHABET, "MT160Jz4XLyqlvZInP7jCfu8QBG3s9p2oWrEtbak/ecFA=wgSUdKHRxYhOi5D+mVN".split(""),
				ALPHABET, "8S".split(""));
	}

	public static Rotor<String> rotorIII() {
		return new RotorImpl<>(ALPHABET, "9Ccyh5ejI6gzBfXkUuYdpb4qOVlAFKGNnLmiDJ7T1vWQs3REx=SMt/a28wP0Hro+Z".split(""),
				ALPHABET, "B+".split(""));
	}
}
