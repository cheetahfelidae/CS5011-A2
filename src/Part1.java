import search.Node;
import search.UninformedSearch;
import search.constantVariable.Heuristic;
import search.constantVariable.Map;

import java.util.ArrayList;
import java.util.logging.Logger;

import static search.Printer.*;
import static search.constantVariable.Position.BOB_POSITION;
import static search.constantVariable.Position.GOAL_POSITION;
import static search.constantVariable.Position.ROBOT_POSITION;

/**
 * To run Uninformed search, three command-line arguments are required.
 * - 1st arg : the desired algorithm, "BFS" for Best First Search algorithm or "DFS" Depth First Search algorithm.
 * - 2nd arg : the desired map number, any number from 1 - 6 only.
 */
public class Part1 {

    /**
     * The programme is designed to search for Bob first, if Bob is found and can be reached, then it will search for the safety goal position from the Bob's position.
     *
     * @param args two arguments are required.
     */
    public static void main(String[] args) {
        try {
            String algorithm = args[0];
            int map_no = Integer.parseInt(args[1]);
            char[][] map = Map.getMap(map_no);

            UninformedSearch bob_search = new UninformedSearch(algorithm, map, ROBOT_POSITION.value(), BOB_POSITION.value());
            ArrayList<Node> path_to_bob = bob_search.search();
            print_sub_summary(map, path_to_bob, bob_search.get_num_explored_nodes(), Heuristic.NONE.value());

            UninformedSearch goal_search = new UninformedSearch(algorithm, map, BOB_POSITION.value(), GOAL_POSITION.value());
            ArrayList<Node> path_to_goal = null;
            if (!path_to_bob.isEmpty()) {
                path_to_goal = goal_search.search();
                print_sub_summary(map, path_to_goal, goal_search.get_num_explored_nodes(), Heuristic.NONE.value());
            }

            System.out.println();
            print_summary(map, path_to_bob, bob_search.get_num_explored_nodes(), path_to_goal, goal_search.get_num_explored_nodes());

        } catch (Exception e) {
            Logger.getLogger(Part1.class.getName()).severe("java part1 <algorithm> <map_no>");
        }
    }
}
