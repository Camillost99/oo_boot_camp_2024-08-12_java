/*
 * Copyright (c) 2024 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.order;

import java.util.Arrays;

// Understands specific sequences of elements
public interface Orderable<T> {
    boolean isBetterThan(T other);

    static <S extends Orderable<S>> S best(S first, S... elements) {
        return Arrays.stream(elements).reduce(
                first,
                (champion, challenger) -> challenger.isBetterThan(champion) ? challenger : champion);
    }
}
