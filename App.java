import crosses_zeros.GameSession;
import crosses_zeros.players.PlayerEntity;
import crosses_zeros.players.PlayerFactory;
import crosses_zeros.players.PlayerTypes;

import java.util.List;

public class App {
    private static int BOARD_WIDTH = 5;
    private static int BOARD_HEIGHT = 5;
    private static int NUMBER_OF_CHIPS_TO_WIN = 4;

    public static void main(String[] args) {
        // Создаем игровую сессию
        GameSession gameSession = new GameSession(BOARD_WIDTH, BOARD_HEIGHT, NUMBER_OF_CHIPS_TO_WIN);
        PlayerFactory playerFactory = new PlayerFactory();
        // Получаем из фабрики двух игроков
        PlayerEntity palyer1 = playerFactory.getPlayer(1, PlayerTypes.AI);
        PlayerEntity palyer2 = playerFactory.getPlayer(2, PlayerTypes.AI);
        // Количество ходов
        int turnCount = 0;
        do {
            try {
                System.out.println(gameSession);
                if (gameSession.anyoneWin() != 0) {
                    System.out.println("Победил игрок " + palyer2.getId());
                    break;
                }
                // Действие первого игрока
                System.out.println("Введите координаты хода X и Y (от 0 до "
                        + (BOARD_WIDTH - 1) + ") через пробел >>> ");
                int[] turn = palyer1.makeTurn(BOARD_WIDTH, BOARD_HEIGHT);
                gameSession.addTurn(palyer1.getId(), turn[0], turn[1]);
                turnCount++;
                System.out.println(gameSession);
                if (gameSession.anyoneWin() != 0) {
                    System.out.println("Победил игрок " + palyer1.getId());
                    break;
                }
                // Действие второго игрока
                System.out.println("Введите координаты хода X и Y (от 0 до "
                        + (BOARD_WIDTH - 1) + ") через пробел >>> ");
                turn = palyer2.makeTurn(BOARD_WIDTH, BOARD_HEIGHT);
                gameSession.addTurn(palyer2.getId(), turn[0], turn[1]);
                turnCount++;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (turnCount < BOARD_WIDTH * BOARD_HEIGHT);
        System.out.println("Game over");
    }
}

/*
Output (Игра AI vs AI):

┌─┬ 0 ┬ 1 ┬ 2 ┬ 3 ┬ 4 ┐
├─┼───┼───┼───┼───┼───┤
0 │ X │ X │ O │ X │ O │
├─┼───┼───┼───┼───┼───┤
1 │ O │ X │ X │ O │   │
├─┼───┼───┼───┼───┼───┤
2 │ X │ O │ O │ O │ X │
├─┼───┼───┼───┼───┼───┤
3 │   │ O │ X │ O │   │
├─┼───┼───┼───┼───┼───┤
4 │ X │   │ O │ X │   │
└─┴───┴───┴───┴───┴───┘

Победил игрок 2

 */