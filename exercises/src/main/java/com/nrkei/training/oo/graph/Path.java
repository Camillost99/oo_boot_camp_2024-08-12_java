/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.ArrayList;
import java.util.List;

// Understands a specific route from one Node to another
public class Path {
    private final List<Link> links = new ArrayList<>();

    public Path prepend(Link link) {
        links.addFirst(link);
        return this;
    }

    public double cost() {
        return Link.totalCost(links);
    }

    public int hopCount() {
        return links.size();
    }
}
