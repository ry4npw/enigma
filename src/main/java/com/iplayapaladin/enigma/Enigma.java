package com.iplayapaladin.enigma;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.ObjectArrays;

/**
 * This class is a representation of an Enigma machine. You must add
 * <em>at least</em> one {@link Rotor} and one {@link Reflector} before using
 * this class.
 * 
 * @author Ryan Powell
 * 
 * @param <T>
 */
public class Enigma<T> {
	protected List<Rotor<T>> rotorSequence = new ArrayList<>();
	protected Reflector<T> reflector = null;
	protected PlugBoard<T> plugboard = new PlugBoardImpl<>();

	/**
	 * Add a rotor to the machine. <strong>NOTE:</strong> Rotors are added
	 * right-to-left.
	 * 
	 * @param rotor
	 */
	public void addRotor(Rotor<T> rotor) {
		rotorSequence.add(rotor);
	}

	/**
	 * Add a cable connecting a pair of items to the plug board.
	 * 
	 * @param one
	 * @param two
	 */
	public void addPlugBoardPair(T one, T two) {
		plugboard.addPair(one, two);
	}

	/**
	 * Adds a reflector to the machine.
	 * 
	 * @param reflector
	 */
	public void setReflector(Reflector<T> reflector) {
		this.reflector = reflector;
	}

	/**
	 * Returns the lit lamp for each key press.
	 * 
	 * @param input
	 * @return the lamp lit after tracing the electrical path
	 */
	public T keyPress(T input) {
		Validate.notNull(reflector);

		step();

		T result = input;

		// pass through plug board
		result = plugboard.encode(result);

		// step through rotors right-to-left
		for (int i = 0; i < rotorSequence.size(); i++) {
			result = rotorSequence.get(i).encode(result);
		}

		// reflector
		result = reflector.encode(result);

		// step through rotors left-to-right
		for (int j = rotorSequence.size() - 1; j >= 0; j--) {
			result = rotorSequence.get(j).encodeInverse(result);
		}

		// pass back through plug board
		result = plugboard.encode(result);

		return result;
	}

	/**
	 * Performs encryption of a source array.
	 * 
	 * @param input
	 *            the source array
	 * @return an array of the same length, containing the encrypted source.
	 */
	public T[] encrypt(T[] input) {
		T[] encrypted = ObjectArrays.newArray(input, input.length);

		for (int i = 0; i < input.length; i++) {
			encrypted[i] = keyPress(input[i]);
		}

		return encrypted;
	}

	/**
	 * <p>
	 * Performs the stepping of the rotors before each key press.
	 * </p>
	 */
	protected void step() {
		recursiveRotate(0, true);
	}

	/**
	 * <p>
	 * Performs the action of one of three pawls (levers). The pawl will advance
	 * the next rotor when aligned with the notch of previous rotor.
	 * </p>
	 * <p>
	 * The design also included a feature known as double-stepping. When the
	 * third rotor is moved into its notched position, it will engage the both
	 * the second and third rotors on the next movement. For an in-depth
	 * explanation of double-stepping:
	 * <a>http://home.comcast.net/~dhhamer/downloads/rotors1.pdf</a>
	 * </p>
	 * <p>
	 * Since there were only three pawls, the fourth rotor never stepped. The
	 * position of the fourth rotor can be manually set by the operator.
	 * </p>
	 * 
	 * @param pawl
	 * @param notchEngaged
	 */
	protected void recursiveRotate(int pawl, boolean notchEngaged) {
		if (pawl < 0 || pawl > 2) {
			// out of bounds
			return;
		}

		Rotor<T> rotor = rotorSequence.get(pawl);

		boolean atNotch = rotor.atNotch();
		boolean doRotate = (pawl < 2 && atNotch) || notchEngaged;

		if (doRotate) {
			rotor.rotate();
		}

		recursiveRotate(pawl + 1, atNotch);
	}
}
