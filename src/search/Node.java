package search;

import search.constantVariable.Heuristic;

import java.util.logging.Logger;

public class Node {
    private int x, y;
    private double heuristic, score, pathCost = 0;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    // estimated cost to goal h(n)
    public double getHeuristic() {
        return this.heuristic;
    }

    // cost from initial node to current node g(n)
    public double getPathCost() {
        return this.pathCost;
    }

    // score f(n) determines the order of node expansion for BestFS and A*
    public double getScore() {
        return this.score;
    }

    public void setPathCost(double pathCost) {
        this.pathCost = pathCost;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setHeuristic(char heuristic, Node goalNode) {
        double man_dist = Math.abs(goalNode.getX() - getX()) + Math.abs(goalNode.getY() - getY());
        double euc_dist = Math.sqrt(Math.pow(goalNode.getX() - getX(), 2) + Math.pow(goalNode.getY() - getY(), 2));

        switch (Heuristic.convert(heuristic)) {
            case MANHATTAN:
                this.heuristic = man_dist;
                break;
            case EUCLIDEAN:
                this.heuristic = euc_dist;
                break;
            case COMBINATION:
                this.heuristic = Math.max(man_dist, euc_dist);
                break;
            default:
                Logger.getLogger(Node.class.getName()).severe("HEURISTIC " + heuristic + " IS UNRECOGNISED");
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        // print Node in the form: Node(row, column)
        String output;
        output = "(" + Integer.toString(this.x) + ", " + Integer.toString(this.y) + ")";

        return output;
    }

    @Override
    public boolean equals(Object node) {
        // this will be used to check if particular node is in frontier/explored or not
        if (!(node instanceof Node)) {
            return false;
        }

        Node n2 = (Node) node;

        // custom equality check here.
        return (this.x == n2.x) && (this.y == n2.y);
    }
}
