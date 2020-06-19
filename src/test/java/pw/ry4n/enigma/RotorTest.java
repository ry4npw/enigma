package pw.ry4n.enigma;

import static org.assertj.core.api.Assertions.assertThat;

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
	public void testSetPositionIsEqualToRotate() {
		Rotor<Character> positionedRotor = RotorWiring.rotorI();  // starts at A
		Rotor<Character> rotatedRotor = RotorWiring.rotorI(); // starts at A

		for (int i = 0; i < RotorWiring.ALPHABET.length; i++) {
			positionedRotor.setPosition(RotorWiring.ALPHABET[i]); // set to position A, B, C...

			// they should be the same
			assertThat(positionedRotor.currentPosition()).isEqualTo(rotatedRotor.currentPosition());

			rotatedRotor.rotate(); // now rotate to next position
		}
	}

	@Test
	public void testRingstellung() {
		Rotor<Character> rotorI = RotorWiring.rotorI();

		// with a ringstellung of B, pressing A will be like B was pressed.
		rotorI.setRingstellung('B');
		assertThat(rotorI.encode('A')).isEqualTo('K');
		assertThat(rotorI.encodeInverse('K')).isEqualTo('A');
	}

	@Test
	public void testPositionAndRingstellungOffset() {
		Rotor<Character> rotorI = RotorWiring.rotorI();

		rotorI.setPosition('D');
		rotorI.setRingstellung('D');
		assertThat(rotorI.encode('A')).isEqualTo('E');
		assertThat(rotorI.encodeInverse('E')).isEqualTo('A');
	}
}
