# Java implementation of an Enigma machine

## Summary

This project was an experiment to emulate the hardware of an Enigma machine.  Essentially, it is a state machine that holds the state of rotors, a reflector, and a plug board.  The implementation includes a [`RotorWiring`](src/main/java/pw/ry4n/enigma/util/RotorWiring.java) class with default implementations of the standard German Enigma rotors I through VIII and the wide B reflector.

This java implementation is not optimized!  Due primarily to autoboxing, the performance is rather "poor".  It is, however, an accurate emulation of a physical Enigma machine.  The choice to base implementation on a class and not a primitive was done to support extension to other Java objects, see [below](#using-enigma-to-encrypt-base64).

## Java Dependencies

This project was designed to run on Java 8 or higher.  It has a dependency on the following classes/packages as defined in the [pom.xml](pom.xml).

* Google Guava's `BiMap` and `ObjectArrays` are used by the `PlugBoardImpl` and `ReflectorImpl` classes.
* Apache Commons `Validate` is used by the `Enigma` and `*Impl` classes.
* JUnit and AssertJ are used for unit tests.

## Usage

The following Java code will create an example Enigma machine and configure its state, allowing you to call the `.keyPress()` or `.encrypt()` methods.

```java
Enigma<Character> enigma = new Enigma<>();
enigma.addRotor(RotorWiring.rotorIII()); // ring position defaults to A
enigma.addRotor(RotorWiring.rotorII());
enigma.addRotor(RotorWiring.rotorI());
enigma.setReflector(RotorWiring.reflectorWideB()); // reflector position defaults to A

// single keypress
System.out.println(enigma.keyPress('A'));

// multiple keypresses
System.out.println(enigma.encrypt(EnigmaUtils.toCharacterArray("HELLOWORLD")));
```

## Using enigma to encrypt Base64

It is possible to create your own reflector and rotor wiring with any set of Java objects.  As an example, I created a String-based rotors and reflector based on the Base64 character set.  See [`RotorWiringBase64.java`](src/main/java/pw/ry4n/enigma/util/RotorWiringBase64.java)].

```java
Enigma<String> enigma = new Enigma<>();
enigma.addRotor(RotorWiringBase64.rotorI());
enigma.addRotor(RotorWiringBase64.rotorII());
enigma.addRotor(RotorWiringBase64.rotorIII());
enigma.setReflector(RotorWiringBase64.reflector());

String[] encrypted = enigma.encrypt("SGVsbG8sIFdvcmxkIQ==".split(""));
// will output "i5wlS6iWtFRkCt54MsJc"
System.out.println("Encrypted Base64 string: " + String.join("", encrypted));
```

