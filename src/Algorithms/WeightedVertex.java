package algorithms;

import java.util.Comparator;

class WeightedVertex implements Comparator<WeightedVertex> {
    public int heuristicvalue;
    public int node;

    public WeightedVertex(int node, int heuristicvalue) {
        this.heuristicvalue = heuristicvalue;
        this.node = node;
    }

    public WeightedVertex() {

    }

    @Override
    public int compare(WeightedVertex weightedVertex1, WeightedVertex weightedVertex2) {
        if (weightedVertex1.heuristicvalue < weightedVertex2.heuristicvalue)
            return -1;
        if (weightedVertex1.heuristicvalue > weightedVertex2.heuristicvalue)
            return 1;
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WeightedVertex) {
            WeightedVertex node = (WeightedVertex) obj;
            if (this.node == node.node) {
                return true;
            }
        }
        return false;
    }
}
