package com.codingame.game.pathfinding;

import com.codingame.game.grid.Coord;

public class PathItem {
    public int cumulativeLength = 0;
    public int totalPrevisionalLength = 0;
    public PathItem precedent = null;
    public Coord coord;

    PathItem(Coord coord) {
        this.coord = coord;
    }

    public int getTotalPrevisionalLength() {
        return totalPrevisionalLength;
    }

}
