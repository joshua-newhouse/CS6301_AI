package edu.utdallas.cs6301.tileordering.node;

import edu.utdallas.cs6301.tileordering.StateEnumerable;

public class DFSNode<T extends StateEnumerable<T>> extends Node<T> {
    public DFSNode(Node<T> parent, int depth, T item, int ID) {
        super(parent, depth, item, ID);
    }

    @Override
    protected int computeOrder(Node<T> node) {
        double thisDepth = (double) this.getDepth();
        double nodeDepth = (double) node.getDepth();

        double delta = (1.0 / thisDepth) - (1.0 / nodeDepth);

        int retval = 0;

        if (delta < 0.0) {
            retval = -1;
        } else if (delta > 0.0) {
            retval = 1;
        }

        return retval;
    }
}
