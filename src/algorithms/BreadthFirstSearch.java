package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends UninformedSearch {

    public BreadthFirstSearch(ArrayList<Vertex> vertices) {
        super(vertices);
    }

    public ArrayList<int[]> search(int adj_matrix[][], Vertex start, Vertex dest) {
        ArrayList<int[]> explored = new ArrayList<>();
        Queue<Vertex> frontier = new LinkedList<>();

        start.visited = true;
        frontier.add(start);

        while (!frontier.isEmpty()) {

            Vertex v = frontier.remove();
            explored.add(new int[]{v.x, v.y});

            if (goal_test(v, dest)) {
                break;
            }

            insert_all(frontier, adj_matrix, v);
        }
        return explored;
    }

    private void insert_all(Queue<Vertex> frontier, int adj_matrix[][], Vertex vertex){
        ArrayList<Vertex> successors = expand(adj_matrix, vertex);
        for (Vertex v : successors) {
            if (v != null && !v.visited) {
                v.visited = true;
                frontier.add(v);
            }
        }
    }
}
