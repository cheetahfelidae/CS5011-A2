package search;

import search.constantVariable.Heuristic;

import java.util.logging.Logger;

/**
 * This class represents a cell in a map and each contains its coordinates and other important variables used in the search.
 */
public class Node {
    private int x, y, heuristic_value, score, path_cost = 0;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set_path_cost(int path_cost) {
        this.path_cost = path_cost;
    }

    public void set_score(int score) {
        this.score = score;
    }

    public void set_heuristic_value(char heuristic, Node dest_node) {
        int man_dist = Math.abs(dest_node.getX() - getX()) + Math.abs(dest_node.getY() - getY()),
                euc_dist = (int) Math.sqrt(Math.pow(dest_node.getX() - getX(), 2) + Math.pow(dest_node.getY() - getY(), 2));

        switch (Heuristic.convert(Character.toUpperCase(heuristic))) {
            case MANHATTAN:
                this.heuristic_value = man_dist;
                break;
            case EUCLIDEAN:
                this.heuristic_value = euc_dist;
                break;
            case COMBINATION:
                this.heuristic_value = Math.max(man_dist, euc_dist);
                break;
            default:
                Logger.getLogger(Node.class.getName()).severe("HEURISTIC " + heuristic + " IS UNRECOGNISED");
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * Gets cost to a destination node.
     *
     * @return
     */
    public int get_heuristic_value() {
        return this.heuristic_value;
    }

    /**
     * Gets cost from initial node to this node.
     *
     * @return
     */
    public int get_path_cost() {
        return this.path_cost;
    }

    // score f(n) determines the order of node expansion for BestFS and A*
    public int get_score() {
        return this.score;
    }

    @Override
    public String toString() {
        return "(" + Integer.toString(this.x) + ", " + Integer.toString(this.y) + ")";
    }

    /**
     * Checks if two nodes are equal (x1 == x2 and y1 == y2)
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Node)) {
            return false;
        }

        Node node = (Node) obj;

        return (this.x == node.x) && (this.y == node.y);
    }
}
