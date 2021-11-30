# pattern-examples

Contains code examples that demonstrate application of some [design patterns][patterns].

| Pattern | Intent |
| ------- | ------ |
| [Resource&nbsp;Manager][resource-manager]   | Factor out resource life-cycle management into a separate class, focus client code on resource usage. |
| [Promised&nbsp;Operations][promised-operations] | Separate and control access to operations promised between close collaborators without exposing those operations to the general public. |
| [Selective&nbsp;Visitor][selective-visitor] | Represent an operation to be performed on the elements of an object structure, allowing the addition of new operations without changing the classes of the elements operated upon, and allowing the easy addition of new element classes to the framework. |

#### Platform Requirements

You'll need (at least) Java SE [JDK 8][jdk8], due to its support for [lambdas][lambdas], 
which get used in these examples.
You'll also need [Maven][maven], at least [version 3.5.0][maven-350] is recommended.

#### Building from Sources

Clone this repository, and run the following shell command in the base project folder:

```
mvn -U -B clean install
```

This will build the examples and run their tests.


[jdk8]: https://openjdk.java.net/projects/jdk8/
[lambdas]: https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html
[maven]: https://maven.apache.org/
[maven-350]: https://maven.apache.org/docs/3.5.0/release-notes.html

[patterns]: https://educery.dev/patterns/
[resource-manager]: resource-manager#resource-manager
[selective-visitor]: selective-visitor#selective-visitor
[promised-operations]: promised-operations#promised-operations
