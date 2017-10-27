import algorithms.*;
import algorithms.bestFirstSearch.MapConverter;
import algorithms.bestFirstSearch.Strategy;

import java.util.ArrayList;

public class Printer {

    private static final char OBSTACLE_POSITION = 'X';
    private static final char ROBOT__POSITION = 'I';
    private static final char BOB_POSITION = 'B';
    private static final char GOAL_POSITION = 'G';
    private static final char UNSELECTED_POSITION = '-';
    private static final char SELECTED_PATH = 'O';

    public static ArrayList<int[]> find_shortest_path_BreadthFS(char[][] map, int[][] adj_matrix, int[] start_pos, int[] dest_pos) {
        ArrayList<Vertex> vertices = new ArrayList<>();
        Vertex start = null, dest = null;

        for (int i = 0; i < adj_matrix.length; i++) {
            int x = i / map.length, y = i % map.length;

            Vertex v = new Vertex(x, y);
            if (start_pos[0] == x && start_pos[1] == y) {
                start = v;
            }
            if (dest_pos[0] == x && dest_pos[1] == y) {
                dest = v;
            }

            vertices.add(v);
        }

        if (start == null) {
            return null;
        }

        return new BreadthFirstSearch(vertices).search(adj_matrix, start, dest);
    }

    public static ArrayList<int[]> find_shortest_path_DepthFS(char[][] map, int[][] adj_matrix, int[] start_pos, int[] dest_pos) {
        ArrayList<Vertex> vertices = new ArrayList<>();
        Vertex start = null, dest = null;

        for (int i = 0; i < adj_matrix.length; i++) {
            int x = i / map.length, y = i % map.length;

            Vertex v = new Vertex(x, y);
            if (start_pos[0] == x && start_pos[1] == y) {
                start = v;
            }
            if (dest_pos[0] == x && dest_pos[1] == y) {
                dest = v;
            }

            vertices.add(v);
        }

        if (start == null) {
            return null;
        }

        return new DepthFirstSearch(vertices).search(adj_matrix, start, dest);
    }

    public static ArrayList<int[]> find_shortest_path_BestFS(char[][] map, int[] start, int[] dest) {
        MapConverter m_con = new MapConverter();
        m_con.read(map, start, dest);
        return new Strategy(m_con.get_initial(), m_con.get_goal(), m_con.get_grid()).travel();
    }

    public static ArrayList<int[]> find_shortest_path_AstartFS(char[][] map, int[] start, int[] dest) {
        ArrayList<int[]> block_positions = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == OBSTACLE_POSITION) {
                    block_positions.add(new int[]{i, j});
                }
            }
        }

        return new AStar().travel(map.length, map.length, start, dest, block_positions);
    }

    public static void print_hyphens(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void draw_selected_path(char[][] map, ArrayList<int[]> path_vertices, int[] start, int[] dest) {

        char[][] new_map = new char[map.length][map.length];
        for (int i = 0; i < new_map.length; i++) {
            for (int j = 0; j < new_map.length; j++) {
                switch (map[i][j]) {
                    case 'O':
                        new_map[i][j] = UNSELECTED_POSITION;
                        break;
                    case 'I':
                        new_map[i][j] = ROBOT__POSITION;
                        break;
                    case 'G':
                        new_map[i][j] = GOAL_POSITION;
                        break;
                    case 'B':
                        new_map[i][j] = BOB_POSITION;
                        break;
                    case 'X':
                        new_map[i][j] = OBSTACLE_POSITION;
                }

            }
        }

        boolean dest_exist = false;
        for (int i = 0; i < path_vertices.size(); i++) {
            int x = path_vertices.get(i)[0], y = path_vertices.get(i)[1];
            if (x == dest[0] && y == dest[1]) {
                new_map[x][y] = map[x][y] == GOAL_POSITION ? GOAL_POSITION : BOB_POSITION;
                dest_exist = true;
            } else if(x != start[0] || y != start[1]) {
                new_map[x][y] = SELECTED_PATH;
            }
        }

        System.out.println(path_vertices.size() + " STEPS");
        print_hyphens(new_map.length * 2);
        for (int i = 0; i < new_map.length; i++) {
            for (int j = 0; j < new_map.length; j++) {
                System.out.print(new_map[i][j] + " ");
            }
            System.out.println();
        }
        print_hyphens(new_map.length * 2);

        if (!dest_exist) {
            System.out.println("THE DESTINATION IS UNREACHABLE");
            print_hyphens(new_map.length * 2);
        }

    }
}
