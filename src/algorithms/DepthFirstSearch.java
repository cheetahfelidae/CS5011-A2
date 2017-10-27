package algorithms;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends UninformedSearch {

    public DepthFirstSearch(ArrayList<Vertex> vertices) {
        super(vertices);
    }

    public ArrayList<int[]> search(int adj_matrix[][], Vertex start, Vertex dest) {
        ArrayList<int[]> explored = new ArrayList<>();
        Stack<Vertex> frontier = new Stack<>();

        start.visited = true;
        frontier.add(start);

        while (!frontier.isEmpty()) {

            Vertex v = frontier.pop();
            explored.add(new int[]{v.x, v.y});

            if (goal_test(v, dest)) {
                break;
            }

            insert_all(frontier, adj_matrix, v);
        }

        return explored;
    }

    private void insert_all(Stack frontier, int adj_matrix[][], Vertex vertex) {
        ArrayList<Vertex> successors = expand(adj_matrix, vertex);
        for (Vertex v : successors) {
            if (v != null && !v.visited) {
                v.visited = true;
                frontier.add(v);
            }
        }
    }
}
