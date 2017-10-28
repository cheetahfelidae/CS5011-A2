package search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Map;

import static search.Printer.*;

public class UninformedSearch extends Search {
	private Deque<Node> frontier = new ArrayDeque<Node>();
	private ArrayList<Node> directionBob = new ArrayList<Node>();
	private ArrayList<Node> directionGoal = new ArrayList<Node>();
	private String algorithm;

	public UninformedSearch(String algorithm, char[][] map, int mapNumber) {
		super(map, mapNumber);
		this.algorithm = algorithm;
	}
	
	public void search(char goal) {
		clearData(frontier);
		Map<Node, Node> prev = this.getPrev();
		ArrayList<Node> successors = this.getSuccessors();
		ArrayList<Node> explored = this.getExplored();
		Node startNode = this.getStartNode();
		this.setGoalNode(goal);
		System.out.println("Start node: " + startNode + "\n");
		// BFS uses Deque to store frontier
		frontier.add(startNode);
		Node currentNode = startNode;
		printStatus(goal, currentNode, explored, getMap(), frontier);
		
		// Perform search
		while(!frontier.isEmpty()) {
			// remove the the first node from the frontier 
			currentNode = frontier.poll();
			explored.add(currentNode);
			// check if the robot has reached the goal
			if(currentNode.equals(this.getGoalNode())) {
				// assign new initial state
				this.setStartNode(currentNode);
				this.constructPathToGoal(currentNode);
				this.setStateExplored(this.getStatesExplored() + 1);
				saveObjectivePath(goal);
				printObjectiveCompleted(goal);
				break;
			}
			// expand the nodes
			successors = expand(currentNode, frontier, explored);
			for(Node node : successors) {
				prev.put(node, currentNode);
				if (algorithm.equals("BFS")) {
					frontier.addLast(node);
				} else {
					frontier.addFirst(node);
				}
			}
			printStatus(goal, currentNode, explored, getMap(), frontier);
			checkFailure(goal);
			// keep track of states explored
			if (!currentNode.equals(startNode)) {
				this.setStateExplored(this.getStatesExplored() + 1);
			}
		}
    }
	
	public void printSummary() {
		// print the search summary once the robot reached the goal
		int pathCost = (directionBob.size() + directionGoal.size()) - 2;
		System.out.println("\nSummary");
		if (directionBob.isEmpty() || directionGoal.isEmpty()) {
			System.out.println("Unsuccessful search operation");
			System.out.println("Algorithm: " + algorithm);
			if (directionBob.isEmpty()) {
				System.out.println("Cannot get to Bob");
			} else {
				printPath("Find Bob", this.getMap(), getMapNumber(), directionBob, algorithm);
				System.out.println("Cannot get Bob to safety");
			}
		} else {
			printPath("Find Bob", this.getMap(), getMapNumber(), directionBob, algorithm);
			printPath("Find safe zone", this.getMap(), getMapNumber(), directionGoal, algorithm);
			System.out.println("Path cost: " + pathCost);
		}
		System.out.println("State explored: " + this.getStatesExplored() + "\n");
	}
	
	public ArrayList<Node> getDirectionBob() {
		return this.directionBob;
	}
	
	private void saveObjectivePath(char goal) {
		// save path to Bob or path to goal
		ArrayList<Node> directions = this.getDirections();
		for (Node node : directions) {
			if (goal == 'B') {
				directionBob.add(node);
			} else {
				directionGoal.add(node);
			}
		}
	}
	
	private void checkFailure(char goal) {
		// if there's no more nodes to expand then the search has failed
		if (frontier.isEmpty() && goal == 'B') {
			System.out.println("Failed to find Bob");
		} else if (frontier.isEmpty() && goal == 'G') {
			System.out.println("Failed to get to safety");
		}
	}
	
	private ArrayList<Node> expand(Node node, Deque<Node> frontier, ArrayList<Node> explored) {
		// expand the nodes and return list of successor nodes
		ArrayList<Node> nextStates = getNextStates(node);
		ArrayList<Node> successors = new ArrayList<Node>();
		for(int i = 0; i < nextStates.size(); i++) {
			if (!explored.contains(nextStates.get(i)) &&
					!frontier.contains(nextStates.get(i))) {
				successors.add(nextStates.get(i));
			}
		}
		
		return successors;
	}
	
	private void printObjectiveCompleted(char goal) {
		// print objective complete message
		if (goal == 'B') {
			System.out.println("Found Bob!");
		}
		else {
			System.out.println("Arrived at safe zone");
		}
	}
}
