package com.iplayapaladin.enigma.util;

public class EnigmaUtils {
	/**
	 * Private constructor to prevent instantiation of this class.
	 */
	private EnigmaUtils() {
		// empty method
	}

	/**
	 * Converts a String into an array of {@code Character} objects.
	 * 
	 * @param s
	 * @return an array of {@code Character} objects
	 */
	public static Character[] toCharacterArray(String s) {
		if (s == null) {
			return null;
		}

		Character[] array = new Character[s.length()];

		for (int i = 0; i < s.length(); i++) {
			array[i] = new Character(s.charAt(i));
		}

		return array;
	}
}
