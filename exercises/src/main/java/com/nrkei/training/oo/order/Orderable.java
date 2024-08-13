/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.order;

// Understands specific sequences of elements
public interface Orderable<T> {
    boolean isBetterThan(T other);

    static <S extends Orderable<S>> S best(S first, S... elements) {
        S champion = first;
        for (S challenger: elements) if (challenger.isBetterThan(champion)) champion = challenger;
        return champion;
    }
}
