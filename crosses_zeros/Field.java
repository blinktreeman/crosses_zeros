package crosses_zeros;

public class Field {
    private final int field;
    private boolean active = false;

    public Field(int field, boolean active) {
        this.field = field;
        this.active = active;
    }

    public int getField() {
        return field;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
