package search.constantVariable;

public enum Position {
    ROBOT_POSITION('I'),
    BOB_POSITION('B'),
    GOAL_POSITION('G'),
    OBSTACLE_POSITION('X'),
    AVAILABLE_POSITION('O'),
    UNSELECTED_POSITION('-'),
    CURRENT_POSITION('C'),
    FRONTIER_POSITION('F'),
    EXPLORED_POSITION('.');

    private char position;

    Position(char position) {
        this.position = position;
    }

    public char value() {
        return position;
    }

    /**
     * To handle with switch case
     *
     * @param position
     * @return
     */
    public static Position convert(char position) {
        for (Position e : Position.values()) {
            if (e.value() == position) {
                return e;
            }
        }
        throw new RuntimeException("POSITION ENUM NOT FOUND");
    }
}
