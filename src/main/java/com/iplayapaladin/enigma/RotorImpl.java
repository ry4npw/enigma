package com.iplayapaladin.enigma;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Implementation of the {@link Rotor} interface. Due to autoboxing, performance
 * of this implementation may be "poor".
 * 
 * @author Ryan Powell
 * 
 * @param <T>
 *            The class of the Rotor mappings. For the purpose of Enigma, this
 *            will most likely be {@link Character}'s.
 */
public class RotorImpl<T> implements Rotor<T> {
	protected BiMap<T, T> wiring;
	protected List<T> inputs;
	protected List<T> outputs;
	protected Set<T> notches = new HashSet<>();
	protected int offset = 0;
	protected int ringstellung = 0;

	/**
	 * Parameterized constructor
	 * 
	 * @param inputs
	 * @param outputs
	 * @param notches
	 */
	public RotorImpl(T[] inputs, T[] outputs, T[] notches) {
		Validate.notNull(inputs);
		Validate.notNull(outputs);
		Validate.notEmpty(inputs);
		Validate.isTrue(inputs.length == outputs.length);

		this.inputs = Arrays.asList(inputs);
		this.outputs = Arrays.asList(outputs);

		wiring = HashBiMap.create(inputs.length);

		for (int i = 0; i < inputs.length; i++) {
			wiring.put(inputs[i], outputs[i]);
		}

		for (int j = 0; j < notches.length; j++) {
			this.notches.add(notches[j]);
		}
	}

	@Override
	public T encode(T value) {
		T result = unshift(wiring.get(shift(value)));
		// System.out.println(value + " encoded to " + result);
		return result;
	}

	private T shift(T value) {
		int shiftedValue = (inputs.indexOf(value) + (offset - ringstellung))
				% inputs.size();
		while (shiftedValue < 0) {
			shiftedValue += inputs.size();
		}
		return inputs.get(shiftedValue);
	}

	private T unshift(T value) {
		int unshiftedValue = (inputs.indexOf(value) - (offset - ringstellung))
				% inputs.size();
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
		// TODO inputs = positions? positions should be separate from inputs
		this.ringstellung = inputs.indexOf(ringstellung);
		return this;
	}

	@Override
	public Rotor<T> setPosition(T position) {
		// TODO inputs = positions
		this.offset = inputs.indexOf(position) + ringstellung;
		return this;
	}

	@Override
	public T currentPosition() {
		// TODO inputs = positions
		return inputs.get(offset);
	}

	@Override
	public void rotate() {
		// TODO inputs = positions
		offset = (offset + 1) % inputs.size();
	}

	@Override
	public boolean atNotch() {
		return notches.contains(currentPosition());
	}

	public int getOffset() {
		return offset;
	}
}
