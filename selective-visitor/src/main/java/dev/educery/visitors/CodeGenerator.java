package dev.educery.visitors;

import dev.educery.nodes.*;
import static java.lang.String.*;

/**
 * A simplified example code generator.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @author Copyright 2003,2020 Nikolas S. Boyd
 */
public class CodeGenerator implements
        AssignmentNode.Visitor, ReferenceNode.Visitor, LiteralNode.Visitor {

    public static CodeGenerator create() { return new CodeGenerator(); }
    @Override public void visit(ReferenceNode node) { report("visited reference"); }
    @Override public void visit(LiteralNode node)   { report("visited literal"); }
    @Override public void visit(AssignmentNode node) { node.accept(this); report(form(node)); }

    static final String AssignReport = "code: %s = '%s';";
    public String form(AssignmentNode n) { return format(AssignReport, n.ref.name, n.lit.value); }

} // CodeGenerator
