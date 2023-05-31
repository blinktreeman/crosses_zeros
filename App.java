import crosses_zeros.Field;
import crosses_zeros.GameSession;
import crosses_zeros.Turn;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        GameSession gameSession = new GameSession(5, 5, 5);
        try {
            gameSession.addTurn(1, 0, 0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(gameSession);
        for (Turn i : gameSession.playersTurns) {
            for (List<Field> j : i.directionFields) {
                for (Field k : j) {
                    System.out.print(k.getField() + " ");
                }
                System.out.println("");
            }
        }
    }
}
