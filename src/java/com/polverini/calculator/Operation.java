package com.polverini.calculator;

public interface Operation {
    
    static Operation getOperationFor(final char op) {
        switch(op) {
            case '+' : 
                return Long::sum;
            case '*' :
                return (var v1, var v2) -> v1*v2;
            default :
                throw new UnsupportedOperationException("Operation unsupported " + op);
        }
    }

    long apply(final long v1, final long v2);

}
