package crosses_zeros.exceptions;

public class WrongFieldException extends Exception{
    public WrongFieldException() {
        super("Specified coordinates outside the board");
    }
}
