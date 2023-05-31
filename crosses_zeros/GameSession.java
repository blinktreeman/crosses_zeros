package crosses_zeros;

import crosses_zeros.exceptions.FieldIsOccupiedException;
import crosses_zeros.exceptions.WrongFieldException;

import java.util.ArrayList;
import java.util.List;

public class GameSession extends Board {
    private final int winCount;
    public List<Turn> playersTurns = new ArrayList<>();

    public GameSession(int n, int m, int winCount) {
        super(n, m);
        this.winCount = winCount;
    }

    public void addTurn(int player, int x, int y)
            throws WrongFieldException, FieldIsOccupiedException {
        // Устанавливаем на доске фишку игрока
        setField(player, x, y);
        // Заполняем возможные варианты развития от заданного поля
        setDirections(player, x, y);

    }

    private void setDirections(int player, int x, int y) {
        // Вычисляем номер поля
        int field = y * this.columns + x;
        // Для поля создаем экземпляр Turn
        Turn turn = new Turn(player);

        // North
        // Если расстояние достаточно для размещения выигрышной комбинации
        if (y - winCount >= 0) {
            // Список с полями направления
            List<Field> directions = new ArrayList<>();
            // Стартовое поле в список
            directions.add(new Field(field, true));

            for (int i = 1; i < winCount; i++) {
                // Текущее значение по вертикали "на север"
                int temp = board[field - i * columns];
                if (temp == player) {
                    directions.add(new Field(field - i * columns, true));
                } else if (temp == 0) {
                    directions.add(new Field(field - i * columns, false));
                } else {
                    // По данному направлению фишка соперника,
                    // направление не может быть выигрышным
                    break;
                }
            }
            // Заносим список "север"
            if (directions.size() == winCount) {
                turn.directionFields.add(directions);
            }
        }

        // South
        if (y + winCount < rows) {
            List<Field> directions = new ArrayList<>();
            directions.add(new Field(field, true));

            for (int i = 1; i < winCount; i++) {
                int temp = board[field + i * columns];
                if (temp == player) {
                    directions.add(new Field(field + i * columns, true));
                } else if (temp == 0) {
                    directions.add(new Field(field + i * columns, false));
                } else {
                    break;
                }
            }
            if (directions.size() == winCount) {
                turn.directionFields.add(directions);
            }
        }

        // East
        if (x + winCount < columns) {
            List<Field> directions = new ArrayList<>();
            directions.add(new Field(field, true));
            for (int i = 1; i < winCount; i++) {
                int temp = board[field + i];
                if (temp == player) {
                    directions.add(new Field(field + i, true));
                } else if (temp == 0) {
                    directions.add(new Field(field + i, false));
                } else {
                    break;
                }
            }
            if (directions.size() == winCount) {
                turn.directionFields.add(directions);
            }
        }

        // West
        if (x - winCount >= 0) {
            List<Field> directions = new ArrayList<>();
            directions.add(new Field(field, true));
            for (int i = 1; i < winCount; i++) {
                int temp = board[field - i];
                if (temp == player) {
                    directions.add(new Field(field - i, true));
                } else if (temp == 0) {
                    directions.add(new Field(field - i, false));
                } else {
                    break;
                }
            }
            if (directions.size() == winCount) {
                turn.directionFields.add(directions);
            }
        }

        // NE
        if (y - winCount >= 0 && x + winCount < columns) {
            List<Field> directions = new ArrayList<>();
            directions.add(new Field(field, true));
            for (int i = 1; i < winCount; i++) {
                int temp = board[field - i * columns + i];
                if (temp == player) {
                    directions.add(new Field(field - i * columns + i, true));
                } else if (temp == 0) {
                    directions.add(new Field(field - i * columns + i, false));
                } else {
                    break;
                }
            }
            if (directions.size() == winCount) {
                turn.directionFields.add(directions);
            }
        }

        // NW
        if (y - winCount >= 0 && x - winCount >= 0) {
            List<Field> directions = new ArrayList<>();
            directions.add(new Field(field, true));
            for (int i = 1; i < winCount; i++) {
                int temp = board[field - i * columns - i];
                if (temp == player) {
                    directions.add(new Field(field - i * columns - i, true));
                } else if (temp == 0) {
                    directions.add(new Field(field - i * columns - i, false));
                } else {
                    break;
                }
            }
            if (directions.size() == winCount) {
                turn.directionFields.add(directions);
            }
        }

        // SE
        if (y + winCount < rows && x + winCount < columns) {
            List<Field> directions = new ArrayList<>();
            directions.add(new Field(field, true));
            for (int i = 1; i < winCount; i++) {
                int temp = board[field + i * columns + i];
                if (temp == player) {
                    directions.add(new Field(field + i * columns + i, true));
                } else if (temp == 0) {
                    directions.add(new Field(field + i * columns + i, false));
                } else {
                    break;
                }
            }
            if (directions.size() == winCount) {
                turn.directionFields.add(directions);
            }
        }

        // SW
        if (y + winCount < rows && x - winCount >= 0) {
            List<Field> directions = new ArrayList<>();
            directions.add(new Field(field, true));
            for (int i = 1; i < winCount; i++) {
                int temp = board[field + i * columns - i];
                if (temp == player) {
                    directions.add(new Field(field + i * columns - i, true));
                } else if (temp == 0) {
                    directions.add(new Field(field + i * columns - i, false));
                } else {
                    break;
                }
            }
            if (directions.size() == winCount) {
                turn.directionFields.add(directions);
            }
        }

        playersTurns.add(turn);
    }
}
