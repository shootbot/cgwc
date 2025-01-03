import com.codingame.game.Organ;
import com.codingame.game.OrganType;
import com.codingame.game.Player;
import com.codingame.game.grid.Coord;
import com.codingame.game.grid.Direction;
import com.codingame.game.grid.Protein;
import com.codingame.game.grid.Tile;
import com.codingame.my.Printer;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class BfsPlayer {

    public static void main(String args[]) {
        Printer out = new Printer();
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // columns in the game grid
        int height = in.nextInt(); // rows in the game grid
        Player me = new Player(1);
        Player opp = new Player(0);

        while (true) {
            int entityCount = in.nextInt();
            Set<Coord> proteins = new LinkedHashSet<>();
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
                    tile.placeOrgan(new Organ(owner == 1 ? me : opp, OrganType.of(type), Direction.of(organDir)));
                }
                if (type.equals("A")) {
                    tile.setProtein(Protein.A);
                } else if (type.equals("B")) {
                    tile.setProtein(Protein.B);
                } else if (type.equals("C")) {
                    tile.setProtein(Protein.C);
                } else if (type.equals("D")) {
                    tile.setProtein(Protein.D);
                }

                tile.setProtein();
            }
            int myA = in.nextInt();
            int myB = in.nextInt();
            int myC = in.nextInt();
            int myD = in.nextInt(); // your protein stock
            int oppA = in.nextInt();
            int oppB = in.nextInt();
            int oppC = in.nextInt();
            int oppD = in.nextInt(); // opponent's protein stock
            int requiredActionsCount = in.nextInt(); // your number of organisms, output an action for each one in any order
            for (int i = 0; i < requiredActionsCount; i++) {
                // To debug: System.err.println("Debug messages...");
                System.out.println(chooseMove());
            }
        }
    }

    private static String chooseMove() {
        return ;
    }


}