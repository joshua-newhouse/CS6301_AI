package edu.utdallas.cs6301.tileordering.node;

import edu.utdallas.cs6301.tileordering.NodeFactory;
import edu.utdallas.cs6301.tileordering.StateEnumerable;

import java.util.Collection;
import java.util.Collections;

public class DFSNode<T extends StateEnumerable<T>> extends Node<T> {
    private static final int MAX_DEPTH = 22;

    public DFSNode(Node<T> parent, int depth, T item, int ID) {
        super(parent, depth, item, ID);
    }

    @Override
    protected int computeOrder(Node<T> node) {
        return node.getCost() - this.getCost();
    }

    @Override
    public Collection<Node<T>> getSuccessors(NodeFactory<T> nodeFactory) {
        return this.getDepth() >= MAX_DEPTH ?
                Collections.emptyList() : super.getSuccessors(nodeFactory);
    }
}
