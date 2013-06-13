package com.iplayapaladin.enigma;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;

public class ReflectorImpl<T> implements Reflector<T> {
	protected Map<T, T> wiring = new HashMap<>();

	public ReflectorImpl(T[] inputs, T[] outputs) {
		Validate.notNull(inputs);
		Validate.notNull(outputs);
		Validate.notEmpty(inputs);
		Validate.isTrue(inputs.length == outputs.length);

		for (int i = 0; i < inputs.length; i++) {
			wiring.put(inputs[i], outputs[i]);
		}
	}

	public T encode(T value) {
		T result = wiring.get(value);
		//System.out.println(value + " reflected to " + result);
		return result;
	}

	@Override
	public Reflector<T> setPosition(T position) {
		// TODO Auto-generated method stub
		return null;
	}
}
