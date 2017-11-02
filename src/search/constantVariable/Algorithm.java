package search.constantVariable;

/**
 * This is used to store predefined constant variables of the name of the four algorithms.
 */
public enum Algorithm {
    BREADTH_FIRST_SEARCH("BFS"),
    DEPTH_FIRST_SEARCH("DFS"),
    BEST_FIRST_SEARCH("BestFS"),
    A_STAR("A*");

    private String name;

    Algorithm(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    /**
     * To handle with switch case
     *
     * @param name
     * @return
     */
    public static Algorithm convert(String name) {
        for (Algorithm e : Algorithm.values()) {
            if (e.toString().equals(name)) {
                return e;
            }
        }
        throw new RuntimeException("ALGORITHM ENUM NOT FOUND");
    }
}
