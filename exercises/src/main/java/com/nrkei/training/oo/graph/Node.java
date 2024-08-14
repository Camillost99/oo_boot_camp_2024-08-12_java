/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.ArrayList;
import java.util.List;

// Understands its neighbors
public class Node {
    private static final int UNREACHABLE = -1;
    private static final List<Node> NO_VISITED_NODE = new ArrayList<>();

    private final List<Node> neighbors = new ArrayList<>();

    public Node to(Node neighbor) {
        neighbors.add(neighbor);
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return hopCount(destination, NO_VISITED_NODE) != UNREACHABLE;
    }

    public int hopCount(Node destination) {
        int result = hopCount(destination, NO_VISITED_NODE);
        if (result == UNREACHABLE) throw new IllegalArgumentException("Destination not reachable");
        return result;
    }

    private int hopCount(Node destination, List<Node> visitedNodes) {
        if (this == destination) return 0;
        if (visitedNodes.contains(this)) return UNREACHABLE;
        int champion = UNREACHABLE;
        for (Node n: neighbors) {
            int challenger = n.hopCount(destination, copyWithThis(visitedNodes));
            if (challenger == UNREACHABLE) continue;
            if (champion == UNREACHABLE || challenger + 1 < champion) champion = challenger + 1;
        }
        return champion;
    }

    private List<Node> copyWithThis(List<Node> originals) {
        List<Node> results = new ArrayList<>(originals);
        results.add(this);
        return results;
    }
}
