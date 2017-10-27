package algorithms;

import java.util.*;

// http://www.codebytes.in/2015/02/a-shortest-path-finding-algorithm.html

public class AStar {

    //Blocked cells are just null Cell values in grid
    private Vertex[][] grid;
    private PriorityQueue<Vertex> open;
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

    private void checkAndUpdateCost(Vertex current, Vertex t) {
        final int V_H_COST = 10;

        if (t == null || closed[t.getX()][t.getY()]) return;
        int t_final_cost = t.getHeuristicCost() + (current.getFinalCost() + V_H_COST);

        boolean inOpen = open.contains(t);
        if (!inOpen || t_final_cost < t.getFinalCost()) {
            t.setFinalCost(t_final_cost);
            t.setParent(current);
            if (!inOpen) open.add(t);
        }
    }

    private void AStar() {
        //add the start location to open list.
        open.add(grid[startI][startJ]);

        Vertex current;

        while (true) {
            current = open.poll();
            if (current == null) break;
            closed[current.getX()][current.getY()] = true;

            if (current.equals(grid[endI][endJ])) {
                return;
            }

            Vertex t;
            // up
            if (current.getX() - 1 >= 0) {
                t = grid[current.getX()- 1][current.getY()];
                checkAndUpdateCost(current, t);
            }
            // left
            if (current.getY() - 1 >= 0) {
                t = grid[current.getX()][current.getY() - 1];
                checkAndUpdateCost(current, t);
            }
            // right
            if (current.getY() + 1 < grid[0].length) {
                t = grid[current.getX()][current.getY() + 1];
                checkAndUpdateCost(current, t);
            }
            // down
            if (current.getX() + 1 < grid.length) {
                t = grid[current.getX() + 1][current.getY()];
                checkAndUpdateCost(current, t);
            }
        }
    }

    /*
    Params :
    tCase = search_DFS case No.
    x, y = Board's dimensions
    si, sj = start location's x and y coordinates
    ei, ej = end location's x and y coordinates
    int[][] blocked = array containing inaccessible cell coordinates
    */
    public ArrayList<int[]> travel(int x, int y, int[] start, int[] dest, ArrayList<int[]> block_positions) {
        // reset
        grid = new Vertex[x][y];
        closed = new boolean[x][y];
        open = new PriorityQueue<>((Object o1, Object o2) -> {
            Vertex c1 = (Vertex) o1;
            Vertex c2 = (Vertex) o2;

            return c1.getFinalCost() < c2.getFinalCost() ? -1 : c1.getFinalCost() > c2.getFinalCost() ? 1 : 0;
        });
        //Set start position
        setStartCell(start);  //Setting to 0,0 by default. Will be useful for the UI part

        //Set End Location
        setEndCell(dest);

        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                grid[i][j] = new Vertex(i, j);
                /**
                 * Manhattan Distance
                 * Use this method because we are allowed to move only in four directions only (right, left, top, bottom).
                 */
                grid[i][j].setHeuristicCost(Math.abs(i - endI) + Math.abs(j - endJ));
            }
        }
        grid[start[0]][start[1]].setFinalCost(0);

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
            Vertex current = grid[endI][endJ];
//            path_vertices.add(new int[]{current.getX(), current.j});
            while (current.getParent() != null) {
                path_vertices.add(new int[]{current.getX(), current.getY()});
                current = current.getParent();
            }
            path_vertices.add(start);
            Collections.reverse(path_vertices);
        } else System.out.println("No possible path");

        return path_vertices;
    }
}
