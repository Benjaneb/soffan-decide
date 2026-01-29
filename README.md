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
- Implementing LIC1, LIC2, LIC9, LIC10 & FUV as well as doing some refactoring.

**David Hübinette, davpers@kth.se**
- Implementing LIC0, LIC8 & LIC 13 as well as doing some refactoring.

**Daglas Aitsen, daglas@kth.se**
- Implementing LIC5, LIC6, LIC7, LIC11 & PUM as well as setting up Gradle and doing some documentation.

**Pierre Castañeda Segerström, pise@kth.se**
- Implementing LIC3, LIC4, LIC12 & LIC14 as well as setting up the class structure and doing most of the documentation.

<br>

## Ways of Working

**Current state: “Foundation Established”** (state 2)

<br>

**Stage 1: "Principles Established"**
- [x] Principles and constraints are committed to by the team.
- [x] Principles and constraints are agreed to by the stakeholders.
- [x] The tool needs of the work and its stakeholders are agreed.
- [x] A recommendation for the approach to be taken is available.
- [x] The context within which the team will operate is understood.
- [x] The constraints that apply to the selection, acquisition, and use of practices and tools are known.

<br>

**Stage 2: "Principles Established"**
- [x] The key practices and tools that form the foundation of the way-of-working are selected.
- [x] Enough practices for work to start are agreed to by the team.
- [x] All non-negotiable practices and tools have been identified.
- [x] The gaps that exist between the practices and tools that are needed and the practices and tools that are available have been analyzed and understood.
- [x] The capability gaps that exist between what is needed to execute the desired way of working and the capability levels of the team have been analyzed and understood.
- [x] The selected practices and tools have been integrated to form a usable way-of-working>

<br>

Upon our initial meeting, we discussed and concluded answers to all of the points addressed in **“Principles Established”** and **“Foundation Established"**. This laid a solid foundation for us to start working together as a team. While one could argue that we have also fulfilled all criteria under **“In Use"**, there are some aspects of our ways of working that could be further solidified:

<br>

**Stage 3: "In Use"**
- [x] The practices and tools are being used to do real work.
- [ ] The use of the practices and tools selected are regularly inspected.
- [x] The practices and tools are being adapted to the team’s context.
- [x] The use of the practices and tools is supported by the team.
- [x] Procedures are in place to handle feedback on the team’s way of working.
- [ ] The practices and tools support team communication and collaboration.

<br>

**A simple way to address these would be to:**
1. Our ways of working currently rely on following our set conventions manually. There are ways to automate checks and verifications like this through GitHub Actions.
2. Reflect after each assignment, and evaluate if our ways of working need refinement. It is simpler to collect feedback throughout a “cycle” and apply it to the next one, rather than during an ongoing assignment (unless deemed absolutely necessary).
