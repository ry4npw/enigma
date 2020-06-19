package pw.ry4n.enigma;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import pw.ry4n.enigma.util.EnigmaUtils;
import pw.ry4n.enigma.util.RotorWiring;

public class EnigmaCharacterTest {
	@Test
	public void testReflectorsAreReflexive() {
		RotorWiring.reflectorA();
		RotorWiring.reflectorB();
		RotorWiring.reflectorC();
		RotorWiring.reflectorThinB();
		RotorWiring.reflectorThinC();
	}

	@Test
	public void testThreeRotorStep() {
		Enigma<Character> machine = new Enigma<>();
		machine.addRotor(RotorWiring.rotorIII().setPosition('U'));
		machine.addRotor(RotorWiring.rotorII().setPosition('A'));
		machine.addRotor(RotorWiring.rotorI().setPosition('A'));

		assertThat(getPositions(machine)).isEqualTo("AAU");

		machine.step();
		assertThat(getPositions(machine)).isEqualTo("AAV");

		machine.step();
		assertThat(getPositions(machine)).isEqualTo("ABW");

		machine.step();
		assertThat(getPositions(machine)).isEqualTo("ABX");
	}

	@Test
	public void testThreeRotorDoubleStep() {
		Enigma<Character> machine = new Enigma<>();
		machine.addRotor(RotorWiring.rotorIII().setPosition('U'));
		machine.addRotor(RotorWiring.rotorII().setPosition('D'));
		machine.addRotor(RotorWiring.rotorI().setPosition('A'));

		assertThat(getPositions(machine)).isEqualTo("ADU");

		machine.step();
		assertThat(getPositions(machine)).isEqualTo("ADV");

		machine.step();
		assertThat(getPositions(machine)).isEqualTo("AEW");

		machine.step();
		assertThat(getPositions(machine)).isEqualTo("BFX");

		machine.step();
		assertThat(getPositions(machine)).isEqualTo("BFY");
	}

	@Test
	public void testFourRotorDoubleStep() {
		Enigma<Character> machine = new Enigma<>();
		machine.addRotor(RotorWiring.rotorIII().setPosition('U'));
		machine.addRotor(RotorWiring.rotorII().setPosition('D'));
		machine.addRotor(RotorWiring.rotorI().setPosition('U'));
		machine.addRotor(RotorWiring.rotorVIII().setPosition('A'));

		assertThat(getPositions(machine)).isEqualTo("AUDU");

		machine.step();
		assertThat(getPositions(machine)).isEqualTo("AUDV");

		machine.step();
		assertThat(getPositions(machine)).isEqualTo("AUEW");

		machine.step();
		assertThat(getPositions(machine)).isEqualTo("AVFX");

		machine.step();
		assertThat(getPositions(machine)).isEqualTo("AVFY");
	}

	protected String getPositions(Enigma<Character> machine) {
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
		Enigma<Character> machine = new Enigma<>();
		machine.addRotor(RotorWiring.rotorIII().setPosition('A'));
		machine.addRotor(RotorWiring.rotorII().setPosition('A'));
		machine.addRotor(RotorWiring.rotorI().setPosition('A'));
		machine.setReflector(RotorWiring.reflectorB());

		assertThat(machine.keyPress('A')).isEqualTo('B');
		assertThat(machine.keyPress('A')).isEqualTo('D');
		assertThat(machine.keyPress('A')).isEqualTo('Z');
		assertThat(machine.keyPress('A')).isEqualTo('G');
		assertThat(machine.keyPress('A')).isEqualTo('O');
	}

	/**
	 * With the rotors I, II, III (from left to right), wide B-reflector, all ring
	 * settings in B-position, and start position AAA, typing AAAAA will produce the
	 * encoded sequence EWTYX.
	 */
	@Test
	public void testKeyPressWithRingstellung() {
		Enigma<Character> machine = new Enigma<>();
		machine.addRotor(RotorWiring.rotorIII().setPosition('A').setRingstellung('B'));
		machine.addRotor(RotorWiring.rotorII().setPosition('A').setRingstellung('B'));
		machine.addRotor(RotorWiring.rotorI().setPosition('A').setRingstellung('B'));
		machine.setReflector(RotorWiring.reflectorB());

		assertThat(machine.keyPress('A')).isEqualTo('E');
		assertThat(machine.keyPress('A')).isEqualTo('W');
		assertThat(machine.keyPress('A')).isEqualTo('T');
		assertThat(machine.keyPress('A')).isEqualTo('Y');
		assertThat(machine.keyPress('A')).isEqualTo('X');
	}

	/**
	 * Using rotors I, II and III as before, place the ring settings at 10 ('J'), 14
	 * ('N') and 21 ('U'). Then turn the rotors to set the message key to X Y Z (the
	 * letters showing on the rings, not the letters underneath them) and decipher
	 * the following message:
	 * 
	 * Q K T P E B Z I U K
	 */
	@Test
	public void testEncrypt() {
		Enigma<Character> machine = new Enigma<>();
		machine.addRotor(RotorWiring.rotorIII().setPosition('Z').setRingstellung('U'));
		machine.addRotor(RotorWiring.rotorII().setPosition('Y').setRingstellung('N'));
		machine.addRotor(RotorWiring.rotorI().setPosition('X').setRingstellung('J'));
		machine.setReflector(RotorWiring.reflectorB());

		Character[] input = EnigmaUtils.stringToCharacterArray("QKTPEBZIUK");
		Character[] expected = EnigmaUtils.stringToCharacterArray("GOODRESULT");
		assertThat(machine.encrypt(input)).isEqualTo(expected);
	}

	/**
	 * Add plug board settings for these 10 pairs:
	 * 
	 * AP BR CM FZ GJ IL NT OV QS WX
	 * 
	 * Using rotors I, II and III with ring settings 10, 14 and 21 and message key V
	 * Q Q to decipher the following:
	 * 
	 * H A B H V H L Y D F N A D Z Y
	 * 
	 * Your result should be four English words and your rotors should finish at
	 * positions V R F.
	 */
	@Test
	public void testPlugBoard() {
		Enigma<Character> machine = new Enigma<>();
		machine.addRotor(RotorWiring.rotorIII().setPosition('Q').setRingstellung('U'));
		machine.addRotor(RotorWiring.rotorII().setPosition('Q').setRingstellung('N'));
		machine.addRotor(RotorWiring.rotorI().setPosition('V').setRingstellung('J'));
		machine.setReflector(RotorWiring.reflectorB());
		machine.addPlugBoardPair('A', 'P');
		machine.addPlugBoardPair('B', 'R');
		machine.addPlugBoardPair('C', 'M');
		machine.addPlugBoardPair('F', 'Z');
		machine.addPlugBoardPair('G', 'J');
		machine.addPlugBoardPair('I', 'L');
		machine.addPlugBoardPair('N', 'T');
		machine.addPlugBoardPair('O', 'V');
		machine.addPlugBoardPair('Q', 'S');
		machine.addPlugBoardPair('W', 'X');

		Character[] input = EnigmaUtils.stringToCharacterArray("HABHVHLYDFNADZY");
		Character[] expected = EnigmaUtils.stringToCharacterArray("THATSITWELLDONE");
		assertThat(machine.encrypt(input)).isEqualTo(expected);
		assertThat(getPositions(machine)).isEqualTo("VRF");
	}
}
