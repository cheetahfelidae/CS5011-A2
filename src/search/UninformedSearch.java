package search;

import search.constantVariable.Algorithm;
import search.constantVariable.Heuristic;
import search.constantVariable.Position;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.logging.Logger;

public class UninformedSearch extends Search {
    private Deque<Node> frontier = new ArrayDeque<>();

    public UninformedSearch(String algorithm, char[][] map, int mapNumber) {
        super(algorithm, map, mapNumber);
    }

    public void search(char goal) {
        clear_data();

        ArrayList<Node> explored = get_explored();
        Node initial_node = get_initial_node();
        set_dest_node(goal);

        System.out.println("START NODE: " + initial_node);

        // BFS uses Deque to store frontier
        frontier.add(initial_node);
        Node cur_node = initial_node;
        Printer.print_status(cur_node, explored, get_map(), frontier.contains(cur_node));

        // Perform search
        while (!frontier.isEmpty()) {
            // remove the the first node from the frontier
            cur_node = frontier.poll();
            explored.add(cur_node);

            if (reach_goal(cur_node, goal)) {
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
            Printer.print_status(cur_node, explored, get_map(), frontier.contains(cur_node));
            check_failure(goal);

            // keep track of states explored
            if (!cur_node.equals(initial_node)) {
                set_explored_state(get_explored_state() + 1);
            }
        }
    }

    protected void check_failure(char dest) {
        // if there's no more nodes to be explored, the search is failed
        if (frontier.isEmpty()) {
            switch (Position.convert(dest)) {
                case BOB_POSITION:
                    Logger.getLogger(UninformedSearch.class.getName()).warning("BOB IS NEVER REACHED");
                    break;
                case GOAL_POSITION:
                    Logger.getLogger(UninformedSearch.class.getName()).warning("SAFETY GOAL IS NEVER REACHED");
                    break;
                default:
                    Logger.getLogger(UninformedSearch.class.getName()).warning("DESTINATION " + dest + " IS UNRECOGNISED");
            }
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

    protected void clear_data() {
        // clear everything in order to prepare for new search operation
        frontier.clear();
        super.clear_data();
    }

    public void process() {
        super.process();
        Printer.print_summary(algorithm, path_to_bob, path_to_goal, get_map(), get_map_no(), get_explored_state(), Heuristic.NONE.value());
    }
}
