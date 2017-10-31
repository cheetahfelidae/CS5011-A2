package search;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
        return Double.compare(n1.get_score(), n2.get_score());
    }
}
