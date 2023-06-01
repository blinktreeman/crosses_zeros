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

    /**
     * Добавление хода в игру
     * 
     * @param player номер игрока
     * @param x      координата x хода
     * @param y      координата y хода
     * @throws WrongFieldException      ход вне пределов доски
     * @throws FieldIsOccupiedException поле занято
     */
    public void addTurn(int player, int x, int y)
            throws WrongFieldException, FieldIsOccupiedException {
        // Устанавливаем на доске фишку игрока
        setField(player, x, y);
        // Заполняем возможные варианты развития от заданного поля
        setDirections(player, x, y);
        // При каждом ходе перестраиваем варианты развития
        rebuildDirections(player, x, y);
    }

    /**
     * Поиск среди ходов комбинаций с необходмиым количеством установленных фишек
     * 
     * @return номер игрока с победной комбинацией, 0 если нет
     */
    public int anyoneWin() {
        for (Turn turn : playersTurns) {
            for (List<Field> fieldList : turn.directionFields) {
                if (fieldList.stream().filter(f -> f.isActive()).count() == winCount) {
                    return turn.getPlayer();
                }
            }
        }
        return 0;
    }

    private void rebuildDirections(int player, int x, int y) {
        int field = y * this.columns + x;
        for (Turn turn : playersTurns) {
            // Если ход в списке ходов того же игрока,
            // корректируем его варианты развития
            // TODO: stream
            if (turn.getPlayer() == player) {
                for (List<Field> fieldList : turn.directionFields) {
                    for (Field f : fieldList) {
                        if (f.getField() == field) {
                            f.setActive();
                        }
                    }
                }
            }
            // иначе удаляем варианты из списка соперника
            // TODO: stream
            else {
                for (int i = 0; i < turn.directionFields.size(); i++) {
                    List<Field> fieldList = turn.directionFields.get(i);
                    boolean isPresent = false;
                    for (int j = 0; j < fieldList.size(); j++) {
                        if (fieldList.get(j).getField() == field) {
                            isPresent = true;
                            break;
                        }
                    }
                    if (isPresent) {
                        turn.directionFields.remove(fieldList);
                    }
                }
            }
        }
    }

    /**
     * Сохраняет возможные варианты развития от установленного поля
     * по 8-ми направлениям (N, NE, E, SE, S, SW, W, NW).
     * Размещение выигрышной комбинации возможно, если достаточно полей (winCount)
     * и по направлению нет фишек соперника.
     * 
     * @param player
     * @param x
     * @param y
     */
    private void setDirections(int player, int x, int y) {
        // Вычисляем номер поля
        int field = y * this.columns + x;
        // Для поля создаем экземпляр Turn
        Turn turn = new Turn(player);

        // North
        // Если расстояние достаточно для размещения выигрышной комбинации
        if (y - winCount + 1 >= 0) {
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
        if (y + winCount <= rows) {
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
        if (x + winCount <= columns) {
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
        if (x - winCount + 1 >= 0) {
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
        if (y - winCount + 1 >= 0 && x + winCount <= columns) {
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
        if (y - winCount + 1 >= 0 && x - winCount + 1 >= 0) {
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
        if (y + winCount <= rows && x + winCount <= columns) {
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
        if (y + winCount <= rows && x - winCount + 1 >= 0) {
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
        // Ход с возможными вариантами развития заносим в список ходов
        playersTurns.add(turn);
    }
}
