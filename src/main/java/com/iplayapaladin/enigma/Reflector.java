package com.iplayapaladin.enigma;

public interface Reflector<T> {
	/**
	 * @param value
	 * @return the mapped/paired object.
	 */
	T encode(T value);

	/**
	 * Set the current position of the ring.
	 * 
	 * @param position
	 */
	Reflector<T> setPosition(T position);
}
