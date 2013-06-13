package com.iplayapaladin.enigma.util;

import com.iplayapaladin.enigma.Reflector;
import com.iplayapaladin.enigma.ReflectorImpl;
import com.iplayapaladin.enigma.Rotor;
import com.iplayapaladin.enigma.RotorImpl;

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

	public static Reflector<Character> reflectorWideB() {
		return new ReflectorImpl<>(
				EnigmaUtils.toCharacterArray("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
				EnigmaUtils.toCharacterArray("YRUHQSLDPXNGOKMIEBFZCWVJAT"));
	}

	public static Rotor<Character> rotorI() {
		return new RotorImpl<>(
				EnigmaUtils.toCharacterArray("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
				EnigmaUtils.toCharacterArray("EKMFLGDQVZNTOWYHXUSPAIBRCJ"),
				EnigmaUtils.toCharacterArray("Q"));
	}

	public static Rotor<Character> rotorII() {
		return new RotorImpl<>(
				EnigmaUtils.toCharacterArray("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
				EnigmaUtils.toCharacterArray("AJDKSIRUXBLHWTMCQGZNPYFVOE"),
				EnigmaUtils.toCharacterArray("E"));
	}

	public static Rotor<Character> rotorIII() {
		return new RotorImpl<>(
				EnigmaUtils.toCharacterArray("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
				EnigmaUtils.toCharacterArray("BDFHJLCPRTXVZNYEIWGAKMUSQO"),
				EnigmaUtils.toCharacterArray("V"));
	}

	public static Rotor<Character> rotorIV() {
		return new RotorImpl<>(
				EnigmaUtils.toCharacterArray("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
				EnigmaUtils.toCharacterArray("ESOVPZJAYQUIRHXLNFTGKDCMWB"),
				EnigmaUtils.toCharacterArray("J"));
	}

	public static Rotor<Character> rotorV() {
		return new RotorImpl<>(
				EnigmaUtils.toCharacterArray("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
				EnigmaUtils.toCharacterArray("VZBRGITYUPSDNHLXAWMJQOFECK"),
				EnigmaUtils.toCharacterArray("Z"));
	}

	public static Rotor<Character> rotorVI() {
		return new RotorImpl<>(
				EnigmaUtils.toCharacterArray("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
				EnigmaUtils.toCharacterArray("JPGVOUMFYQBENHZRDKASXLICTW"),
				EnigmaUtils.toCharacterArray("ZM"));
	}

	public static Rotor<Character> rotorVII() {
		return new RotorImpl<>(
				EnigmaUtils.toCharacterArray("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
				EnigmaUtils.toCharacterArray("NZJHGRCXMYSWBOUFAIVLPEKQDT"),
				EnigmaUtils.toCharacterArray("ZM"));
	}

	public static Rotor<Character> rotorVIII() {
		return new RotorImpl<>(
				EnigmaUtils.toCharacterArray("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
				EnigmaUtils.toCharacterArray("FKQHTLXOCBJSPDZRAMEWNIUYGV"),
				EnigmaUtils.toCharacterArray("ZM"));
	}
}
