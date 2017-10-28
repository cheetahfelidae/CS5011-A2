package search;

import java.util.ArrayList;
import java.util.Deque;

public class Printer {

    public static void printStatus(char goal, Node currentNode, ArrayList<Node> explored, char[][] map, Deque<Node> frontier) {
        // print the current node, frontier, explored
        System.out.println("--------------------------------------");
        String printOut;
        Node node;

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                node = new Node(i, j);
                if (node.equals(currentNode)) {
                    printOut = "C";
                } else if (frontier.contains(node)) {
                    printOut = "F";
                } else if (explored.contains(node)) {
                    printOut = "E";
                } else {
                    printOut = Character.toString(map[i][j]);
                }
                System.out.printf("%-4s", printOut);
            }
            System.out.println("");
            printOut = "";
        }
        System.out.println("--------------------------------------");

    }

    public static void printPath(String objective, char[][] map, int map_no, ArrayList<Node> directions, String algorithm) {
        // print path from initial position to goal
        System.out.println("--------------------------------------");
        if (algorithm.equals("BFS")) {
            System.out.println("Breadth First Search");
        } else {
            System.out.println("Depth First Search");
        }
        System.out.println("Map " + map_no);
        System.out.println("Objective: " + objective);
        System.out.println("--------------------------------------");
        String line = "";
        Node node;
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                node = new Node(i, j);
                if (directions.contains(node) && directions.indexOf(node) == 0) {
                    line = "I";
                } else if (directions.contains(node) && map[i][j] == 'G' &&
                        directions.indexOf(node) == directions.size()-1) {
                    line = "G";
                } else if (directions.contains(node) && map[i][j] == 'B' &&
                        directions.indexOf(node) == directions.size()-1) {
                    line = "B";
                }else if (directions.contains(node)) {
                    line = Integer.toString(directions.indexOf(node));
                }else if (!directions.contains(node) && map[i][j] == 'I') {
                    line = "O";
                }else {
                    line = map[i][j] + " ";
                }
                System.out.printf("%-4s", line);
            }
            System.out.println("");
            line = "";
        }
        System.out.println("--------------------------------------");
    }

}
