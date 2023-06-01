import crosses_zeros.Field;
import crosses_zeros.GameSession;
import crosses_zeros.Turn;
import crosses_zeros.players.PlayerEntity;
import crosses_zeros.players.PlayerFactory;
import crosses_zeros.players.PlayerTypes;

import java.util.List;

public class App {
    private static int BOARD_WIDTH = 7;
    private static int BOARD_HEIGHT = 5;
    private static int NUMBER_OF_CHIPS_TO_WIN = 4;

    public static void main(String[] args) {
        GameSession gameSession = new GameSession(BOARD_WIDTH, BOARD_HEIGHT, NUMBER_OF_CHIPS_TO_WIN);
        PlayerFactory playerFactory = new PlayerFactory();
        PlayerEntity palyer1 = playerFactory.getPlayer(1, PlayerTypes.HUMAN);
        PlayerEntity palyer2 = playerFactory.getPlayer(2, PlayerTypes.AI);

        do {
            try {
                int x = palyer1.makeTurn()[0];
                int y = palyer1.makeTurn()[1];
                gameSession.addTurn(palyer1.getId(), 2, 2);
                gameSession.addTurn(palyer2.getId(), 0, 0);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } while (gameSession.anyoneWin() == 0);

        System.out.println(gameSession);
    }
}
