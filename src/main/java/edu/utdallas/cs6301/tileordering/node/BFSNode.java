package edu.utdallas.cs6301.tileordering.node;

import edu.utdallas.cs6301.tileordering.StateEnumerable;

public class BFSNode<T extends StateEnumerable<T>> extends Node<T> {
    public BFSNode(Node<T> parent, int depth, T item) {
        super(parent, depth, item);
    }

    @Override
    public int compareTo(Node<T> node) {
        return this.getDepth() - node.getDepth();
    }
}
