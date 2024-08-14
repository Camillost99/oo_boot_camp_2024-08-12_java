/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.List;

// Understands a connection from one Node to another
class Link {
    private final double cost;
    private final Node target;

    Link(double cost, Node target) {
        this.cost = cost;
        this.target = target;
    }

    double hopCount(Node destination, List<Node> visistedNodes) {
        return target.hopCount(destination, visistedNodes) + 1;
    }

    double cost(Node destination, List<Node> visistedNodes) {
        return target.cost(destination, visistedNodes) + cost;
    }
}
