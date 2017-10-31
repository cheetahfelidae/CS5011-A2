package search;

import search.constantVariable.Algorithm;

import java.util.*;
import java.util.logging.Logger;

import static search.Printer.print_animate_result;
import static search.Printer.print_hyphens;

public class UninformedSearch extends Search {

    public UninformedSearch(String algorithm, char[][] map, char initial_position, char dest_position) {
        super(algorithm, map, initial_position, dest_position);
    }

    /**
     * Breadth first search: explores the nodes at the same level first then next level.
     * Depth first search: explores as far as possible the nodes along a branch.
     *
     * @return
     */
    public ArrayList<Node> search() {
        Map<Node, Node> ancestors = new HashMap<>();
        ArrayList<Node> path_to_dest = new ArrayList<>();
        Deque<Node> frontier = new ArrayDeque<>();
        ArrayList<Node> explored_nodes = new ArrayList<>();

        frontier.add(initial_node);

        int round = 1;
        while (!frontier.isEmpty()) {
            Node cur_node = frontier.poll();
            explored_nodes.add(cur_node);

            if (cur_node.equals(dest_node)) {// GOAL-TEST
                num_explored_nodes++;

                path_to_dest = create_path_to_dest(ancestors, cur_node);

                System.out.println("DESTINATION IS FOUND");
                print_hyphens(map.length * 3);

                break;
            }

            for (Node node : expand(cur_node, frontier, explored_nodes)) {
                ancestors.put(node, cur_node);

                switch (Algorithm.convert(algorithm)) {
                    case BREADTH_FIRST_SEARCH:
                        frontier.addLast(node);
                        break;
                    case DEPTH_FIRST_SEARCH:
                        frontier.addFirst(node);
                        break;
                    default:
                        Logger.getLogger(UninformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
                }

            }

            print_animate_result(round++, cur_node, explored_nodes, map, algorithm, initial_node, dest_node);

            if (!cur_node.equals(initial_node)) {
                num_explored_nodes++;
            }
        }

        return path_to_dest;
    }

    /**
     * Gets the node's neighbors (Up, Down, Left and Right).
     *
     * @param node
     * @return
     */
    protected ArrayList<Node> get_neighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        int x = node.getX(), y = node.getY();

        // Up
        if (is_legal_move(x - 1, y)) {
            neighbors.add(new Node(x - 1, y));
        }

        // Left
        if (is_legal_move(x, y - 1)) {
            neighbors.add(new Node(x, y - 1));
        }

        // Right
        if (is_legal_move(x, y + 1)) {
            neighbors.add(new Node(x, y + 1));
        }

        // Down
        if (is_legal_move(x + 1, y)) {
            neighbors.add(new Node(x + 1, y));
        }

        return neighbors;
    }

    /**
     * Gets successor nodes (as long as they are NOT explored and in frontier) to be explored next.
     *
     * @param node
     * @param frontier
     * @param explored
     * @return
     */
    private ArrayList<Node> expand(Node node, Deque<Node> frontier, ArrayList<Node> explored) {
        ArrayList<Node> successors = new ArrayList<>();

        for (Node n : get_neighbors(node)) {
            if (!(explored.contains(n) || frontier.contains(n))) {
                successors.add(n);
            }
        }

        return successors;
    }
}
