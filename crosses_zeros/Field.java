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

    public boolean isActive() {
        return active;
    }

    public void setActive() {
        this.active = true;
    }
}
