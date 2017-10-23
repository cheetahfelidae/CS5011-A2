package algorithms.bestFirstSearch;

public class MapConverter {
    private int gridSize;
    private Node[][] grid;
    private Node initial, goal;

    public Node get_initial() {
        return initial;
    }

    public Node get_goal() {
        return goal;
    }

    public Node[][] get_grid() {
        return grid;
    }

    /*For each node that is not a wall represented as a 3, the corresponding up, down, left, and right neighbors will be
   added to a list*/
    private void buildNeighbors(Node n, int row, int col) {
        if (n.getType() != 3) {
            if (row == 0) {//Check for edge cases where neighbor amount will vary
                if (col == 0) {
                    n.addNeighbor(grid[row + 1][col]);
                    n.addNeighbor(grid[row][col + 1]);
                } else if (col == gridSize - 1) {
                    n.addNeighbor(grid[row + 1][col]);
                    n.addNeighbor(grid[row][col - 1]);
                } else {
                    n.addNeighbor(grid[row + 1][col]);
                    n.addNeighbor(grid[row][col + 1]);
                    n.addNeighbor(grid[row][col - 1]);
                }
            } else if (row == gridSize - 1) {
                if (col == gridSize - 1) {
                    n.addNeighbor(grid[row - 1][col]);
                    n.addNeighbor(grid[row][col - 1]);
                } else if (col == 0) {
                    n.addNeighbor(grid[row - 1][col]);
                    n.addNeighbor(grid[row][col + 1]);
                } else {
                    n.addNeighbor(grid[row - 1][col]);
                    n.addNeighbor(grid[row][col - 1]);
                    n.addNeighbor(grid[row][col + 1]);
                }
            } else if (col == 0) {
                n.addNeighbor(grid[row + 1][col]);
                n.addNeighbor(grid[row - 1][col]);
                n.addNeighbor(grid[row][col + 1]);
            } else if (col == gridSize - 1) {
                n.addNeighbor(grid[row + 1][col]);
                n.addNeighbor(grid[row - 1][col]);
                n.addNeighbor(grid[row][col - 1]);
            } else {
                n.addNeighbor(grid[row + 1][col]);
                n.addNeighbor(grid[row - 1][col]);
                n.addNeighbor(grid[row][col - 1]);
                n.addNeighbor(grid[row][col + 1]);
            }
        }
    }

    public void read(char[][] map) {
        gridSize = map.length;
        grid = new Node[gridSize][gridSize];

        /*Create integer matrix for the given input. Nodes are given integer values corresponding to types*/
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]) {
                    case 'O':
                        grid[i][j] = new Node(i, j, 0);
                        break;
                    case 'I':
                        grid[i][j] = new Node(i, j, 1);
                        initial = grid[i][j];
                        break;
                    case 'G':
                        grid[i][j] = new Node(i, j, 2);
                        goal = grid[i][j];
                        break;
                    case 'B':
                        grid[i][j] = new Node(i, j, 4);
                    case 'X':
                        grid[i][j] = new Node(i, j, 3);
                        break;
                    default:
                        System.out.println("Error " + map[i][j]);
                }
            }
        }

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                buildNeighbors(grid[i][j], i, j);
            }
        }
    }
}