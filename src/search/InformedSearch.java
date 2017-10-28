package search;

import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.logging.Logger;

public class InformedSearch extends Search {
    private PriorityQueue<Node> frontier;
    protected String heuristicType;

    public InformedSearch(String algorithm, String heuristicType, char[][] map, int mapNumber) {
        super(algorithm, map, mapNumber);
        this.heuristicType = heuristicType;
        frontier = new PriorityQueue<>(new NodeComparator());
    }

    public void search(char goal) {
        clearData();
        Map<Node, Node> prev = this.getPrev();
        ArrayList<Node> successors;
        ArrayList<Node> explored = this.getExplored();
        Node startNode = getInitial_node();

        startNode.setPathCost(0);
        set_dest_node(goal);
        System.out.println("Start node: " + startNode + "\n");
        // BFS uses Deque to store frontier
        frontier.add(startNode);

        // Perform search
        while (!frontier.isEmpty()) {
            // remove the the first node from the frontier
            Node cur_node = frontier.poll();
            Printer.printStatus(cur_node, explored, getMap(), frontier.contains(cur_node));
            explored.add(cur_node);

            if (reach_goal(cur_node, goal)) {
                break;
            }

            // expand the nodes
            successors = expand(cur_node, frontier, explored);
            frontier.addAll(successors);
            for (Node node : successors) {
                prev.put(node, cur_node);
            }

            Printer.printStatus(cur_node, explored, getMap(), frontier.contains(cur_node));
            check_failure(goal);
            // keep track of states explored
            if (!cur_node.equals(startNode)) {
                this.setExplored_state(this.get_explored_state() + 1);
            }
        }
    }

    public ArrayList<Node> getDirectionBob() {
        return this.directionBob;
    }

    private ArrayList<Node> expand(Node node, PriorityQueue<Node> frontier,
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

    protected void check_failure(char dest) {
        // if there's no more nodes to be explored, the search is failed
        if (frontier.isEmpty()) {
            switch (dest) {
                case BOB_POSITION:
                    Logger.getLogger(InformedSearch.class.getName()).warning("BOB IS NEVER REACHED");
                    break;
                case GOAL_POSITION:
                    Logger.getLogger(InformedSearch.class.getName()).warning("SAFETY GOAL IS NEVER REACHED");
                    break;
                default:
                    Logger.getLogger(InformedSearch.class.getName()).warning("DESTINATION " + dest + " IS UNRECOGNISED");
            }
        }
    }

    protected void clearData() {
        // clear everything in order to prepare for new search operation
        frontier.clear();
        super.clearData();
    }

    public void process() {
        super.process();
        Printer.print_summary(algorithm, directionBob, directionGoal, getMap(), getMap_no(), get_explored_state(), heuristicType);
    }
}
