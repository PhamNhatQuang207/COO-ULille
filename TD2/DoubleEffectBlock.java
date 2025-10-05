public class DoubleEffectBlock extends Block {
    private Block block;

    public DoubleEffectBlock(Block block) {
        this.block = block; 
    }

    @Override
    public int getNbCoins() {
        return 2 * block.getNbCoins();
    }

    @Override
    protected void applyEffect(Player p) {
        block.applyEffect(p);
        block.applyEffect(p);
    }
}
