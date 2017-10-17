import Algorithms.BFS;
import Algorithms.DFS;
import Algorithms.Vertex;

import java.util.ArrayList;

public class Main {
    private final char OBSTACLE_POSITION = 'X';
    private final char ROBOT__POSITION = 'I';
    private final char BOB_POSITION = 'B';
    private final char GOAL_POSITION = 'G';
    private final char UNSELECTED_PATH = '#';
    private final char SELECTED_POSITION = '-';

    public ArrayList<int[]> find_shortest_path_BFS(char[][] map, int[][] adj_matrix, int[] start) {
        ArrayList vertices = new ArrayList();
        Vertex start_vertex = null;
        for (int i = 0; i < adj_matrix.length; i++) {
            int x = i / map.length, y = i % map.length;
            vertices.add(new Vertex(x, y));
            if (start[0] == x && start[1] == y) {
                start_vertex = (Vertex) vertices.get(vertices.size() - 1);
            }
        }

        if (start_vertex == null) {
            return null;
        }

        return new BFS(vertices).travel(adj_matrix, start_vertex);
    }

    public ArrayList<int[]> find_shortest_path_DFS(char[][] map, int[][] adj_matrix, int[] start) {
        ArrayList vertices = new ArrayList();
        Vertex start_vertex = null;
        for (int i = 0; i < adj_matrix.length; i++) {
            int x = i / map.length, y = i % map.length;
            vertices.add(new Vertex(x, y));
            if (start[0] == x && start[1] == y) {
                start_vertex = (Vertex) vertices.get(vertices.size() - 1);
            }
        }

        if (start_vertex == null) {
            return null;
        }

        return new DFS(vertices).travel(adj_matrix, start_vertex);
    }

    public void draw_selected_path(char[][] map, ArrayList<int[]> trvl_vertices, int[] dest) {
        char[][] new_map = new char[map.length][map.length];
        for (int i = 0; i < new_map.length; i++) {
            for (int j = 0; j < new_map.length; j++) {
                new_map[i][j] = map[i][j] == OBSTACLE_POSITION ? OBSTACLE_POSITION : UNSELECTED_PATH;
            }
        }

        for (int i = 0; i < trvl_vertices.size(); i++) {
            int x = trvl_vertices.get(i)[0], y = trvl_vertices.get(i)[1];

            switch (map[x][y]) {
                case ROBOT__POSITION:
                    new_map[x][y] = ROBOT__POSITION;
                    break;
                case BOB_POSITION:
                    new_map[x][y] = BOB_POSITION;
                    break;
                case GOAL_POSITION:
                    new_map[x][y] = GOAL_POSITION;
                    break;
                default:
                    new_map[x][y] = SELECTED_POSITION;
            }

            if (x == dest[0] && y == dest[1]) {
                break;
            }
        }

        for (int i = 0; i < new_map.length; i++) {
            for (int j = 0; j < new_map.length; j++) {
                System.out.print(new_map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        ArrayList<int[]> trvl_vertices;
        System.out.println("Depth First Search..");
        System.out.println("Position: The robot -> Bob");
        trvl_vertices = main.find_shortest_path_DFS(SquareMap.MAP1.value(), SquareMap.MAP1.formatted_value(), new int[]{0, 0});
        main.draw_selected_path(SquareMap.MAP1.value(), trvl_vertices, new int[]{7, 8});
        System.out.println();

        System.out.println("Position: Bob -> The safe goal");
        trvl_vertices = main.find_shortest_path_DFS(SquareMap.MAP1.value(), SquareMap.MAP1.formatted_value(), new int[]{7, 8});
        main.draw_selected_path(SquareMap.MAP1.value(), trvl_vertices, new int[]{9, 9});
        System.out.println();

        System.out.println("Breadth First Search..");
        System.out.println("Position: The robot -> Bob");
        trvl_vertices = main.find_shortest_path_BFS(SquareMap.MAP1.value(), SquareMap.MAP1.formatted_value(), new int[]{0, 0});
        main.draw_selected_path(SquareMap.MAP1.value(), trvl_vertices, new int[]{7, 8});
        System.out.println();

        System.out.println("Position: Bob -> The safe goal");
        trvl_vertices = main.find_shortest_path_BFS(SquareMap.MAP1.value(), SquareMap.MAP1.formatted_value(), new int[]{7, 8});
        main.draw_selected_path(SquareMap.MAP1.value(), trvl_vertices, new int[]{9, 9});
        System.out.println();
    }
}
