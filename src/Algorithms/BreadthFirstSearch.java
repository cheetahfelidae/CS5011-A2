package Algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {
    private Queue<NonWeightedVertex> queue = new LinkedList<>();
    private ArrayList<NonWeightedVertex> vertices;

    /**
     * find neighbors of node using adjacency matrix.
     * if adjacency_matrix[i][j]==1, then vertices at index i and index j are connected.
     *
     * @param adjacency_matrix
     * @param x
     * @return
     */
    private ArrayList<NonWeightedVertex> find_neighbours(int adjacency_matrix[][], NonWeightedVertex x) {
        int nodeIndex = -1;

        ArrayList<NonWeightedVertex> neighbours = new ArrayList<NonWeightedVertex>();
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

    public ArrayList<int[]> travel(int adjacency_matrix[][], NonWeightedVertex nonWeightedVertex) {
        ArrayList<int[]> vertices = new ArrayList<>();
        queue.add(nonWeightedVertex);
        nonWeightedVertex.visited = true;
        while (!queue.isEmpty()) {

            NonWeightedVertex element = queue.remove();
            vertices.add(new int[]{element.x, element.y});
            ArrayList<NonWeightedVertex> neighbours = find_neighbours(adjacency_matrix, element);
            for (int i = 0; i < neighbours.size(); i++) {
                NonWeightedVertex n = neighbours.get(i);
                if (n != null && !n.visited) {
                    queue.add(n);
                    n.visited = true;

                }
            }

        }
        return vertices;
    }

    public BreadthFirstSearch(ArrayList vertices) {
        this.vertices = vertices;
    }
}
