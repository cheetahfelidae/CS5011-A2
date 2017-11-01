package search;

import java.util.*;
import java.util.logging.Logger;

public class Search {
    private int num_explored_nodes;

    protected String algorithm;
    protected char[][] map;
    protected char initial_position, dest_position;
    protected Node initial_node, dest_node;

    public Search(String algorithm, char[][] map, char initial_position, char dest_position) {
        this.algorithm = algorithm;
        this.map = map;
        this.initial_position = initial_position;
        this.dest_position = dest_position;
        this.initial_node = make_node(this.initial_position);
        this.dest_node = make_node(this.dest_position);
    }

    /**
     * Set the number of visited nodes.
     *
     * @param num_explored_nodes
     */
    protected void set_num_explored_nodes(int num_explored_nodes) {
        this.num_explored_nodes = num_explored_nodes;
    }

    /**
     * Counts the number of visited nodes.
     *
     * @return
     */
    public int get_num_explored_nodes() {
        return num_explored_nodes;
    }

    /**
     * To be overridden.
     *
     * @return
     */
    public ArrayList<Node> search() {
        return null;
    }

    /**
     * This is used to create an initial, Bob and Goal nodes.
     * It finds their coordinate (x,y) which will be used to create a node.
     *
     * @param position
     * @return
     */
    private Node make_node(char position) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == position) {
                    return new Node(i, j);
                }
            }
        }

        Logger.getLogger(Search.class.getName()).severe("POSITION " + position + " IS NOT FOUND IN THE MAP");
        return null;
    }

    /**
     * To be overridden.
     *
     * @param node
     * @return
     */
    protected ArrayList<Node> get_neighbors(Node node) {
        return null;
    }

    /**
     * Checks if the moving to a particular node (or cell in the map) is legal (i.e. array out of bound, blocked position)
     *
     * @param x
     * @param y
     * @return
     */
    protected boolean is_legal_move(int x, int y) {
        return !(x < 0 || x >= map.length || y < 0 || y >= map[0].length) && (map[x][y] != 'X');
    }

    /**
     * Constructs a path from an initial node to a destination node by backtracking from the destination to the initial node.
     *
     * @param dest_node
     */
    public ArrayList<Node> create_path_to_dest(Map<Node, Node> ancestors, Node dest_node) {
        ArrayList<Node> path_to_dest = new ArrayList<>();

        Node node = dest_node;
        while (node != null) {
            path_to_dest.add(node);
            node = ancestors.get(node);
        }

        Collections.reverse(path_to_dest);

        return path_to_dest;
    }
}
