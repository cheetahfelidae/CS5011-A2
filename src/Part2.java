import search.InformedSearch;
import search.Node;
import search.UninformedSearch;
import search.constantVariable.Heuristic;
import search.constantVariable.Map;

import java.util.ArrayList;
import java.util.logging.Logger;

import static search.Printer.print_summary;
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

    public static void main(String[] args) {
        try {
            String algorithm = args[0];
            char heuristic = args[1].charAt(0);
            int map_no = Integer.parseInt(args[2]);
            char[][] map = Map.getMap(map_no);

            System.out.println("MAP: " + map_no);
            System.out.println("ALGORITHM: " + algorithm);

            // FIND PATH TO BOB
            InformedSearch bob_search = new InformedSearch(algorithm, heuristic, map, map_no, ROBOT_POSITION.value(), BOB_POSITION.value());
            ArrayList<Node> path_to_bob = bob_search.search();
            print_summary(algorithm, map, map_no, path_to_bob, bob_search.get_explored_state(), Heuristic.NONE.value());

            // FIND PATH FROM BOB TO GOAL
            if (!path_to_bob.isEmpty()) {
                InformedSearch goal_search = new InformedSearch(algorithm, heuristic, map, map_no, BOB_POSITION.value(), GOAL_POSITION.value());
                ArrayList<Node> path = goal_search.search();
                print_summary(algorithm, map, map_no, path, goal_search.get_explored_state(), Heuristic.NONE.value());
            }

        } catch (Exception e) {
            Logger.getLogger(Part2.class.getName()).warning("java part2 <algorithm> <heuristic> <map_no>");
        }
    }

}
