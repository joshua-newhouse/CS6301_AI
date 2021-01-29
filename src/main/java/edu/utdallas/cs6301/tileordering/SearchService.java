package edu.utdallas.cs6301.tileordering;

import edu.utdallas.cs6301.tileordering.node.BFSNode;
import edu.utdallas.cs6301.tileordering.node.Node;
import edu.utdallas.cs6301.tileordering.node.NodeFactory;

import java.util.*;

public class SearchService<T extends StateEnumerable<T>> {
    private final Node<T> goalNode;
    private final NodeFactory<T> nodeFactory;
    private final PriorityQueue<Node<T>> queue = new PriorityQueue<>();

    public SearchService(T initialState, T goalState, String costFunction) {
        this.nodeFactory = getNodeFactory(costFunction);
        this.goalNode = nodeFactory.getNode(null, 0, goalState);

        /* Add the initial node to the queue */
        queue.add(nodeFactory.getNode(null, 0, initialState));
    }

    public List<T> getPath() {
        while(!queue.isEmpty()) {
            Node<T> nextNode = queue.poll();

            if(nextNode.getItem().equals(goalNode.getItem())) {
                return buildPathList(nextNode);
            }

            queue.addAll(nextNode.getSuccessors(nodeFactory));
        }

        return Collections.emptyList();
    }

    private List<T> buildPathList(Node<T> leafNode) {
        List<T> pathList = new ArrayList<>();

        for(Node<T> current = leafNode; current != null; current = current.getParent()) {
            pathList.add(current.getItem());
        }

        Collections.reverse(pathList);
        return pathList;
    }

    private NodeFactory<T> getNodeFactory(String costFunction) {
        switch(costFunction) {
            case "DFS":
            case "UCS":
            case "GS":
            case "A-star":
            case "BFS":
            default:
                return BFSNode::new;
        }
    }
}
