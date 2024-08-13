/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantities;

// Understands a specific measurement
public class IntervalQuantity {
    protected final double amount;
    protected final Unit unit;

    IntervalQuantity(double amount, Unit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof IntervalQuantity && this.equals((IntervalQuantity)obj);
    }

    private boolean equals(IntervalQuantity other) {
        return this.isCompatible(other) && this.amount == convertedAmount(other);
    }

    private boolean isCompatible(IntervalQuantity other) {
        return this.unit.isCompatible(other.unit);
    }

    protected double convertedAmount(IntervalQuantity other) {
        return this.unit.convertedAmount(other.amount, other.unit);
    }

    @Override
    public int hashCode() {
        return unit.hashCode(amount);
    }
}
