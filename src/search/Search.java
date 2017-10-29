package search;

import search.constantVariable.Position;

import java.util.*;
import java.util.logging.Logger;

public class Search {
    private Map<Node, Node> prev = new HashMap<>();
    private ArrayList<Node> path_to_goal = new ArrayList<>();
    private ArrayList<Node> explored = new ArrayList<>();
    private Node initial_node;
    private Node dest_node;
    private char[][] map;
    private int map_no, explored_state;

    protected ArrayList<Node> path_to_bob = new ArrayList<>();
    protected ArrayList<Node> directionGoal = new ArrayList<>();
    protected String algorithm;

    public Search(String algorithm, char[][] map, int map_no) {
        this.algorithm = algorithm;
        this.map = map;
        this.initial_node = find_node(Position.BOB_POSITION.value());
        this.set_map_no(map_no);
        this.set_explored_state(0);
    }

    public void set_explored_state(int explored_state) {
        this.explored_state = explored_state;
    }

    public void set_initial_node(Node initial_node) {
        this.initial_node = initial_node;
    }

    public void set_map_no(int map_no) {
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

    public Node get_initial_node() {
        return initial_node;
    }

    public char[][] get_map() {
        return map;
    }

    public void create_path_to_goal(Node currentNode) {
        // construct path to goal by backtracking from goal to initial position
        Node node = currentNode;
        while (node != null) {
            path_to_goal.add(node);
            node = prev.get(node);
        }

        Collections.reverse(path_to_goal);
    }

    public ArrayList<Node> getPath_to_goal() {
        return path_to_goal;
    }

    public ArrayList<Node> get_explored() {
        return explored;
    }

    public Map<Node, Node> getPrev() {
        // get parent node of current node
        return prev;
    }

    public Node get_dest_node() {
        return dest_node;
    }

    public int get_map_no() {
        // get chosen map number
        return map_no;
    }

    public int get_explored_state() {
        // count how many states have been explored
        return explored_state;
    }

    public void search(char goal) {
    }

    private Node find_node(char name) {
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

    protected ArrayList<Node> get_next_states(Node node) {
        ArrayList<Node> nextStates = new ArrayList<>();
        int x = node.getX(), y = node.getY();

        // get the potential next moves (North, South, East, West)
        // Up
        if (is_valid_child(x - 1, y)) {
            nextStates.add(new Node(x - 1, y));
        }

        // Left
        if (is_valid_child(x, y - 1)) {
            nextStates.add(new Node(x, y - 1));
        }

        // Right
        if (is_valid_child(x, y + 1)) {
            nextStates.add(new Node(x, y + 1));
        }

        // Down
        if (is_valid_child(x + 1, y)) {
            nextStates.add(new Node(x + 1, y));
        }

        return nextStates;
    }

    protected boolean is_valid_child(int x, int y) {
        // validate if the move is legal or not (out of bound, blocked, etc.)
        return !(x < 0 || x >= map.length || y < 0 || y >= map[0].length) && (map[x][y] != 'X');
    }

    protected void clear_data() {
        get_explored().clear();
        getPath_to_goal().clear();
        getPrev().clear();
    }

    protected boolean reach_goal(Node node, char goal) {

        if (node.equals(get_dest_node())) {
            // assign new initial state
            set_initial_node(node);
            create_path_to_goal(node);
            set_explored_state(get_explored_state() + 1);
            save_objective_path(goal);
            print_objective_completed(goal);
            
            return true;
        }

        return false;
    }

    private void save_objective_path(char dest) {
        // save path to Bob or path to goal
        for (Node node : getPath_to_goal()) {
            switch (Position.convert(dest)) {
                case BOB_POSITION:
                    path_to_bob.add(node);
                    break;
                case GOAL_POSITION:
                    directionGoal.add(node);
                    break;
                default:
                    Logger.getLogger(Search.class.getName()).severe("DESTINATION " + dest + " IS UNRECOGNISED");
            }
        }
    }

    private void print_objective_completed(char dest) {
        // print objective complete message
        switch (Position.convert(dest)) {
            case BOB_POSITION:
                System.out.println("BOB IS FOUND");
                break;
            case GOAL_POSITION:
                System.out.println("SAFE GOAL IS REACHED");
                break;
            default:
                Logger.getLogger(Search.class.getName()).severe("DESTINATION " + dest + " IS UNRECOGNISED");
        }
    }

    protected ArrayList<Node> get_path_to_bob() {
        return this.path_to_bob;
    }

    protected void check_failure(char dest) {
    }

    public void process() {
        // search for Bob, then search for safe goal position
        search(Position.BOB_POSITION.value());

        // only search for goal position if the robot managed to find a way to get to Bob
        if (!get_path_to_bob().isEmpty()) {
            search(Position.GOAL_POSITION.value());
        }
    }
}
