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