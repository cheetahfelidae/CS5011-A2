import java.util.ArrayList;

public class Main {

    private void depth_first_search(char[][] map, int[][] adj_matrix, int[] robot, int[] bob, int[] goal) {
        ArrayList<int[]> path_vertices;

        Printer.print_hyphens(map.length * 2);
        System.out.println("DEPTH FIRST SEARCH");
        System.out.println("ROBOT(I) -> BOB(B)");
        path_vertices = Printer.find_shortest_path_DepthFS(map, adj_matrix, robot, bob);
        Printer.draw_selected_path(map, path_vertices, robot, bob);
        System.out.println();

        Printer.print_hyphens(map.length * 2);
        System.out.println("BOB(B) -> GOAL(G)");
        path_vertices = Printer.find_shortest_path_DepthFS(map, adj_matrix, bob, goal);
        Printer.draw_selected_path(map, path_vertices, bob, goal);
        System.out.println();
    }

    private void breadth_first_search(char[][] map, int[][] adj_matrix, int[] robot, int[] bob, int[] goal) {
        ArrayList<int[]> path_vertices;

        Printer.print_hyphens(map.length * 2);
        System.out.println("BREADTH FIRST SEARCH");
        System.out.println("ROBOT(I) -> BOB(B)");
        path_vertices = Printer.find_shortest_path_BreadthFS(map, adj_matrix, robot, bob);
        Printer.draw_selected_path(map, path_vertices, robot, bob);
        System.out.println();

        Printer.print_hyphens(map.length * 2);
        System.out.println("BOB(B) -> GOAL(G)");
        path_vertices = Printer.find_shortest_path_BreadthFS(map, adj_matrix, bob, goal);
        Printer.draw_selected_path(map, path_vertices, bob, goal);
        System.out.println();
    }

    private void best_first_search(char[][] map, int[] robot, int[] bob, int[] goal) {
        ArrayList<int[]> path_vertices;

        Printer.print_hyphens(map.length * 2);
        System.out.println("BEST FIRST SEARCH");
        System.out.println("ROBOT(I) -> BOB(B)");
        Printer.print_hyphens(map.length * 2);
        path_vertices = Printer.find_shortest_path_BestFS(map, robot, bob);
        Printer.draw_selected_path(map, path_vertices, robot, bob);
        System.out.println();

        Printer.print_hyphens(map.length * 2);
        System.out.println("BOB(B) -> GOAL(G)");
        path_vertices = Printer.find_shortest_path_BestFS(map, bob, goal);
        Printer.draw_selected_path(map, path_vertices, bob, goal);
        System.out.println();
    }

    private void a_star_search(char[][] map, int[] robot, int[] bob, int[] goal) {
        ArrayList<int[]> path_vertices;

        Printer.print_hyphens(map.length * 2);
        System.out.println("A STAR SEARCH");
        System.out.println("ROBOT(I) -> BOB(B)");
        path_vertices = Printer.find_shortest_path_AstartFS(map, robot, bob);
        Printer.draw_selected_path(map, path_vertices, robot, bob);
        System.out.println();

        Printer.print_hyphens(map.length * 2);
        System.out.println("BOB(B) -> GOAL(G)");
        path_vertices = Printer.find_shortest_path_AstartFS(map, bob, goal);
        Printer.draw_selected_path(map, path_vertices, bob, goal);
        System.out.println();
    }

    private void process(char[][] map, int[][] adj_matrix, int[] robot, int[] bob, int[] goal) {
//        depth_first_search(map, adj_matrix, robot, bob, goal);
//
//        breadth_first_search(map, adj_matrix, robot, bob, goal);
//
        best_first_search(map, robot, bob, goal);

//        a_star_search(map, robot, bob, goal);
    }

    public static void main(String[] args) {
        Main main = new Main();

        System.out.println("********************************* MAP 1 ***********************************************");
        main.process(Map.MAP1.map_value(), Map.MAP1.to_adj_matrix(), Map.START_MAP1.position_value(), Map.BOB_MAP1.position_value(), Map.GOAL_MAP1.position_value());

        System.out.println("********************************* MAP 2 ***********************************************");
        main.process(Map.MAP2.map_value(), Map.MAP2.to_adj_matrix(), Map.START_MAP2.position_value(), Map.BOB_MAP2.position_value(), Map.GOAL_MAP2.position_value());

        System.out.println("********************************* MAP 3 ***********************************************");
        main.process(Map.MAP3.map_value(), Map.MAP3.to_adj_matrix(), Map.START_MAP3.position_value(), Map.BOB_MAP3.position_value(), Map.GOAL_MAP3.position_value());

        System.out.println("********************************* MAP 4 ***********************************************");
        main.process(Map.MAP4.map_value(), Map.MAP4.to_adj_matrix(), Map.START_MAP4.position_value(), Map.BOB_MAP4.position_value(), Map.GOAL_MAP4.position_value());

        System.out.println("********************************* MAP 5 ***********************************************");
        main.process(Map.MAP5.map_value(), Map.MAP5.to_adj_matrix(), Map.START_MAP5.position_value(), Map.BOB_MAP5.position_value(), Map.GOAL_MAP5.position_value());

        System.out.println("********************************* MAP 6 ***********************************************");
        main.process(Map.MAP6.map_value(), Map.MAP6.to_adj_matrix(), Map.START_MAP6.position_value(), Map.BOB_MAP6.position_value(), Map.GOAL_MAP6.position_value());
    }
}

// http://www.stoimen.com/blog/2012/09/24/computer-algorithms-graph-best-first-search/