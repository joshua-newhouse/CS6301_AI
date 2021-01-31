package edu.utdallas.cs6301.tileordering.node;

import edu.utdallas.cs6301.tileordering.StateEnumerable;

public class AStarNode<T extends StateEnumerable<T>> extends Node<T> {
    public AStarNode(Node<T> parent, int depth, T item, int ID) {
        super(parent, depth, item, ID);
    }

    @Override
    protected int getCost() {
        return 0;
    }
}
