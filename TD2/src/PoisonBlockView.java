public class PoisonBlockView extends BlockView {
    public PoisonBlockView(Block block) {
        super(block);
    }

    @Override
    public void display() {
        System.out.println("☠️ Poison Block View");
    }
    
}
