package search;

import search.constantVariable.Algorithm;
import search.constantVariable.Heuristic;
import search.constantVariable.Position;

import java.util.ArrayList;
import java.util.logging.Logger;

import static search.constantVariable.Position.*;

/**
 * This class is responsible for printing a result of the search process to the commandline terminal.
 */
public class Printer {
    public static final int ONE_SECOND = 0;
    private static final String TWO_SPACES = "  ";

    public static void print_hyphens(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void print_asterisks(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    public static void print_timer(String prefix_text, int seconds) {
        for (int i = seconds; i > 0; i--) {
            System.out.print(prefix_text + " " + i + " SECONDS..");
            sleep(ONE_SECOND);
            clear_screen();
        }
        System.out.println();
    }

    /**
     * This method is used to clean screen to be able to render a motion.
     */
    public static void clear_screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * This makes the programme sleep for a specific amount of time.
     *
     * @param millis the number of milli seconds of the thread sleep.
     */
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void print_search_result(char[][] map, ArrayList path_to_dest, char initial_pos, char dest_pos) {
        System.out.print(path_to_dest.isEmpty() ? "UNABLE TO FIND A PATH " : "FOUND A PATH ");
        if (initial_pos == ROBOT_POSITION.value() && dest_pos == BOB_POSITION.value()) {
            System.out.println("FROM ROBOT TO BOB");
        } else if (initial_pos == BOB_POSITION.value() && dest_pos == GOAL_POSITION.value()) {
            System.out.println("FROM BOB TO GOAL");
        } else {
            Logger.getLogger(Printer.class.getName()).severe("START NODE TO DESTINATION IS UNRECOGNISED");
        }
        print_hyphens(map.length * 3);
    }

    /**
     * This is used to render a motion search.
     * It prints the current node, the list of nodes (states) expanded.
     *
     * @param round
     * @param cur_node
     * @param explored
     * @param map
     * @param algorithm
     * @param initial_node
     * @param dest_node
     */
    public static void print_animate_result(int round, Node cur_node, ArrayList<Node> explored, char[][] map, String algorithm, Node initial_node, Node dest_node) {
        if (round == 1) {
            clear_screen();
            print_timer("THE SEARCH STARTS IN", 5);
        } else {
            sleep(ONE_SECOND / 2);
        }
        clear_screen();

        print_hyphens(map.length * 3);
        System.out.println("INITIAL POSITION: " + initial_node);

        System.out.println("OBJECTIVE: ");
        print_full_algo_name(algorithm);
        int x = dest_node.getX(), y = dest_node.getY();
        switch (Position.convert(map[x][y])) {
            case BOB_POSITION:
                System.out.println("Robot(" + ROBOT_POSITION.value() + ") Finding Bob(" + BOB_POSITION.value() + ")..");
                break;
            case GOAL_POSITION:
                System.out.println("Robot taking Bob(" + BOB_POSITION.value() + ") to Goal(" + GOAL_POSITION.value() + ")..");
                break;
            default:
                Logger.getLogger(Printer.class.getName()).warning("FIND WHOM (" + x + "," + y + ") IS UNRECOGNISED");
        }

        System.out.println("ROUND: " + round);
        print_hyphens(map.length * 3);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Node node = new Node(i, j);

                if (node.equals(cur_node)) {
                    System.out.print(CURRENT_POSITION.value() + TWO_SPACES);

                } else if (explored.contains(node) && map[i][j] != ROBOT_POSITION.value() && map[i][j] != BOB_POSITION.value() && map[i][j] != GOAL_POSITION.value()) {
                    System.out.print(EXPLORED_POSITION.value() + TWO_SPACES);

                } else {
                    System.out.print(map[i][j] + TWO_SPACES);

                }

            }
            System.out.println();
        }

        print_hyphens(map.length * 3);
    }

