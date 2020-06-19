package pw.ry4n.enigma;

import java.util.HashMap;
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
 * @param <T> The class of the Rotor mappings. For the purpose of Enigma, this
 *            will most likely be {@link Character}'s.
 */
public class ReflectorImpl<T> implements Reflector<T> {
	protected Map<T, T> wiring;

	/**
	 * Parameterized constructor.
	 * 
	 * @param inputs  The array of input contacts on the reflector, in order
	 * @param outputs The array of output contacts on the reflector. Because the
	 *                reflector is wired in pairs, the outputs must be reflexive to
	 *                the inputs. See {@link ReflectorImpl}.
	 *
	 * @throws IllegalArgumentException when the inputs and outputs are not
	 *                                  reflexive.
	 */
	public ReflectorImpl(T[] inputs, T[] outputs) {
		Validate.notEmpty(inputs);
		Validate.notEmpty(outputs);
		Validate.isTrue(inputs.length == outputs.length);

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
		return result;
	}
}
