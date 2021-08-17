package com.polverini.calculator;

final class ExpressionEvaluatorOne implements ExpressionEvaluator {

    public long eval(final String expression) {
        //Remove the spaces.
        var cExpression = removeSpaces(expression);

        var accumulator = 0L;
        char operation = 0;
        var previous = new StringBuilder();

        for (int x = 0; x < cExpression.length(); x++) {
            var ch = cExpression.charAt(x);
            if (Character.isDigit(ch)) {
                previous.append(ch);
                continue;
            }
            if ('(' == ch) {
                var subExpression = subExpression(cExpression.substring(x));
                previous.append(eval(subExpression));
                // + 1 to remove the last )
                x += subExpression.length() + 1;
                continue;
            }
            if (operation != 0) {
                accumulator = processOperation(operation, previous, accumulator);
            } else {
                accumulator = Long.parseLong(previous.toString());
            }
            previous = new StringBuilder();
            operation = ch;
        }
        return processOperation(operation, previous, accumulator);
    }

    private long processOperation(final char ops, final StringBuilder value, final long currentValue) {
        return Operation.getOperationFor(ops).apply(currentValue, Long.parseLong(value.toString()));
    }


}
