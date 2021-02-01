/*
 * Joshua Newhouse
 * Vamsi Somepalli
 */

package edu.utdallas.cs6301.tileordering.node;

import edu.utdallas.cs6301.tileordering.StateEnumerable;

public class UCSNode<T extends StateEnumerable<T>> extends Node<T> {
    private final int gValue;

    public UCSNode(Node<T> parent, int depth, T item, int ID) {
        super(parent, depth, item, ID);

        this.gValue = parent == null ? 0 : parent.getCost() + item.getCost(parent.getItem());
    }

    @Override
    protected int getCost() {
        return gValue;
    }
}
