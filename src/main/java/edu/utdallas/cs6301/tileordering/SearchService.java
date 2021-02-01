package edu.utdallas.cs6301.tileordering;

import edu.utdallas.cs6301.tileordering.io.IOService;
import edu.utdallas.cs6301.tileordering.node.*;

import java.util.*;

public class SearchService<T extends StateEnumerable<T>> {
    private final Node<T> goalNode;
    private final NodeFactory<T> nodeFactory;
    private final PriorityQueue<Node<T>> queue = new PriorityQueue<>();
    private final Set<T> expandedItems = new HashSet<>();

    private final IOService ioService;

    private int nodeCounter = 0;

    public SearchService(T initialState, T goalState, String searchStrategy, IOService ioService) {
        this.nodeFactory = getNodeFactory(searchStrategy);
        this.ioService = ioService;
        this.goalNode = nodeFactory.getNode(null, 0, goalState);

        /* Add the initial node to the queue */
        queue.add(nodeFactory.getNode(null, 0, initialState));
    }

    public List<T> getPath() {
        while(!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();

            T currentItem = currentNode.getItem();

            if(expandedItems.contains(currentItem)) {
                continue;
            }

            ioService.writeItem(currentItem.toString());

            if(currentItem.equals(goalNode.getItem())) {
                return buildPathList(currentNode);
            }

            expandedItems.add(currentItem);
            queue.addAll(currentNode.getSuccessors(nodeFactory));
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

    private NodeFactory<T> getNodeFactory(String searchStrategy) {
        switch(searchStrategy) {
            case "dfs":
                return (p, d, i) -> new DFSNode<>(p, d, i, nodeCounter++);
            case "ucs":
                return (p, d, i) -> new UCSNode<>(p, d, i, nodeCounter++);
            case "gs":
                return (p, d, i) -> new GSNode<>(p, d, i, nodeCounter++);
            case "a-star":
                return (p, d, i) -> new AStarNode<>(p, d, i, nodeCounter++);
            case "bfs":
            default:
                return (p, d, i) -> new BFSNode<>(p, d, i, nodeCounter++);
        }
    }
}
