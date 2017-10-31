package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BidirectionalSearch extends InformedSearch {
    public BidirectionalSearch(String algorithm, char heuristic, char[][] map, char initial_position, char dest_position) {
        super(algorithm, heuristic, map, initial_position, dest_position);
    }

    public ArrayList<Node> search() {
        ArrayList<Node> path_to_dest = new ArrayList<>();
        Map<Node, Node> ancestors = new HashMap<>();
        ArrayList<Node> successors;
        ArrayList<Node> explored = new ArrayList<>();
        PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparator());
        PriorityQueue<Node> frontierEnd = new PriorityQueue<>(new NodeComparator());

        frontier.add(initial_node);

        Map<Node, Node> prevEnd = new HashMap<>();
        ArrayList<Node> successorsEnd;
        ArrayList<Node> exploredEnd = new ArrayList<>();
        Node startNodeEnd = dest_node;
//		Node goalNodeEnd = new Node(startNode.getX(), startNode.getY());
        frontierEnd.add(startNodeEnd);

        // perform search
        while (!frontier.isEmpty() && !frontierEnd.isEmpty()) {
            Node currentNode = frontier.poll();
            explored.add(currentNode);
            Node currentNodeEnd = frontierEnd.poll();
            exploredEnd.add(currentNode);

            if (currentNode.equals(currentNodeEnd)) {
                System.out.println("Path found");
                break;
            }

            successors = expand(currentNode, frontier, explored);
            successorsEnd = expand(currentNodeEnd, frontierEnd, exploredEnd);
            frontier.addAll(successors);
            frontierEnd.addAll(successorsEnd);
            for (Node node : successors) {
                ancestors.put(node, currentNode);
                prevEnd.put(node, currentNode);
            }

            if (!currentNode.equals(initial_node)) {
                num_explored_nodes++;
            }
        }

        return path_to_dest;
    }
}
