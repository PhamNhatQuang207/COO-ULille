public class AirShipment extends Shipment {
    
    private static final int LIMIT = 80000;
    
    public AirShipment(int distance) {
        super(distance, LIMIT);
    }

    @Override
    public void add(Goods g) {
        int newQuantity = this.currentQuantity + g.getVolume();

        if (newQuantity > limit) {
            throw new IllegalStateException("Capacity limit exceeded. Current: " + currentQuantity + ", Limit: " + limit + ", To add: " + g.getVolume());
        }

        goodsList.add(g);
        currentQuantity = newQuantity;
    }

    @Override
    public double cost() {

        return 10 * distance + currentQuantity * 4;
    }
    
}
