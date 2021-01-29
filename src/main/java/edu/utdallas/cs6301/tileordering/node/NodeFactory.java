package edu.utdallas.cs6301.tileordering.node;

import edu.utdallas.cs6301.tileordering.StateEnumerable;

@FunctionalInterface
public interface NodeFactory<T extends StateEnumerable<T>> {
    Node<T> getNode(Node<T> parent, int depth, T item);
}
