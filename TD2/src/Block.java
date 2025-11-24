public abstract class Block {
    public static final int NB_COINS = 1;
    protected int nbCoins;
    public Block() {
            this.nbCoins = NB_COINS;
    }
    public int getNbCoins() {
        return this.nbCoins;
    }   
    public void isBroken(Player p) {
        p.addPoints(this.getNbCoins());
        applyEffect(p);
    }
    protected abstract void applyEffect(Player p);
}
