package search.constantVariable;

public enum Algorithm {
    BREADTH_FIRST_SEARCH("BFS"),
    DEPT_FIRST_SEARCH("DFS"),
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
