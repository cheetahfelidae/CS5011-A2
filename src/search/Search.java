package search;

import java.util.*;

public class Search {
    protected String algorithm;
    protected char[][] map;
    protected Node initial_node;
    protected Node dest_node;
    protected int num_explored_nodes = 0;

    public Search(String algorithm, char[][] map, char initial_position, char dest_position) {
        this.algorithm = algorithm;
        this.map = map;
        this.initial_node = find_node(initial_position);
        this.dest_node = find_node(dest_position);
    }

    /**
     * counts the number of visited nodes.
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
     * Creates node using coordinates
     *
     * @param name
     * @return
     */
    private Node find_node(char name) {
        Node node = new Node(0, 0);

        outer_loop:
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == name) {
                    node.setX(i);
                    node.setY(j);
                    break outer_loop;
                }
            }
        }

        return node;
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
     * Checks if the move is legal (i.e. array out of bound, blocked position)
     *
     * @param x
     * @param y
     * @return
     */
    protected boolean is_legal_move(int x, int y) {
        return !(x < 0 || x >= map.length || y < 0 || y >= map[0].length) && (map[x][y] != 'X');
    }

    /**
     * Constructs a path from a initial node to a destination node by backtracking from the destination to the start.
     *
     * @param node
     */
    public ArrayList<Node> create_path_to_dest(Map<Node, Node> ancestors, Node node) {
        ArrayList<Node> path_to_dest = new ArrayList<>();

        Node n = node;
        while (n != null) {
            path_to_dest.add(n);
            n = ancestors.get(n);
        }

        Collections.reverse(path_to_dest);

        return path_to_dest;
    }
}
