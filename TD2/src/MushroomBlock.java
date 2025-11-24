public class MushroomBlock extends Block {
    public MushroomBlock() {
        super();
    }

    @Override
    protected void applyEffect(Player p) {
        p.modifyResistance(+1); 
    }
}
