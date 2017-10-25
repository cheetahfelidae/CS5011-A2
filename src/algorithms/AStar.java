package algorithms;

import java.util.*;

// http://www.codebytes.in/2015/02/a-shortest-path-finding-algorithm.html

public class AStar {
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
    private Cell[][] grid;
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

    private void checkAndUpdateCost(Cell current, Cell t) {
        final int V_H_COST = 10;

        if (t == null || closed[t.i][t.j]) return;
        int t_final_cost = t.heuristicCost + (current.finalCost + V_H_COST);

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
            // up
            if (current.i - 1 >= 0) {
                t = grid[current.i - 1][current.j];
                checkAndUpdateCost(current, t);
            }
            // left
            if (current.j - 1 >= 0) {
                t = grid[current.i][current.j - 1];
                checkAndUpdateCost(current, t);
            }
            // right
            if (current.j + 1 < grid[0].length) {
                t = grid[current.i][current.j + 1];
                checkAndUpdateCost(current, t);
            }
            // down
            if (current.i + 1 < grid.length) {
                t = grid[current.i + 1][current.j];
                checkAndUpdateCost(current, t);
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
                /**
                 * Manhattan Distance
                 * Use this method because we are allowed to move only in four directions only (right, left, top, bottom).
                  */
                grid[i][j].heuristicCost = Math.abs(i - endI) + Math.abs(j - endJ);
            }
        }
        grid[start[0]][start[1]].finalCost = 0;

           /*
             Set blocked cells. Simply set the cell values to null
             for blocked cells.
           */
        for (int[] b : block_positions) {
            setBlocked(b);
        }

        AStar();

        ArrayList<int[]> path_vertices = new ArrayList<>();
        if (closed[endI][endJ]) {
            //Trace back the path
            Cell current = grid[endI][endJ];
//            path_vertices.add(new int[]{current.i, current.j});
            while (current.parent != null) {
                path_vertices.add(new int[]{current.i, current.j});
                current = current.parent;
            }
            path_vertices.add(start);
            Collections.reverse(path_vertices);
        } else System.out.println("No possible path");

        return path_vertices;
    }
}
