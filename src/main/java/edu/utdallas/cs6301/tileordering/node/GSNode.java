package edu.utdallas.cs6301.tileordering.node;

import edu.utdallas.cs6301.tileordering.StateEnumerable;

public class GSNode<T extends StateEnumerable<T>> extends Node<T> {
    public GSNode(Node<T> parent, int depth, T item, int ID) {
        super(parent, depth, item, ID);
    }

    @Override
    protected int computeOrder(Node<T> node) {
        return 0;
    }
}
