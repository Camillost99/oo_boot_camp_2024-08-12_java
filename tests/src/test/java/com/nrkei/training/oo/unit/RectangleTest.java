/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import com.nrkei.training.oo.rectangle.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Ensures Rectangle operates correctly
class RectangleTest {

    @Test void area() {
        assertEquals(24.0, new Rectangle(4.0, 6.0).area());
    }

    @Test void perimeter() {
        assertEquals(20.0, new Rectangle(4.0, 6.0).perimeter());
    }

    @Test void parameterRanges() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 6));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(4, 0));
    }
}
