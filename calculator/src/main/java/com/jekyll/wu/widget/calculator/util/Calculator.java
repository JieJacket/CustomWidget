package com.jekyll.wu.widget.calculator.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;


import com.jekyll.wu.widget.calculator.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by jie on 2017/5/2.
 */

public class Calculator {

    public static final String[] OPERANDS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
//    public static final String[] OPERATORS = {"+", "-", "x", "÷", "%"};

    public static final Operator[] OPERATORS = {
            Operator.PLUS,
            Operator.MINUS,
            Operator.MULTIPLY,
            Operator.DIVISION,
            Operator.PERCENT,
//            Operator.LEFT_BRACKET,
//            Operator.RIGHT_BRACKET,
    };
    private final Context context;

    public Calculator(@NonNull Context context) {
        this.context = context;
    }

    private TextView inputTextView;

    public void setInputTextView(TextView inputTextView) {
        this.inputTextView = inputTextView;
    }


    private static final Double MAX_NUMBER = 9999999999.99d;//操作数最大值

    private static final int MAX_DECIMAL_LENGTH = 2;//小数位最长长度

    private StringBuffer input = new StringBuffer();

    public void append(String s) {

        input.append(s);
        //TODO check valid for input

        setInputText();
    }

    /**
     * 输入数字
     *
     * @param index
     */
    public void appendOperand(int index) {
        if (index < 0 || index > OPERANDS.length - 1) {
            return;
        }
        append(OPERANDS[index]);
    }

    /**
     * 输入字符
     *
     * @param index
     */
    public void appendOperator(int index) {
        if (index < 0 || index > OPERATORS.length - 1) {
            return;
        }
        append(OPERATORS[index].getSymbol());
    }

    /**
     * 后退
     */
    public void delete() {
        int length = input.length();
        if (length > 0) {
            input.deleteCharAt(length - 1);
        }
        setInputText();
    }

    private void setInputText() {
        if (inputTextView != null) {
            inputTextView.setText(input.toString());
        }
    }

    /**
     * 清空
     */
    public void clear() {
        input = new StringBuffer();
        setInputText();
    }


    public Double calculator() {
        try {
            List<Object> objects = handleInput();
            return transferAndCalculator(objects);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Object> handleInput() throws RuntimeException {
        if (input.length() == 0) {
            return null;
        }
        List<Object> result = new LinkedList<>();
        char[] chars = input.toString().toCharArray();
        int i = 0;
        StringBuilder operand = new StringBuilder();
        while (i < chars.length) {
            char c = chars[i];
            if (Character.isDigit(c) || '.' == c) {
                operand.append(c);
            } else if (OperatorUtils.isOperator(Character.toString(c))) {
                if (operand.length() > 0) {
                    Double d = Double.parseDouble(operand.toString());
                    result.add(d);
                    operand = new StringBuilder();
                }
                result.add(OperatorUtils.create(Character.toString(c)));
            } else {
                throw new RuntimeException(context.getString(R.string.str_error_character, Character.toString(c)));
            }
            i++;
        }
        if (operand.length() > 0) {
            Double d = Double.parseDouble(operand.toString());
            result.add(d);
        }
        return result;
    }

    private Double transferAndCalculator(List<Object> objects) {
        if (objects == null || objects.isEmpty()) {
            return null;
        }
        Stack<Operator> operators = new Stack<>();
        Stack<Double> operands = new Stack<>();
        for (Object obj : objects) {
            if (obj instanceof Double) {
                operands.push((Double) obj);
            } else if (obj instanceof Operator) {
                Operator operator = (Operator) obj;
                operation(operators, operands, operator);
            }
        }

        return calculator(operators, operands);
    }

    private void operation(Stack<Operator> operators, Stack<Double> operands, Operator operator) {
        if (!operators.isEmpty() && operator.compareTo(operators.peek()) <= 0) {
            Operator top = operators.pop();
            if (OperatorUtils.isUnaryOperator(top)) {
                Double result = OperatorUtils.calculator(top, operands.pop(), null);
                operands.push(result);
            } else if (OperatorUtils.isBinaryOperator(top)) {
                Double result = OperatorUtils.calculator(top, operands.pop(), operands.pop());
                operands.push(result);
            }
        }
        operators.push(operator);
    }

    private Double calculator(Stack<Operator> operators, Stack<Double> operands) {
        if (operands.isEmpty()) {
            return null;
        }
        if (operators.isEmpty()){
            return operands.pop();
        }
        while (!operators.isEmpty()) {
            if (OperatorUtils.isUnaryOperator(operators.peek())) {
                Double result = OperatorUtils.calculator(operators.pop(), operands.pop(), null);
                operands.push(result);
            } else if (OperatorUtils.isBinaryOperator(operators.peek())) {
                Double result = OperatorUtils.calculator(operators.pop(), operands.pop(), operands.pop());
                operands.push(result);
            } else {
                throw new RuntimeException(context.getString(R.string.str_error_character, operators.peek().getSymbol()));
            }
        }

        if (!operands.isEmpty() && operands.size() == 1) {
            return operands.pop();
        }
        return null;
    }

}
