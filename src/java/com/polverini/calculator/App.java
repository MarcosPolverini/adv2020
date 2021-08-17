package com.polverini.calculator;

import java.nio.file.Files;
import java.nio.file.Path;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println(getSumForVersion(1));
    }

    private static long getSumForVersion(final int version) throws Exception {
        final var expressionEvaluator = ExpressionEvaluator.getInstanceForVersion(version);
        return Files.lines(getInputPath(version))
                .map(expressionEvaluator::eval)
                .mapToLong(Long::valueOf)
                .sum();
    }

    private static Path getInputPath(final int version) throws Exception {
        var fileName = String.format("input%d.txt", version);
        var input = App.class.getClassLoader().getResource(fileName);
        return Path.of(input.toURI());
    }
}
