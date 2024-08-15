/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.List;

// Understands a connection from one Node to another
class Link {
    static final CostStrategy LEAST_COST = cost -> cost;
    static final CostStrategy FEWEST_HOPS = _ -> 1.0;

    private final double cost;
    private final Node target;

    Link(double cost, Node target) {
        this.cost = cost;
        this.target = target;
    }

    static double totalCost(List<Link> links) {
        return links.stream().mapToDouble(l -> l.cost).sum();
    }

    double cost(Node destination, List<Node> visistedNodes, Link.CostStrategy strategy) {
        return target.cost(destination, visistedNodes, strategy) + strategy.cost(cost);
    }

    Path path(Node destination, List<Node> visistedNodes) {
        return target.path(destination, visistedNodes).prepend(this);
    }

    interface CostStrategy {
        double cost(double amount);
    }
}
