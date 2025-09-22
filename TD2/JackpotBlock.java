package TD2;

import java.util.Random;

public class JackpotBlock extends Block {
    private static final Random ALEA = new Random();

    public JackpotBlock() {
        super();
    }

    @Override
    public int getNbCoins() {
        return 1 + ALEA.nextInt(20);
    }

    @Override
    protected void applyEffect(Player p) {
    }
}

