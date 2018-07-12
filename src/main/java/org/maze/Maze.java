package org.maze;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

class Maze {
    String print(String command) {
        String[] commands = command.split("\n");

        Grid[][] allRenderGrids = Grid.buildGrids(
                renderDimension(commands[0]),
                connectionDefinition(commands[1]));

        return format(allRenderGrids);
    }

    private Connections connectionDefinition(String connectionCommand) {
        return Connections.buildFrom(connectionCommand).convertedBy(roadMappingRender());
    }

    private Dimension renderDimension(String roadGridCommand) {
        return Dimension.buildFrom(roadGridCommand).convertedBy(roadMappingRender());
    }

    private IntUnaryOperator roadMappingRender() {
        return connection -> connection * 2 + 1;
    }

    private String format(Grid[][] allRenderGrids) {
        List<String> results = Arrays.stream(allRenderGrids)
                .map(renderGrid -> String.join(" ", Arrays.stream(renderGrid).map(Object::toString).collect(Collectors.toList())))
                .collect(Collectors.toList());
        return String.join("\n", results);
    }
}

