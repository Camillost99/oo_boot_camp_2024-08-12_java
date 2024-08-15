/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.List;
import java.util.stream.Stream;

// Understands a connection from one Node to another
class Link {
    private final double cost;
    private final Node target;

    Link(double cost, Node target) {
        this.cost = cost;
        this.target = target;
    }

    static double totalCost(List<Link> links) {
        return links.stream().mapToDouble(l -> l.cost).sum();
    }

    Stream<Path> paths(Node destination, List<Node> visistedNodes) {
        return target.paths(destination, visistedNodes).map(p -> p.prepend(this));
    }

    Stream<Path> paths(List<Node> visistedNodes) {
        return target.paths(visistedNodes).map(p -> p.prepend(this));
    }
}
