# Java implementation of an Enigma machine

## Summary

This project was an experiment to emulate the hardware of an Enigma machine.  Essentially, it is a state machine that holds the state of rotors, a reflector, and a plug board.  The implementation includes a [`RotorWiring`](src/main/java/pw/ry4n/enigma/util/RotorWiring.java) class with default implementations of the standard German Enigma rotors I through VIII and the wide B reflector.

## Java Dependencies

* Google Guava's `BiMap` and `ObjectArrays` are used by the `PlugBoardImpl` and `ReflectorImpl` classes.
* Apache Commons `Validate` is used by the `Enigma` and `*Impl` classes.
* JUnit and AssertJ are used for unit tests.

## Usage

The following Java code will create an example Enigma machine and configure its state, allowing you to call `.keyPress()`.

```java
Enigma enigma = new Enigma<>();
enigma.addRotor(RotorWiring.rotorIII()); // ring position defaults to A
enigma.addRotor(RotorWiring.rotorII());
enigma.addRotor(RotorWiring.rotorI());
enigma.setReflector(RotorWiring.reflectorWideB()); // reflector position defaults to A

enigma.keyPress('A');
```

Note the default implementations using the `RotorWiring` is based on Character input.  It is possible to create your own rotor implementations that are arrays of other types, such as the Base64 character set, which would support encryption of Base64 strings.
