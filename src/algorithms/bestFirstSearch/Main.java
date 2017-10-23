package algorithms.bestFirstSearch;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        FileParser fp = new FileParser();
        try {
            fp.parse();

            System.out.println("The shortest path to the goal is: ");
            System.out.println("");
            Strategy strat = new Strategy(fp.getInitial(), fp.getGoal(), fp.getGrid());
            strat.search();
            strat.printGrid();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
