package crosses_zeros.exceptions;

public class FieldIsOccupiedException extends Exception {
    public FieldIsOccupiedException() {
        super("Wrong turn. The field is occupied");
    }
}
