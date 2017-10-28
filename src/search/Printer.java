package search;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Printer {

    // TODO - to be improved
    public static void printStatus(Node currentNode, ArrayList<Node> explored, char[][] map, boolean frontier_contains_node) {
        // print the current node, frontier, explored
        System.out.println("--------------------------------------");
        String printOut;
        Node node;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                node = new Node(i, j);
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

    public static void printPath(String objective, char[][] map, int map_no, ArrayList<Node> directions, String algorithm, String heuristic) {
        // print path from initial position to goal
        System.out.println("--------------------------------------");
        switch (algorithm) {
            case "BFS":
                System.out.println("Breadth First Search");
                break;
            case "DFS":
                System.out.println("Depth First Search");
                break;
            case "BestFS":
                System.out.println("Best First Search");
                break;
            case "A*":
                System.out.println("A*");
                break;
            default:
                Logger.getLogger(Printer.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
        }
        System.out.println("Map " + map_no);
        System.out.println("Objective: " + objective);

        switch (heuristic) {
            case "M":
                System.out.println("Heuristic: Manhattan distance");
                break;
            case "E":
                System.out.println("Heuristic: Euclidean distance");
                break;
            case "C":
                System.out.println("Heuristic: Combination");
                break;
            case "N/A":
                break;
            default:
                Logger.getLogger(Printer.class.getName()).severe("HEURISTIC " + heuristic + " IS UNRECOGNISED");
        }

        System.out.println("--------------------------------------");
        String line;
        Node node;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                node = new Node(i, j);
                if (directions.contains(node) && directions.indexOf(node) == 0) {
                    line = "I";
                } else if (directions.contains(node) && map[i][j] == 'G' &&
                        directions.indexOf(node) == directions.size() - 1) {
                    line = "G";
                } else if (directions.contains(node) && map[i][j] == 'B' &&
                        directions.indexOf(node) == directions.size() - 1) {
                    line = "B";
                } else if (directions.contains(node)) {
                    line = Integer.toString(directions.indexOf(node));
                } else if (!directions.contains(node) && map[i][j] == 'I') {
                    line = "O";
                } else {
                    line = map[i][j] + " ";
                }
                System.out.printf("%-4s", line);
            }
            System.out.println();
        }
        System.out.println("--------------------------------------");
    }

    public static void print_summary(String algorithm, ArrayList<Node> directionBob, ArrayList<Node> directionGoal, char[][] map, int map_no, int explored_state, String heuristic) {
        // print the search summary once the robot reached the goal
        int pathCost = (directionBob.size() + directionGoal.size()) - 2;
        System.out.println("\nSummary");
        if (directionBob.isEmpty() || directionGoal.isEmpty()) {
            System.out.println("Unsuccessful search operation");
            System.out.println("Algorithm: " + algorithm);
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
        System.out.println("State explored: " + explored_state + "\n");
    }

}
