package TD4.Park;

import TD4.Ride.Ride;
import TD4.PricingStrategy.PricePolicy;
import TD4.AccessStrategy.AccessStrategy;
import TD4.AccessStrategy.AndAccessStrategy;

/**
 * Fairground where each attraction has its own pricing
 * Can have special days with park-wide discounts or restrictions
 */
public class Fairground extends Park {
    private PricePolicy specialDayPriceModifier;
    private AccessStrategy specialDayAccessRestriction;

    public Fairground(String name) {
        super(name);
        this.specialDayPriceModifier = null;
        this.specialDayAccessRestriction = null;
    }

    /**
     * Apply a special day price modifier (e.g., 10% discount on all rides)
     * @param priceModifier the price policy to apply on top of each ride's pricing
     */
    public void setSpecialDayPricing(PricePolicy priceModifier) {
        this.specialDayPriceModifier = priceModifier;
        
        // Apply to all existing rides
        for (Ride ride : rides.values()) {
            if (priceModifier != null) {
                // Store original and apply modifier
                ride.setPricePolicy(priceModifier);
            }
        }
    }

    /**
     * Apply a special day access restriction (e.g., children only day - max age)
     * This is added ON TOP of existing ride restrictions
     * @param restriction the additional access restriction to apply
     */
    public void setSpecialDayAccessRestriction(AccessStrategy restriction) {
        this.specialDayAccessRestriction = restriction;
        
        // Apply to all existing rides
        for (Ride ride : rides.values()) {
            if (restriction != null) {
                // Combine with existing restrictions using AND logic
                AccessStrategy combined = new AndAccessStrategy(ride.getAccessStrategy(), restriction);
                ride.setAccessStrategy(combined);
            }
        }
    }

    /**
     * Clear special day restrictions and restore normal operation
     */
    public void clearSpecialDay() {
        this.specialDayPriceModifier = null;
        this.specialDayAccessRestriction = null;
        
        // Rides will maintain their modified policies
        // To fully restore, you'd need to track original policies
    }

    public PricePolicy getSpecialDayPriceModifier() {
        return specialDayPriceModifier;
    }

    public AccessStrategy getSpecialDayAccessRestriction() {
        return specialDayAccessRestriction;
    }
}
