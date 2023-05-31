package crosses_zeros;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private final int player;
    public List<List<Field>> directionFields = new ArrayList<>();

    public Turn(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public List<List<Field>> getDirectionFields() {
        return directionFields;
    }
}

