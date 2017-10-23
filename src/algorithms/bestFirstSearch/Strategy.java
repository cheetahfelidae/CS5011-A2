package algorithms.bestFirstSearch;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Strategy {

    Node initial;
    Node goal;
    Node[][] grid;
    ArrayList<Node> closed = new ArrayList<>();
    NodeComparator nc = new NodeComparator();
    PriorityQueue<Node> open = new PriorityQueue<>(nc);
    boolean pathFound = false;

    public Strategy(Node initial, Node goal, Node[][] grid) {
        this.initial = initial;
        this.goal = goal;
        this.grid = grid;
        open.add(initial);
    }

    public void evaluate(Node current) {
        current.setCost(Math.sqrt(Math.pow((current.getX() - goal.getX()), 2) + Math.pow((current.getY() - goal.getY()), 2)));
    }

    public void getSuccessors(Node n) {
        for (Node neighbor : n.getNeighbors()) {//evaluate cost of all neighbors, set their parent, and add them to the open list
            if (!open.contains(neighbor) && !closed.contains(neighbor)) {
                evaluate(neighbor);
                open.add(neighbor);
                neighbor.setParent(n);
            }
        }
    }

    public void getPath(Node n) {
        Node current = n;
        while (current.getType() != 1) {//backtrack through parents and use boolean marker to indicate path before reaching the initial node
            current.setPath();
            current = current.getParent();
        }
    }

    public void printGrid() {
        if (!pathFound) {
            System.out.println("No path found");
        } else {
            int count = 1;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j].getType() == 0) {
                        if (grid[i][j].getPath()) {//boolean tracker of what nodes are in the path
                            System.out.print(count++ + " ");
                        } else {
                            System.out.print(". ");
                        }
                    } else if (grid[i][j].getType() == 1) {//initial
                        System.out.print("I ");
                    } else if (grid[i][j].getType() == 2) {//goal
                        System.out.print("G ");
                     } else if (grid[i][j].getType() == 4) {
                        System.out.print("B ");
                    }
                    else {
                        System.out.print("X ");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public void search() {
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
}