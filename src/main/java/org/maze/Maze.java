package org.maze;

import java.util.Arrays;
import java.util.stream.Collectors;

class Maze {
    String print(String command) {
        Grid[][] renderGrids = RenderGrid.buildGrids(
                Arrays.stream(command.split(" "))
                        .mapToInt(Integer::parseInt).map(dimension -> dimension * 2 + 1)
                        .toArray());

        StringBuilder result = new StringBuilder();
        for (Grid[] renderGrid : renderGrids) {
            result.append(String.join(" ", Arrays.stream(renderGrid).map(Object::toString).collect(Collectors.toList()))).append("\n");
        }
        return result.toString();
    }
}

class RenderGrid implements Grid {
    private int x;
    private int y;

    private RenderGrid(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Grid[][] buildGrids(int[] renderGridDimensions) {
        Grid[][] renderGrids = new RenderGrid[renderGridDimensions[0]][renderGridDimensions[1]];

        for (int x = 0; x < renderGrids.length; x++) {
            for (int y = 0; y < renderGrids[x].length; y++) {
                renderGrids[x][y] = new RenderGrid(x, y);
            }
        }

        return renderGrids;
    }

    @Override
    public String toString() {
        return (x - 1) % 2 == 0 && (y - 1) % 2 == 0 && x - 1 >= 0 && y - 1 >= 0 ? "[R]" : "[W]";
    }
}

interface Grid {
}
