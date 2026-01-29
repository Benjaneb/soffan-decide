# soffan-decide

**A reference implementation of the DECIDE launch decision function for a hypothetical missile defense system.**

<br>

## What this project does

This project implements the DECIDE() decision function. Given a snapshot of radar tracking data and configuration parameters, the program produces a deterministic launch ("YES") or no-launch ("NO") decision at the moment it is called. The decision is deterministic and based solely on the provided input data and parameters.

The main purpose of the project is exercising best practices in adopting version control in a larger team setting. The goal is therefore clarity and traceability throughout collaboration rather than real-world deployment.

<br>

## How to use it

**Requirements**
- **Java 17** or later
- **Gradle 7** or later

<br>

**Running and testing**
- **Build**: `gradle build`
- **Run**: `java -jar build/libs/decide-1.0-SNAPSHOT.jar`
- **Test**: `gradle test`

<br>

## Contributing

General contribution guidelines are described in [CONTRIBUTING.md](CONTRIBUTING.md).

<br>

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for full license text.

<br>

## Statement of Contributions
This section describes the specific contributions made by each team member for this assignment.

**Benjamin Widman, bwidman@kth.se**
- ...

**David Hübinette, davpers@kth.se**
- ...

**Daglas Aitsen, daglas@kth.se**
- ...

**Pierre Castañeda Segerström, pise@kth.se**
- ...

<br>

## Ways of Working

**Current state: “Foundation Established”** (state 2)

Upon our initial meeting, we discussed and concluded answers to all of the points addressed in “Principles Established” and “Foundation Established". This laid a solid foundation for us to start working together as a team. While one could argue that we have also fulfilled all criteria under “In Use", there are some aspects of our ways of working that could be further solidified:
1. _“Practices and tools are regularly inspected”_
2. _“Procedures are in place to handle feedback on the team’s way of working”_

<br>

**A simple way to address these would be to:**
1. Our ways of working currently rely on following our set conventions manually. There are ways to automate checks and verifications like this through GitHub Actions.
2. Reflect after each assignment, and evaluate if our ways of working need refinement. It is simpler to collect feedback throughout a “cycle” and apply it to the next one, rather than during an ongoing assignment (unless deemed absolutely necessary). 