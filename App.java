import crosses_zeros.Field;
import crosses_zeros.GameSession;
import crosses_zeros.Turn;

import java.util.List;

public class App {
    private static int BOARD_WIDTH = 7;
    private static int BOARD_HEIGHT = 5;
    private static int NUMBER_OF_CHIPS_TO_WIN = 4;

    public static void main(String[] args) {
        GameSession gameSession = new GameSession(BOARD_WIDTH, BOARD_HEIGHT, NUMBER_OF_CHIPS_TO_WIN);
        try {
            gameSession.addTurn(1, 2, 2);
            gameSession.addTurn(2, 0, 0);
            gameSession.addTurn(2, 2, 0);
            gameSession.addTurn(2, 1, 0);
            gameSession.addTurn(2, 3, 0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(gameSession);
        // for (Turn i : gameSession.playersTurns) {
        //     for (List<Field> j : i.directionFields) {
        //         for (Field k : j) {
        //             System.out.print(k.getField() + " " + k.isActive() + " ");
        //         }
        //         System.out.println("");
        //     }
        // }
        System.out.println(gameSession.anyoneWin());
    }
}
