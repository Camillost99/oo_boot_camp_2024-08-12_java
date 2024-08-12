/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import com.nrkei.training.oo.quantities.Quantity;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static com.nrkei.training.oo.quantities.Unit.*;
import static org.junit.jupiter.api.Assertions.*;

// Ensures Quantity operates correctly
public class QuantityTest {

    @Test void equalityOfLikeUnits() {
        assertEquals(new Quantity(4, TABLESPOON), new Quantity(4, TABLESPOON));
        assertNotEquals(new Quantity(4, TABLESPOON), new Quantity(6, TABLESPOON));
        assertNotEquals(new Quantity(4, TABLESPOON), new Object());
        assertNotEquals(new Quantity(4, TABLESPOON), null);
    }

    @Test void equalityOfDifferentUnits() {
        assertNotEquals(new Quantity(4, TABLESPOON), new Quantity(4, TEASPOON));
    }

    @Test void setOperations() {
        assertTrue(new HashSet<>(Collections.singletonList(new Quantity(4, TABLESPOON))).contains(new Quantity(4, TABLESPOON)));
        assertEquals(1, new HashSet<>(Arrays.asList(new Quantity(4, TABLESPOON), new Quantity(4, TABLESPOON))).size());
    }

    @Test void hash() {
        assertEquals(new Quantity(4, TABLESPOON).hashCode(), new Quantity(4, TABLESPOON).hashCode());
    }
}
