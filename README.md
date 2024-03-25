# Martian Robots
## A coding exercise by Gareth Tomlinson. 25 March 2024
![Static Badge](https://img.shields.io/badge/Project-Martian_Robots-pink) ![Static Badge](https://img.shields.io/badge/Kotlin-1.9.20-purple)  ![Static Badge](https://img.shields.io/badge/JDK-21-blue) ![Static Badge](https://img.shields.io/badge/Java-19-yellow) ![Static Badge](https://img.shields.io/badge/TDD-Absolutely!-green)

A program to send Robots to Mars.

Author: [Gareth Tomlinson](mailto:garetht@garethtomlinson.co.uk), garetht@garethtomlinson.co.uk, +44 7957 363 603, [LinkedIn](https://www.linkedin.com/in/gareth-tomlinson/)

---

## Getting Started
The main entry point for the program is the `Earth` class.  It has a static method `expedition` which sends a series of Robots to Mars.

The input for expedition is a `Mars` object, specifying the planet size and a list of `Mission`s which includes details of the Robots and their instructions.
Both can be parsed from a string input with the `mars` and `missions` static methods respectively.

```kotlin
val missionDetails = """
5 3             // Size of Mars expressed as the upper bound of x y coordinates. The lower bound is 0 0. In this example Mars is a 6 x 4 grid. 
1 1 E           // Landing location (x y) and initial orientation of the first Robot. 
RFRFRFRF        // Commands for the first Robot. L = turn 90d left. R = turn 90d right. F = move forward one grid square if possible.
                // A blank line denotes a new Robot being sent to Mars.
3 2 N
FRRFLLFFRRFLL

0 3 W
LLFFFLFLFL
"""

val mars = Earth.mars(missionDetails)
val missions = Earth.missions(missionDetails = missionDetails, mars=mars)

val log: Log = Earth.expedition(missions)

print(log)
/* This outputs to standard out:
1 1 E           // Location and orientation of the last position of the Robot in the form x y orientation (location is zero-based)
3 3 N LOST      // Robots are "lost" if they leave the grid. This is indicated with "LOST" at the end of the output for the lost Robot. 
2 3 S           // Subsequent robots will ignore commands which try and move them to the same square as a "lost" robot.
 */
```

The `Earth.expedition` method returns a `Log` object which can be used to interpret the mission details programmatically.
The `toString()` method of `Log` is used to format the output for printing.

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

