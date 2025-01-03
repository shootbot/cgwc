import com.codingame.game.GridMaker;
import com.codingame.game.Organ;
import com.codingame.game.OrganType;
import com.codingame.game.Player;
import com.codingame.game.grid.*;
import com.codingame.game.pathfinding.Bfs;
import com.codingame.game.pathfinding.PathItem;
import com.codingame.my.Printer;

import java.util.*;

public class BfsPlayer {

    public static void main(String args[]) {
        Printer out = new Printer();
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // columns in the game grid
        int height = in.nextInt(); // rows in the game grid
        Player me = new Player(); me.setId(1);
        Player opp = new Player(); opp.setId(0);
        Grid grid;

        while (true) {
            int entityCount = in.nextInt();
            List<Tile> tiles = new ArrayList<>();
            Map<Coord, Tile> mapTiles = new HashMap<>();
            Set<Coord> myOrgans = new HashSet<>();
            Set<Coord> myHarvesters = new HashSet<>();
            List<Coord> proteins = new ArrayList<>();
            for (int i = 0; i < entityCount; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                Coord coord = new Coord(x, y);
                Tile tile = new Tile(coord);
                String type = in.next(); // WALL, ROOT, BASIC, TENTACLE, HARVESTER, SPORER, A, B, C, D
                int owner = in.nextInt(); // 1 if your organ, 0 if enemy organ, -1 if neither
                int organId = in.nextInt(); // id of this entity if it's an organ, 0 otherwise
                String organDir = in.next(); // N,E,S,W or X if not an organ
                int organParentId = in.nextInt();
                int organRootId = in.nextInt();

                if (type.equals("WALL")) {
                    tile.setObstacle();
                }
                if (type.equals("ROOT") || type.equals("BASIC") || type.equals("TENTACLE") || type.equals("HARVESTER") || type.equals("SPORER")) {
                    Player player;
                    Organ organ;
                    if (owner == 1) {
                        player = me;
                        myOrgans.add(tile.coord);
                        if (type.equals("HARVESTER")) {
                            myHarvesters.add(tile.coord); // todo учесть direction
                        }
                    } else {
                        player = opp;
                    }
                    organ = new Organ(organId, OrganType.of(type), player, null, Direction.of(organDir), organParentId, organRootId);
                    tile.placeOrgan(organ);
                }
                if (type.equals("A")) {
                    tile.setProtein(Protein.A);
                    proteins.add(tile.coord);
                } else if (type.equals("B")) {
                    tile.setProtein(Protein.B);
                    proteins.add(tile.coord);
                } else if (type.equals("C")) {
                    tile.setProtein(Protein.C);
                    proteins.add(tile.coord);
                } else if (type.equals("D")) {
                    tile.setProtein(Protein.D);
                    proteins.add(tile.coord);
                }
                tiles.add(tile);
                mapTiles.put(coord, tile);
            }
            int myA = in.nextInt();
            int myB = in.nextInt();
            int myC = in.nextInt();
            int myD = in.nextInt(); // your protein stock
            int oppA = in.nextInt();
            int oppB = in.nextInt();
            int oppC = in.nextInt();
            int oppD = in.nextInt(); // opponent's protein stock

            grid = GridMaker.initGrid(width, height, tiles);
            for (Coord c: myOrgans) {
                proteins.removeAll(grid.getNeighbours(c)); // todo ну нельзя просто всех удалять
            }
            Bfs bfs = new Bfs(grid);

            int requiredActionsCount = in.nextInt(); // your number of organisms, output an action for each one in any order
            for (int i = 0; i < requiredActionsCount; i++) {
                PathItem from = bfs.find(bfs.inflate(proteins), myOrgans);
                if (from != null) {
                    int len = length(from);
                    if (len == 2) {
                        int organId = mapTiles.get(from.coord).getOrgan().get().getId();
                        Coord to = from.precedent.coord;
                        Coord protein = findAdjProtein(grid, to, proteins);
                        out.harvester(organId, to.x, to.y, to.direction(protein));
                    } else if (len > 2) {
                        int organId = mapTiles.get(from.coord).getOrgan().get().getId();
                        Coord to = from.precedent.coord;
                        out.basic(organId, to.x, to.y);
                    } else {
                        System.err.println("Invalid path len: " + len + " from " + from);
                    }
                } else {
                    out.justwait();
                }
            }
        }
    }

    private static Coord findAdjProtein(Grid grid, Coord from, List<Coord> proteins) {
        for (Coord c: grid.getNeighbours(from)) {
            if (proteins.contains(c)) return c;
        }
        System.err.println("error! couldnt find adj protein for " + from);
        return null;
    }

    static int length(PathItem pi) {
        int len = 0;
        while (pi != null) {
            pi = pi.precedent;
            len++;
        }
        return len;
    }


}