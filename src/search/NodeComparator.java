package search;

import java.util.Comparator;

/**
 * This class is used in PriorityQueue queue as a constructor overload and this contains a comparator indicating how the sort works (using score as the priority).
 */
public class NodeComparator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
        return Double.compare(n1.get_score(), n2.get_score());
    }
}
