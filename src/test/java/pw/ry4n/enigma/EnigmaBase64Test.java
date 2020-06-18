package pw.ry4n.enigma;

import static org.assertj.core.api.Assertions.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import pw.ry4n.enigma.util.RotorWiringBase64;

public class EnigmaBase64Test {
	@Test
	public void testBase64ReflectorIsReflexive() {
		Reflector<String> reflector = RotorWiringBase64.reflector();

		for (String s : RotorWiringBase64.ALPHABET) {
			assertThat(reflector.encode(reflector.encode(s))).isEqualTo(s);
		}
	}

	@Test
	public void testBase64Enigma() throws UnsupportedEncodingException {
		// create two machines with the same initial settings
		Enigma<String> encryptingMachine = new Enigma<>();
		encryptingMachine.addRotor(RotorWiringBase64.rotorI());
		encryptingMachine.addRotor(RotorWiringBase64.rotorII());
		encryptingMachine.addRotor(RotorWiringBase64.rotorIII());
		encryptingMachine.setReflector(RotorWiringBase64.reflector());

		Enigma<String> decryptingMachine = new Enigma<>();
		decryptingMachine.addRotor(RotorWiringBase64.rotorI());
		decryptingMachine.addRotor(RotorWiringBase64.rotorII());
		decryptingMachine.addRotor(RotorWiringBase64.rotorIII());
		decryptingMachine.setReflector(RotorWiringBase64.reflector());

		// "SGVsbG8sIFdvcmxkIQ==" is the Base64 representation of "Hello, World!"
		String[] input = "SGVsbG8sIFdvcmxkIQ==".split("");

		// encrypt with the first machine
		String[] encrypted = encryptingMachine.encrypt(input);

		// decrypt with the second machine
		String[] decrypted = decryptingMachine.encrypt(encrypted);

		// validate the decrypted output is identical to the input
		assertThat(decrypted).isEqualTo(input);
	}
}
