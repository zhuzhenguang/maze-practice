package org.maze;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MazeTest {
    @Test
    public void should_init_road_grids() {
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
}
