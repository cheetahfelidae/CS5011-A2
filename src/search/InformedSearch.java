package search;

import search.constantVariable.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.logging.Logger;

import static search.Printer.print_animate_result;
import static search.Printer.print_search_result;

/**
 * This is instantiated in Search2 class.
 * To meet the part-2 requirements, it is designed to be implemented using Best First Search and A* Search algorithms.
 */
public class InformedSearch extends GeneralSearch {
    protected char heuristic_method;

    public InformedSearch(String algorithm, char heuristic_method, char[][] map, char initial_position, char dest_position) {
        super(algorithm, map, initial_position, dest_position);
        this.heuristic_method = heuristic_method;
    }

    /**
     * 1. Initiate Frontier container with initial node.
     * 2. Loop Until Frontier is empty.
     * - Remove the first node X from Frontier
     * - If X is the destination, the search have succeeded, otherwise find all X-neighbor nodes
     * - Score each node n with h(n)
     * - Merge the set of new states into Frontier(priority queue) using scores as the priority
     *
     * @return
     */
    public ArrayList<Node> search() {
        Map<Node, Node> ancestors = new HashMap<>();
        ArrayList<Node> path_to_dest = new ArrayList<>();
        PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparator());
        ArrayList<Node> explored_nodes = new ArrayList<>();

        frontier.add(initial_node);

        int round = 1;
        while (!frontier.isEmpty()) {
            Node cur_node = frontier.poll();
            explored_nodes.add(cur_node);

            if (cur_node.equals(dest_node)) {// GOAL-TEST
                path_to_dest = create_path_to_dest(ancestors, cur_node);

                print_animate_result(round, cur_node, explored_nodes, map, algorithm, initial_node, dest_node);
                break;
            }

            ArrayList<Node> successors = expand(cur_node, frontier, explored_nodes);
            frontier.addAll(successors);
            for (Node successor : successors) {
                ancestors.put(successor, cur_node);
            }

            print_animate_result(round++, cur_node, explored_nodes, map, algorithm, initial_node, dest_node);
        }

        // the number of explored nodes excludes the initial node.
        set_num_explored_nodes(explored_nodes.size() - 1);

        print_search_result(map, path_to_dest, initial_position,  dest_position);

        return path_to_dest;
    }

    /**
     * Gets all neighbor nodes (North, South, East, West) of the node and score each node n
     * with either h(n) <Best First Search> or g(n) + h(n) <A*>
     * <p>
     * h(n) = the cost of the path from the node n to the destination node.
     * g(n) = the cost of the path from the initial node to the node n.
     *
     * @param node
     * @return
     */
    protected ArrayList<Node> get_neighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        int x = node.getX();
        int y = node.getY();

        // Left
        if (is_legal_move(x, y - 1)) {
            Node left = new Node(x, y - 1);
            left.set_path_cost(node.get_path_cost() + 1);
            left.set_heuristic_value(heuristic_method, dest_node);

            switch (Algorithm.convert(algorithm)) {
                case BEST_FIRST_SEARCH:
                    left.set_score(left.get_heuristic_value());
                    break;
                case A_STAR:
                    left.set_score(left.get_heuristic_value() + left.get_path_cost());
                    break;
                default:
                    Logger.getLogger(InformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
            }

            neighbors.add(left);
        }

        // Right
        if (is_legal_move(x, y + 1)) {
            Node right = new Node(x, y + 1);
            right.set_path_cost(node.get_path_cost() + 1);
            right.set_heuristic_value(heuristic_method, dest_node);

            switch (Algorithm.convert(algorithm)) {
                case BEST_FIRST_SEARCH:
                    right.set_score(right.get_heuristic_value());
                    break;
                case A_STAR:
                    right.set_score(right.get_heuristic_value() + right.get_path_cost());
                    break;
                default:
                    Logger.getLogger(InformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
            }

            neighbors.add(right);
        }

        // Up
        if (is_legal_move(x - 1, y)) {
            Node up = new Node(x - 1, y);
            up.set_path_cost(node.get_path_cost() + 1);
            up.set_heuristic_value(heuristic_method, dest_node);

            switch (Algorithm.convert(algorithm)) {
                case BEST_FIRST_SEARCH:
                    up.set_score(up.get_heuristic_value());
                    break;
                case A_STAR:
                    up.set_score(up.get_heuristic_value() + up.get_path_cost());
                    break;
                default:
                    Logger.getLogger(InformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
            }

            neighbors.add(up);
        }

        // Down
        if (is_legal_move(x + 1, y)) {
            Node down = new Node(x + 1, y);
            down.set_path_cost(node.get_path_cost() + 1);
            down.set_heuristic_value(heuristic_method, dest_node);

            switch (Algorithm.convert(algorithm)) {
                case BEST_FIRST_SEARCH:
                    down.set_score(down.get_heuristic_value());
                    break;
                case A_STAR:
                    down.set_score(down.get_heuristic_value() + down.get_path_cost());
                    break;
                default:
                    Logger.getLogger(InformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
            }

            neighbors.add(down);
        }

        return neighbors;
    }

    /**
     * It is used by expand() to find the node with the same coordinate (but maybe different score).
     *
     * @param frontier
     * @param node
     * @return
     */
    private Node find_node(PriorityQueue<Node> frontier, Node node) {
        for (int i = 0; i < frontier.size(); i++) {
            Node n = frontier.peek();
            if (n.equals(node)) {
                return n;
            }
        }

        return null;
    }

    protected ArrayList<Node> expand(Node node, PriorityQueue<Node> frontier, ArrayList<Node> explored_nodes) {
        ArrayList<Node> neighbors = get_neighbors(node);
        ArrayList<Node> successors = new ArrayList<>();

        for (Node neighbor : neighbors) {

            if (!explored_nodes.contains(neighbor) && !frontier.contains(neighbor)) {
                successors.add(neighbor);
            }

            /**
             * For A*, if the node is in Frontier but with higher PATH-COST then replace old node with new node
             */
            if (algorithm.equals(Algorithm.A_STAR.toString())) {

                if (frontier.contains(neighbor)) {
                    Node old_node = find_node(frontier, neighbor);
                    if (old_node != null) {
                        if (old_node.get_path_cost() > neighbor.get_path_cost()) {
                            frontier.remove(old_node);
                            frontier.add(neighbor);
                        }
                    }
                }

            }

        }

        return successors;
    }
}
