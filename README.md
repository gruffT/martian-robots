# Martian Robots
## A coding exercise by Gareth Tomlinson. 25 March 2024
![Static Badge](https://img.shields.io/badge/Project-Martian_Robots-pink) ![Static Badge](https://img.shields.io/badge/Kotlin-1.9.20-purple)  ![Static Badge](https://img.shields.io/badge/JDK-21-blue) ![Static Badge](https://img.shields.io/badge/Java-19-yellow) ![Static Badge](https://img.shields.io/badge/TDD-Absolutely!-green)

A program to send Robots to Mars.

Author: [Gareth Tomlinson](mailto:garetht@garethtomlinson.co.uk), garetht@garethtomlinson.co.uk, +44 7957 363 603, [LinkedIn](https://www.linkedin.com/in/gareth-tomlinson/)

---

## Getting Started
TODO

## Contributing
### Pre-commit hook
As there is no CI for this project, we have configured a pre-commit hook to lint and test before each commit.

You can install this with
```shell
./gradlew addPrecommitHook
```

If you find this slows down your commit cadence, you can remove it with
```shell
./gradlew removePrecommitHook
```

### Running the test suite
Running the test suite exercises all of the code. Inspecting the test names should give you a good idea of the functionality of the program.
```shell
./gradlew test
```

### Linting
This project has linting enabled using [ktlint-gradle](https://github.com/JLLeitschuh/ktlint-gradle).
You may wish to install the [IntelliJ Plugin](https://plugins.jetbrains.com/plugin/15057-ktlint) and use the `format on save` option for simple developer experience. The IDE config files have not been committed as it was felt this may be intrusive for the reviewer.

Manual lint checks can be executed with
```shell
./gradlew ktlintCheck
```

Manual formatting can be executed with
```shell
./gradlew ktlintFormat
```

---

