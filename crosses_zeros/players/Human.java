package crosses_zeros.players;

public class Human extends AbstractPlayer implements PlayerEntity {
    
    public Human(int id) {
        this.id = id;
    }

    @Override
    public int[] makeTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeTurn'");
    }

    @Override
    public int getId() {
        return id;
    }
    
}
