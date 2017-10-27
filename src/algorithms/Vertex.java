package algorithms;

public class Vertex {
    private int x;
    private int y;
    private boolean visit;

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
