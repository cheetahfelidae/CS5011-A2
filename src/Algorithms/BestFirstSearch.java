package Algorithms;

import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BestFirstSearch {
    private PriorityQueue<WeightedVertex> priorityQueue;
    private int heuristicvalues[];
    private int numberOfNodes;

    public static final int MAX_VALUE = 999;

    public BestFirstSearch(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        this.priorityQueue = new PriorityQueue<WeightedVertex>(this.numberOfNodes,
                new WeightedVertex());
    }

    public void bestFirstSearch(int adjacencyMatrix[][], int[] heuristicvalues, int source) {
        int evaluationNode;
        int destinationNode;
//        int visited[] = new int[numberOfNodes + 1];
        int visited[] = new int[numberOfNodes];
        this.heuristicvalues = heuristicvalues;

        priorityQueue.add(new WeightedVertex(source, this.heuristicvalues[source]));
        visited[source] = 1;

        while (!priorityQueue.isEmpty()) {
            evaluationNode = getNodeWithMinimumHeuristicValue();
//            destinationNode = 1;
            destinationNode = 0;

            System.out.print(evaluationNode + "\t");
            while (destinationNode < numberOfNodes) {
                WeightedVertex vertex = new WeightedVertex(destinationNode, this.heuristicvalues[destinationNode]);
                if ((adjacencyMatrix[evaluationNode][destinationNode] != MAX_VALUE
                        && evaluationNode != destinationNode) && visited[destinationNode] == 0) {
                    priorityQueue.add(vertex);
                    visited[destinationNode] = 1;
                }
                destinationNode++;
            }
        }
    }

    private int getNodeWithMinimumHeuristicValue() {
        WeightedVertex vertex = priorityQueue.remove();
        return vertex.node;
    }

    public static void main(String... arg) {
        int adjacency_matrix[][];
        int number_of_vertices;
        int source = 0;
        int heuristicvalues[];

        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Enter the number of vertices");
            number_of_vertices = scan.nextInt();
            adjacency_matrix = new int[number_of_vertices + 1][number_of_vertices + 1];
            heuristicvalues = new int[number_of_vertices + 1];

            System.out.println("Enter the Weighted Matrix for the graph");
            for (int i = 1; i <= number_of_vertices; i++) {
                for (int j = 1; j <= number_of_vertices; j++) {
                    adjacency_matrix[i][j] = scan.nextInt();
                    if (i == j) {
                        adjacency_matrix[i][j] = 0;
                        continue;
                    }
                    if (adjacency_matrix[i][j] == 0) {
                        adjacency_matrix[i][j] = MAX_VALUE;
                    }
                }
            }
            for (int i = 1; i <= number_of_vertices; i++) {
                for (int j = 1; j <= number_of_vertices; j++) {
                    if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0) {
                        adjacency_matrix[j][i] = 1;
                    }
                }
            }

            System.out.println("Enter the heuristic values of the nodes");
            for (int vertex = 1; vertex <= number_of_vertices; vertex++) {
                System.out.print(vertex + ".");
                heuristicvalues[vertex] = scan.nextInt();
                System.out.println();
            }

            System.out.println("Enter the source ");
            source = scan.nextInt();

            System.out.println("The graph is explored as follows");
            BestFirstSearch bestFirstSearch = new BestFirstSearch(number_of_vertices);
            bestFirstSearch.bestFirstSearch(adjacency_matrix, heuristicvalues, source);

        } catch (InputMismatchException inputMismatch) {
            System.out.println("Wrong Input Format");
        }
        scan.close();
    }
}