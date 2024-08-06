/*
 * Copyright (c) 2024 by Fred George
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
}
