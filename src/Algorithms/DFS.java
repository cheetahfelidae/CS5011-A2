package Algorithms;

import java.util.ArrayList;
import java.util.Stack;

public class DFS {
    private ArrayList vertices;
    /**
     * find neighbors of node using adjacency matrix.
     * if adjacency_matrix[i][j]==1, then vertices at index i and index j are connected.
     * @param adjacency_matrix
     * @param x
     * @return
     */
    private ArrayList find_neighbours(int adjacency_matrix[][], Vertex x) {
        int nodeIndex = -1;
        ArrayList neighbours = new ArrayList();

        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(x)) {
                nodeIndex = i;
                break;
            }
        }

        if (nodeIndex != -1) {
            for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
                if (adjacency_matrix[nodeIndex][j] == 1) {
                    neighbours.add(vertices.get(j));
                }
            }
        }
        return neighbours;
    }

    public ArrayList<int[]> travel(int adjacency_matrix[][], Vertex vertex) {
        ArrayList<int[]> travel_vertices = new ArrayList<>();
        Stack stack = new Stack();
        stack.add(vertex);
        vertex.visited = true;

        while (!stack.isEmpty()) {
            Vertex element = (Vertex) stack.pop();
            travel_vertices.add(new int[]{element.x, element.y});

            ArrayList neighbours = find_neighbours(adjacency_matrix, element);
            for (int i = 0; i < neighbours.size(); i++) {
                Vertex n = (Vertex) neighbours.get(i);
                if (n != null && !n.visited) {
                    stack.add(n);
                    n.visited = true;

                }
            }
        }

        return travel_vertices;
    }

    public DFS(ArrayList vertices) {
        this.vertices = vertices;
    }
}
