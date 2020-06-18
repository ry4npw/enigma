package pw.ry4n.enigma;

public interface PlugBoard<T> {
	/**
	 * Connects both items in the Plug Board with a cable.
	 * 
	 * @param first
	 * @param second
	 */
	void addPair(T first, T second);

	/**
	 * Performs the PlugBoard encoding of the input.
	 * 
	 * @param input
	 * @return the paired item, or the {@code input} if not in a pair.
	 */
	T encode(T input);
}
