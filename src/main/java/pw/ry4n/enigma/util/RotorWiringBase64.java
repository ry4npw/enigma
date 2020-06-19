package pw.ry4n.enigma.util;

import pw.ry4n.enigma.Reflector;
import pw.ry4n.enigma.ReflectorImpl;
import pw.ry4n.enigma.Rotor;
import pw.ry4n.enigma.RotorImpl;

/**
 * A proof-of-concept rotor wiring for a Base64
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
	public static final String[] ALPHABET = EnigmaUtils
			.stringToStringArray("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=");

	public static Reflector<String> reflector() {
		// reflector must be wired in pairs
		String[] outputs = EnigmaUtils
				.stringToStringArray("h0cUtk8+qKJfeQn3N=r5DpbimxgWC9MLaAXoF1YOjVIS/E7v6Z42BlzPyTwuGdHsR");
		return new ReflectorImpl<>(ALPHABET, outputs);
	}

	public static Rotor<String> rotorI() {
		return rotor("bAPp7ZRnMge5=StoHL+iXITDxJYdjkB3QFh96N8syWuCKUOVEqvw40l/rmzfG1ac2", "0");
	}

	public static Rotor<String> rotorII() {
		return rotor("MT160Jz4XLyqlvZInP7jCfu8QBG3s9p2oWrEtbak/ecFA=wgSUdKHRxYhOi5D+mVN", "8S");
	}

	public static Rotor<String> rotorIII() {
		return rotor("9Ccyh5ejI6gzBfXkUuYdpb4qOVlAFKGNnLmiDJ7T1vWQs3REx=SMt/a28wP0Hro+Z", "B+");
	}

	private static Rotor<String> rotor(String outputs, String notchPositions) {
		return new RotorImpl<>(ALPHABET, EnigmaUtils.stringToStringArray(outputs), ALPHABET,
				EnigmaUtils.stringToStringArray(notchPositions));
	}
}
