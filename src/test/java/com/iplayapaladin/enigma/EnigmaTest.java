package com.iplayapaladin.enigma;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.iplayapaladin.enigma.util.RotorWiring;

public class EnigmaTest {
	private Enigma<Character> machine;

	@Before
	public void setUp() {
		machine = new Enigma<>();
	}

	@Test
	public void testThreeRotorStep() {
		machine.addRotor(RotorWiring.rotorIII().setPosition('U'));
		machine.addRotor(RotorWiring.rotorII().setPosition('A'));
		machine.addRotor(RotorWiring.rotorI().setPosition('A'));

		assertEquals("AAU", getPositions());

		machine.step();
		assertEquals("AAV", getPositions());

		machine.step();
		assertEquals("ABW", getPositions());

		machine.step();
		assertEquals("ABX", getPositions());
	}

	@Test
	public void testThreeRotorDoubleStep() {
		machine.addRotor(RotorWiring.rotorIII().setPosition('U'));
		machine.addRotor(RotorWiring.rotorII().setPosition('D'));
		machine.addRotor(RotorWiring.rotorI().setPosition('A'));

		assertEquals("ADU", getPositions());

		machine.step();
		assertEquals("ADV", getPositions());

		machine.step();
		assertEquals("AEW", getPositions());

		machine.step();
		assertEquals("BFX", getPositions());

		machine.step();
		assertEquals("BFY", getPositions());
	}

	@Test
	public void testFourRotorDoubleStep() {
		machine.addRotor(RotorWiring.rotorIII().setPosition('U'));
		machine.addRotor(RotorWiring.rotorII().setPosition('D'));
		machine.addRotor(RotorWiring.rotorI().setPosition('U'));
		machine.addRotor(RotorWiring.rotorVIII().setPosition('A'));

		assertEquals("AUDU", getPositions());

		machine.step();
		assertEquals("AUDV", getPositions());

		machine.step();
		assertEquals("AUEW", getPositions());

		machine.step();
		assertEquals("AVFX", getPositions());

		machine.step();
		assertEquals("AVFY", getPositions());
	}

	protected String getPositions() {
		StringBuilder sb = new StringBuilder();
		for (Rotor<Character> rotor : machine.rotorSequence) {
			sb.append(rotor.currentPosition());
		}
		return StringUtils.reverse(sb.toString());
	}

	/**
	 * With the rotors I, II and III (from left to right), wide B-reflector, all
	 * ring settings in A-position, and start position AAA, typing AAAAA will
	 * produce the encoded sequence BDZGO.
	 */
	@Test
	public void testKeyPress() {
		machine.addRotor(RotorWiring.rotorIII().setPosition('A'));
		machine.addRotor(RotorWiring.rotorII().setPosition('A'));
		machine.addRotor(RotorWiring.rotorI().setPosition('A'));
		machine.setReflector(RotorWiring.reflectorWideB());

		assertEquals('B', machine.keyPress('A').charValue());
		assertEquals('D', machine.keyPress('A').charValue());
		assertEquals('Z', machine.keyPress('A').charValue());
		assertEquals('G', machine.keyPress('A').charValue());
		assertEquals('O', machine.keyPress('A').charValue());
	}

	/**
	 * With the rotors I, II, III (from left to right), wide B-reflector, all
	 * ring settings in B-position, and start position AAA, typing AAAAA will
	 * produce the encoded sequence EWTYX.
	 */
	@Test
	public void testKeyPressWithRingstellung() {
		machine.addRotor(RotorWiring.rotorIII().setPosition('A')
				.setRingstellung('B'));
		machine.addRotor(RotorWiring.rotorII().setPosition('A')
				.setRingstellung('B'));
		machine.addRotor(RotorWiring.rotorI().setPosition('A')
				.setRingstellung('B'));
		machine.setReflector(RotorWiring.reflectorWideB());

		assertEquals('E', machine.keyPress('A').charValue());
		assertEquals('W', machine.keyPress('A').charValue());
		assertEquals('T', machine.keyPress('A').charValue());
		assertEquals('Y', machine.keyPress('A').charValue());
		assertEquals('X', machine.keyPress('A').charValue());
	}

	/**
	 * Using rotors I, II and III as before, place the ring settings at 10, 14
	 * and 21. Then turn the rotors to set the message key to X Y Z (the letters
	 * showing on the rings, not the letters underneath them) and decipher the
	 * following message:
	 * 
	 * Q K T P E B Z I U K
	 */
	@Test
	public void testEncrypt() {
		machine.addRotor(RotorWiring.rotorIII().setPosition('Z')
				.setRingstellung('U'));
		machine.addRotor(RotorWiring.rotorII().setPosition('Y')
				.setRingstellung('N'));
		machine.addRotor(RotorWiring.rotorI().setPosition('X')
				.setRingstellung('J'));
		machine.setReflector(RotorWiring.reflectorWideB());

		assertEquals("GOODRESULT", encrypt("QKTPEBZIUK"));
	}

	protected String encrypt(String input) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < input.length(); i++) {
			sb.append(machine.keyPress(input.charAt(i)));
			System.out.println(getPositions());
		}

		return sb.toString();
	}

	/**
	 * To try this out, write in the plug board settings for these 10 pairs:
	 * 
	 * AP BR CM FZ GJ IL NT OV QS WX
	 * 
	 * Using rotors I, II and III with ring settings 10, 14 and 21 from our
	 * previous example and message key V Q Q decipher the following:
	 * 
	 * H A B H V H L Y D F N A D Z Y
	 * 
	 * Your result should be four English words and your rotors should finish at
	 * positions V R F.
	 */
	@Test
	public void testPlugBoard() {
		machine.addRotor(RotorWiring.rotorIII().setPosition('Q')
				.setRingstellung('U'));
		machine.addRotor(RotorWiring.rotorII().setPosition('Q')
				.setRingstellung('N'));
		machine.addRotor(RotorWiring.rotorI().setPosition('V')
				.setRingstellung('J'));
		machine.setReflector(RotorWiring.reflectorWideB());

		System.out.println(encrypt("HABHVHLYDFNADZY"));
		assertEquals("VRF", getPositions());
	}
}
