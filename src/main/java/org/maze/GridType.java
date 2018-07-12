package org.maze;

enum GridType {
    Render, Road;

    static GridType getBy(int x, int y) {
        return (x - 1) % 2 == 0 && (y - 1) % 2 == 0 && x - 1 >= 0 && y - 1 >= 0 ? Road : Render;
    }
}
