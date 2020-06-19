package pw.ry4n.enigma.util;

import pw.ry4n.enigma.Reflector;
import pw.ry4n.enigma.ReflectorImpl;
import pw.ry4n.enigma.Rotor;
import pw.ry4n.enigma.RotorImpl;

/**
 * Rotor wirings for the German Enigma rotors from
 * <a>http://en.wikipedia.org/wiki/Enigma_rotor_details</a>.
 * 
 * @author Ryan Powell
 */
public class RotorWiring {
	/**
	 * Private constructor to prevent instantiation of this class.
	 */
	private RotorWiring() {
		// empty method
	}

	public static final Character[] ALPHABET = EnigmaUtils.stringToCharacterArray("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

	public static Reflector<Character> reflectorA() {
		return reflector("EJMZALYXVBWFCRQUONTSPIKHGD");
	}

	public static Reflector<Character> reflectorB() {
		return reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
	}

	public static Reflector<Character> reflectorC() {
		return reflector("FVPJIAOYEDRZXWGCTKUQSBNMHL");
	}

	public static Reflector<Character> reflectorThinB() {
		return reflector("ENKQAUYWJICOPBLMDXZVFTHRGS");
	}

	public static Reflector<Character> reflectorThinC() {
		return reflector("RDOBJNTKVEHMLFCWZAXGYIPSUQ");
	}

	public static Rotor<Character> rotorI() {
		return rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", "Q");
	}

	public static Rotor<Character> rotorII() {
		return rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", "E");
	}

	public static Rotor<Character> rotorIII() {
		return rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", "V");
	}

	public static Rotor<Character> rotorIV() {
		return rotor("ESOVPZJAYQUIRHXLNFTGKDCMWB", "J");
	}

	public static Rotor<Character> rotorV() {
		return rotor("VZBRGITYUPSDNHLXAWMJQOFECK", "Z");
	}

	public static Rotor<Character> rotorVI() {
		return rotor("JPGVOUMFYQBENHZRDKASXLICTW", "ZM");
	}

	public static Rotor<Character> rotorVII() {
		return rotor("NZJHGRCXMYSWBOUFAIVLPEKQDT", "ZM");
	}

	public static Rotor<Character> rotorVIII() {
		return rotor("FKQHTLXOCBJSPDZRAMEWNIUYGV", "ZM");
	}

	private static Reflector<Character> reflector(String outputs) {
		return new ReflectorImpl<>(ALPHABET, EnigmaUtils.stringToCharacterArray(outputs));
	}

	private static Rotor<Character> rotor(String outputs, String notchPositions) {
		return new RotorImpl<>(ALPHABET, EnigmaUtils.stringToCharacterArray(outputs), ALPHABET,
				EnigmaUtils.stringToCharacterArray(notchPositions));
	}
}
