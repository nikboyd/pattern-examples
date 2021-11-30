### Selective Visitor

The code herein provides an example application of the [Selective Visitor][design-pattern] design pattern.

**Intent:** Represent an operation to be performed on the elements of an object structure, 
allowing the addition of new operations without changing the classes of the elements operated upon, 
and allowing the easy addition of new element classes to the framework.

### Code Generation Demo

The included [GeneratorTest][test-class] class exercises a small demonstration of this design pattern.
A small collection of abstract syntax tree AST [node classes][node-package] are included in the nodes package.
Each of these exposes a Visitor type, which visits a node of that kind.
The [CodeGenerator][visitors] class implements each of these Visitor types, so that it can determine 
whether and what to do when visiting each kind of Node, even selecting those kinds of nodes it will visit.

Thus, unlike the original [Visitor][gof-visitor] design pattern, which would require it to visit all the kinds 
Node classes available in the AST, it can pick and choose which kinds of nodes it needs to visit.
This also allows the Node classes to be changed more easily over time, as the associated grammar may 
undergo gradual change, esp. during its initial development.

[design-pattern]: https://educery.dev/patterns/selective-visitor/
[test-class]: src/test/java/test/educery/visitors/GeneratorTest.java#L8
[node-package]: src/main/java/dev/educery/nodes
[visitors]: src/main/java/dev/educery/visitors/CodeGenerator.java#L11
[gof-visitor]: https://en.wikipedia.org/wiki/Visitor_pattern
