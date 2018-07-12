package org.maze;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import java.util.regex.Pattern;

class Connection {
    private Grid startGrid;
    private Grid endGrid;

    Connection(String[] startGridString, String[] endGridString) {
        if (anyOneNotNumber(startGridString) || anyOneNotNumber(endGridString)) {
            throw new MazeException("Invalid number format");
        }

        startGrid = new Grid(
                Integer.parseInt(startGridString[0]),
                Integer.parseInt(startGridString[1]),
                GridType.Road);
        endGrid = new Grid(
                Integer.parseInt(endGridString[0]),
                Integer.parseInt(endGridString[1]),
                GridType.Road);
    }

    private boolean anyOneNotNumber(String[] grids) {
        return !Arrays.stream(grids).allMatch(grid -> Pattern.matches("^-?\\d*$", grid));
    }

    private Connection(Grid startGrid, Grid endGrid) {
        this.startGrid = startGrid;
        this.endGrid = endGrid;
    }

    Connection convertedBy(IntUnaryOperator intUnaryOperator) {
        Grid startGrid = new Grid(
                intUnaryOperator.applyAsInt(this.startGrid.x()),
                intUnaryOperator.applyAsInt(this.startGrid.y()),
                GridType.Road);
        Grid endGrid = new Grid(
                intUnaryOperator.applyAsInt(this.endGrid.x()),
                intUnaryOperator.applyAsInt(this.endGrid.y()),
                GridType.Road);
        return new Connection(startGrid, endGrid);
    }

    Grid connectionPoint() {
        return startGrid.getGridBetween(endGrid);
    }
}
