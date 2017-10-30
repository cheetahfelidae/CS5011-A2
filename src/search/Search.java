package search;

import java.util.*;

public class Search {
    private Map<Node, Node> prev_path = new HashMap<>();
    private ArrayList<Node> explored = new ArrayList<>();
    private char[][] map;
    private int num_explored_nodes;

    protected String algorithm;
    protected Node initial_node;
    protected Node dest_node;
    protected ArrayList<Node> path_to_dest = new ArrayList<>();

    public Search(String algorithm, char[][] map, char initial_position, char dest_position) {
        this.algorithm = algorithm;
        this.map = map;
        this.initial_node = find_node(initial_position);
        this.dest_node = find_node(dest_position);
        this.set_explored_state(0);
    }

    public void set_explored_state(int explored_state) {
        this.num_explored_nodes = explored_state;
    }

    public void set_initial_node(Node initial_node) {
        this.initial_node = initial_node;
    }

    public char[][] get_map() {
        return map;
    }

    public ArrayList<Node> create_path_to_dest(Node node) {
        // construct path to goal by backtracking from goal to initial position
        ArrayList<Node> path = new ArrayList<>();

        Node n = node;
        while (n != null) {
            path.add(n);
            n = prev_path.get(n);
        }

        Collections.reverse(path);

        return path;
    }

    public ArrayList<Node> get_explored() {
        return explored;
    }

    public Map<Node, Node> get_prev_path() {
        // get parent node of current node
        return prev_path;
    }

    public int get_num_explored_nodes() {
        // count how many states have been explored
        return num_explored_nodes;
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

    protected boolean reach_dest(Node node) {

        if (node.equals(dest_node)) {
            // assign new initial state
            set_initial_node(node);
            set_explored_state(get_num_explored_nodes() + 1);
            ArrayList<Node> path = create_path_to_dest(node);
            save_objective_path(path);

            System.out.println("DESTINATION IS FOUND");

            return true;
        }

        return false;
    }

    private void save_objective_path(ArrayList<Node> path) {
        for (Node node : path) {
            path_to_dest.add(node);
        }
    }

    protected void check_failure() {
    }
}
