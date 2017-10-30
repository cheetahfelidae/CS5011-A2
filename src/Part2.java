import search.InformedSearch;
import search.Node;
import search.constantVariable.Heuristic;
import search.constantVariable.Map;

import java.util.ArrayList;
import java.util.logging.Logger;

import static search.Printer.*;
import static search.constantVariable.Position.BOB_POSITION;
import static search.constantVariable.Position.GOAL_POSITION;
import static search.constantVariable.Position.ROBOT_POSITION;

/**
 * To run Informed Search, three command-line arguments are required.
 * - 1st arg : the desired algorithm, "BestFS" for Best First Search algorithm or "A*" for A* algorithm.
 * - 2nd arg : the desired heuristic, 'M' for Manhattan Distance, 'E' for Euclidian distance or 'C' for the combination of Manhattan and Euclidian distance.
 * - 3rd arg : the desired map number, any number from 1 - 6 only.
 */
public class Part2 {

    /**
     * The programme is designed to search for Bob first, if Bob is found and can be reached, then it will search for the safety goal position from the Bob's position.
     *
     * @param args three arguments are required.
     */
    public static void main(String[] args) {
        try {
            String algorithm = args[0];
            char heuristic = args[1].charAt(0);
            int map_no = Integer.parseInt(args[2]);
            char[][] map = Map.getMap(map_no);

            InformedSearch bob_search = new InformedSearch(algorithm, heuristic, map, ROBOT_POSITION.value(), BOB_POSITION.value());
            ArrayList<Node> path_to_bob = bob_search.search();
            print_sub_summary(map, path_to_bob, bob_search.get_num_explored_nodes(), Heuristic.NONE.value());

            InformedSearch goal_search = new InformedSearch(algorithm, heuristic, map, BOB_POSITION.value(), GOAL_POSITION.value());
            ArrayList<Node> path_to_goal = null;
            if (!path_to_bob.isEmpty()) {
                path_to_goal = goal_search.search();
                print_sub_summary(map, path_to_goal, goal_search.get_num_explored_nodes(), Heuristic.NONE.value());
            }

            System.out.println();
            print_summary(map, path_to_bob, bob_search.get_num_explored_nodes(), path_to_goal, goal_search.get_num_explored_nodes());

        } catch (Exception e) {
            Logger.getLogger(Part2.class.getName()).warning("java part2 <algorithm> <heuristic> <map_no>");
        }
    }

}
