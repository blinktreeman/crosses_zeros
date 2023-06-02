package crosses_zeros.players;

import crosses_zeros.Field;
import crosses_zeros.GameSession;
import crosses_zeros.Turn;

import java.util.ArrayList;
import java.util.List;

/**
 * Действия AI
 * Умеет только вредить,
 * Выигрывать почти не умеет
 */
public class Artificial extends AbstractPlayer implements PlayerEntity {

    public Artificial(int id) {
        this.id = id;
    }

    @Override
    public int[] makeTurn(int columns, int rows) {
        // Все ходы соперника
        List<Turn> oppTurns = new ArrayList<>();
        for (Turn turn : GameSession.playersTurns) {
            if (turn.getPlayer() != id) {
                oppTurns.add(turn);
            }
        }
        // Все собственные ходы
        List<Turn> myTurns = new ArrayList<>();
        for (Turn turn : GameSession.playersTurns) {
            if (turn.getPlayer() == id) {
                myTurns.add(turn);
            }
        }
        int[] aiTurn;

        // Для всех чужих комбинаций
        // Закрываем если сложено n-1
        aiTurn = closeOpponentTurn(oppTurns, 1, columns, rows);
        if (aiTurn != null) {
            return aiTurn;
        }

        // Закрываем если сложено n-2
        aiTurn = closeOpponentTurn(oppTurns, 2, columns, rows);
        if (aiTurn != null) {
            return aiTurn;
        }

        // Пытаемся развить собственный успех
        if (!myTurns.isEmpty()) {
            for (Turn turn : myTurns) {
                if (!turn.directionFields.isEmpty()) {
                    Field dangerField = turn.directionFields.get(0).stream().filter(f ->
                            !f.isActive()).findAny().orElseThrow();
                    return new int[]{dangerField.getField() % columns,
                            dangerField.getField() / rows};
                }
            }
        }

        // Пытаемся закрыть любое развитие соперника
        aiTurn = closeOpponentTurn(oppTurns,
                GameSession.winCount - 1,
                columns,
                rows);
        if (aiTurn != null) {
            return aiTurn;
        }

        // При отсутствии вариантов развития ставим в первую свободную ячейку
        for (int i = 0; i <= GameSession.board.length; i++) {
            if (GameSession.board[i] == 0) {
                System.out.println(i);
                return new int[]{i % columns, i / rows};
            }
        }
        return null;
    }

    private int[] closeOpponentTurn(List<Turn> turns,
                                    int fieldsToClose,
                                    int columns,
                                    int rows) {
        int[] aiTurn = new int[2];
        for (Turn turn : turns) {
            for (List<Field> fields : turn.directionFields) {
                if (fields.stream().filter(Field::isActive).count() ==
                        GameSession.winCount - fieldsToClose) {
                    Field dangerField = fields.stream().filter(f ->
                            !f.isActive()).findAny().orElseThrow();
                    aiTurn[0] = dangerField.getField() % columns;
                    aiTurn[1] = dangerField.getField() / rows;
                    return aiTurn;
                }
            }
        }
        return null;
    }

    @Override
    public int getId() {
        return id;
    }
}
