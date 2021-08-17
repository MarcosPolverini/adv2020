package com.polverini.calculator;

public interface ExpressionEvaluator {

    static ExpressionEvaluator getInstanceForVersion(final int version) {
        switch (version) {
            case 1:
                return new ExpressionEvaluatorOne();
            default:
                throw new UnsupportedOperationException("Version not implemented");
        }
    }

    long eval(final String expression);


    default String subExpression(final String expression) {
        int open = 0;
        int length = 0;
        do {
            var ch = expression.charAt(length++);
            if (ch == ')') {
                open--;
            } else if (ch == '(') {
                open++;
            }
        } while (open != 0);
        return expression.substring(1, length - 1);
    }

    default String removeSpaces(final String expression) {
        return expression.replaceAll("\\s", "");
    }

}
