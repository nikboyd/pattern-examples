package dev.educery.nodes;

/**
 * A literal value node.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @author Copyright 2003,2020 Nikolas S. Boyd
 */
public class LiteralNode extends Node {

    public static interface Visitor extends Node.Visitor { void visit(LiteralNode node); }
    @Override public void accept(Node.Visitor v) { accepts(v, (n) -> ((Visitor)v).visit(this)); }

    public static LiteralNode with(String value) { return new LiteralNode(value); }
    public LiteralNode(String value) { this.value = value; }
    public String value;

} // LiteralNode
