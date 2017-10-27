package algorithms;

public class Vertex {
    private int x;
    private int y;
    private boolean visit;

    private int heuristicCost = 0;
    private int finalCost = 0;
    private Vertex parent;

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    public boolean isVisit() {
        return visit;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setFinalCost(int finalCost) {
        this.finalCost = finalCost;
    }

    public int getFinalCost() {
        return finalCost;
    }

    public void setHeuristicCost(int heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    public int getHeuristicCost() {
        return heuristicCost;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public Vertex getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }
}