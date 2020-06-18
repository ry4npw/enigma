package pw.ry4n.enigma;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.Validate;

/**
 * Implementation of a reflector. Because of physical properties, a reflector is
 * wired in pairs, that is if "A" is wired to "D", then activating either
 * contact will activate the paired contact. In our implementation this means
 * "A" must encode to "D" and "D" must encode back to "A". This is enforced by
 * the {@link #assertWiringIsReflexive()} method called in the constructor.
 * 
 * @author Ryan Powell
 *
 * @param <T> The class of the Rotor mappings. For the purpose of Enigma, this
 *            will most likely be {@link Character}'s.
 */
public class ReflectorImpl<T> implements Reflector<T> {
	protected HashMap<T, T> wiring;
	protected List<T> positions;

	/**
	 * Offset is the rotation offset of the entire rotor assembly.
	 */
	protected int offset = 0;

	/**
	 * Parameterized constructor.
	 * 
	 * @param inputs    The array of input contacts on the reflector, in order
	 * @param outputs   The array of output contacts on the reflector. These outputs
	 *                  must be reflexive to the inputs. See {@link ReflectorImpl}.
	 * @param positions the array of positions marked on the reflector
	 */
	public ReflectorImpl(T[] inputs, T[] outputs, T[] positions) {
		Validate.notEmpty(inputs);
		Validate.notEmpty(outputs);
		Validate.isTrue(inputs.length == outputs.length);
		Validate.notEmpty(positions);
		Validate.isTrue(inputs.length == positions.length);

		this.positions = Arrays.asList(positions);

		wiring = new HashMap<>(inputs.length);

		for (int i = 0; i < inputs.length; i++) {
			Validate.notNull(inputs[i]);
			Validate.notNull(outputs[i]);
			wiring.put(inputs[i], outputs[i]);
		}

		assertWiringIsReflexive();
	}

	private void assertWiringIsReflexive() {
		for (T item : wiring.keySet()) {
			if (!item.equals(wiring.get(wiring.get(item)))) {
				throw new IllegalArgumentException("The reflector was not reflexive! '" + item + "' maps to '"
						+ wiring.get(wiring.get(item)) + "'.");
			}
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
