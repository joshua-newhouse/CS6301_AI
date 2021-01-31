package edu.utdallas.cs6301.tileordering.node;

import edu.utdallas.cs6301.tileordering.StateEnumerable;

public class AStarNode<T extends StateEnumerable<T>> extends Node<T> {
    private final int gValue;
    private final int hValue;

    public AStarNode(Node<T> parent, int depth, T item, int ID) {
        super(parent, depth, item, ID);

        this.gValue = parent == null ? 0 : ((AStarNode<T>) parent).gValue + item.getCost(parent.getItem());
        this.hValue = item.getDistanceFromGoal();
    }

    @Override
    protected int getCost() {
        return this.gValue + this.hValue;
    }
}
