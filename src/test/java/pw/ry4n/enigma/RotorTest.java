package pw.ry4n.enigma;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pw.ry4n.enigma.util.RotorWiring;

public class RotorTest {
	private RotorImpl<Character> rotorI;

	@Before
	public void setUp() {
		rotorI = (RotorImpl<Character>) RotorWiring.rotorI();
	}

	@Test
	public void testEncode() {
		assertEquals('E', rotorI.encode('A').charValue());
		assertEquals('A', rotorI.encodeInverse('E').charValue());
		assertEquals('L', rotorI.encode('E').charValue());
		assertEquals('E', rotorI.encodeInverse('L').charValue());
	}

	@Test
	public void testEncodeWithRotate() {
		rotorI.rotate();
		assertEquals('J', rotorI.encode('A').charValue());
		assertEquals('A', rotorI.encodeInverse('J').charValue());
		rotorI.rotate();
		assertEquals('K', rotorI.encode('A').charValue());
		assertEquals('A', rotorI.encodeInverse('K').charValue());
		rotorI.rotate();
		assertEquals('C', rotorI.encode('A').charValue());
		assertEquals('A', rotorI.encodeInverse('C').charValue());
	}

	@Test
	public void testAtNotch() {
		rotorI.setPosition('Q');
		assertTrue(rotorI.atNotch());
		rotorI.rotate();
		assertFalse(rotorI.atNotch());
	}

	@Test
	public void testRingstellung() {
		rotorI.setRingstellung('B');
		assertEquals('K', rotorI.encode('A').charValue());
		assertEquals('A', rotorI.encodeInverse('K').charValue());
	}
}
