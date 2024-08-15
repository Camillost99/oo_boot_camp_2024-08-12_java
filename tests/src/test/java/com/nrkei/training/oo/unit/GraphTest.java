/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import com.nrkei.training.oo.graph.Node;
import com.nrkei.training.oo.graph.Path;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Ensures graph algorithms work correctly
public class GraphTest {
    private static final double EPSILON = 1e-10;

    private final static Node A = new Node();
    private final static Node B = new Node();
    private final static Node C = new Node();
    private final static Node D = new Node();
    private final static Node E = new Node();
    private final static Node F = new Node();
    private final static Node G = new Node();

    static {
        B.cost(5).to(A);
        B.cost(6).to(C).cost(7).to(D).cost(2).to(E).cost(3).to(B).cost(4).to(F);
        C.cost(1).to(D);
        C.cost(8).to(E);
    }

    @Test void canReach() {
        assertTrue(B.canReach(B));
        assertTrue(B.canReach(A));
        assertTrue(B.canReach(F));
        assertTrue(B.canReach(D));
        assertTrue(C.canReach(F));
        assertFalse(G.canReach(B));
        assertFalse(A.canReach(B));
        assertFalse(B.canReach(G));
    }

    @Test void hopCount() {
        assertEquals(0, B.hopCount(B));
        assertEquals(1, B.hopCount(A));
        assertEquals(1, B.hopCount(F));
        assertEquals(2, B.hopCount(D));
        assertEquals(3, C.hopCount(F));
        assertThrows(IllegalArgumentException.class, () -> G.hopCount(B));
        assertThrows(IllegalArgumentException.class, () -> A.hopCount(B));
        assertThrows(IllegalArgumentException.class, () -> B.hopCount(G));
    }

    @Test void cost() {
        assertEquals(0, B.cost(B));
        assertEquals(5, B.cost(A));
        assertEquals(4, B.cost(F));
        assertEquals(7, B.cost(D));
        assertEquals(10, C.cost(F));
        assertThrows(IllegalArgumentException.class, () -> G.cost(B));
        assertThrows(IllegalArgumentException.class, () -> A.cost(B));
        assertThrows(IllegalArgumentException.class, () -> B.cost(G));
    }

    @Test void path() {
        assertPath(A, A, 0, 0);
        assertPath(B, A, 1, 5);
        assertPath(B, F, 1, 4);
        assertPath(B, D, 2, 7);
        assertPath(C, F, 4, 10);
        assertThrows(IllegalArgumentException.class, () -> G.path(A));
        assertThrows(IllegalArgumentException.class, () -> A.path(B));
        assertThrows(IllegalArgumentException.class, () -> B.path(G));
    }

    @Test void pathsToDestination() {
        assertEquals(1, A.paths(A).size());
        assertEquals(1, B.paths(A).size());
        assertEquals(1, B.paths(F).size());
        assertEquals(2, B.paths(D).size());
        assertEquals(3, C.paths(F).size());
        assertEquals(0, G.paths(B).size());
        assertEquals(0, B.paths(G).size());
        assertEquals(0, A.paths(B).size());
    }

    @Test void allPathsFromSource() {
        assertEquals(1, A.paths().size());
        assertEquals(9, B.paths().size());
        assertEquals(15, C.paths().size());
        assertEquals(6, D.paths().size());
        assertEquals(7, E.paths().size());
        assertEquals(1, F.paths().size());
        assertEquals(1, G.paths().size());
    }

    private void assertPath(Node source, Node destination, long expectedHopCount, long expectedCost) {
        Path p = source.path(destination);
        assertEquals(expectedHopCount, p.hopCount());
        assertEquals(expectedCost, p.cost(), EPSILON);
    }
}
