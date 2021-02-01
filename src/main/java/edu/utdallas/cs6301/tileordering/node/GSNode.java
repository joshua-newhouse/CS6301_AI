/*
 * Joshua Newhouse
 * Vamsi Somepalli
 */

package edu.utdallas.cs6301.tileordering.node;

import edu.utdallas.cs6301.tileordering.StateEnumerable;

public class GSNode<T extends StateEnumerable<T>> extends Node<T> {
    private final int hValue;

    public GSNode(Node<T> parent, int depth, T item, int ID) {
        super(parent, depth, item, ID);

        this.hValue = item.getDistanceFromGoal();
    }

    @Override
    protected int getCost() {
        return this.hValue;
    }
}
