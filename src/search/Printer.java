package search;

import search.constantVariable.Algorithm;
import search.constantVariable.Heuristic;

import java.util.ArrayList;
import java.util.logging.Logger;

import static search.constantVariable.Position.*;


public class Printer {
    private static final String TWO_SPACES = "  ";

    public static void print_hyphens(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    // TODO - to be improved
    public static void print_status(Node currentNode, ArrayList<Node> explored, char[][] map, boolean frontier_contains_node) {
        // print the current node, frontier, explored
        print_hyphens(map.length * 3);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Node node = new Node(i, j);
                if (node.equals(currentNode)) {
                    System.out.print(CURRENT_POSITION.value() + TWO_SPACES);
                } else if (frontier_contains_node) {
                    System.out.print(FRONTIER_POSITION.value() + TWO_SPACES);
                } else if (explored.contains(node)) {
                    System.out.print(EXPLORED_POSITION.value() + TWO_SPACES);
                } else {
                    System.out.print(map[i][j] + TWO_SPACES);
                }
            }
            System.out.println();
        }

        print_hyphens(map.length * 3);
    }

    public static void print_full_algo_name(String algorithm) {
        System.out.print("ALGORITHM: ");
        switch (Algorithm.convert(algorithm)) {
            case BREADTH_FIRST_SEARCH:
                System.out.println("Breadth First Search");
                break;
            case DEPT_FIRST_SEARCH:
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

    public static void print_summary(char[][] map, ArrayList<Node> path, int num_explored_nodes, char heuristic) {
        print_hyphens(map.length * 3);

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

        System.out.println("PATH COST: " + (path.size() - 2));
        System.out.println("NUMBER OF VISITED SEARCH STATES: " + num_explored_nodes);

        print_hyphens(map.length * 3);
    }


//    public static void print_summary(String algorithm, ArrayList<Node> path_to_bob, ArrayList<Node> path_to_goal, char[][] map, int map_no, int explored_state, char heuristic) {
//        // print the search summary once the robot reached the goal
//        print_hyphens(map.length * 3);
//        System.out.println("Summary");
//
//        if (path_to_bob.isEmpty() || path_to_goal.isEmpty()) {
//            System.out.println("Unsuccessful search operation");
//            System.out.println("ALGORITHM: " + algorithm);
//            if (path_to_bob.isEmpty()) {
//                System.out.println("Cannot get to Bob");
//            } else {
//                print_path("Find Bob", map, map_no, path_to_bob, algorithm, heuristic);
//                System.out.println("Cannot get Bob to safety");
//            }
//        } else {
//            print_path("Find Bob", map, map_no, path_to_bob, algorithm, heuristic);
//            print_path("Find safe zone", map, map_no, path_to_goal, algorithm, heuristic);
//            System.out.println("Path cost: " + ((path_to_bob.size() + path_to_goal.size()) - 2));
//        }
//
//        System.out.println("State explored: " + explored_state);
//        print_hyphens(map.length * 3);
//    }

}
