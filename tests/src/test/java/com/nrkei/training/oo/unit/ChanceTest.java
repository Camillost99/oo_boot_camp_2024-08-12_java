/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import com.nrkei.training.oo.probability.Chance;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

// Ensures Chance works correctly
public class ChanceTest {
    private static final Chance IMPOSSIBLE = new Chance(0.0);
    private static final Chance UNLIKELY = new Chance(0.25);
    private static final Chance EQUALLY_LIKELY = new Chance(0.5);
    private static final Chance LIKELY = new Chance(0.75);
    private static final Chance CERTAIN = new Chance(1.0);

    @Test void equality() {
        assertEquals(LIKELY, new Chance(0.75));
        assertNotEquals(LIKELY, UNLIKELY);
        assertNotEquals(LIKELY, new Object());
        assertNotEquals(LIKELY, null);
    }

    @Test void setOperations() {
        assertTrue(new HashSet<>(Collections.singletonList(LIKELY)).contains(new Chance(0.75)));
        assertEquals(1, new HashSet<>(Arrays.asList(LIKELY, new Chance(0.75))).size());
    }

    @Test void hash() {
        assertEquals(LIKELY.hashCode(), new Chance(0.75).hashCode());
        assertEquals(new Chance(0.3).hashCode(), new Chance(0.3).not().not().hashCode());
    }

    @Test void not() {
        assertEquals(UNLIKELY, LIKELY.not());
        assertEquals(LIKELY, LIKELY.not().not());
        assertEquals(CERTAIN, IMPOSSIBLE.not());
        assertEquals(EQUALLY_LIKELY, EQUALLY_LIKELY.not());
        assertEquals(new Chance(0.3), new Chance(0.3).not().not());
    }

    @Test void and() {
        assertEquals(UNLIKELY, EQUALLY_LIKELY.and(EQUALLY_LIKELY));
        assertEquals(new Chance(0.1875), LIKELY.and(UNLIKELY));
        assertEquals(LIKELY.and(UNLIKELY), UNLIKELY.and(LIKELY));
        assertEquals(IMPOSSIBLE, LIKELY.and(IMPOSSIBLE));
        assertEquals(LIKELY, CERTAIN.and(LIKELY));
    }

    @Test void or() {
        assertEquals(LIKELY, EQUALLY_LIKELY.or(EQUALLY_LIKELY));
        assertEquals(new Chance(0.8125), LIKELY.or(UNLIKELY));
        assertEquals(LIKELY.or(UNLIKELY), UNLIKELY.or(LIKELY));
        assertEquals(LIKELY, LIKELY.or(IMPOSSIBLE));
        assertEquals(CERTAIN, CERTAIN.or(LIKELY));
    }

    @Test void invalidLikelihood() {
        assertThrows(AssertionError.class, () -> new Chance(-0.01));
        assertThrows(AssertionError.class, () -> new Chance(1.01));
    }
}
