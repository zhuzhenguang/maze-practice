package org.maze;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Connections {
    private List<Connection> connections;

    private Connections(List<Connection> connections) {
        this.connections = connections;
    }

    static Connections buildFrom(String command) {
        String[] connectionString = command.split(";");

        List<Connection> connections = Arrays.stream(connectionString).map(connection -> {
            String[] connectionGrids = connection.split(" ");
            String[] startGridString = connectionGrids[0].split(",");
            String[] endGridString = connectionGrids[1].split(",");
            return new Connection(startGridString, endGridString);
        }).collect(Collectors.toList());

        return new Connections(connections);
    }

    Connections convertedBy(IntUnaryOperator intUnaryOperator) {
        return new Connections(this.connections
                .stream()
                .map(connection -> connection.convertedBy(intUnaryOperator))
                .collect(Collectors.toList()));
    }

    boolean contains(int x, int y) {
        return connectionPoints().stream().anyMatch(point -> point.x() == x && point.y() == y);
    }

    private List<Grid> connectionPoints() {
        return connections.stream().map(Connection::connectionPoint).collect(Collectors.toList());
    }
}

class Connection {
    private Grid startGrid;
    private Grid endGrid;

    Connection(String[] startGridString, String[] endGridString) {
        if (anyOneNotNumber(startGridString) || anyOneNotNumber(endGridString)) {
            throw new MazeException("Invalid number format");
        }
        startGrid = new Grid(Integer.parseInt(startGridString[0]), Integer.parseInt(startGridString[1]), GridType.Road);
        endGrid = new Grid(Integer.parseInt(endGridString[0]), Integer.parseInt(endGridString[1]), GridType.Road);
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
