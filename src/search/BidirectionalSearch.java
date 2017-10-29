package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BidirectionalSearch extends InformedSearch {
    private PriorityQueue<Node> frontierEnd;

    public BidirectionalSearch(String algorithm, char heuristic, char[][] map, int mapNumber, char initial_position, char dest_position) {
        super(algorithm, heuristic, map, mapNumber, initial_position, dest_position);
        frontierEnd = new PriorityQueue<>(new NodeComparator());
    }

    public void search() {
        // from start
        Map<Node, Node> prev = this.get_prev_path();
        ArrayList<Node> successors;
        ArrayList<Node> explored = this.get_explored();
        // BFS uses Deque to store frontier
        frontier.add(initial_node);

        // from goal
        Map<Node, Node> prevEnd = new HashMap<>();
        ArrayList<Node> successorsEnd;
        ArrayList<Node> exploredEnd = new ArrayList<>();
        Node startNodeEnd = dest_node;
//		Node goalNodeEnd = new Node(startNode.getX(), startNode.getY());
        frontierEnd.add(startNodeEnd);

        // perform search
        while (!frontier.isEmpty() && !frontierEnd.isEmpty()) {
            // remove the the first node from the frontier
            Node currentNode = frontier.poll();
            explored.add(currentNode);
            Node currentNodeEnd = frontierEnd.poll();
            exploredEnd.add(currentNode);
            // check if the robot has reached the goal
            if (currentNode.equals(currentNodeEnd)) {
                System.out.println("Path found");
                break;
            }
            // expand the nodes
            successors = expand(currentNode, frontier, explored);
            successorsEnd = expand(currentNodeEnd, frontierEnd, exploredEnd);
            frontier.addAll(successors);
            frontierEnd.addAll(successorsEnd);
            for (Node node : successors) {
                prev.put(node, currentNode);
                prevEnd.put(node, currentNode);
            }
            // keep track of states explored
            if (!currentNode.equals(initial_node)) {
                set_explored_state(get_explored_state() + 1);
            }
        }
    }
}
