package pw.ry4n.enigma;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;

/**
 * Implementation of the {@link Rotor} interface. Due to autoboxing, performance
 * of this implementation may be "poor".
 * 
 * @author Ryan Powell
 * 
 * @param <T> The class of the Rotor mappings. For the purpose of Enigma, this
 *            will most likely be {@link Character}'s.
 */
public class RotorImpl<T> extends ReflectorImpl<T> implements Rotor<T> {
	protected List<T> inputs;
	protected List<T> outputs;
	protected Set<T> notches = new HashSet<>();

	/**
	 * Ringstellung (or ring setting) is the offset of the wiring relative to the
	 * positions.
	 */
	protected int ringstellung = 0;

	/**
	 * Parameterized constructor.
	 * 
	 * @param inputs    the array of input contacts on the rotor, in order
	 * @param outputs   the array of output contacts on the rotor, ordered to map to
	 *                  the corresponding input. for example, if A mapped to F and A
	 *                  was the first element in {@code inputs}, the F should be the
	 *                  first element in {@code outputs}.
	 * @param positions the array of positions marked on the rotor
	 * @param notches   the array of positions at which the rotor is notched, and
	 *                  should turn the adjacent rotor
	 */
	public RotorImpl(T[] inputs, T[] outputs, T[] positions, T[] notches) {
		super(inputs, outputs, positions);

		this.inputs = Arrays.asList(inputs);
		this.outputs = Arrays.asList(outputs);

		for (int j = 0; j < notches.length; j++) {
			Validate.notNull(notches[j]);
			Validate.isTrue(this.positions.contains(notches[j]));
			this.notches.add(notches[j]);
		}
	}

	@Override
	public T encode(T value) {
		T result = unshift(wiring.get(shift(value)));
		// System.out.println(value + " encoded to " + result);
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
		int shiftedValue = (inputs.indexOf(value) + (offset - ringstellung)) % inputs.size();
		while (shiftedValue < 0) {
			shiftedValue += inputs.size();
		}
		return inputs.get(shiftedValue);
	}

	/**
	 * Performs the inverse of {@link #shift(T)}.
	 * 
	 * @param value
	 * @return the unshifted input value
	 */
	private T unshift(T value) {
		int unshiftedValue = (inputs.indexOf(value) - (offset - ringstellung)) % inputs.size();
		while (unshiftedValue < 0) {
			unshiftedValue += inputs.size();
		}
		return inputs.get(unshiftedValue);
	}

	@Override
	public T encodeInverse(T value) {
		T result = unshift(wiring.inverse().get(shift(value)));
		// System.out.println(value + " inverse encoded to " + result);
		return result;
	}

	@Override
	public Rotor<T> setRingstellung(T ringstellung) {
		this.ringstellung = positions.indexOf(ringstellung);
		return this;
	}

	@Override
	public Rotor<T> setPosition(T position) {
		this.offset = positions.indexOf(position) + ringstellung;
		return this;
	}

	@Override
	public T currentPosition() {
		return positions.get(offset);
	}

	@Override
	public void rotate() {
		offset = (offset + 1) % positions.size();
	}

	@Override
	public boolean atNotch() {
		return notches.contains(currentPosition());
	}
}
