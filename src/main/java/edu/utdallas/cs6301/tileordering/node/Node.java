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

    public Node(Node<T> parent, int depth, T item) {
        this.parent = parent;
        this.depth = depth;
        this.item = item;
    }

    public Node<T> getParent() {
        return parent;
    }

    public T getItem() {
        return item;
    }

    public int getDepth() {
        return depth;
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
}
