package com.stjean.operation;

import java.util.Arrays;

public class OperationMathematique {
    public Integer[] trier(Integer[] listes) {
        if (listes == null) return new Integer[0];
        Integer[] copy = Arrays.copyOf(listes, listes.length);
        Arrays.sort(copy, (a, b) -> b - a); // décroissant
        return copy;
    }
}
