import java.util.Arrays;

public enum SquareMap {
    MAP1(new char[][]{
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
    }),
    MAP2(new char[][]{
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
    }),
    MAP3(new char[][]{
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
    }),
    MAP4(new char[][]{
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
    }),
    MAP5(new char[][]{
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
    }),
    MAP6(new char[][]{
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
    });
    private char[][] map;

    SquareMap(char[][] map) {
        this.map = map;
    }

    public char[][] value() {
        return map;
    }

    public int[][] formatted_value() {
        int map_wide = map.length;
        int[][] adjacency_matrix = new int[map_wide * map_wide][map_wide * map_wide];

        for (int i = 0; i < adjacency_matrix.length; i++) {
            int x_node = i / map_wide, y_node = i % map_wide;

            if (map[x_node][y_node] != 'X') {

                for (int j = 0; j < adjacency_matrix.length; j++) {
                    int x_neighbor = j / map_wide, y_neighbor = j % map_wide;
                    boolean cond_1 = (x_node == x_neighbor + 1 || x_node == x_neighbor - 1) && y_node == y_neighbor || (x_node == x_neighbor && (y_node == y_neighbor + 1 || y_node == y_neighbor - 1)),
                            cond_2 = map[x_neighbor][y_neighbor] != 'X';

                    if (cond_1 && cond_2) {
                        adjacency_matrix[i][j] = 1;
                    } else {
                        adjacency_matrix[i][j] = 0;
                    }
                }

            } else {
                for (int j = 0; j < adjacency_matrix.length; j++) {
                    adjacency_matrix[i][j] = 0;
                }
            }
        }

        return adjacency_matrix;
    }

}
