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

	public static final Character[] ALPHABET = EnigmaUtils.toCharacterArray("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

	public static Reflector<Character> reflectorWideB() {
		return new ReflectorImpl<>(ALPHABET, EnigmaUtils.toCharacterArray("YRUHQSLDPXNGOKMIEBFZCWVJAT"), ALPHABET);
	}

	public static Rotor<Character> rotorI() {
		return new RotorImpl<>(ALPHABET, EnigmaUtils.toCharacterArray("EKMFLGDQVZNTOWYHXUSPAIBRCJ"), ALPHABET,
				EnigmaUtils.toCharacterArray("Q"));
	}

	public static Rotor<Character> rotorII() {
		return new RotorImpl<>(ALPHABET, EnigmaUtils.toCharacterArray("AJDKSIRUXBLHWTMCQGZNPYFVOE"), ALPHABET,
				EnigmaUtils.toCharacterArray("E"));
	}

	public static Rotor<Character> rotorIII() {
		return new RotorImpl<>(ALPHABET, EnigmaUtils.toCharacterArray("BDFHJLCPRTXVZNYEIWGAKMUSQO"), ALPHABET,
				EnigmaUtils.toCharacterArray("V"));
	}

	public static Rotor<Character> rotorIV() {
		return new RotorImpl<>(ALPHABET, EnigmaUtils.toCharacterArray("ESOVPZJAYQUIRHXLNFTGKDCMWB"), ALPHABET,
				EnigmaUtils.toCharacterArray("J"));
	}

	public static Rotor<Character> rotorV() {
		return new RotorImpl<>(ALPHABET, EnigmaUtils.toCharacterArray("VZBRGITYUPSDNHLXAWMJQOFECK"), ALPHABET,
				EnigmaUtils.toCharacterArray("Z"));
	}

	public static Rotor<Character> rotorVI() {
		return new RotorImpl<>(ALPHABET, EnigmaUtils.toCharacterArray("JPGVOUMFYQBENHZRDKASXLICTW"), ALPHABET,
				EnigmaUtils.toCharacterArray("ZM"));
	}

	public static Rotor<Character> rotorVII() {
		return new RotorImpl<>(ALPHABET, EnigmaUtils.toCharacterArray("NZJHGRCXMYSWBOUFAIVLPEKQDT"), ALPHABET,
				EnigmaUtils.toCharacterArray("ZM"));
	}

	public static Rotor<Character> rotorVIII() {
		return new RotorImpl<>(ALPHABET, EnigmaUtils.toCharacterArray("FKQHTLXOCBJSPDZRAMEWNIUYGV"), ALPHABET,
				EnigmaUtils.toCharacterArray("ZM"));
	}
}