    private static void print_full_algo_name(String algorithm) {
        System.out.print("ALGORITHM: ");
        switch (Algorithm.convert(algorithm)) {
            case BREADTH_FIRST_SEARCH:
                System.out.println("Breadth First Search");
                break;
            case DEPTH_FIRST_SEARCH:
                System.out.println("Depth First Search");
                break;
            case BEST_FIRST_SEARCH:
                System.out.println("Best First Search");
                break;
            case A_STAR:
                System.out.println("A*");
                break;
            default:
                Logger.getLogger(Printer.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
        }
    }

    private static void print_path(char[][] map, ArrayList<Node> path) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Node node = new Node(i, j);
                if (path.contains(node)) {

                    int num = path.indexOf(node);
                    if (num == 0 || num == path.size() - 1) {
                        System.out.print(map[i][j] + TWO_SPACES);
                    } else {
                        System.out.print(num < 10 ? num + TWO_SPACES : num + " ");
                    }

                } else if (map[i][j] == AVAILABLE_POSITION.value()) {
                    System.out.print(UNSELECTED_POSITION.value() + TWO_SPACES);
                } else {
                    System.out.print(map[i][j] + TWO_SPACES);
                }
            }
            System.out.println();
        }
    }

    /**
     * Print the main summary of the path (if found) from either the robot to Bob or Bob to the goal.
     * the quality of the solution (i.e. the cost/length of the robot route),
     * and efficiency indicated by the number of nodes explored by the selected algorithm.
     *
     * @param map
     * @param path
     * @param num_explored_nodes
     * @param heuristic
     */
    public static void print_sub_summary(char[][] map, ArrayList<Node> path, char initial_pos, char dest_pos, int num_explored_nodes, char heuristic) {
        if (!path.isEmpty()) {
            print_asterisks(map.length * 6);

            if (initial_pos == ROBOT_POSITION.value() && dest_pos == BOB_POSITION.value()) {
                System.out.println("SUB SUMMARY - ROBOT -> BOB");
            } else if (initial_pos == BOB_POSITION.value() && dest_pos == GOAL_POSITION.value()) {
                System.out.println("SUB SUMMARY - BOB -> GOAL");
            } else {
                Logger.getLogger(Printer.class.getName()).severe("START NODE TO DESTINATION IS UNRECOGNISED");
            }

            switch (Heuristic.convert(heuristic)) {
                case MANHATTAN:
                    System.out.println("HEURISTIC: Manhattan Distance");
                    break;
                case EUCLIDEAN:
                    System.out.println("HEURISTIC: Euclidean Distance");
                    break;
                case COMBINATION:
                    System.out.println("HEURISTIC: Combination of Manhattan and Euclidean Distance");
                    break;
                case NONE:
                    break;
                default:
                    Logger.getLogger(Printer.class.getName()).severe("HEURISTIC " + heuristic + " IS UNRECOGNISED");

            }

            print_hyphens(map.length * 3);
            print_path(map, path);
            print_hyphens(map.length * 3);
            System.out.printf("PATH COST (excluding the initial node): %d - 1 = %d\n", path.size(), path.size() - 1);
            System.out.println("#EXPLORED NODES: " + num_explored_nodes);
            print_asterisks(map.length * 6);
        }
    }

    /**
     * Similar to print_sub_summary() but it prints summary of the two paths, i.e. the path from the initial node to the goal node,
     * and shows the total cost/length of the robot route and the total number of explored nodes after the search.
     *
     * @param map
     * @param path_to_bob
     * @param num_explored_nodes_to_bob
     * @param path_to_goal
     * @param num_explored_nodes_to_goal
     */
    public static void print_summary(char[][] map, ArrayList<Node> path_to_bob, int num_explored_nodes_to_bob, ArrayList<Node> path_to_goal, int num_explored_nodes_to_goal) {
        print_asterisks(map.length * 6);

        if (path_to_bob == null || path_to_bob.isEmpty()) {
            System.out.println("UNABLE TO FIND A PATH FROM ROBOT TO BOB");
        }
        if (path_to_goal == null || path_to_goal.isEmpty()) {
            System.out.println("UNABLE TO FIND A PATH FROM BOB TO GOAL");
        }

        if (!(path_to_bob.isEmpty() || path_to_goal.isEmpty())) {
            System.out.println("MAIN SUMMARY - ROBOT -> BOB -> GOAL");

            ArrayList<Node> init_to_goal_path = new ArrayList<>();
            init_to_goal_path.addAll(path_to_bob);
            init_to_goal_path.remove(init_to_goal_path.size() - 1);
            init_to_goal_path.addAll(path_to_goal);

            print_hyphens(map.length * 3);
            print_path(map, init_to_goal_path);
            print_hyphens(map.length * 3);

            System.out.printf("PATH COST (excluding the initial and Bob node): %d + %d - 2 = %d\n", path_to_bob.size(), path_to_goal.size(), path_to_bob.size() + path_to_goal.size() - 2);
            System.out.printf("#EXPLORED NODES: %d + %d = %d\n", num_explored_nodes_to_bob, num_explored_nodes_to_goal, num_explored_nodes_to_bob + num_explored_nodes_to_goal);
        } else {
            System.out.println("THE SEARCH IS FAILED..");
        }
        print_asterisks(map.length * 6);
    }

}
