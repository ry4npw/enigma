package pw.ry4n.enigma;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import pw.ry4n.enigma.util.RotorWiring;

public class ReflectorTest {
	@Test
	public void testReflectorsAreReflexive() {
		RotorWiring.reflectorA();
		RotorWiring.reflectorB();
		RotorWiring.reflectorC();
		RotorWiring.reflectorThinB();
		RotorWiring.reflectorThinC();
	}

	@Test
	public void testReflectorPosition() {
		Reflector<Character> reflectorB = RotorWiring.reflectorB();

		reflectorB.setPosition('A');
		assertThat(reflectorB.encode('A')).isEqualTo('Y');

		reflectorB.setPosition('B');
		assertThat(reflectorB.encode('A')).isEqualTo('R');

		reflectorB.setPosition('C');
		assertThat(reflectorB.encode('A')).isEqualTo('U');

		reflectorB.setPosition('D');
		assertThat(reflectorB.encode('A')).isEqualTo('H');

		reflectorB.setPosition('Y');
		assertThat(reflectorB.encode('A')).isEqualTo('A');

		reflectorB.setPosition('Z');
		assertThat(reflectorB.encode('A')).isEqualTo('T');

		
	}
}
