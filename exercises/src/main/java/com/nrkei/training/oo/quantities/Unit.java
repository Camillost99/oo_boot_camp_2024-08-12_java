/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantities;

// Understands a specific metric
public class Unit {
    public static final Unit TEASPOON = new Unit();
    public static final Unit TABLESPOON = new Unit();
    public static final Unit OUNCE = new Unit();
    public static final Unit CUP = new Unit();
    public static final Unit PINT = new Unit();
    public static final Unit QUART = new Unit();
    public static final Unit GALLON = new Unit();

    private Unit() {}
}
