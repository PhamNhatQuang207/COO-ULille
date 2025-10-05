public class LifeBonusBlock extends Block {
    public LifeBonusBlock() {
        super();
        this.nbCoins = 0; 
    }

    @Override
    public int getNbCoins() {
        return this.nbCoins;
    }

    @Override
    protected void applyEffect(Player p) {
        p.changeNbLives(+1);
    }
}
