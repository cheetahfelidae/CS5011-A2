package search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BidirectionalSearch extends InformedSearch {
    private PriorityQueue<Node> frontier;
    private PriorityQueue<Node> frontierEnd;

    public BidirectionalSearch(String algorithm, String heuristicType, char[][] map, int mapNumber) {
        super(algorithm, heuristicType, map, mapNumber);
        frontierEnd = new PriorityQueue<>(new NodeComparator());
    }

    public void search(char goal) {
        // from start
        clearData();
        Map<Node, Node> prev = this.getPrev();
        ArrayList<Node> successors;
        ArrayList<Node> explored = this.getExplored();
        Node startNode = this.getInitial_node();
        this.set_dest_node(goal);
        this.get_dest_node();
        // BFS uses Deque to store frontier
        frontier.add(startNode);

        // from goal
        Map<Node, Node> prevEnd = new HashMap<>();
        ArrayList<Node> successorsEnd;
        ArrayList<Node> exploredEnd = new ArrayList<>();
        Node startNodeEnd = this.get_dest_node();
//		Node goalNodeEnd = new Node(startNode.getX(), startNode.getY());
        frontierEnd.add(startNodeEnd);
        Node currentNodeEnd;

        // perform search
        while (!frontier.isEmpty() && !frontierEnd.isEmpty()) {
            // remove the the first node from the frontier
            Node currentNode = frontier.poll();
            explored.add(currentNode);
            currentNodeEnd = frontierEnd.poll();
            exploredEnd.add(currentNode);
            // check if the robot has reached the goal
            if (currentNode.equals(currentNodeEnd)) {
                System.out.println("Path found");
                break;
            }
            // expand the nodes
            successors = Expand(currentNode, frontier, explored);
            successorsEnd = Expand(currentNodeEnd, frontierEnd, exploredEnd);
            frontier.addAll(successors);
            frontierEnd.addAll(successorsEnd);
            for (Node node : successors) {
                prev.put(node, currentNode);
                prevEnd.put(node, currentNode);
            }
            // keep track of states explored
            if (!currentNode.equals(startNode)) {
                this.setExplored_state(this.get_explored_state() + 1);
            }
        }
    }

    public ArrayList<Node> get_next_states(Node node) {
        int x = node.getX();
        int y = node.getY();
        Node goalNode = this.get_dest_node();
        ArrayList<Node> nextStates = new ArrayList<Node>();
        /**
         * BestFS score f(n) = h(n)
         * A* score f(n) = g(n) + h(n)
         *
         * h(n) = estimated cost of the path from the state at node n to the goal
         * g(n) = the cost of the path from the start to the node n
         */
        // North
        if (is_valid_child(x - 1, y)) {
            Node up = new Node(x - 1, y);
            up.setPathCost(node.getPathCost() + 1);
            up.setHeuristic(heuristicType, goalNode);
            if (algorithm.equals("BestFS")) {
                up.setScore(up.getHeuristic());
            } else {
                up.setScore(up.getHeuristic() + up.getPathCost());
            }
            nextStates.add(up);
        }

        // South
        if (is_valid_child(x + 1, y)) {
            Node down = new Node(x + 1, y);
            down.setPathCost(node.getPathCost() + 1);
            down.setHeuristic(heuristicType, goalNode);
            if (algorithm.equals("BestFS")) {
                down.setScore(down.getHeuristic());
            } else {
                down.setScore(down.getHeuristic() + down.getPathCost());
            }
            nextStates.add(down);
        }

        // West
        if (is_valid_child(x, y - 1)) {
            Node left = new Node(x, y - 1);
            left.setPathCost(node.getPathCost() + 1);
            left.setHeuristic(heuristicType, goalNode);
            if (algorithm.equals("BestFS")) {
                left.setScore(left.getHeuristic());
            } else {
                left.setScore(left.getHeuristic() + left.getPathCost());
            }
            nextStates.add(left);
        }

        // East
        if (is_valid_child(x, y + 1)) {
            Node right = new Node(x, y + 1);
            right.setPathCost(node.getPathCost() + 1);
            right.setHeuristic(heuristicType, goalNode);
            if (algorithm.equals("BestFS")) {
                right.setScore(right.getHeuristic());
            } else {
                right.setScore(right.getHeuristic() + right.getPathCost());
            }
            nextStates.add(right);
        }

        return nextStates;
    }

    protected void clearData() {
        // clear everything in order to prepare for new search operation
        frontier.clear();
        super.clearData();
    }

    private ArrayList<Node> Expand(Node node, PriorityQueue<Node> frontier,
                                   ArrayList<Node> explored) {
        // retrieve successors nodes
        ArrayList<Node> nextStates = get_next_states(node);
        ArrayList<Node> successors = new ArrayList<Node>();
        for (int i = 0; i < nextStates.size(); i++) {
            if (algorithm.equals("BestFS")) {
                if (!explored.contains(nextStates.get(i)) &&
                        !frontier.contains(nextStates.get(i))) {
                    successors.add(nextStates.get(i));
                }
            }
            /**
             * if state is in a node in frontier but with higher PATH-COST then
             * replace old node with new node (A*)
             */
            else {
                if (!explored.contains(nextStates.get(i)) &&
                        !frontier.contains(nextStates.get(i))) {
                    successors.add(nextStates.get(i));
                } else if (frontier.contains(nextStates.get(i))) {
                    Node oldNode = new Node(0, 0);
                    for (Node n : frontier) {
                        if (n.equals(nextStates.get(i))) {
                            oldNode = n;
                            break;
                        }
                    }
                    if (oldNode.getPathCost() > nextStates.get(i).getPathCost()) {
                        frontier.remove(oldNode);
                        frontier.add(nextStates.get(i));
                    }
                }
            }
        }

        return successors;
    }

    public void process() {
        // search for Bob, then search for safe goal position
        search(BOB_POSITION);

        // TODO - to be constructed
        // only search for goal position if the robot managed to find a way to get to Bob
//			if (!getDirectionBob().isEmpty()) {
//				search(GOAL_POSITION);
//			}
//
//			print_summary();

    }
}
