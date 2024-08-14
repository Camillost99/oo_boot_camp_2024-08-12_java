/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import com.nrkei.training.oo.graph.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Ensures graph algorithms work correctly
public class GraphTest {
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
}
