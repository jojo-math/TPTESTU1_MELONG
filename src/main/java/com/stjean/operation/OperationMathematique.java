package com.stjean.operation;

public class OperationMathematique {
    public boolean estPositif(int nombre) {
        return nombre >= 0;
    }

    public long factoriel(int n) {
        if (n < 0) throw new IllegalArgumentException("n < 0");
        long res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }
}
