package edu.utdallas.cs6301.tileordering.node;

import edu.utdallas.cs6301.tileordering.StateEnumerable;

public class UCSNode<T extends StateEnumerable<T>> extends Node<T> {
    public UCSNode(Node<T> parent, int depth, T item, int ID) {
        super(parent, depth, item, ID);
    }

    @Override
    protected int computeOrder(Node<T> node) {
        return 0;
    }
}
