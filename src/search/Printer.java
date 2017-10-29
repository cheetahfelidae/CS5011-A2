package search;

import search.constantVariable.Algorithm;
import search.constantVariable.Heuristic;
import java.util.ArrayList;
import java.util.logging.Logger;
import static search.constantVariable.Position.*;



public class Printer {

    // TODO - to be improved
    public static void printStatus(Node currentNode, ArrayList<Node> explored, char[][] map, boolean frontier_contains_node) {
        // print the current node, frontier, explored
        System.out.println("--------------------------------------");

        String printOut;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Node node = new Node(i, j);
                if (node.equals(currentNode)) {
                    printOut = "C";
                } else if (frontier_contains_node) {
                    printOut = "F";
                } else if (explored.contains(node)) {
                    printOut = "E";
                } else {
                    printOut = Character.toString(map[i][j]);
                }
                System.out.printf("%-4s", printOut);
            }
            System.out.println("");
        }

        System.out.println("--------------------------------------");

    }

    public static void printPath(String objective, char[][] map, int map_no, ArrayList<Node> directions, String algorithm, char heuristic) {
        // print path from initial position to goal
        System.out.println("--------------------------------------");
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
        System.out.println("search.constantVariable.Map " + map_no);
        System.out.println("Objective: " + objective);

        switch (Heuristic.convert(heuristic)) {
            case MANHATTAN:
                System.out.println("Heuristic: Manhattan distance");
                break;
            case EUCLIDEAN:
                System.out.println("Heuristic: Euclidean distance");
                break;
            case COMBINATION:
                System.out.println("Heuristic: Combination");
                break;
            case NONE:
                break;
            default:
                Logger.getLogger(Printer.class.getName()).severe("HEURISTIC " + heuristic + " IS UNRECOGNISED");

        }

        System.out.println("--------------------------------------");

        String position;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Node node = new Node(i, j);
                if (directions.contains(node)) {
                    if (directions.indexOf(node) == 0) {
                        position = ROBOT_POSITION.value() + "";
                    } else if (map[i][j] == GOAL_POSITION.value() && directions.indexOf(node) == directions.size() - 1) {
                        position = GOAL_POSITION.value() + "";
                    } else if (map[i][j] == BOB_POSITION.value() && directions.indexOf(node) == directions.size() - 1) {
                        position = BOB_POSITION.value() + "";
                    } else {
                        position = Integer.toString(directions.indexOf(node));
                    }
                } else if (map[i][j] == ROBOT_POSITION.value()) {
                    position = "O";
                } else {
                    position = map[i][j] + " ";
                }

                System.out.printf("%-4s", position);
            }
            System.out.println();
        }

        System.out.println("--------------------------------------");
    }

    public static void print_summary(String algorithm, ArrayList<Node> directionBob, ArrayList<Node> directionGoal, char[][] map, int map_no, int explored_state, char heuristic) {
        // print the search summary once the robot reached the goal
        int pathCost = (directionBob.size() + directionGoal.size()) - 2;
        System.out.println();
        System.out.println("Summary");

        if (directionBob.isEmpty() || directionGoal.isEmpty()) {
            System.out.println("Unsuccessful search operation");
            System.out.println("search.constantVariable.Algorithm: " + algorithm);
            if (directionBob.isEmpty()) {
                System.out.println("Cannot get to Bob");
            } else {
                printPath("Find Bob", map, map_no, directionBob, algorithm, heuristic);
                System.out.println("Cannot get Bob to safety");
            }
        } else {
            printPath("Find Bob", map, map_no, directionBob, algorithm, heuristic);
            printPath("Find safe zone", map, map_no, directionGoal, algorithm, heuristic);
            System.out.println("Path cost: " + pathCost);
        }

        System.out.println("State explored: " + explored_state);
        System.out.println();
    }

}
