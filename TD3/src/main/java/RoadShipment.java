public class RoadShipment extends Shipment {
    
    private static final int LIMIT = 38000;
    
    public RoadShipment(int distance) {
        super(distance, LIMIT);
    }

    @Override
    public void add(Goods g) {
        int newQuantity = this.currentQuantity + g.getWeight();

        if (newQuantity > limit) {
            throw new IllegalStateException("Capacity limit exceeded. Current: " + currentQuantity + ", Limit: " + limit + ", To add: " + g.getWeight());
        }

        goodsList.add(g);
        currentQuantity = newQuantity;
    }

    @Override
    public double cost() {
        return distance * currentQuantity * 4;
    }
    
}
