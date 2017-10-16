import java.util.ArrayList;
import java.util.Arrays;

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
//        ArrayList<int[][]> vertices = new ArrayList<>();
//        int matrix_high = map1.length, matrix_wide = map1[0].length;
//        for(int i = 0; i < matrix_high; i++) {
//            int[][] neighbors = new int[matrix_high * matrix_wide][2];
//
//            for(int j = 0; j < matrix_high * matrix_wide; j++) {
//                neighbors[j][0] = j / matrix_high;
//            }
//            for(int j = 0; j < matrix_high * matrix_wide; j++) {
//                neighbors[j][1] = j % matrix_wide;
//            }
//            vertices.add(neighbors);
//
//            for (int[] n : neighbors) {
//                System.out.print(Arrays.toString(n) + " ");
//            }
//            System.out.println();
//            System.out.println();
//        }

        int[][] adj
        for(int i = 0; i < matrix_high; i++) {
            int[][] neighbors = new int[matrix_high * matrix_wide][2];

            for(int j = 0; j < matrix_high * matrix_wide; j++) {
                neighbors[j][0] = j / matrix_high;
            }
            for(int j = 0; j < matrix_high * matrix_wide; j++) {
                neighbors[j][1] = j % matrix_wide;
            }
            vertices.add(neighbors);

            for (int[] n : neighbors) {
                System.out.print(Arrays.toString(n) + " ");
            }
            System.out.println();
            System.out.println();
        }
    }
}
