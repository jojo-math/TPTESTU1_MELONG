package com.stjean.operation;

import java.util.Arrays;

public class OperationMathematique {
    public Integer[] trier(Integer[] listes) {
        if (listes == null) return new Integer[0];
        Integer[] copy = Arrays.copyOf(listes, listes.length);
        Arrays.sort(copy, (a, b) -> b - a); // décroissant
        return copy;

    }

    public boolean estPositif(int nombre) {
        return nombre >= 0;
    }

    public long factoriel(int n) {
        if (n < 0) throw new IllegalParamISIException("Factoriel: n négatif");
        long res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }

    public double maxNumba(double[] valeurs) {
        if (valeurs == null || valeurs.length == 0)
            throw new IllegalArgumentException("Tableau vide");
        double max = valeurs[0];
        for (int i = 1; i < valeurs.length; i++) {
            if (valeurs[i] > max) max = valeurs[i];
        }
        return max;
    }


}
