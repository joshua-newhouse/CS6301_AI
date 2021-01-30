package edu.utdallas.cs6301.tileordering.node;

import edu.utdallas.cs6301.tileordering.StateEnumerable;

public class DFSNode<T extends StateEnumerable<T>> extends Node<T> {
    public DFSNode(Node<T> parent, int depth, T item, int ID) {
        super(parent, depth, item, ID);
    }

    @Override
    protected int computeOrder(Node<T> node) {
        return node.getDepth() - this.getDepth();
    }
}
