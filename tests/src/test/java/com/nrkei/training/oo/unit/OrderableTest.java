/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import com.nrkei.training.oo.order.Orderable;
import com.nrkei.training.oo.probability.Chance;
import com.nrkei.training.oo.rectangle.Rectangle;
import org.junit.jupiter.api.Test;

import static com.nrkei.training.oo.order.Orderable.best;
import static com.nrkei.training.oo.quantities.Unit.*;
import static com.nrkei.training.oo.quantities.Unit.CELSIUS;
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

    @Test void leastLikelyChance() {
        assertEquals(new Chance(0.25), Orderable.best(
                new Chance(0.5), new Chance(0.25), new Chance(0.75)
        ));
    }

    @Test void testMaxQuantity() {
        assertEquals(QUART.s(2), Orderable.best(
                GALLON.s(0.2), OUNCE.s(24), GALLON.s(0.5), CUP.s(7)
        ));
        assertEquals(CELSIUS.s(100), Orderable.best(
                FAHRENHEIT.s(212), CELSIUS.s(0), FAHRENHEIT.s(50), CELSIUS.s(-40)
        ));
    }
}
