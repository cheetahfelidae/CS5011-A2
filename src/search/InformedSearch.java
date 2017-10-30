package search;

import search.constantVariable.Algorithm;
import search.constantVariable.Position;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.logging.Logger;


public class InformedSearch extends Search {
    protected PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparator());
    protected char heuristic;

    public InformedSearch(String algorithm, char heuristic, char[][] map, char initial_position, char dest_position) {
        super(algorithm, map, initial_position, dest_position);
        this.heuristic = heuristic;
    }

    public ArrayList<Node> search() {
        ArrayList<Node> explored = this.get_explored();
        initial_node.setPathCost(0);

        System.out.println("INITIAL POSITION: " + initial_node);
        // BFS uses Deque to store frontier
        frontier.add(initial_node);

        // Perform search
        while (!frontier.isEmpty()) {
            // remove the the first node from the frontier
            Node cur_node = frontier.poll();
            Printer.print_status(cur_node, explored, get_map(), frontier.contains(cur_node));
            explored.add(cur_node);

            if (reach_dest(cur_node)) {
                break;
            }

            // expand the nodes
            ArrayList<Node> successors = expand(cur_node, frontier, explored);
            frontier.addAll(successors);
            for (Node node : successors) {
                get_prev_path().put(node, cur_node);
            }

            Printer.print_status(cur_node, explored, get_map(), frontier.contains(cur_node));
            check_failure();
            // keep track of states explored
            if (!cur_node.equals(initial_node)) {
                set_explored_state(this.get_num_explored_nodes() + 1);
            }
        }

        return path_to_dest;
    }

    protected ArrayList<Node> expand(Node node, PriorityQueue<Node> frontier, ArrayList<Node> explored) {
        // retrieve successors nodes
        ArrayList<Node> next_states = get_next_states(node);
        ArrayList<Node> successors = new ArrayList<>();

        for (Node state : next_states) {
            if (algorithm.equals(Algorithm.BEST_FIRST_SEARCH.toString())) {
                if (!explored.contains(state) && !frontier.contains(state)) {
                    successors.add(state);
                }
            }
            /**
             * if state is in a node in frontier but with higher PATH-COST then replace old node with new node (A*)
             */
            else if (algorithm.equals(Algorithm.A_STAR.toString())) {
                if (!explored.contains(state) && !frontier.contains(state)) {
                    successors.add(state);
                } else if (frontier.contains(state)) {
                    Node oldNode = new Node(0, 0);
                    for (int i = 0; i < frontier.size(); i++) {
                        Node n = frontier.peek();
                        if (n.equals(state)) {
                            oldNode = n;
                            break;
                        }
                    }
                    if (oldNode.getPathCost() > state.getPathCost()) {
                        frontier.remove(oldNode);
                        frontier.add(state);
                    }
                }
            } else {
                Logger.getLogger(InformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
            }
        }

        return successors;
    }

    public ArrayList<Node> get_next_states(Node node) {
        int x = node.getX();
        int y = node.getY();
        ArrayList<Node> nextStates = new ArrayList<>();

        /**
         * BestFS score f(n) = h(n)
         * A* score f(n) = g(n) + h(n)
         *
         * h(n) = estimated cost of the path from the state at node n to the goal
         * g(n) = the cost of the path from the start to the node n
         */
        // Up
        if (is_valid_child(x - 1, y)) {
            Node up = new Node(x - 1, y);
            up.setPathCost(node.getPathCost() + 1);
            up.setHeuristic(heuristic, dest_node);
            if (algorithm.equals(Algorithm.BEST_FIRST_SEARCH.toString())) {
                up.setScore(up.getHeuristic());
            } else if (algorithm.equals(Algorithm.A_STAR.toString())) {
                up.setScore(up.getHeuristic() + up.getPathCost());
            } else {
                Logger.getLogger(InformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
            }
            nextStates.add(up);
        }

        // Down
        if (is_valid_child(x + 1, y)) {
            Node down = new Node(x + 1, y);
            down.setPathCost(node.getPathCost() + 1);
            down.setHeuristic(heuristic, dest_node);
            if (algorithm.equals(Algorithm.BEST_FIRST_SEARCH.toString())) {
                down.setScore(down.getHeuristic());
            } else if (algorithm.equals(Algorithm.A_STAR.toString())) {
                down.setScore(down.getHeuristic() + down.getPathCost());
            } else {
                Logger.getLogger(InformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
            }
            nextStates.add(down);
        }

        // Left
        if (is_valid_child(x, y - 1)) {
            Node left = new Node(x, y - 1);
            left.setPathCost(node.getPathCost() + 1);
            left.setHeuristic(heuristic, dest_node);
            if (algorithm.equals(Algorithm.BEST_FIRST_SEARCH.toString())) {
                left.setScore(left.getHeuristic());
            } else if (algorithm.equals(Algorithm.A_STAR.toString())) {
                left.setScore(left.getHeuristic() + left.getPathCost());
            } else {
                Logger.getLogger(InformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
            }
            nextStates.add(left);
        }

        // Right
        if (is_valid_child(x, y + 1)) {
            Node right = new Node(x, y + 1);
            right.setPathCost(node.getPathCost() + 1);
            right.setHeuristic(heuristic, dest_node);
            if (algorithm.equals(Algorithm.BEST_FIRST_SEARCH.toString())) {
                right.setScore(right.getHeuristic());
            } else if (algorithm.equals(Algorithm.A_STAR.toString())) {
                right.setScore(right.getHeuristic() + right.getPathCost());
            } else {
                Logger.getLogger(InformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
            }
            nextStates.add(right);
        }

        return nextStates;
    }

    protected void check_failure(char dest) {
        // if there's no more nodes to be explored, the search is failed
        if (frontier.isEmpty()) {
            switch (Position.convert(dest)) {
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
}
