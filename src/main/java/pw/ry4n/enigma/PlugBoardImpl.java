package pw.ry4n.enigma;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class PlugBoardImpl<T> implements PlugBoard<T> {
	protected BiMap<T, T> plugboard = HashBiMap.create(13);

	@Override
	public void addPair(T one, T two) {
		plugboard.put(one, two);
	}

	@Override
	public T encode(T input) {
		if (plugboard.containsKey(input)) {
			return plugboard.get(input);
		} else if (plugboard.containsValue(input)) {
			return plugboard.inverse().get(input);
		}
		return input;
	}
}
