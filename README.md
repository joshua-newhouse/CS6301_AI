# Joshua Newhouse and Vamsi Somepalli
# CS6301_AI

## List of source files
View the java-doc overview-summary.html page with a web browser.

## Development Environment
- Linux 5.4.0-62-generic #70~18.04.1-Ubuntu SMP x86_64 GNU/Linux
- Java 1.8

## Build Program
For convenience, we have provided the jar pre-built but if you want to build it from scratch then use the following
instructions.

This is a Maven project and so to build it, from the top level directory, execute:

```$ mvn clean package```

The program will be a Java jar archive called search-1.0-SNAPSHOT.jar located in the target directory.

## Run Program
For convenience, we have provided a Bash script called search which wraps the jar.  Execute it as follows:

```./search [OPTIONS] <search-strategy> <INPUT_FILE>```

To run the java jar directly, on the command line and in the directory where the jar is located, execute:

```java -jar search-1.0-SNAPSHOT.jar [OPTIONS] <search-strategy> <input-file-path>```

For example:

```java -jar search-1.0-SNAPSHOT.jar [--cost] A-star /home/user/tile-ordering/tile1.exe```

```
Usage: search [OPTIONS] <search-strategy> <INPUT_FILE>
Options:
        -c, --cost      Applies movement cost.
        -d, --dump      Suppresses writing every expanded tile sequence.
        -h, --help      Help information
        -t, --test      Supply an initial tile sequence string on command line instead of reading from a file.
                        Example: search -t WWWWWWxBBBBBB

Search Strategies:
        BFS     Breadth first search
        DFS     Depth first search
        UCS     Uniform cost search
        GS      Greedy search
        A-star  A* search
```
