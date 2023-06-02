package crosses_zeros.players;

import java.util.Scanner;

/**
 * Действия человека
 */
public class Human extends AbstractPlayer implements PlayerEntity {
    private static final Scanner SCANNER = new Scanner(System.in);

    public Human(int id) {
        this.id = id;
    }

    /**
     * Ход человека
     * @param columns
     * @param rows
     * @return
     */
    @Override
    public int[] makeTurn(int columns, int rows) {
        int[] turn = new int[2];
        turn[0] = SCANNER.nextInt();
        turn[1] = SCANNER.nextInt();
        return turn;
    }

    @Override
    public int getId() {
        return id;
    }
    
}
