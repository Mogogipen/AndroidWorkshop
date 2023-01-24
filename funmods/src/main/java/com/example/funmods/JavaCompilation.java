package com.example.funmods;

import java.math.BigDecimal;

public class JavaCompilation {

    public static void main(String[] args) {
        BigDecimal fifteen = new BigDecimal("15");

        BigDecimal nonNullFifteen = null;
        try {
            if (fifteen == null) {
                throw new NullPointerException();
            } else {
                nonNullFifteen = fifteen;
            }
        } catch (NullPointerException e) {
            nonNullFifteen = new BigDecimal("15");
        }

        BigDecimal nonNullNoInlineFifteen = null;
        try {
            if (fifteen == null) {
                throw new NullPointerException();
            } else {
                nonNullNoInlineFifteen = fifteen;
            }
        } catch (NullPointerException e) {
            // surrounded with Function object
            nonNullNoInlineFifteen = new BigDecimal("15");
        }

    }

}
