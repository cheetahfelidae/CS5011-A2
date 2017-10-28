package search;

import java.util.*;

public class Search {
    protected static final char ROBOT_POSITION = 'I';
    protected static final char BOB_POSITION = 'B';
    protected static final char GOAL_POSITION = 'G';

    private Map<Node, Node> prev = new HashMap<>();
    private ArrayList<Node> path_to_goal = new ArrayList<>();
    private ArrayList<Node> explored = new ArrayList<>();
    private Node initial_node;
    private Node dest_node;
    private char[][] map;
    private int map_no, explored_state;

    protected ArrayList<Node> directionBob = new ArrayList<>();
    protected ArrayList<Node> directionGoal = new ArrayList<>();
    protected String algorithm;

    public Search(String algorithm, char[][] map, int map_no) {
        this.algorithm = algorithm;
        this.map = map;
        this.initial_node = find_node(ROBOT_POSITION);
        this.setMap_no(map_no);
        this.setExplored_state(0);
    }

    public void setExplored_state(int explored_state) {
        this.explored_state = explored_state;
    }

    public void setInitial_node(Node initial_node) {
        this.initial_node = initial_node;
    }

    public void setMap_no(int map_no) {
        this.map_no = map_no;
    }

    public void set_dest_node(char goal) {
        // goal can be Bob (B) or safe zone (G)
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == goal) {
                    dest_node = new Node(i, j);
                    break;
                }
            }
        }
    }

    public Node getInitial_node() {
        return initial_node;
    }

    public char[][] getMap() {
        return map;
    }

    public void create_path_to_goal(Node currentNode) {
        // construct path to goal by backtracking from goal to initial position
        for (Node node = currentNode; node != null; node = prev.get(node)) {
            path_to_goal.add(node);
        }

        Collections.reverse(path_to_goal);
    }

    public ArrayList<Node> getPath_to_goal() {
        return path_to_goal;
    }

    public ArrayList<Node> getExplored() {
        return explored;
    }

    public Map<Node, Node> getPrev() {
        // get parent node of current node
        return prev;
    }

    public Node get_dest_node() {
        return dest_node;
    }

    public int getMap_no() {
        // get chosen map number
        return map_no;
    }

    public int get_explored_state() {
        // count how many states have been explored
        return explored_state;
    }

    public void search(char goal) {
    }

    public Node find_node(char name) {
        Node node = new Node(0, 0);

        // find robot's initial position
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == name) {
                    node.setX(i);
                    node.setY(j);
                    break;
                }
            }
        }

        return node;
    }

    public ArrayList<Node> get_next_states(Node node) {
        ArrayList<Node> nextStates = new ArrayList<>();
        int x = node.getX(), y = node.getY();

        // get the potential next moves (North, South, East, West)
        // North
        if (is_valid_child(x - 1, y)) {
            nextStates.add(new Node(x - 1, y));
        }

        // West
        if (is_valid_child(x, y - 1)) {
            nextStates.add(new Node(x, y - 1));
        }

        // East
        if (is_valid_child(x, y + 1)) {
            nextStates.add(new Node(x, y + 1));
        }

        // South
        if (is_valid_child(x + 1, y)) {
            nextStates.add(new Node(x + 1, y));
        }

        return nextStates;
    }

    protected boolean is_valid_child(int x, int y) {
        // validate if the move is legal or not (out of bound, blocked, etc.)
        return !(x < 0 || x >= map.length || y < 0 || y >= map[0].length) && (map[x][y] != 'X');
    }

    protected void clearData() {
        getExplored().clear();
        getPath_to_goal().clear();
        getPrev().clear();
    }

    protected boolean reach_goal(Node node, char goal) {
        if (node.equals(get_dest_node())) {
            // assign new initial state
            setInitial_node(node);
            create_path_to_goal(node);
            setExplored_state(get_explored_state() + 1);
            saveObjectivePath(goal);
            printObjectiveCompleted(goal);
            return true;
        }
        return false;
    }

    protected void saveObjectivePath(char goal) {
        // save path to Bob or path to goal
        for (Node node : getPath_to_goal()) {
            if (goal == BOB_POSITION) {
                directionBob.add(node);
            } else {
                directionGoal.add(node);
            }
        }
    }

    private void printObjectiveCompleted(char goal) {
        // print objective complete message
        if (goal == BOB_POSITION) {
            System.out.println("Found Bob!");
        } else {
            System.out.println("Arrived at safe zone");
        }
    }

    protected ArrayList<Node> getDirectionBob() {
        return this.directionBob;
    }

    protected void check_failure(char dest) {
    }

    public void process() {
        // search for Bob, then search for safe goal position
        search(BOB_POSITION);

        // only search for goal position if the robot managed to find a way to get to Bob
        if (!getDirectionBob().isEmpty()) {
            search(GOAL_POSITION);
        }
    }
}
