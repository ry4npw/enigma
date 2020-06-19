package pw.ry4n.enigma;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;

/**
 * Implementation of a reflector. The reflector ensured that Enigma would be
 * self-reciprocal; thus, with two identically configured machines, a message
 * could be encrypted on one and decrypted on the other, without the need for a
 * bulky mechanism to switch between encryption and decryption modes.
 * 
 * <p>
 * Because of physical properties, a reflector is wired in pairs, that is if "A"
 * is wired to "D", then activating either contact will activate the paired
 * contact. In our implementation this means "A" must encode to "D" and "D" must
 * encode back to "A". This is enforced by the
 * {@link #assertWiringIsReflexive()} method called in the constructor.
 * </p>
 * 
 * @author Ryan Powell
 *
 * @param <T> The class of the Rotor mappings.
 */
public class ReflectorImpl<T> implements Reflector<T> {
	protected List<T> inputs;
	protected int offset = 0;
	protected List<T> positions;
	protected Map<T, T> wiring;

	/**
	 * Parameterized constructor.
	 * 
	 * @param inputs    The array of input contacts on the reflector, in order
	 * @param outputs   The array of output contacts on the reflector. Because the
	 *                  reflector is wired in pairs, the outputs must be reflexive
	 *                  to the inputs. See {@link ReflectorImpl}.
	 * @param positions the array of positions marked on the rotor
	 *
	 * @throws IllegalArgumentException when the inputs and outputs are not
	 *                                  reflexive.
	 */
	public ReflectorImpl(T[] inputs, T[] outputs, T[] positions) {
		Validate.notEmpty(inputs);
		Validate.notEmpty(outputs);
		Validate.isTrue(inputs.length == outputs.length);

		this.inputs = Arrays.asList(inputs);
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
		T result = wiring.get(shift(value));
		return result;
	}

	/**
	 * Shifts the input value based on the rotation of the rotor. To determine the
	 * position of the wiring, we need to "shift" the input by the offset and
	 * ringstellung.
	 * 
	 * @param value
	 * @return the shifted input value
	 */
	private T shift(T value) {
		int shiftedValue = (inputs.indexOf(value) + offset) % inputs.size();
		while (shiftedValue < 0) {
			shiftedValue += inputs.size();
		}
		return inputs.get(shiftedValue);
	}

	@Override
	public Reflector<T> setPosition(T position) {
		this.offset = positions.indexOf(position);
		return this;
	}
}
