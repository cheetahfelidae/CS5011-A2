import Algorithms.BFS;
import Algorithms.DFS;
import Algorithms.Vertex;

import java.util.ArrayList;

public class Main {
    private ArrayList<int[]> find_shortest_path_BFS(char[][] map, int[][] adj_matrix, int[] start) {
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

    private ArrayList<int[]> find_shortest_path_DFS(char[][] map, int[][] adj_matrix, int[] start) {
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

    private void draw_selected_path(char[][] map, ArrayList<int[]> trvl_vertices, int[] dest) {
        final char OBSTACLE_POSITION = 'X';
        final char ROBOT__POSITION = 'I';
        final char BOB_POSITION = 'B';
        final char GOAL_POSITION = 'G';
        final char UNSELECTED_PATH = '#';
        int selected_position = 1;
        String space = " ";

        String[][] new_map = new String[map.length][map.length];
        for (int i = 0; i < new_map.length; i++) {
            for (int j = 0; j < new_map.length; j++) {
                new_map[i][j] = map[i][j] == OBSTACLE_POSITION ? OBSTACLE_POSITION + space : UNSELECTED_PATH + space;
            }
        }

        boolean dest_exist = false;
        for (int i = 0; i < trvl_vertices.size(); i++) {
            int x = trvl_vertices.get(i)[0], y = trvl_vertices.get(i)[1];

            if (i == 0) {
                new_map[x][y] = ROBOT__POSITION + space;
            } else {
                new_map[x][y] = selected_position > 9 ? selected_position++ + "" : selected_position++ + space;
            }

            if (x == dest[0] && y == dest[1]) {
                new_map[x][y] = map[x][y] == GOAL_POSITION ? GOAL_POSITION + space : BOB_POSITION + space;
                dest_exist = true;
                break;
            }
        }

        for (int i = 0; i < new_map.length; i++) {
            for (int j = 0; j < new_map.length; j++) {
                System.out.print(new_map[i][j] + " ");
            }
            System.out.println();
        }

        if (!dest_exist) {
            System.out.println("The destination does not exist!!");
        }
    }

    public void process(char[][] map, int[][] adj_matrix, int[] start, int[] bob, int[] goal) {
        ArrayList<int[]> trvl_vertices;
        System.out.println("Depth First Search..");
        System.out.println("Position: The robot -> Bob");
        trvl_vertices = find_shortest_path_DFS(map, adj_matrix, start);
        draw_selected_path(map, trvl_vertices, bob);
        System.out.println();

        System.out.println("Position: Bob -> The safe goal");
        trvl_vertices = find_shortest_path_DFS(map, adj_matrix, bob);
        draw_selected_path(map, trvl_vertices, goal);
        System.out.println();

        System.out.println("Breadth First Search..");
        System.out.println("Position: The robot -> Bob");
        trvl_vertices = find_shortest_path_BFS(map, adj_matrix, start);
        draw_selected_path(map, trvl_vertices, bob);
        System.out.println();

        System.out.println("Position: Bob -> The safe goal");
        trvl_vertices = find_shortest_path_BFS(map, adj_matrix, start);
        draw_selected_path(map, trvl_vertices, goal);
        System.out.println();
    }

    public static void main(String[] args) {
        Main main = new Main();

        System.out.println("********************************* MAP 1 ***************************************************");
        main.process(SquareMap.MAP1.map_value(), SquareMap.MAP1.formatted_value(), SquareMap.START_MAP1.position_value(), SquareMap.BOB_MAP1.position_value(), SquareMap.GOAL_MAP1.position_value());

        System.out.println("********************************* MAP 2 ***************************************************");

        main.process(SquareMap.MAP2.map_value(), SquareMap.MAP2.formatted_value(), SquareMap.START_MAP2.position_value(), SquareMap.BOB_MAP2.position_value(), SquareMap.GOAL_MAP2.position_value());

        System.out.println("********************************* MAP 3 ***************************************************");

        main.process(SquareMap.MAP3.map_value(), SquareMap.MAP3.formatted_value(), SquareMap.START_MAP3.position_value(), SquareMap.BOB_MAP3.position_value(), SquareMap.GOAL_MAP3.position_value());

        System.out.println("********************************* MAP 4 ***************************************************");

        main.process(SquareMap.MAP4.map_value(), SquareMap.MAP4.formatted_value(), SquareMap.START_MAP4.position_value(), SquareMap.BOB_MAP4.position_value(), SquareMap.GOAL_MAP4.position_value());

        System.out.println("********************************* MAP 5 ***************************************************");

        main.process(SquareMap.MAP5.map_value(), SquareMap.MAP5.formatted_value(), SquareMap.START_MAP5.position_value(), SquareMap.BOB_MAP5.position_value(), SquareMap.GOAL_MAP5.position_value());
    }
}
