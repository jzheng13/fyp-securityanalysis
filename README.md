# A Logic-based Security Analyser for Interconnected User Accounts

Final Year Project for Imperial College London BEng JMC

## Contents

1. `counter`*

   Java source code for GUI, attack graph generating and processing, network graph generating and processing, prolog file reading and parsing, prolog program output getter. Also with external libraries required for displaying and etc.

2. `debug`

   Prolog code during development for debugging purposes and trial and error. Not important.

3. `doc`

   Documentation including report, description of example database and etc.

4. `engine`*

   Prolog source files for reasoning engine. Parsed code also goes here.

> **Note**: Only folders marked with an asterisk contain source code.

## How to run

Note that the tool has dependencies on SWI-prolog and MySQL. Hence, both are required to run the tool on the machine.

Run from within Eclipse environment using the run button on the AppFrame class.

Or run the `tool.jar` file on a 32-bit Java Virtual Machine.

```sh
java -jar tool.jar
```
