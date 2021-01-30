package edu.utdallas.cs6301.tileordering.node;

import edu.utdallas.cs6301.tileordering.StateEnumerable;

public class BFSNode<T extends StateEnumerable<T>> extends Node<T> {
    public BFSNode(Node<T> parent, int depth, T item, int ID) {
        super(parent, depth, item, ID);
    }

    @Override
    public int compareTo(Node<T> node) {
        int deltaDepth = this.getDepth() - node.getDepth();

        return deltaDepth != 0 ?
                deltaDepth :
                this.getNodeID() - node.getNodeID();
    }
}
