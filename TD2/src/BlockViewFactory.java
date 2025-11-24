import java.util.*;
public class BlockViewFactory {
    private static Map<Class<? extends Block>, BlockViewCreator> registry = new HashMap<>();

    public static void register(Class<? extends Block> blockClass, BlockViewCreator creator) {
        registry.put(blockClass, creator);
    }

    public static BlockView createView(Block block) {
        BlockViewCreator creator = registry.get(block.getClass());
        if (creator == null) {
            throw new IllegalArgumentException("No view registered for " + block.getClass());
        }
        return creator.create(block);
    }

    // interface tạo view
    public interface BlockViewCreator {
        BlockView create(Block block);
    }
}
