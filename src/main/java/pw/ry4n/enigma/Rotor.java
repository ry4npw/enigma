package pw.ry4n.enigma;

public interface Rotor<T> {
	/**
	 * @param value
	 * @return the mapped/paired object.
	 */
	T encode(T value);

	/**
	 * @param value
	 * @return the inverse mapping (left to right).
	 */
	T encodeInverse(T value);

	/**
	 * Set the internal wiring of the rotor relative to the rotor.
	 * 
	 * @param ringstellung
	 * @return this {@link Rotor<T>} object for in-lining of this method
	 */
	Rotor<T> setRingstellung(T ringstellung);

	/**
	 * Set the current position of the ring.
	 * 
	 * @param position
	 * @return this {@link Reflector<T>} object for in-lining of this method
	 */
	Rotor<T> setPosition(T position);

	/**
	 * Advance the rotor one position.
	 */
	void rotate();

	/**
	 * @return the current position of the rotor visible to the operator.
	 */
	T currentPosition();

	/**
	 * This method must be called <em>AFTER</em> {@link #rotate()} has occurred to
	 * return an accurate result.
	 * 
	 * @return {@code true} when this rotor is in the notched position and the next
	 *         rotor in the sequence should be rotated.
	 */
	boolean atNotch();
}
