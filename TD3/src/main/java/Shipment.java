import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Shipment {

    protected final int distance; 
    protected final int limit;    
    protected final List<Goods> goodsList;
    protected int currentQuantity;

    public Shipment(int distance, int limit) {
        if (distance < 0 || limit < 0) {
            throw new IllegalArgumentException("Distance and limit must be non-negative.");
        }
        this.distance = distance;
        this.limit = limit;
        this.goodsList = new ArrayList<>();
        this.currentQuantity = 0;
    }

    public abstract double cost();

    public abstract void add(Goods g);

    public List<Goods> allGoods() { 
        return Collections.unmodifiableList(goodsList);
    }
    
    public int getCurrentQuantity() {
        return currentQuantity;
    }
    
    public int getQuantityLimit() {
        return limit;
    }
}