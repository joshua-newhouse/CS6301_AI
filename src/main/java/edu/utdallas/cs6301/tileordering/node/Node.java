package edu.utdallas.cs6301.tileordering.node;

import edu.utdallas.cs6301.tileordering.NodeFactory;
import edu.utdallas.cs6301.tileordering.StateEnumerable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Node<T extends StateEnumerable<T>> implements Comparable<Node<T>> {
    private final Node<T> parent;
    private final int depth;
    private final T item;
    private final int nodeID;

    public Node(Node<T> parent, int depth, T item, int ID) {
        this.parent = parent;
        this.depth = depth;
        this.item = item;
        this.nodeID = ID;
    }

    public Node<T> getParent() {
        return parent;
    }

    public T getItem() {
        return item;
    }

    protected int getDepth() {
        return depth;
    }

    public int getNodeID() {
        return nodeID;
    }

    protected abstract int computeOrder(Node<T> node);

    @Override
    public int compareTo(Node<T> node) {
        int ordering = computeOrder(node);

        return ordering != 0 ?
                ordering :
                this.getNodeID() - node.getNodeID();
    }

    public Collection<Node<T>> getSuccessors(NodeFactory<T> nodeFactory) {
        Collection<Node<T>> successors = new ArrayList<>();

        List<T> successorItems = getItem().getNextStates();
        successorItems.forEach(i ->
                successors.add(
                        nodeFactory.getNode(this, getDepth() + 1, i)
                )
        );

        return successors;
    }

    protected int getCost() {
        return depth;
    }
}
