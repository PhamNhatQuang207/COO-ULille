package TD4.Park;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import TD4.Ride.Ride;

/**
 * Abstract base class for park entities (AmusementPark and Fairground)
 */
public abstract class Park {
    protected String name;
    protected Map<String, Ride> rides;

    public Park(String name) {
        this.name = name;
        this.rides = new HashMap<>();
    }

    /**
     * Add a ride to the park
     * @param key unique identifier for the ride
     * @param ride the ride to add
     */
    public void addRide(String key, Ride ride) {
        rides.put(key, ride);
    }

    /**
     * Remove a ride from the park
     * @param key unique identifier of the ride to remove
     * @return the removed ride, or null if not found
     */
    public Ride removeRide(String key) {
        return rides.remove(key);
    }

    /**
     * Get a ride by its key
     * @param key unique identifier of the ride
     * @return the ride, or null if not found
     */
    public Ride getRide(String key) {
        return rides.get(key);
    }

    /**
     * Get all rides in the park
     * @return collection of all rides
     */
    public Collection<Ride> getAllRides() {
        return rides.values();
    }

    public String getName() {
        return name;
    }
}
