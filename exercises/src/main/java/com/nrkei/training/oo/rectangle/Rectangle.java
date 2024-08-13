/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.rectangle;

import com.nrkei.training.oo.order.Orderable;

// Understands a four-sided polygon with sides at right angles
public class Rectangle implements Orderable<Rectangle> {
    public static Rectangle square(double side) {
        return new Rectangle(side, side);
    }

    private final double length;
    private final double width;

    public Rectangle(double length, double width) {
        if (length <= 0 || width <= 0) throw new IllegalArgumentException("Dimensions must be bigger than zero");
        this.length = length;
        this.width = width;
    }

    public double area() {
        return length * width;
    }

    public double perimeter() {
        return 2 * (length + width);
    }

    @Override
    public boolean isBetterThan(Rectangle other) {
        return this.area() > other.area();
    }
}
