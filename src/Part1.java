import search.UninformedSearch;

import java.util.logging.Logger;

/**
 * To run Uninformed search, three command-line arguments are required.
 * - 1st arg : the desired algorithm, "BFS" for Best First Search algorithm or "DFS" Depth First Search algorithm.
 * - 2nd arg : the desired map number, any number from 1 - 6 only.
 */
public class Part1 {

    public static void main(String[] args) {
        try {
            String algorithm = args[0];
            int map_no = Integer.parseInt(args[1]);

            System.out.println("Map " + map_no);
            System.out.println("Algorithm: " + algorithm);

            UninformedSearch search = new UninformedSearch(algorithm, Map.getMap(map_no), map_no);

            // search for Bob, then search for safe goal position
            search.search('B');

            // only search for goal position if the robot managed to find a way to get to Bob
            if (!search.getDirectionBob().isEmpty()) {
                search.search('G');
            }

            search.printSummary();

        } catch (Exception e) {
            Logger.getLogger(Part1.class.getName()).severe("java part1 <algorithm> <map_no>");
        }
    }
}
