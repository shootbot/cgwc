package com.codingame.game.pathfinding;


import com.codingame.game.grid.Coord;
import com.codingame.game.grid.Grid;
import com.codingame.game.grid.Tile;

import java.util.*;

public class Bfs {

    private final Grid grid;
    private final boolean[][] visited;
    private final Deque<PathItem> queue = new ArrayDeque<>();


    public Bfs(Grid grid) {
        this.grid = grid;
        this.visited = new boolean[grid.height][grid.height];
    }

//    public void reset() {
//        visited = new boolean[grid.height][grid.height];
//    }


//    public void addStartingPoint(int startX, int startY) {
//        if (isValid(startX, startY) && !visited[startX][startY]) {
//            queue.add(new Cell(startX, startY, 0));
//            visited[startX][startY] = true;
//        }
//    }


    public PathItem findNearestProtein(Collection<Coord> starts) {
        starts.forEach(c -> {
                PathItem pi = new PathItem(c);
                queue.offerLast(pi);
        });
        while (!queue.isEmpty()) {
            PathItem current = queue.poll();

            if (grid.get(current.coord).hasProtein()) {
                return current;
            }

            for (Coord next : grid.getNeighbours(current.coord)) {
                Tile nextTile = grid.get(next);
                if (!nextTile.isObstacle() && !nextTile.hasOrgan() && !visited[next.getX()][next.getY()]) {
                    visited[next.getX()][next.getY()] = true;
                    queue.add(new PathItem(next));
                }
            }
        }

        return null;
    }
}
