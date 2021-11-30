package dev.educery.nodes;

import java.lang.reflect.Type;
import java.util.function.*;
import java.util.*;

/**
 * A node in an abstract syntax tree AST.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @author Copyright 2003,2020 Nikolas S. Boyd
 */
public abstract class Node implements Reporting {
    public void accept(Visitor v) { accepts(v, (n) -> v.visit(this)); }
    public void accepts(Visitor v, Consumer c) { if (v.canVisit(this)) c.accept(this);}

    /**
     * Defines protocols to visit nodes.
     */
    public static interface Visitor extends Reporting {
        default void visit(Node n) { n.accept(this); }
        default boolean canVisit(Node n) {
            // does this (derived) visitor implement the provided node visitor type?
            // using the visitor type design and naming convention: NodeType.Visitor extends Node.Visitor
            String nodeType = n.getClass().getTypeName();
            Type[] visitorTypes = getClass().getGenericInterfaces();
            return matchAny(wrap(visitorTypes), (type) -> type.getTypeName().startsWith(nodeType)); }

    } // Visitor

    public static <T> List<T> wrap(T... items) {
        return (items == null || items.length == 0) ? new ArrayList() : Arrays.asList(items); }

    public static <T> boolean matchAny(Collection<T> items, Predicate<? super T> p) {
        return (items == null || items.isEmpty()) ? false : items.stream().anyMatch(p); }

} // Node
