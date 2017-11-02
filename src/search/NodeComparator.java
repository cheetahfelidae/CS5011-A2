package search;

import java.util.Comparator;

/**
 * This class is used to pass in PriorityQueue queue as a constructor overload which contains a comparator indicating how the sort works
 * (in this programme, it is implemented to use scores as the priority).
 */
public class NodeComparator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
        return Double.compare(n1.get_score(), n2.get_score());
    }
}
