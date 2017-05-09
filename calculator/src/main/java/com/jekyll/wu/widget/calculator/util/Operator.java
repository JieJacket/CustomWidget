package com.jekyll.wu.widget.calculator.util;

import android.support.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jiewu on 2017/5/7.
 */

public final class Operator implements Comparable<Operator> {

    public static Set<Operator> Operators = new HashSet<>();

    public static Operator PLUS = new Operator("+", 1);
    public static Operator MINUS = new Operator("-", 1);
    public static Operator MULTIPLY = new Operator("x", 2);
    public static Operator DIVISION = new Operator("÷", 2);
    public static Operator PERCENT = new Operator("%", 3);
//    public static Operator LEFT_BRACKET = new Operator("(", 4);
//    public static Operator RIGHT_BRACKET = new Operator(")", 0);

    static {
        Operators.add(PLUS);
        Operators.add(MINUS);
        Operators.add(MULTIPLY);
        Operators.add(DIVISION);
        Operators.add(PERCENT);
//        Operators.add(LEFT_BRACKET);
//        Operators.add(RIGHT_BRACKET);
    }

    private String symbol;
    private int level;

    private Operator(@NonNull String symbol, int level) {
        this.symbol = symbol;
        this.level = level;
    }

    private Operator() {
    }

    @Override
    public int compareTo(@NonNull Operator o) {
        if (level > o.level) {
            return 1;
        }
        return 0;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Operator) {
            Operator compare = (Operator) obj;
            return symbol.equals(compare.symbol) && level == compare.level;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return symbol.hashCode() & level;
    }

}
