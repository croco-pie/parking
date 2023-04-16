package com.crocopie.parking.enumerated;

import lombok.Getter;

@Getter
public enum Size {
    S(1), M(2), L(4);

    private final int capacity;

    Size(int capacity) {
        this.capacity = capacity;
    }
}
