package org.maze;

class Grid {
    private int x;
    private int y;
    private GridType gridType;

    Grid(int x, int y, GridType gridType) {
        if (x < 0 || y < 0) {
            throw new MazeException("Number out of range");
        }

        this.x = x;
        this.y = y;
        this.gridType = gridType;
    }

    static Grid[][] buildGrids(Dimension renderDimension, Connections connectionDefinition) {
        Grid[][] renderGrids = new Grid[renderDimension.x()][renderDimension.y()];

        for (int x1 = 0; x1 < renderGrids.length; x1++) {
            for (int y1 = 0; y1 < renderGrids[x1].length; y1++) {
                renderGrids[x1][y1] = new Grid(x1, y1, getGridType(connectionDefinition, x1, y1));
            }
        }
        return renderGrids;
    }

    private static GridType getGridType(Connections connectionDefinition, int x1, int y1) {
        return connectionDefinition.contains(x1, y1) ? GridType.Road : GridType.getBy(x1, y1);
    }

    Grid getGridBetween(Grid anotherGrid) {
        return new Grid(
                x() == anotherGrid.x() ? x() : Math.min(x(), anotherGrid.x()) + 1,
                y() == anotherGrid.y() ? y() : Math.min(y(), anotherGrid.y()) + 1,
                GridType.Road);
    }

    int x() {
        return x;
    }

    int y() {
        return y;
    }

    @Override
    public String toString() {
        return gridType == GridType.Road ? "[R]" : "[W]";
    }
}