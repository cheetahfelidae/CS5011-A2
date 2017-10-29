package search;

public class Node {
	private int x, y;
	private double heuristic, score, pathCost;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	// estimated cost to goal h(n)
	public double getHeuristic() {
		return this.heuristic;
	}

	// cost from initial node to current node g(n)
	public double getPathCost() {
		return this.pathCost;
	}

	// score f(n) determines the order of node expansion for BestFS and A*
	public double getScore() {
		return this.score;
	}

	public void setPathCost(double pathCost) {
		this.pathCost = pathCost;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public void setHeuristic(String heuristic, Node goalNode) {
        double man_dist = Math.abs(goalNode.getX() - getX()) + Math.abs(goalNode.getY() - getY());
        double euc_dist = Math.sqrt(Math.pow(goalNode.getX() - getX(), 2) + Math.pow(goalNode.getY() - getY(), 2));

		// get the chosen heuristic
		if (heuristic.equals(Heuristic.MANHATTAN.toString())) {
			// Manhattan distance
			this.heuristic = man_dist;
		} else if (heuristic.equals(Heuristic.EUCLIDEAN.toString())) {
			// Euclidean distance
			this.heuristic = euc_dist;
		} else if (heuristic.equals(Heuristic.COMBINATION.toString())) {
			// choose the maximum between Manhattan distance and Euclidean distance
			this.heuristic = Math.max(man_dist, euc_dist);
		}
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		// print Node in the form: Node(row, column)
		String output;
		output = "Node(" + Integer.toString(this.x) + ", " + Integer.toString(this.y) + ")";

		return output;
	}

	@Override
	public boolean equals(Object node) {
	    // this will be used to check if particular node is in frontier/explored or not
		if (!(node instanceof Node)) {
	        return false;
	    }

	    Node n2 = (Node) node;

	    // custom equality check here.
	    return (this.x == n2.x) && (this.y == n2.y);
	}
}
