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
    private static final double UNREACHABLE = Double.POSITIVE_INFINITY;
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
        var result = hopCount(destination, NO_VISITED_NODE);
        if (result == UNREACHABLE) throw new IllegalArgumentException("Destination not reachable");
        return (int)result;
    }

    private double hopCount(Node destination, List<Node> visitedNodes) {
        if (this == destination) return 0.0;
        if (visitedNodes.contains(this)) return UNREACHABLE;
        var champion = UNREACHABLE;
        for (Node n: neighbors) {
            double challenger = n.hopCount(destination, copyWithThis(visitedNodes)) + 1;
            if (challenger < champion) champion = challenger;
        }
        return champion;
    }

    private List<Node> copyWithThis(List<Node> originals) {
        List<Node> results = new ArrayList<>(originals);
        results.add(this);
        return results;
    }
}
