import Algorithms.DFS;
import Algorithms.Vertex;

import java.util.ArrayList;

public class Main {
    private static char[][] map1 = new char[][]{
            {'I', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X', 'O'},
            {'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'X', 'X', 'O', 'X', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'B', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'G'},
    };

    private static char[][] map2 = new char[][]{
            {'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
            {'O', 'X', 'O', 'X', 'O', 'O', 'B', 'O', 'O', 'X'},
            {'O', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O'},
            {'O', 'O', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'G', 'O', 'I', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    };

    private static char[][] map3 = new char[][]{
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
            {'O', 'I', 'O', 'O', 'O', 'X', 'X', 'O', 'B', 'O'},
            {'O', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'G', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
    };

    private static char[][] map4 = new char[][]{
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
            {'O', 'I', 'O', 'O', 'O', 'X', 'X', 'O', 'B', 'O'},
            {'O', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'X'},
            {'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'G', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
    };

    private static char[][] map5 = new char[][]{
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'B', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'X'},
            {'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
            {'O', 'X', 'O', 'O', 'G', 'O', 'X', 'O', 'I', 'O'},
            {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O'}
    };

    private static char[][] map6 = new char[][]{
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
            {'O', 'O', 'B', 'O', 'O', 'X', 'X', 'O', 'O', 'O'},
            {'O', 'X', 'X', 'X', 'O', 'O', 'X', 'O', 'O', 'I'},
            {'O', 'X', 'X', 'X', 'O', 'O', 'X', 'X', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'X', 'O'},
            {'X', 'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O'},
            {'X', 'X', 'O', 'O', 'O', 'X', 'X', 'O', 'X', 'X'},
            {'O', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'O', 'X'},
            {'O', 'X', 'X', 'X', 'G', 'O', 'X', 'X', 'O', 'X'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O'}
    };


    public static void main(String[] args) {
        int map_high = map1.length, map_wide = map1[0].length;
        int matrix_wide = map_high * map_wide;
        int[][] adjacency_matrix = new int[matrix_wide][matrix_wide];

        for (int i = 0; i < matrix_wide; i++) {
            int x_node = i / map_high, y_node = i % map_wide;

            for (int j = 0; j < matrix_wide; j++) {
                int x_neighbor = j / map_high, y_neighbor = j % map_wide;

                if (((x_node == x_neighbor + 1 || x_node == x_neighbor - 1) && y_node == y_neighbor)
                        || (x_node == x_neighbor && (y_node == y_neighbor + 1 || y_node == y_neighbor - 1))) {
                    adjacency_matrix[i][j] = 1;
                } else {
                    adjacency_matrix[i][j] = 0;
                }

            }
        }


        ArrayList vertices = new ArrayList();
        for (int i = 0; i < matrix_wide * matrix_wide; i++) {
            vertices.add(new Vertex(i / map_high, i % map_wide));
        }

        DFS dfs = new DFS(vertices);
        ArrayList<int[]> travel_vertices = dfs.travel(adjacency_matrix, (Vertex) vertices.get(0));

        char[][] new_map = new char[map_high][map_wide];
        for (int i = 0; i < map_high; i++) {
            for (int j = 0; j < map_wide; j++) {
                new_map[i][j] = 'X';
            }
        }

        for (int i = 0; i < travel_vertices.size(); i++) {
            int x = travel_vertices.get(i)[0], y = travel_vertices.get(i)[1];
            new_map[x][y] = '-';
            if (x == 7 && y == 8) {
                break;
            }
        }

        for (int i = 0; i < map_high; i++) {
            for (int j = 0; j < map_wide; j++) {
                System.out.print(new_map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
