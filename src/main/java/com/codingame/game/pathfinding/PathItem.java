package com.codingame.game.pathfinding;

import com.codingame.game.grid.Coord;

public class PathItem {
    public int cumulativeLength = 0;
    int totalPrevisionalLength = 0;
    PathItem precedent = null;
    Coord coord;

    PathItem(Coord coord) {
        this.coord = coord;
    }

    public int getTotalPrevisionalLength() {
        return totalPrevisionalLength;
    }

}
