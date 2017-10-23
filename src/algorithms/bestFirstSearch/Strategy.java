package algorithms.bestFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

// https://codereview.stackexchange.com/questions/122525/shortest-path-navigation-across-a-grid-using-best-first-search

public class Strategy {
    private Node goal;
    private Node[][] map;
    private ArrayList<Node> closed = new ArrayList<>();
    private NodeComparator nc = new NodeComparator();
    private PriorityQueue<Node> open = new PriorityQueue<>(nc);
    private boolean pathFound = false;

    public Strategy(Node initial, Node goal, Node[][] map) {
        this.goal = goal;
        this.map = map;
        open.add(initial);
    }

    private void evaluate(Node current) {
        current.setCost(Math.sqrt(Math.pow(current.getX() - goal.getX(), 2) + Math.pow(current.getY() - goal.getY(), 2)));
    }

    private void getSuccessors(Node n) {
        for (Node neighbor : n.getNeighbors()) {//evaluate cost of all neighbors, set their parent, and add them to the open list
            if (!open.contains(neighbor) && !closed.contains(neighbor)) {
                evaluate(neighbor);
                open.add(neighbor);
                neighbor.setParent(n);
            }
        }
    }

    private void getPath(Node n) {
        Node current = n;
        while (current.getType() != 1) {//backtrack through parents and use boolean marker to indicate path before reaching the initial node
            current.setPath();
            current = current.getParent();
        }
    }

    private void search() {
        while (!open.isEmpty()) {
            Node current = open.poll();
            closed.add(current);

            if (goal.isEqual(current)) {
                pathFound = true;
                getPath(current);
            } else {
                getSuccessors(current);
            }
        }
    }

    public ArrayList<int[]> travel() {
        search();

        ArrayList<int[]> path_vertices = new ArrayList<>();
        if (!pathFound) {
            System.out.println("No path found");
        } else {
            int[] i_position = new int[]{0, 0};
            int[] g_position = new int[]{0, 0};
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j].getType() == 1) {
                        i_position = new int[]{i, j};
                        path_vertices.add(i_position);
                    } else if (map[i][j].getType() == 2) {
                        g_position = new int[]{i, j};
                        path_vertices.add(g_position);
                    } else if (map[i][j].getPath()) {
                        path_vertices.add(new int[]{i, j});
                    }
                }
            }

            // check if the initial is on the below or right from the goal
            if (i_position[0] > g_position[0] || i_position[1] > g_position[1]) {
                Collections.reverse(path_vertices);
            }
        }

        return path_vertices;
    }
}