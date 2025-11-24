package TD4.Ride;

import TD4.AccessStrategy.AccessNotGrantedException;
import TD4.AccessStrategy.AccessStrategy;
import TD4.Person.Person;
import TD4.PricingStrategy.PricePolicy;

/**
 * Represents an attraction/ride in an amusement park
 */
public class Ride {
    private String name;
    private String originalName;
    private AccessStrategy accessStrategy;
    private AccessStrategy originalAccessStrategy;
    private PricePolicy pricePolicy;
    private PricePolicy originalPricePolicy;
    private boolean isOutOfOrder;

    public Ride(String name, AccessStrategy accessStrategy, PricePolicy pricePolicy) {
        this.name = name;
        this.originalName = name;
        this.accessStrategy = accessStrategy;
        this.originalAccessStrategy = accessStrategy;
        this.pricePolicy = pricePolicy;
        this.originalPricePolicy = pricePolicy;
        this.isOutOfOrder = false;
    }

    /**
     * The person p uses (if granted) this ride.
     * @param p the person who wants to use this ride
     * @return the fee for this ride for p
     * @throws AccessNotGrantedException if person p cannot use this ride: access is not granted for p
     */
    public float welcome(Person p) throws AccessNotGrantedException {
        if (isOutOfOrder) {
            throw new AccessNotGrantedException("Ride '" + name + "' is out of order");
        }
        
        if (!accessStrategy.accessGranted(p)) {
            throw new AccessNotGrantedException("Access denied to '" + name + "': " + accessStrategy.description());
        }
        
        return pricePolicy.getPrice(p);
    }

    /**
     * Mark the ride as out of order (maintenance mode)
     */
    public void outOfOrder() {
        if (!isOutOfOrder) {
            isOutOfOrder = true;
            name = originalName + " (out of service)";
        }
    }

    /**
     * Reopen the ride after maintenance
     */
    public void open() {
        if (isOutOfOrder) {
            isOutOfOrder = false;
            name = originalName;
            accessStrategy = originalAccessStrategy;
            pricePolicy = originalPricePolicy;
        }
    }

    public String getName() {
        return name;
    }

    public void setAccessStrategy(AccessStrategy accessStrategy) {
        this.accessStrategy = accessStrategy;
        if (!isOutOfOrder) {
            this.originalAccessStrategy = accessStrategy;
        }
    }

    public void setPricePolicy(PricePolicy pricePolicy) {
        this.pricePolicy = pricePolicy;
        if (!isOutOfOrder) {
            this.originalPricePolicy = pricePolicy;
        }
    }

    public AccessStrategy getAccessStrategy() {
        return accessStrategy;
    }

    public PricePolicy getPricePolicy() {
        return pricePolicy;
    }

    public boolean isOutOfOrder() {
        return isOutOfOrder;
    }
}
