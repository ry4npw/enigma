package pw.ry4n.enigma.util;

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
	public static Character[] stringToCharacterArray(String s) {
		if (s == null) {
			return null;
		}

		Character[] array = new Character[s.length()];

		for (int i = 0; i < s.length(); i++) {
			array[i] = s.charAt(i);
		}

		return array;
	}

	/**
	 * Converts a String into an array of {@code String} objects, where each object
	 * in the array is a single letter.
	 * 
	 * @param s
	 * @return an array of {@code String} letters from the input string
	 */
	public static String[] stringToStringArray(String s) {
		return s.split("");
	}
}
