package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class UninformedSearch {
    private ArrayList<Vertex> vertices;
    /**
     * find neighbors of node using adjacency matrix.
     * if adjacency_matrix[i][j]==1, then vertices at index i and index j are connected.
     * @param adj_matrix
     * @param x
     * @return
     */
    protected ArrayList<Vertex> expand(int adj_matrix[][], Vertex x) {
        int v_index = -1;
        ArrayList<Vertex> successors = new ArrayList<>();

        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(x)) {
                v_index = i;
                break;
            }
        }

        if (v_index != -1) {
            for (int j = 0; j < adj_matrix[v_index].length; j++) {
                if (adj_matrix[v_index][j] == 1) {
                    successors.add(vertices.get(j));
                }
            }
        }
        return successors;
    }

    protected boolean goal_test(Vertex v, Vertex dest) {
        return v.x == dest.x && v.y == dest.y;
    }

    protected UninformedSearch(ArrayList vertices) {
        this.vertices = vertices;
    }
}
