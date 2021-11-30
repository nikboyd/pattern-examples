package dev.educery.nodes;

/**
 * An assignment node.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @author Copyright 2003,2020 Nikolas S. Boyd
 */
public class AssignmentNode extends Node {

    public static interface Visitor extends Node.Visitor { void visit(AssignmentNode node); }
    public void accept(Visitor v) { report("accepted visitor"); v.visit(ref); v.visit(lit); }

    public static AssignmentNode with(ReferenceNode ref, LiteralNode lit) { return new AssignmentNode(ref, lit); }
    public AssignmentNode(ReferenceNode ref, LiteralNode lit) { this.ref = ref; this.lit = lit; }
    public ReferenceNode ref;
    public LiteralNode lit;

} // AssignmentNode
