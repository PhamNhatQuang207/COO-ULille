public class BlockViewLeft extends BlockView {
    public BlockViewLeft(Block block) {
        super(block);
    }

    @Override
    public void display() {
        System.out.println("⬅️  Block View (Left)");
    }
}