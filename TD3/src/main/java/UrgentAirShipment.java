public class UrgentAirShipment extends AirShipment {
    public UrgentAirShipment(int distance) {
        super(distance);
    }
    @Override
    public double cost() {
        return super.cost()*2;
    }
}
