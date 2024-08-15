/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;

// Understands its neighbors
public class Node {
    private static final List<Node> NO_VISITED_NODES = new ArrayList<>();

    private final List<Link> links = new ArrayList<>();

    public boolean canReach(Node destination) {
        return path(destination, NO_VISITED_NODES, Path::cost) != Path.NONE;
    }

    public int hopCount(Node destination) {
        return path(destination, Path::hopCount).hopCount();
    }

    public double cost(Node destination) {
        return path(destination).cost();
    }

    public Path path(Node destination) {
        return path(destination, Path::cost);
    }

    private Path path(Node destination, ToDoubleFunction<Path> strategy) {
        var result = path(destination, NO_VISITED_NODES, strategy);
        if (result == Path.NONE) throw new IllegalArgumentException("Destination not reachable");
        return result;
    }

    Path path(Node destination, List<Node> visitedNodes, ToDoubleFunction<Path> strategy) {
        if (this == destination) return new Path.ActualPath();
        if (visitedNodes.contains(this)) return Path.NONE;
        return links.stream()
                .map(link -> link.path(destination, copyWithThis(visitedNodes), strategy))
                .min(Comparator.comparingDouble(strategy))
                .orElse(Path.NONE);
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
