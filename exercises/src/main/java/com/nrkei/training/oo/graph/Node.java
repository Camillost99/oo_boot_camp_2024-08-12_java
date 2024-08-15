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
import java.util.stream.Stream;

// Understands its neighbors
public class Node {
    private static final List<Node> NO_VISITED_NODES = new ArrayList<>();

    private final List<Link> links = new ArrayList<>();

    public boolean canReach(Node destination) {
        return !paths(destination).isEmpty();
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

    public List<Path> paths(Node destination) {
        return Path.filter(paths(NO_VISITED_NODES), destination).toList();
    }

    public List<Path> paths() {
        return paths(NO_VISITED_NODES).toList();
    }

    Stream<Path> paths(List<Node> visitedNodes) {
        if (visitedNodes.contains(this)) return Stream.empty();
        return Stream.concat(
                Stream.of(new Path(this)),
         links.stream().flatMap(link -> link.paths(copyWithThis(visitedNodes))));
    }

    private Path path(Node destination, ToDoubleFunction<Path> strategy) {
        return paths(destination).stream()
                .min(Comparator.comparingDouble(strategy))
                .orElseThrow(() -> new IllegalArgumentException("Destination cannot be reached"));
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
