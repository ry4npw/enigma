package pw.ry4n.enigma;

public interface Reflector<T> {
	/**
	 * @param value
	 * @return the mapped/paired object.
	 */
	T encode(T value);
}
