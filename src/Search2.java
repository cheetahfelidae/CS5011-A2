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
 * To be able to run Informed Search programme, three command-line arguments are required.
 * - 1st arg : the desired algorithm, "BestFS" for Best First Search algorithm or "A*" for A* algorithm.
 * - 2nd arg : the desired heuristic_method, 'M' for Manhattan Distance, 'E' for Euclidian distance or 'C' for the combination of Manhattan and Euclidian distance.
 * - 3rd arg : the desired map number, any number from 1 - 6 only.
 */
public class Search2 {

    /**
     * The programme is designed to search for Bob first and if Bob is found and can be reached, then it will search for the safety goal position from the Bob's position.
     *
     * @param args three arguments are required.
     */
    public static void main(String[] args) {
        try {
            String algorithm = args[0];
            char heuristic_method = args[1].charAt(0);
            int map_no = Integer.parseInt(args[2]);
            char[][] map = Map.get_map(map_no);

            InformedSearch bob_search = new InformedSearch(algorithm, heuristic_method, map, ROBOT_POSITION.value(), BOB_POSITION.value());
            ArrayList<Node> path_to_bob = bob_search.search();
            print_sub_summary(map, path_to_bob, ROBOT_POSITION.value(), BOB_POSITION.value(), bob_search.get_num_explored_nodes(), Heuristic.NONE.value());

            InformedSearch goal_search = new InformedSearch(algorithm, heuristic_method, map, BOB_POSITION.value(), GOAL_POSITION.value());
            ArrayList<Node> path_to_goal = null;
            if (!path_to_bob.isEmpty()) {
                System.out.println("CLEAR SCREEN IN 15 SECONDS");
                sleep(ONE_SECOND * 15);

                path_to_goal = goal_search.search();
                print_sub_summary(map, path_to_goal, BOB_POSITION.value(), GOAL_POSITION.value(), goal_search.get_num_explored_nodes(), Heuristic.NONE.value());
            }

            System.out.println("\n\n");
            print_summary(map, path_to_bob, bob_search.get_num_explored_nodes(), path_to_goal, goal_search.get_num_explored_nodes());

        } catch (Exception e) {
            Logger.getLogger(Search2.class.getName()).warning("java Search2 <algorithm> <heuristic_method> <map_no>");
        }
    }

}
