package search;

public enum Heuristic {
    MANHATTAN("M"),
    EUCLIDEAN("E"),
    COMBINATION("C");

    private String name;

    Heuristic(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
