public class FluvialShipment extends Shipment {
    
    private static final int LIMIT = 300000;
    
    public FluvialShipment(int distance) {
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

        return distance * Math.sqrt(currentQuantity);
    }
}