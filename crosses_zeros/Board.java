package crosses_zeros;

import crosses_zeros.exceptions.FieldIsOccupiedException;
import crosses_zeros.exceptions.WrongFieldException;

/**
 * Игровая доска
 */
public abstract class Board {
    private char FIRST_PLAYER_MARK = 'X';
    private char SECOND_PLAYER_MARK = 'O';
    protected int columns;
    protected int rows;
    protected int[] board;

    public Board(int n, int m) {
        columns = n;
        rows = m;
        board = new int[n * m];
    }

    protected void setField(int player, int x, int y) throws WrongFieldException, FieldIsOccupiedException {
        if (x > columns || y > rows) {
            throw new WrongFieldException();
        }
        int field = y * columns + x;
        if (board[field] != 0) {
            throw new FieldIsOccupiedException();
        }
        board[field] = player;
    }

    /**
     * Отрисовка доски
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Header
        sb.append("\u250C\u2500");
        for (int i = 0; i < columns; i++) {
            sb.append("\u252C ").append(i).append(" ");
        }
        sb.append("\u2510\n");
        // Body
        for (int j = 0; j < rows; j++) {
            sb.append("\u251C\u2500");
            for (int i = 0; i < columns; i++) {
                sb.append("\u253C\u2500\u2500\u2500");
            }
            sb.append("\u2524\n");
            sb.append(j + " \u2502");
            for (int i = 0; i < columns; i++) {
                sb.append(" ");
                switch (board[j * columns + i]) {
                    case 1:
                        sb.append(FIRST_PLAYER_MARK);
                        break;
                    case 2:
                        sb.append(SECOND_PLAYER_MARK);
                        break;
                    default:
                        sb.append(" ");
                }
                sb.append(" \u2502");
            }
            sb.append("\n");
        }
        // Footer
        sb.append("\u2514\u2500");
        for (int i = 0; i < columns; i++) {
            sb.append("\u2534\u2500\u2500\u2500");
        }
        sb.append("\u2518\n");
        return sb.toString();
    }
}
