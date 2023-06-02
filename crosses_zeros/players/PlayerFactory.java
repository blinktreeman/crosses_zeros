package crosses_zeros.players;

public class PlayerFactory {
    private PlayerEntity player;

    public PlayerEntity getPlayer(int id, PlayerTypes playerType) {
        switch (playerType) {
            case HUMAN : {
                player = new Human(id);
                break;
            }
            case AI : {
                player = new Artificial(id);
                break;
            }
        }
        return player;
    }
}
