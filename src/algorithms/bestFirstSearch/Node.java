package algorithms.bestFirstSearch;

import java.util.*;

public class Node {

    private double cost;
    private int type;
    private int x;
    private int y;
    private ArrayList<Node> neighbors = new ArrayList<>();
    private Node parent = null;
    private boolean inPath = false;


    public Node(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int n) {
        this.x = n;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int n) {
        this.y = n;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double n) {
        this.cost = n;
    }

    public void setType(int n) {
        this.type = n;
    }

    public int getType() {
        return this.type;
    }

    public void addNeighbor(Node n) {
        if (n.getType() != 3) {//check for not adding walls as neighbor=
            neighbors.add(n);
        }
    }

    public boolean isEqual(Node n2) {
        if (this.type == n2.getType()) {
            return true;
        } else return false;
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node n) {
        parent = n;
    }

    public boolean getPath() {
        return inPath;
    }

    public void setPath() {
        inPath = true;
    }

}