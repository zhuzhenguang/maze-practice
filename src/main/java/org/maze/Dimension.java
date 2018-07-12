package org.maze;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import java.util.regex.Pattern;

class Dimension {
    private int x;
    private int y;

    private Dimension(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Dimension buildFrom(String command) {
        String[] dimensionString = command.split(" ");

        if (!Arrays.stream(dimensionString).allMatch(dimension -> Pattern.matches("\\d*$", dimension))) {
            throw new MazeException("Invalid number format");
        }
        return new Dimension(Integer.parseInt(dimensionString[0]), Integer.parseInt(dimensionString[1]));
    }

    Dimension convertedBy(IntUnaryOperator intUnaryOperator) {
        return new Dimension(intUnaryOperator.applyAsInt(x), intUnaryOperator.applyAsInt(y));
    }

    int x() {
        return x;
    }

    int y() {
        return y;
    }
}
