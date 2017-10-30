package search;

import search.constantVariable.Algorithm;

import java.util.*;
import java.util.logging.Logger;

import static search.Printer.print_animate_search;
import static search.Printer.print_hyphens;

public class UninformedSearch extends Search {
    private Deque<Node> frontier = new ArrayDeque<>();

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

            for (Node node : expand(cur_node, frontier, explored)) {
                ancestors.put(node, cur_node);

                switch (Algorithm.convert(algorithm)) {
                    case BREADTH_FIRST_SEARCH:
                        frontier.addLast(node);
                        break;
                    case DEPT_FIRST_SEARCH:
                        frontier.addFirst(node);
                        break;
                    default:
                        Logger.getLogger(UninformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
                }

            }

            print_animate_search(round++, cur_node, explored, map, frontier.contains(cur_node), algorithm, initial_node, dest_node);

            if (!cur_node.equals(initial_node)) {
                num_explored_nodes++;
            }
        }

        return path_to_dest;
    }

    /**
     * Gets neighbor nodes (Up, Down, Left and Right) of the node.
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
     * Expand nodes as long as they are  to be explored next.
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
