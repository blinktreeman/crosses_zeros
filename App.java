import crosses_zeros.Board;

public class App {
    public static void main(String[] args) {
        Board board = new Board(5, 5);
        try {
            board.setField(0, 0, 1);
            board.setField(4, 4, 2);
            board.setField(4, 7, 2);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(board);
    }
}
