import search.InformedSearch;

import java.util.logging.Logger;

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
            String heuristic = args[1];
            int map_no = Integer.parseInt(args[2]);

            System.out.println("Map " + map_no);
            System.out.println("Algorithm: " + algorithm);

            InformedSearch search = new InformedSearch(algorithm, heuristic, Map.getMap(map_no), map_no);

            // search for Bob, then search for safe goal position
            search.search('B');

            // only search for goal position if the robot managed to find a way to get to Bob
            if (!search.getDirectionBob().isEmpty()) {
                search.search('G');
            }

            search.printSummary();

        } catch (Exception e) {
            Logger.getLogger(Part2.class.getName()).warning("java part2 <algorithm> <heuristic> <map_no>");
        }
    }

}