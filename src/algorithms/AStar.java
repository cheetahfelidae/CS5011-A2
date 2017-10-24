package algorithms;

import java.util.*;

// http://www.codebytes.in/2015/02/a-shortest-path-finding-algorithm.html

public class AStar {
    private final int DIAGONAL_COST = 14;
    private final int V_H_COST = 10;

    private class Cell {
        private int heuristicCost = 0; //Heuristic cost
        private int finalCost = 0; //G+H
        private int i, j;
        private Cell parent;

        Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "[" + this.i + ", " + this.j + "]";
        }
    }

    //Blocked cells are just null Cell values in grid
    private Cell[][] grid = new Cell[5][5];
    private PriorityQueue<Cell> open;
    private boolean closed[][];
    private int startI, startJ;
    private int endI, endJ;

    private void setBlocked(int[] position) {
        grid[position[0]][position[1]] = null;
    }

    private void setStartCell(int[] position) {
        startI = position[0];
        startJ = position[1];
    }

    private void setEndCell(int[] position) {
        endI = position[0];
        endJ = position[1];
    }

    private void checkAndUpdateCost(Cell current, Cell t, int cost) {
        if (t == null || closed[t.i][t.j]) return;
        int t_final_cost = t.heuristicCost + cost;

        boolean inOpen = open.contains(t);
        if (!inOpen || t_final_cost < t.finalCost) {
            t.finalCost = t_final_cost;
            t.parent = current;
            if (!inOpen) open.add(t);
        }
    }

    private void AStar() {
        //add the start location to open list.
        open.add(grid[startI][startJ]);

        Cell current;

        while (true) {
            current = open.poll();
            if (current == null) break;
            closed[current.i][current.j] = true;

            if (current.equals(grid[endI][endJ])) {
                return;
            }

            Cell t;
            if (current.i - 1 >= 0) {
                t = grid[current.i - 1][current.j];
                checkAndUpdateCost(current, t, current.finalCost + V_H_COST);

                if (current.j - 1 >= 0) {
                    t = grid[current.i - 1][current.j - 1];
                    checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
                }

                if (current.j + 1 < grid[0].length) {
                    t = grid[current.i - 1][current.j + 1];
                    checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
                }
            }

            if (current.j - 1 >= 0) {
                t = grid[current.i][current.j - 1];
                checkAndUpdateCost(current, t, current.finalCost + V_H_COST);
            }

            if (current.j + 1 < grid[0].length) {
                t = grid[current.i][current.j + 1];
                checkAndUpdateCost(current, t, current.finalCost + V_H_COST);
            }

            if (current.i + 1 < grid.length) {
                t = grid[current.i + 1][current.j];
                checkAndUpdateCost(current, t, current.finalCost + V_H_COST);

                if (current.j - 1 >= 0) {
                    t = grid[current.i + 1][current.j - 1];
                    checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
                }

                if (current.j + 1 < grid[0].length) {
                    t = grid[current.i + 1][current.j + 1];
                    checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
                }
            }
        }
    }

    /*
    Params :
    tCase = travel case No.
    x, y = Board's dimensions
    si, sj = start location's x and y coordinates
    ei, ej = end location's x and y coordinates
    int[][] blocked = array containing inaccessible cell coordinates
    */
    public ArrayList<int[]> travel(int x, int y, int[] start, int[] dest, ArrayList<int[]> block_positions) {
        // reset
        grid = new Cell[x][y];
        closed = new boolean[x][y];
        open = new PriorityQueue<>((Object o1, Object o2) -> {
            Cell c1 = (Cell) o1;
            Cell c2 = (Cell) o2;

            return c1.finalCost < c2.finalCost ? -1 : c1.finalCost > c2.finalCost ? 1 : 0;
        });
        //Set start position
        setStartCell(start);  //Setting to 0,0 by default. Will be useful for the UI part

        //Set End Location
        setEndCell(dest);

        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                grid[i][j] = new Cell(i, j);
                grid[i][j].heuristicCost = Math.abs(i - endI) + Math.abs(j - endJ);
//                  System.out.print(grid[i][j].heuristicCost+" ");
            }
//              System.out.println();
        }
        grid[start[0]][start[1]].finalCost = 0;

           /*
             Set blocked cells. Simply set the cell values to null
             for blocked cells.
           */
        for (int[] b : block_positions) {
            setBlocked(b);
        }

        //Display initial map
//        System.out.println("Grid: ");
//        for (int i = 0; i < x; ++i) {
//            for (int j = 0; j < y; ++j) {
//                if (i == start[0] && j == start[1]) System.out.print("SO  "); //Source
//                else if (i == dest[0] && j == dest[1]) System.out.print("DE  ");  //Destination
//                else if (grid[i][j] != null) System.out.printf("%-3d ", 0);
//                else System.out.print("BL  ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        AStar();
//        System.out.println("\nScores for cells: ");
//        for (int i = 0; i < x; ++i) {
//            for (int j = 0; j < x; ++j) {
//                if (grid[i][j] != null) System.out.printf("%-3d ", grid[i][j].finalCost);
//                else System.out.print("BL  ");
//            }
//            System.out.println();
//        }
//        System.out.println();


        ArrayList<int[]> path_vertices = new ArrayList<>();
        if (closed[endI][endJ]) {
            //Trace back the path
//            System.out.println("Path: ");
            Cell current = grid[endI][endJ];
//            System.out.print(current);
            path_vertices.add(new int[]{current.i, current.j});
            while (current.parent != null) {
//                System.out.print(" -> " + current.parent);
                path_vertices.add(new int[]{current.i, current.j});
                current = current.parent;
            }
//            System.out.println();
        } else System.out.println("No possible path");

        path_vertices.add(start);
        Collections.reverse(path_vertices);

        return path_vertices;
    }

//    public static void main(String[] args) throws Exception {
//        travel(1, 5, 5, 0, 0, 3, 2, new int[][]{{0, 4}, {2, 2}, {3, 1}, {3, 3}});
//        travel(2, 5, 5, 0, 0, 4, 4, new int[][]{{0, 4}, {2, 2}, {3, 1}, {3, 3}});
//        travel(3, 7, 7, 2, 1, 5, 4, new int[][]{{4, 1}, {4, 3}, {5, 3}, {2, 3}});
//
//        travel(1, 5, 5, 0, 0, 4, 4, new int[][]{{3, 4}, {3, 3}, {4, 3}});
//    }
}
