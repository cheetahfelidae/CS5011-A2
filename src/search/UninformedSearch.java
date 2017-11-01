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
     * 1. Initiate Frontier container with initial node.
     * 2. Loop Until Frontier is empty.
     * - Remove the first node X from Frontier.
     * - If X is the destination, the search have succeeded, otherwise find all X-neighbor nodes.
     * - Merge*** the set of new nodes into Frontier (if node is not explored, and not in Frontier).
     * *** If Breadth first search is requested, new nodes will be inserted into at the end of Frontier (FIFO)
     * while if Depth first search is requested, they will be inserted at the head of the queue (LIFO).
     *
     * @return
     */
    public ArrayList<Node> search() {
        Map<Node, Node> ancestors = new HashMap<>();// used in create_path_to_dest() to track from destination to the starting node.
        ArrayList<Node> path_to_dest = new ArrayList<>();
        Deque<Node> frontier = new ArrayDeque<>();
        ArrayList<Node> explored_nodes = new ArrayList<>();

        frontier.add(initial_node);

        int round = 1;
        while (!frontier.isEmpty()) {
            Node cur_node = frontier.poll();

            explored_nodes.add(cur_node);

            if (cur_node.equals(dest_node)) {// GOAL-TEST

                path_to_dest = create_path_to_dest(ancestors, cur_node);

                print_animate_result(round, cur_node, explored_nodes, map, algorithm, initial_node, dest_node);

                System.out.println("DESTINATION IS FOUND");
                print_hyphens(map.length * 3);

                break;
            }

            for (Node successor : expand(cur_node, frontier, explored_nodes)) {
                ancestors.put(successor, cur_node);

                switch (Algorithm.convert(algorithm)) {
                    case BREADTH_FIRST_SEARCH:
                        frontier.addLast(successor);
                        break;
                    case DEPTH_FIRST_SEARCH:
                        frontier.addFirst(successor);
                        break;
                    default:
                        Logger.getLogger(UninformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
                }

            }

            print_animate_result(round++, cur_node, explored_nodes, map, algorithm, initial_node, dest_node);
        }

        // the number of explored nodes excludes the initial node.
        set_num_explored_nodes(explored_nodes.size() - 1);

        return path_to_dest;
    }

    /**
     * Get the node's neighbors (North, South, West and East nodes).
     *
     * @param node
     * @return
     */
    protected ArrayList<Node> get_neighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        int x = node.getX(), y = node.getY();

        // North
        if (is_legal_move(x - 1, y)) {
            neighbors.add(new Node(x - 1, y));
        }

        // West
        if (is_legal_move(x, y - 1)) {
            neighbors.add(new Node(x, y - 1));
        }

        // East
        if (is_legal_move(x, y + 1)) {
            neighbors.add(new Node(x, y + 1));
        }

        // South
        if (is_legal_move(x + 1, y)) {
            neighbors.add(new Node(x + 1, y));
        }

        return neighbors;
    }

    /**
     * Get successor nodes (as long as they are NOT explored and in Frontier to avoid the infinite loop) to be explored next.
     *
     * @param node
     * @param frontier
     * @param explored_nodes
     * @return
     */
    private ArrayList<Node> expand(Node node, Deque<Node> frontier, ArrayList<Node> explored_nodes) {
        ArrayList<Node> successors = new ArrayList<>();

        for (Node neighbor : get_neighbors(node)) {
            if (!explored_nodes.contains(neighbor) && !frontier.contains(neighbor)) {
                successors.add(neighbor);
            }
        }

        return successors;
    }
}
