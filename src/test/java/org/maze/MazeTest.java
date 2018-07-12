package org.maze;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MazeTest {
    @Test
    public void should_print_grids_without_connection() {
        Maze maze = new Maze();

        String result = maze.print("3 3");

        assertEquals("[W] [W] [W] [W] [W] [W] [W]\n" +
                "[W] [R] [W] [R] [W] [R] [W]\n" +
                "[W] [W] [W] [W] [W] [W] [W]\n" +
                "[W] [R] [W] [R] [W] [R] [W]\n" +
                "[W] [W] [W] [W] [W] [W] [W]\n" +
                "[W] [R] [W] [R] [W] [R] [W]\n" +
                "[W] [W] [W] [W] [W] [W] [W]\n", result);
    }

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
}
