package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BidirectionalSearch extends InformedSearch {
    private PriorityQueue<Node> frontierEnd;

    public BidirectionalSearch(String algorithm, String heuristicType, char[][] map, int mapNumber) {
        super(algorithm, heuristicType, map, mapNumber);
        frontierEnd = new PriorityQueue<>(new NodeComparator());
    }

    public void search(char goal) {
        // from start
        clear_data();
        Map<Node, Node> prev = this.getPrev();
        ArrayList<Node> successors;
        ArrayList<Node> explored = this.get_explored();
        Node startNode = this.get_initial_node();
        set_dest_node(goal);
        get_dest_node();
        // BFS uses Deque to store frontier
        frontier.add(startNode);

        // from goal
        Map<Node, Node> prevEnd = new HashMap<>();
        ArrayList<Node> successorsEnd;
        ArrayList<Node> exploredEnd = new ArrayList<>();
        Node startNodeEnd = get_dest_node();
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
            if (!currentNode.equals(startNode)) {
                set_explored_state(get_explored_state() + 1);
            }
        }
    }

    protected void clear_data() {
        // clear everything in order to prepare for new search operation
        frontier.clear();
        super.clear_data();
    }

    public void process() {
        // search for Bob, then search for safe goal position
        search(BOB_POSITION);

        // TODO - to be constructed
        // only search for goal position if the robot managed to find a way to get to Bob
//			if (!get_path_to_bob().isEmpty()) {
//				search(GOAL_POSITION);
//			}
//
//			print_summary();

    }
}
