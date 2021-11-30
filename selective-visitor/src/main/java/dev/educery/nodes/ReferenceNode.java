package dev.educery.nodes;

/**
 * A reference node.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @author Copyright 2003,2020 Nikolas S. Boyd
 */
public class ReferenceNode extends Node {

    public static interface Visitor extends Node.Visitor { void visit(ReferenceNode node); }
    @Override public void accept(Node.Visitor v) { accepts(v, (n) -> ((Visitor)v).visit(this)); }

    public static ReferenceNode named(String name) { return new ReferenceNode(name); }
    public ReferenceNode(String name) { this.name = name; }
    public String name;

} // ReferenceNode
