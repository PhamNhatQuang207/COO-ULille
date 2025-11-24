public class PoisonBlock extends Block {
    public PoisonBlock() {
        super();
        this.nbCoins = 0;
    }

    @Override
    public int getNbCoins() {
        return 0;
    }

    @Override
    protected void applyEffect(Player p) {
        p.changeNbLives(-1);
    }
}
