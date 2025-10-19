import java.util.Objects;

public class Goods {
    private final int weight; // #weight: int
    private final int volume; // #volume: int

    public Goods(int weight, int volume) { // +Goods(weight int, volume int)
        if (weight < 0 || volume < 0) {
            throw new IllegalArgumentException("Weight and volume must be non-negative.");
        }
        this.weight = weight;
        this.volume = volume;
    }

    public int getWeight() { // +getWeight(): int
        return weight;
    }

    public int getVolume() { // +getVolume(): int
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return weight == goods.weight && volume == goods.volume;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, volume);
    }
}