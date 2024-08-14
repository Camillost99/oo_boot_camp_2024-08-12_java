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
    private final List<Node> neighbors = new ArrayList<>();

    public Node to(Node neighbor) {
        neighbors.add(neighbor);
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return canReach(destination, noVisitedNodes());
    }

    public int hopCount(Node destination) {
        int result = hopCount(destination, noVisitedNodes());
        if (result == UNREACHABLE) throw new IllegalArgumentException("Destination not reachable");
        return result;
    }

    private int hopCount(Node destination, List<Node> visitedNodes) {
        if (this == destination) return 0;
        if (visitedNodes.contains(this)) return UNREACHABLE;
        visitedNodes.add(this);
        for (Node n: neighbors) {
            int neighborHopCount = n.hopCount(destination, visitedNodes);
            if (neighborHopCount != UNREACHABLE) return neighborHopCount + 1;
        }
        return UNREACHABLE;
    }

    private boolean canReach(Node destination, List<Node> visitedNodes) {
        if (this == destination) return true;
        if (visitedNodes.contains(this)) return false;
        visitedNodes.add(this);
        return neighbors.stream().anyMatch(n -> n.canReach(destination, visitedNodes));
    }

    private List<Node> noVisitedNodes() {
        return new ArrayList<>();
    }
}
