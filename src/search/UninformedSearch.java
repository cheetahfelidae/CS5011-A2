package search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Map;
import java.util.logging.Logger;

public class UninformedSearch extends Search {
    private Deque<Node> frontier = new ArrayDeque<>();

    public UninformedSearch(String algorithm, char[][] map, int mapNumber) {
        super(algorithm, map, mapNumber);
    }

    public void search(char goal) {
        clearData();

        Map<Node, Node> prev = getPrev();
        ArrayList<Node> successors;
        ArrayList<Node> explored = getExplored();
        Node start_node = getInitial_node();
        set_dest_node(goal);

        System.out.println("Start node: " + start_node + "\n");
        // BFS uses Deque to store frontier
        frontier.add(start_node);
        Node cur_node = start_node;
        Printer.printStatus(cur_node, explored, getMap(), frontier.contains(cur_node));

        // Perform search
        while (!frontier.isEmpty()) {
            // remove the the first node from the frontier
            cur_node = frontier.poll();
            explored.add(cur_node);

            if (reach_goal(cur_node, goal)) {
                break;
            }

            // expand the nodes
            successors = expand(cur_node, frontier, explored);
            for (Node node : successors) {
                prev.put(node, cur_node);
                if (algorithm.equals("BFS")) {
                    frontier.addLast(node);
                } else {
                    frontier.addFirst(node);
                }
            }
            Printer.printStatus(cur_node, explored, getMap(), frontier.contains(cur_node));
            check_failure(goal);

            // keep track of states explored
            if (!cur_node.equals(start_node)) {
                this.setExplored_state(this.get_explored_state() + 1);
            }
        }
    }

    protected void check_failure(char dest) {
        // if there's no more nodes to be explored, the search is failed
        if (frontier.isEmpty()) {
            switch (dest) {
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

    protected void clearData() {
        // clear everything in order to prepare for new search operation
        frontier.clear();
        super.clearData();
    }

    public void process() {
        super.process();
        Printer.print_summary(algorithm, directionBob, directionGoal, getMap(), getMap_no(), get_explored_state(), "N/A");
    }
}
