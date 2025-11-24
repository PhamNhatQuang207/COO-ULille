import java.util.*;

public class Main {
    public static void main(String[] args) {
        Player mario = Player.MARIO;

        List<Block> blocks = new ArrayList<>();
        blocks.add(new MushroomBlock());
        blocks.add(new LifeBonusBlock());
        blocks.add(new PoisonBlock());
        blocks.add(new JackpotBlock());
        blocks.add(new DoubleEffectBlock(new LifeBonusBlock()));

        // Đăng ký các View
        BlockViewFactory.register(MushroomBlock.class, MushroomBlockView::new);
        BlockViewFactory.register(LifeBonusBlock.class, LifeBonusBlockView::new);
        BlockViewFactory.register(PoisonBlock.class, PoisonBlockView::new);
        BlockViewFactory.register(JackpotBlock.class, JackpotBlockView::new);
        BlockViewFactory.register(DoubleEffectBlock.class, DoubleEffectBlockView::new);

        // Tạo danh sách View từ danh sách Block
        List<BlockView> views = new ArrayList<>();
        for (Block b : blocks) {
            views.add(BlockViewFactory.createView(b));
        }

        // Hiển thị tất cả block
        for (BlockView v : views) {
            v.display();
        }

        // Test Mario phá các block
        for (Block b : blocks) {
            b.isBroken(mario);
        }

        System.out.println("Mario lives = " + mario.getNbLives());
        System.out.println("Mario resistance = " + mario.getResistance());
        System.out.println("Mario points = " + mario.getNbPoints());
    }
}
