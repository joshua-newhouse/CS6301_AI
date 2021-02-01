/*
 * Joshua Newhouse
 * Vamsi Somepalli
 */

package edu.utdallas.cs6301.tileordering;

import edu.utdallas.cs6301.tileordering.node.Node;

@FunctionalInterface
public interface NodeFactory<T extends StateEnumerable<T>> {
    Node<T> getNode(Node<T> parent, int depth, T item);
}
