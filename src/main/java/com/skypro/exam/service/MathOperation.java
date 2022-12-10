package com.skypro.exam.service;

public enum MathOperation {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String sign;

    MathOperation(String sign) {
        this.sign = sign;
    }

    public int getAnswer(int num1, int num2) {
        if (num2 == 0 && this == DIVIDE) {
            throw new IllegalArgumentException("Zero division");
        }
        return switch (this) {
            case PLUS -> num1 + num2;
            case MINUS -> num1 - num2;
            case MULTIPLY -> num1 * num2;
            case DIVIDE -> num1 / num2;
        };
    }

    public String getQuestion(int num1, int num2) {
        return String.format("%d %s %d = ?", num1, this.sign, num2);
    }
}
