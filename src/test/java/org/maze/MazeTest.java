package org.maze;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class MazeTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_print_grids_with_connection() {
        Maze maze = new Maze();

        String result = maze.print("3 3\n" +
                "0,1 0,2;0,0 1,0;0,1 1,1;0,2 1,2;1,0 1,1;1,1 1,2;1,1 2,1;1,2 2,2;2,0 2,1");

        assertEquals("[W] [W] [W] [W] [W] [W] [W]\n" +
                "[W] [R] [W] [R] [R] [R] [W]\n" +
                "[W] [R] [W] [R] [W] [R] [W]\n" +
                "[W] [R] [R] [R] [R] [R] [W]\n" +
                "[W] [W] [W] [R] [W] [R] [W]\n" +
                "[W] [R] [R] [R] [W] [R] [W]\n" +
                "[W] [W] [W] [W] [W] [W] [W]", result);
    }

    @Test
    public void should_throw_exception_when_input_dimension_as_invalid_number() {
        Maze maze = new Maze();

        thrown.expect(MazeException.class);
        thrown.expectMessage("Invalid number format");

        maze.print("3 a\n0,1 0,2");
    }

    @Test
    public void should_throw_exception_when_input_connections_as_invalid_number() {
        Maze maze = new Maze();

        thrown.expect(MazeException.class);
        thrown.expectMessage("Invalid number format");

        maze.print("3 3\n0,b 0,2");
    }

    @Test
    public void should_throw_exception_when_input_negative_number() {
        Maze maze = new Maze();

        thrown.expect(MazeException.class);
        thrown.expectMessage("Number out of range");

        maze.print("3 3\n0,-1 0,2");
    }
}
