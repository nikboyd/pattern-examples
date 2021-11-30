package test.educery.visitors;

import org.junit.*;
import dev.educery.nodes.*;
import dev.educery.visitors.*;

/**
 * Confirms proper operation of a selective visitor.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @author Copyright 2003,2020 Nikolas S. Boyd
 */
public class GeneratorTest {

    @Test public void visitNodes() { CodeGenerator.create().visit(sampleAssignment()); }
    AssignmentNode sampleAssignment() { return AssignmentNode.with(sampleReference(), sampleLiteral()); }
    ReferenceNode sampleReference() { return ReferenceNode.named("sample"); }
    LiteralNode sampleLiteral() { return LiteralNode.with("value"); }

} // GeneratorTest
