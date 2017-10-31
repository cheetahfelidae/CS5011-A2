import java.util.logging.Logger;

/**
 * To run Search, three command-line arguments are required.
 * - 1st arg : the desired algorithm, "BestFS" for Best First Search algorithm or "A*" for A* algorithm.
 * - 2nd arg : the desired heuristic_method, 'M' for Manhattan Distance, 'E' for Euclidian distance or 'C' for the combination of Manhattan and Euclidian distance.
 * - 3rd arg : the desired map number, any number from 1 - 6 only.
 */
public class Part3 {

    /**
     *
     * @param args three arguments are required.
     */
    public static void main(String[] args) {
        try {


        } catch (Exception e) {
            Logger.getLogger(Part1.class.getName()).severe("java part3 <algorithm> <heuristic_method> <map_no>");
        }
    }

}
