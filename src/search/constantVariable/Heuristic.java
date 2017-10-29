package search.constantVariable;

public enum Heuristic {
    MANHATTAN('M'),
    EUCLIDEAN('E'),
    COMBINATION('C'),
    NONE(' ');

    private char name;

    Heuristic(char name) {
        this.name = name;
    }

    public char value() {
        return name;
    }

    /**
     * To handle with switch case
     *
     * @param name
     * @return
     */
    public static Heuristic convert(char name) {
        for (Heuristic e : Heuristic.values()) {
            if (e.value() == name) {
                return e;
            }
        }
        throw new RuntimeException("HEURISTIC ENUM NOT FOUND");
    }
}
