package com.jekyll.wu.widget.calculator.util;

/**
 * Created by jiewu on 2017/5/7.
 */

public class OperatorUtils {

    public static Operator create(String symbol) {
        for (Operator operator : Operator.Operators) {
            if (operator.getSymbol().equals(symbol)) {
                return operator;
            }
        }
        return null;
    }

    public static boolean isOperator(String symbol) {
        for (Operator operator : Operator.Operators) {
            if (symbol.equals(operator.getSymbol())) {
                return true;
            }
        }
        return false;
    }

    public static Double calculator(Operator curTop, Double op1, Double op2) {
        if (curTop == null || op1 == null) {
            return null;
        }
        if (curTop == Operator.PERCENT) {
            return op1 / 100;
        }
        if (op2 == null) {
            return null;
        } else if (curTop == Operator.PLUS) {
            return op1 + op2;
        } else if (curTop == Operator.MINUS) {
            return op2 - op1;
        } else if (curTop == Operator.MULTIPLY) {
            return op1 * op2;
        } else if (curTop == Operator.DIVISION) {
            return op2 / op1;
        }
        return null;
    }

    /**
     * 判断是否是一元运算符
     *
     * @param operator
     * @return
     */
    public static boolean isUnaryOperator(Operator operator) {
        return operator == Operator.PERCENT;
    }

    /**
     * 判断运算符是不是二元运算符
     *
     * @param operator
     * @return
     */
    public static boolean isBinaryOperator(Operator operator) {
        return operator == Operator.PLUS || operator == Operator.MINUS ||
                operator == Operator.MULTIPLY || operator == Operator.DIVISION;
    }

    /**
     * 是不是普通的加减乘除运算符
     *
     * @param operator
     * @return
     */
    public static boolean isCommonOperator(Operator operator) {
        return operator == Operator.PLUS || operator == Operator.MINUS ||
                operator == Operator.MULTIPLY || operator == Operator.DIVISION ||
                operator == Operator.PERCENT;
    }

    public static boolean isCommonOperator(String symbol) {
        Operator operator = create(symbol);
        if (operator != null) {
            return isCommonOperator(operator);
        }
        return false;
    }

}
