package algorithms.bestFirstSearch;

public class MapConverter {

    int gridSize;
    Node[][] grid;
    Node initial;
    Node goal;

    public void read(char[][] map) {
        gridSize = map.length;
        grid = new Node[gridSize][gridSize];

        /*Create integer matrix for the given input. Nodes are given integer values corresponding to types*/
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 'O') {
                    grid[i][j] = new Node(i, j, 0);//open
                } else if (map[i][j] == 'X') {
                    grid[i][j] = new Node(i, j, 3);//wall
                } else if (map[i][j] == 'I') {
                    Node temp = new Node(i, j, 1);//initial
                    grid[i][j] = temp;
                    initial = temp;
                } else if (map[i][j] == 'G') {
                    Node temp = new Node(i, j, 2);//goal
                    grid[i][j] = temp;
                    goal = temp;
                } else if (map[i][j] == 'B') {
                    Node temp = new Node(i, j, 4);//Bob
                    grid[i][j] = temp;
                } else {
                    System.out.println("Err" + map[i][j]);
                }
            }
        }

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                buildNeighbors(grid[i][j], i, j);
            }
        }
    }

    /*For each node that is not a wall represented as a 3, the corresponding up, down, left, and right neighbors will be
    added to a list*/
    public void buildNeighbors(Node n, int row, int col) {
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

    public Node getInitial() {
        return initial;
    }

    public Node getGoal() {
        return goal;
    }

    public Node[][] getGrid() {
        return grid;
    }
}