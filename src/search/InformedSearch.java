package search;

import search.constantVariable.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.logging.Logger;

import static search.Printer.print_animate_search;


public class InformedSearch extends Search {
    protected PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparator());
    protected char heuristic;

    public InformedSearch(String algorithm, char heuristic, char[][] map, char initial_position, char dest_position) {
        super(algorithm, map, initial_position, dest_position);
        this.heuristic = heuristic;
    }

    public ArrayList<Node> search() {
        Map<Node, Node> ancestors = new HashMap<>();
        ArrayList<Node> path_to_dest = new ArrayList<>();
        ArrayList<Node> explored = new ArrayList<>();

        frontier.add(initial_node);

        int round = 1;
        while (!frontier.isEmpty()) {
            Node cur_node = frontier.poll();
            explored.add(cur_node);

            if (cur_node.equals(dest_node)) {// GOAL-TEST
                num_explored_nodes++;

                path_to_dest = create_path_to_dest(ancestors, cur_node);

                System.out.println("DESTINATION IS FOUND");
                break;
            }

            ArrayList<Node> successors = expand(cur_node, frontier, explored);
            frontier.addAll(successors);
            for (Node node : successors) {
                ancestors.put(node, cur_node);
            }

            print_animate_search(round++, cur_node, explored, map, frontier.contains(cur_node), algorithm, initial_node, dest_node);

            if (!cur_node.equals(initial_node)) {
                num_explored_nodes++;
            }
        }

        return path_to_dest;
    }

    protected ArrayList<Node> expand(Node node, PriorityQueue<Node> frontier, ArrayList<Node> explored) {
        // retrieve successors nodes
        ArrayList<Node> next_states = get_neighbors(node);
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

    /**
     * Gets all neighbor nodes (North, South, East, West) of the node.
     * BestFS score f(n) = h(n)
     * A* score f(n) = g(n) + h(n)
     * <p>
     * h(n) = estimated cost of the path from the state at node n to the goal
     * g(n) = the cost of the path from the start to the node n
     *
     * @param node
     * @return
     */
    public ArrayList<Node> get_neighbors(Node node) {
        int x = node.getX();
        int y = node.getY();
        ArrayList<Node> nextStates = new ArrayList<>();

        // Up
        if (is_legal_move(x - 1, y)) {
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
        if (is_legal_move(x + 1, y)) {
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
        if (is_legal_move(x, y - 1)) {
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
        if (is_legal_move(x, y + 1)) {
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
        if (frontier.isEmpty()) {
            System.out.println("THERE IS NO MORE NODE TO BE EXPLORED, THE SEARCH IS OVER");
        }
    }
}
