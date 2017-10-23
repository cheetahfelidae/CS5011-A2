import algorithms.BreadthFirstSearch;
import algorithms.DepthFirstSearch;
import algorithms.NonWeightedVertex;
import algorithms.bestFirstSearch.MapConverter;
import algorithms.bestFirstSearch.Strategy;

import java.util.ArrayList;

public class Main {
    private ArrayList<int[]> find_shortest_path_BreadthFS(char[][] map, int[][] adj_matrix, int[] start) {
        ArrayList vertices = new ArrayList();
        NonWeightedVertex start_nonWeightedVertex = null;
        for (int i = 0; i < adj_matrix.length; i++) {
            int x = i / map.length, y = i % map.length;
            vertices.add(new NonWeightedVertex(x, y));
            if (start[0] == x && start[1] == y) {
                start_nonWeightedVertex = (NonWeightedVertex) vertices.get(vertices.size() - 1);
            }
        }

        if (start_nonWeightedVertex == null) {
            return null;
        }

        return new BreadthFirstSearch(vertices).travel(adj_matrix, start_nonWeightedVertex);
    }

    private ArrayList<int[]> find_shortest_path_DepthFS(char[][] map, int[][] adj_matrix, int[] start) {
        ArrayList vertices = new ArrayList();
        NonWeightedVertex start_nonWeightedVertex = null;
        for (int i = 0; i < adj_matrix.length; i++) {
            int x = i / map.length, y = i % map.length;
            vertices.add(new NonWeightedVertex(x, y));
            if (start[0] == x && start[1] == y) {
                start_nonWeightedVertex = (NonWeightedVertex) vertices.get(vertices.size() - 1);
            }
        }

        if (start_nonWeightedVertex == null) {
            return null;
        }

        return new DepthFirstSearch(vertices).travel(adj_matrix, start_nonWeightedVertex);
    }

    private ArrayList<int[]> find_shortest_path_BestFS(char[][] map, int[] goal) {
        MapConverter fp = new MapConverter();
        fp.read(map);
        return new Strategy(fp.get_initial(), fp.get_goal(), fp.get_grid()).travel();
    }

    private void print_hyphens(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private void draw_selected_path(char[][] map, ArrayList<int[]> path_vertices, int[] dest) {
        final char OBSTACLE_POSITION = 'X';
        final char ROBOT__POSITION = 'I';
        final char BOB_POSITION = 'B';
        final char GOAL_POSITION = 'G';
        final char UNSELECTED_PATH = '-';
        final char SELECTED_PATH = 'O';

        char[][] new_map = new char[map.length][map.length];
        for (int i = 0; i < new_map.length; i++) {
            for (int j = 0; j < new_map.length; j++) {
                new_map[i][j] = map[i][j] == OBSTACLE_POSITION ? OBSTACLE_POSITION : UNSELECTED_PATH;
            }
        }

        boolean dest_exist = false;
        for (int i = 0; i < path_vertices.size(); i++) {
            int x = path_vertices.get(i)[0], y = path_vertices.get(i)[1];

            if (i == 0) {
                new_map[x][y] = ROBOT__POSITION;
            } else if (x == dest[0] && y == dest[1]) {
                new_map[x][y] = map[x][y] == GOAL_POSITION ? GOAL_POSITION : BOB_POSITION;
                dest_exist = true;
                break;
            } else {
                new_map[x][y] = SELECTED_PATH;
            }
        }

        print_hyphens(new_map.length * 2);
        for (int i = 0; i < new_map.length; i++) {
            for (int j = 0; j < new_map.length; j++) {
                System.out.print(new_map[i][j] + " ");
            }
            System.out.println();
        }
        print_hyphens(new_map.length * 2);

        if (!dest_exist) {
            System.out.println("The destination does not exist!!");
            print_hyphens(new_map.length * 2);
        }

    }

    public void process(char[][] map, int[][] adj_matrix, int[] start, int[] bob, int[] goal) {
        ArrayList<int[]> path_vertices;
//        print_hyphens(map.length * 2);
//        System.out.println("Depth First Search..");
//        System.out.println("Position: The robot -> Bob");
//        path_vertices = find_shortest_path_DepthFS(map, adj_matrix, start);
//        draw_selected_path(map, path_vertices, bob);
//        System.out.println();
//
//        print_hyphens(map.length * 2);
//        System.out.println("Position: Bob -> The safe goal");
//        path_vertices = find_shortest_path_DepthFS(map, adj_matrix, bob);
//        draw_selected_path(map, path_vertices, goal);
//        System.out.println();
//
//        print_hyphens(map.length * 2);
//        System.out.println("Breadth First Search..");
//        System.out.println("Position: The robot -> Bob");
//        path_vertices = find_shortest_path_BreadthFS(map, adj_matrix, start);
//        draw_selected_path(map, path_vertices, bob);
//        System.out.println();
//
//        print_hyphens(map.length * 2);
//        System.out.println("Position: Bob -> The safe goal");
//        path_vertices = find_shortest_path_BreadthFS(map, adj_matrix, start);
//        draw_selected_path(map, path_vertices, goal);
//        System.out.println();

        path_vertices = find_shortest_path_BestFS(map, goal);
        draw_selected_path(map, path_vertices, goal);
    }

    public static void main(String[] args) {
        Main main = new Main();

        System.out.println("********************************* MAP 1 ***************************************************");
        main.process(SquareMap.MAP1.map_value(), SquareMap.MAP1.to_adj_matrix(), SquareMap.START_MAP1.position_value(), SquareMap.BOB_MAP1.position_value(), SquareMap.GOAL_MAP1.position_value());

        System.out.println("********************************* MAP 2 ***************************************************");

        main.process(SquareMap.MAP2.map_value(), SquareMap.MAP2.to_adj_matrix(), SquareMap.START_MAP2.position_value(), SquareMap.BOB_MAP2.position_value(), SquareMap.GOAL_MAP2.position_value());

        System.out.println("********************************* MAP 3 ***************************************************");

        main.process(SquareMap.MAP3.map_value(), SquareMap.MAP3.to_adj_matrix(), SquareMap.START_MAP3.position_value(), SquareMap.BOB_MAP3.position_value(), SquareMap.GOAL_MAP3.position_value());

        System.out.println("********************************* MAP 4 ***************************************************");

        main.process(SquareMap.MAP4.map_value(), SquareMap.MAP4.to_adj_matrix(), SquareMap.START_MAP4.position_value(), SquareMap.BOB_MAP4.position_value(), SquareMap.GOAL_MAP4.position_value());

        System.out.println("********************************* MAP 5 ***************************************************");

        main.process(SquareMap.MAP5.map_value(), SquareMap.MAP5.to_adj_matrix(), SquareMap.START_MAP5.position_value(), SquareMap.BOB_MAP5.position_value(), SquareMap.GOAL_MAP5.position_value());
    }
}

// http://www.stoimen.com/blog/2012/09/24/computer-algorithms-graph-best-first-search/