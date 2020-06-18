package pw.ry4n.enigma;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import pw.ry4n.enigma.util.RotorWiring;

public class RotorTest {
	@Test
	public void testRotorIAEncodesToE() {
		Rotor<Character> rotorI = RotorWiring.rotorI();
		assertThat(rotorI.encode('A')).isEqualTo('E');
		assertThat(rotorI.encodeInverse('E')).isEqualTo('A');
	}

	@Test
	public void testRotorIEEncodesToL() {
		Rotor<Character> rotorI = RotorWiring.rotorI();
		assertThat(rotorI.encode('E')).isEqualTo('L');
		assertThat(rotorI.encodeInverse('L')).isEqualTo('E');
	}

	@Test
	public void testRotorIAEncodesToJAfterFirstRotation() {
		Rotor<Character> rotorI = RotorWiring.rotorI();
		rotorI.rotate();
		assertThat(rotorI.encode('A')).isEqualTo('J');
		assertThat(rotorI.encodeInverse('J')).isEqualTo('A');
	}

	@Test
	public void testRotorIAEncodesToKAfterSecondRotation() {
		Rotor<Character> rotorI = RotorWiring.rotorI();
		rotorI.rotate();
		rotorI.rotate();
		assertThat(rotorI.encode('A')).isEqualTo('K');
		assertThat(rotorI.encodeInverse('K')).isEqualTo('A');
	}

	@Test
	public void testRotorIAEncodesToCAfterThirdRotation() {
		Rotor<Character> rotorI = RotorWiring.rotorI();
		rotorI.rotate();
		rotorI.rotate();
		rotorI.rotate();
		assertThat(rotorI.encode('A')).isEqualTo('C');
		assertThat(rotorI.encodeInverse('C')).isEqualTo('A');
	}

	@Test
	public void testAtNotch() {
		Rotor<Character> rotorI = RotorWiring.rotorI();
		rotorI.setPosition('Q');
		assertThat(rotorI.atNotch()).isTrue();
	}

	@Test
	public void testNotAtNotch() {
		Rotor<Character> rotorI = RotorWiring.rotorI();
		rotorI.rotate();
		assertThat(rotorI.atNotch()).isFalse();
	}

	@Test
	public void testRingstellung() {
		Rotor<Character> rotorI = RotorWiring.rotorI();
		rotorI.setRingstellung('B');
		assertThat(rotorI.encode('A')).isEqualTo('K');
		assertThat(rotorI.encodeInverse('K')).isEqualTo('A');
	}
}
