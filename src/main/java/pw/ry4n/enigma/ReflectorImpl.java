package pw.ry4n.enigma;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ReflectorImpl<T> implements Reflector<T> {
	protected BiMap<T, T> wiring;
	protected List<T> positions;

	/**
	 * Offset is the rotation offset of the entire rotor assembly.
	 */
	protected int offset = 0;

	/**
	 * Parameterized constructor.
	 * 
	 * @param inputs    the array of input contacts on the reflector, in order
	 * @param outputs   the array of output contacts on the reflector, ordered to
	 *                  map to the corresponding input. for example, if A mapped to
	 *                  F and A was the first element in {@code inputs}, the F
	 *                  should be the first element in {@code outputs}.
	 * @param positions the array of positions marked on the reflector
	 */
	public ReflectorImpl(T[] inputs, T[] outputs, T[] positions) {
		Validate.notEmpty(inputs);
		Validate.notEmpty(outputs);
		Validate.isTrue(inputs.length == outputs.length);
		Validate.notEmpty(positions);
		Validate.isTrue(inputs.length == positions.length);

		this.positions = Arrays.asList(positions);

		wiring = HashBiMap.create(inputs.length);

		for (int i = 0; i < inputs.length; i++) {
			Validate.notNull(inputs[i]);
			Validate.notNull(outputs[i]);
			wiring.put(inputs[i], outputs[i]);
		}
	}

	@Override
	public T encode(T value) {
		T result = wiring.get(value);
		// System.out.println(value + " reflected to " + result);
		return result;
	}

	@Override
	public Reflector<T> setPosition(T position) {
		this.offset = positions.indexOf(position);
		return this;
	}
}
