/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import com.nrkei.training.oo.rectangle.Rectangle;
import org.junit.jupiter.api.Test;

import static com.nrkei.training.oo.order.Orderable.best;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Ensures that Orderable algorithms function correctly
public class OrderableTest {

    @Test void rectangleWithLargestArea() {
        assertEquals(24, best(
                new Rectangle(2, 3),
                new Rectangle(4, 6),
                Rectangle.square(3))
                .area());
    }
}
