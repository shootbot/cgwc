import com.codingame.game.grid.Coord;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Grow and multiply your organisms to end up larger than your opponent.
 **/
public class WaitPlayer {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // columns in the game grid
        int height = in.nextInt(); // rows in the game grid


        // game loop
        while (true) {
            int entityCount = in.nextInt();
            Set<Coord> proteins = new LinkedHashSet<>();
            for (int i = 0; i < entityCount; i++) {
                int x = in.nextInt();
                int y = in.nextInt(); // grid coordinate
                String type = in.next(); // WALL, ROOT, BASIC, TENTACLE, HARVESTER, SPORER, A, B, C, D

                if (type.equals("A")) {
                    proteins.add(new Coord(x, y));
                }
                int owner = in.nextInt(); // 1 if your organ, 0 if enemy organ, -1 if neither
                int organId = in.nextInt(); // id of this entity if it's an organ, 0 otherwise
                String organDir = in.next(); // N,E,S,W or X if not an organ
                int organParentId = in.nextInt();
                int organRootId = in.nextInt();
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
                System.out.println("WAIT");
            }
        }
    }
}
