package search;

import search.constantVariable.Algorithm;
import search.constantVariable.Heuristic;
import search.constantVariable.Position;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.logging.Logger;

import static search.Printer.print_status;
import static search.Printer.print_summary;

public class UninformedSearch extends Search {
    private Deque<Node> frontier = new ArrayDeque<>();

    public UninformedSearch(String algorithm, char[][] map, int mapNumber, char initial_position, char dest_position) {
        super(algorithm, map, mapNumber, initial_position, dest_position);
    }

    public ArrayList<Node> search() {
        ArrayList<Node> explored = get_explored();

        System.out.println("START NODE: " + initial_node);

        // BFS uses Deque to store frontier
        frontier.add(initial_node);
        Node cur_node = initial_node;
        print_status(cur_node, explored, get_map(), frontier.contains(cur_node));

        // Perform search
        while (!frontier.isEmpty()) {
            // remove the the first node from the frontier
            cur_node = frontier.poll();
            explored.add(cur_node);

            if (reach_dest(cur_node)) {
                break;
            }

            // expand the nodes
            for (Node node : expand(cur_node, frontier, explored)) {
                get_prev_path().put(node, cur_node);

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
            print_status(cur_node, explored, get_map(), frontier.contains(cur_node));
            check_failure();

            // keep track of states explored
            if (!cur_node.equals(initial_node)) {
                set_explored_state(get_explored_state() + 1);
            }
        }

        return path_to_dest;
    }

    protected void check_failure() {
        if (frontier.isEmpty()) {
            System.out.println("THERE IS NO MORE NODE TO BE EXPLORED, THE SEARCH IS OVER");
        }
    }

    private ArrayList<Node> expand(Node node, Deque<Node> frontier, ArrayList<Node> explored) {
        // expand the nodes and return list of successor nodes
        ArrayList<Node> successors = new ArrayList<>();

        for (Node state : get_next_states(node)) {
            if (!explored.contains(state) && !frontier.contains(state)) {
                successors.add(state);
            }
        }

        return successors;
    }
}
