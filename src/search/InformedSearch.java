package search;

import search.constantVariable.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.logging.Logger;

import static search.Printer.print_animate_result;
import static search.Printer.print_hyphens;


public class InformedSearch extends Search {
    protected char heuristic;

    public InformedSearch(String algorithm, char heuristic, char[][] map, char initial_position, char dest_position) {
        super(algorithm, map, initial_position, dest_position);
        this.heuristic = heuristic;
    }

    public ArrayList<Node> search() {
        Map<Node, Node> ancestors = new HashMap<>();
        ArrayList<Node> path_to_dest = new ArrayList<>();
        PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparator());
        ArrayList<Node> explored_nodes = new ArrayList<>();

        frontier.add(initial_node);

        int round = 1;
        while (!frontier.isEmpty()) {
            Node cur_node = frontier.poll();
            explored_nodes.add(cur_node);

            if (cur_node.equals(dest_node)) {// GOAL-TEST
                num_explored_nodes++;

                path_to_dest = create_path_to_dest(ancestors, cur_node);

                System.out.println("DESTINATION IS FOUND");
                print_hyphens(map.length * 3);
                break;
            }

            ArrayList<Node> successors = expand(cur_node, frontier, explored_nodes);
            frontier.addAll(successors);
            for (Node node : successors) {
                ancestors.put(node, cur_node);
            }

            print_animate_result(round++, cur_node, explored_nodes, map, algorithm, initial_node, dest_node);

            if (!cur_node.equals(initial_node)) {
                num_explored_nodes++;
            }
        }

        return path_to_dest;
    }

    /**
     * Gets all neighbor nodes (North, South, East, West) of the node.
     * BestFS score f(n) = h(n)
     * A* score f(n) = g(n) + h(n)
     *
     * h(n) = estimated cost of the path from the state at node n to the goal
     * g(n) = the cost of the path from the start to the node n
     *
     * @param node
     * @return
     */
    protected ArrayList<Node> get_neighbors(Node node) {
        int x = node.getX();
        int y = node.getY();
        ArrayList<Node> nextStates = new ArrayList<>();

        // Up
        if (is_legal_move(x - 1, y)) {
            Node up = new Node(x - 1, y);
            up.set_path_cost(node.get_path_cost() + 1);
            up.set_heuristic_value(heuristic, dest_node);
            if (algorithm.equals(Algorithm.BEST_FIRST_SEARCH.toString())) {
                up.set_score(up.get_heuristic_value());
            } else if (algorithm.equals(Algorithm.A_STAR.toString())) {
                up.set_score(up.get_heuristic_value() + up.get_path_cost());
            } else {
                Logger.getLogger(InformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
            }
            nextStates.add(up);
        }

        // Down
        if (is_legal_move(x + 1, y)) {
            Node down = new Node(x + 1, y);
            down.set_path_cost(node.get_path_cost() + 1);
            down.set_heuristic_value(heuristic, dest_node);
            if (algorithm.equals(Algorithm.BEST_FIRST_SEARCH.toString())) {
                down.set_score(down.get_heuristic_value());
            } else if (algorithm.equals(Algorithm.A_STAR.toString())) {
                down.set_score(down.get_heuristic_value() + down.get_path_cost());
            } else {
                Logger.getLogger(InformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
            }
            nextStates.add(down);
        }

        // Left
        if (is_legal_move(x, y - 1)) {
            Node left = new Node(x, y - 1);
            left.set_path_cost(node.get_path_cost() + 1);
            left.set_heuristic_value(heuristic, dest_node);
            if (algorithm.equals(Algorithm.BEST_FIRST_SEARCH.toString())) {
                left.set_score(left.get_heuristic_value());
            } else if (algorithm.equals(Algorithm.A_STAR.toString())) {
                left.set_score(left.get_heuristic_value() + left.get_path_cost());
            } else {
                Logger.getLogger(InformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
            }
            nextStates.add(left);
        }

        // Right
        if (is_legal_move(x, y + 1)) {
            Node right = new Node(x, y + 1);
            right.set_path_cost(node.get_path_cost() + 1);
            right.set_heuristic_value(heuristic, dest_node);
            if (algorithm.equals(Algorithm.BEST_FIRST_SEARCH.toString())) {
                right.set_score(right.get_heuristic_value());
            } else if (algorithm.equals(Algorithm.A_STAR.toString())) {
                right.set_score(right.get_heuristic_value() + right.get_path_cost());
            } else {
                Logger.getLogger(InformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
            }
            nextStates.add(right);
        }

        return nextStates;
    }

    protected ArrayList<Node> expand(Node node, PriorityQueue<Node> frontier, ArrayList<Node> explored) {
        ArrayList<Node> neighbors = get_neighbors(node);
        ArrayList<Node> successors = new ArrayList<>();

        for (Node neighbor : neighbors) {
            if (algorithm.equals(Algorithm.BEST_FIRST_SEARCH.toString())) {
                if (!explored.contains(neighbor) && !frontier.contains(neighbor)) {
                    successors.add(neighbor);
                }
            }
            /**
             * if state is in a node in frontier but with higher PATH-COST then replace old node with new node (A*)
             */
            else if (algorithm.equals(Algorithm.A_STAR.toString())) {
                if (!explored.contains(neighbor) && !frontier.contains(neighbor)) {
                    successors.add(neighbor);
                } else if (frontier.contains(neighbor)) {
                    Node oldNode = new Node(0, 0);
                    for (int i = 0; i < frontier.size(); i++) {
                        Node n = frontier.peek();
                        if (n.equals(neighbor)) {
                            oldNode = n;
                            break;
                        }
                    }
                    if (oldNode.get_path_cost() > neighbor.get_path_cost()) {
                        frontier.remove(oldNode);
                        frontier.add(neighbor);
                    }
                }
            } else {
                Logger.getLogger(InformedSearch.class.getName()).severe("ALGORITHM " + algorithm + " IS UNRECOGNISED");
            }
        }

        return successors;
    }
}
