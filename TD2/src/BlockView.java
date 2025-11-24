public abstract class BlockView {
    protected Block block;

    public BlockView(Block block) {
        this.block = block;
    }

    public abstract void display();
}
