package pw.ry4n.enigma;

public interface Reflector<T> {
	/**
	 * @param value
	 * @return the mapped/paired object.
	 */
	T encode(T value);

	/**
	 * Set the current position of the ring.  This is not used for most Enigma
	 * implementations, but is possible with the Enigma G model.
	 * 
	 * @param position
	 * @return this {@link Reflector<T>} object for in-lining of this method
	 */
	Reflector<T> setPosition(T position);
}
