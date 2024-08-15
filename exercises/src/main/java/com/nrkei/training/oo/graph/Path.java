/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.ArrayList;
import java.util.List;

// Understands a specific route from one Node to another
public interface Path {
    Path NONE = new Path() {

        @Override
        public double cost() {
            return Double.POSITIVE_INFINITY;
        }

        @Override
        public int hopCount() {
            return Integer.MAX_VALUE;
        }
    };

    default Path prepend(Link link) {
        return this;
    }

    double cost();

    int hopCount();

    class ActualPath implements Path {
        private final List<Link> links = new ArrayList<>();

        @Override
        public Path prepend(Link link) {
            links.addFirst(link);
            return this;
        }

        @Override
        public double cost() {
            return Link.totalCost(links);
        }

        @Override
        public int hopCount() {
            return links.size();
        }
    }
}
