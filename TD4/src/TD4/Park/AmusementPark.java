package TD4.Park;

import TD4.Ride.Ride;
import TD4.PricingStrategy.PricePolicy;
import TD4.PricingStrategy.NoFeePolicy;

/**
 * Amusement park where entrance fee is paid and attractions are mostly free
 * but some may require additional fees
 */
public class AmusementPark extends Park {
    private static final PricePolicy DEFAULT_PRICE_POLICY = new NoFeePolicy();

    public AmusementPark(String name) {
        super(name);
    }

    /**
     * Add a ride with default no-fee policy
     * @param key unique identifier for the ride
     * @param ride the ride to add
     */
    @Override
    public void addRide(String key, Ride ride) {
        // In amusement parks, rides are free by default (entrance fee was paid)
        // But individual rides can override this if they require additional fees
        super.addRide(key, ride);
    }
}
