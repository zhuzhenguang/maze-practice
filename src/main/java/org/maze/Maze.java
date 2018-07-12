package org.maze;

import java.util.Arrays;
import java.util.stream.Collectors;

class Maze {
    String print(String command) {
        String[] commands = command.split("\n");
        String dimensionString = commands[0];
        String connectionString = commands[1];

        String[] roadDimensions = dimensionString.split(" ");
        String[] connections = connectionString.split(";");

        Grid[][] renderGrids = RenderGrid.buildGrids(convertRoadGridsToRenderGrids(roadDimensions));

        StringBuilder result = new StringBuilder();
        for (Grid[] renderGrid : renderGrids) {
            result.append(String.join(" ", Arrays.stream(renderGrid).map(Object::toString).collect(Collectors.toList()))).append("\n");
        }
        return result.toString();
    }

    private int[] convertRoadGridsToRenderGrids(String[] roadDimensions) {
        return Arrays.stream(roadDimensions)
                .mapToInt(Integer::parseInt).map(dimension -> dimension * 2 + 1)
                .toArray();
    }
}
