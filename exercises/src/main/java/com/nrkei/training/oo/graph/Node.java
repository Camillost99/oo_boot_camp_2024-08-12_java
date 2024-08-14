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

    private final List<Link> links = new ArrayList<>();

    public boolean canReach(Node destination) {
        return hopCount(destination, NO_VISITED_NODE) != UNREACHABLE;
    }

    public int hopCount(Node destination) {
        var result = hopCount(destination, NO_VISITED_NODE);
        if (result == UNREACHABLE) throw new IllegalArgumentException("Destination not reachable");
        return (int)result;
    }

    public double cost(Node destination) {
        var result = cost(destination, NO_VISITED_NODE);
        if (result == UNREACHABLE) throw new IllegalArgumentException("Destination not reachable");
        return result;
    }

    double cost(Node destination, List<Node> visitedNodes) {
        if (this == destination) return 0.0;
        if (visitedNodes.contains(this)) return UNREACHABLE;
        return links.stream()
                .mapToDouble(n -> n.cost(destination, copyWithThis(visitedNodes)))
                .min()
                .orElse(UNREACHABLE);
    }

    double hopCount(Node destination, List<Node> visitedNodes) {
        if (this == destination) return 0.0;
        if (visitedNodes.contains(this)) return UNREACHABLE;
        return links.stream()
                .mapToDouble(n -> n.hopCount(destination, copyWithThis(visitedNodes)))
                .min()
                .orElse(UNREACHABLE);
    }

    private List<Node> copyWithThis(List<Node> originals) {
        List<Node> results = new ArrayList<>(originals);
        results.add(this);
        return results;
    }

    public LinkBuilder cost(double amount) {
        return new LinkBuilder(amount);
    }

    public class LinkBuilder {

        private final double cost;

        public LinkBuilder(double cost) {
            this.cost = cost;
        }

        public Node to(Node neighbor) {
            links.add(new Link(cost, neighbor));
            return neighbor;
        }
    }
}
