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

//    public Deque<PathItem> find(Collection<Coord> starts, Set<Coord> ends) {
//        PathItem item = bfs(starts, ends);
//        Deque<PathItem> path = new ArrayDeque<>();
//        if (item != null) {
//            PathItem i = item;
//            while (i != null) {
//                path.offerFirst( i);
//                i = i.precedent;
//            }
//        }
//        return path;
//    }

        public PathItem find(Collection<Coord> starts, Set<Coord> ends) {
        return bfs(starts, ends);
    }

    public PathItem bfs(Collection<Coord> starts, Set<Coord> ends) {
        starts.forEach(c -> {
                PathItem pi = new PathItem(c);
                queue.offerLast(pi);
        });
        while (!queue.isEmpty()) {
            PathItem current = queue.poll();

            if (ends.contains(current.coord)) {
                return current;
            }

            for (Coord next : grid.getNeighbours(current.coord)) {
                Tile nextTile = grid.get(next);
                if (!nextTile.isObstacle() && !nextTile.hasOrgan() && !visited[next.getX()][next.getY()]) {
                    visited[next.getX()][next.getY()] = true;
                    PathItem nextPi = new PathItem(next);
                    nextPi.precedent = current;
                    queue.add(nextPi);
                }
            }
        }

        return null;
    }

    public Set<Coord> inflate(List<Coord> proteins) {
        Set<Coord> ret = new HashSet<>();
        for (Coord c: proteins) {
            ret.addAll(grid.getNeighbours(c));
        }
        return ret;
    }
}
