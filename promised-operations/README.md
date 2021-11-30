### Promised Operations

The code herein provides an example application of the [Promised Operations][design-pattern] design pattern.

**Intent:** Separate and control access to operations promised between close collaborators without 
exposing those operations to the general public.

### Resource Storage Demo

The included [ResourceTest][resource-test] class exercises a small demonstration of this design pattern.
Test resources are loaded from a sample backing store, a small change is made and saved, 
but only when not already present in the loaded resource data.
Thus, the initial run of the test shows the original data, and subsequent test runs show the alteration.
A clean build of the project will restore the original value by cleaning out the test backing store file.

The [Registry][registry] class defines a companion [Registrar][registrar] type, 
which provides privileged access to its contents.
The Registry collaborates with the [ResourceStorage][storage] class, providing it with an instance of Registrar, 
so that ResourceStorage can use it to register the Resources it loads from its backing store.
In this manner, the Registry controls access to its contents without otherwise exposing them.
By design, only the Registry has access to its contents (they are private), and so it controls the collaboration.

[design-pattern]: https://educery.dev/patterns/promised-operations/
[resource-test]: src/test/java/test/educery/resources/ResourceTest.java#L8
[registry]: src/main/java/dev/educery/resources/Registry.java#L7
[registrar]: src/main/java/dev/educery/resources/Registry.java#L36
[storage]: src/main/java/dev/educery/resources/ResourceStorage.java#L14
