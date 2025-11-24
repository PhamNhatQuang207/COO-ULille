package TD4;

import TD4.AccessStrategy.AccessNotGrantedException;
import TD4.AccessStrategy.AndAccessStrategy;
import TD4.AccessStrategy.MaxAgeStrategy;
import TD4.AccessStrategy.MinAgeStrategy;
import TD4.AccessStrategy.MinHeightStrategy;
import TD4.AccessStrategy.OpenAccess;
import TD4.AccessStrategy.OrAccessStrategy;
import TD4.AccessStrategy.PrimeOnlyAccessStrategy;
import TD4.Park.AmusementPark;
import TD4.Park.Fairground;
import TD4.Person.Person;
import TD4.PricingStrategy.FixedPrice;
import TD4.PricingStrategy.NoFeePolicy;
import TD4.PricingStrategy.PrimeReducePrice;
import TD4.PricingStrategy.YoungSpecialPrice;
import TD4.Ride.Ride;

/**
 * Demonstration of the Amusement Park system
 */
public class Demo {
    public static void main(String[] args) {
        // Create different types of visitors
        Person child = new Person(8, 120, false);        // 8 years, 120cm, not prime
        Person teen = new Person(15, 155, false);        // 15 years, 155cm, not prime
        Person adultVIP = new Person(30, 175, true);     // 30 years, 175cm, prime member
        Person tallKid = new Person(11, 145, false);     // 11 years, 145cm, not prime

        // ===== AMUSEMENT PARK EXAMPLE =====
        System.out.println("=== AMUSEMENT PARK ===\n");
        AmusementPark disneyPark = new AmusementPark("Magic Kingdom");

        // Children's ride: under 12, fixed price 0 (included in park entrance)
        Ride kidsRide = new Ride(
            "Carousel",
            new MaxAgeStrategy(12),
            new NoFeePolicy()
        );
        disneyPark.addRide("carousel", kidsRide);

        // Ghost train: open to everyone, VIP pays half price
        Ride ghostTrain = new Ride(
            "Haunted Mansion",
            new OpenAccess(),
            new PrimeReducePrice(new FixedPrice(5.0f), 0.5f)
        );
        disneyPark.addRide("ghost", ghostTrain);

        // Roller coaster: minimum 140cm, fixed price 10
        Ride rollerCoaster = new Ride(
            "Space Mountain",
            new MinHeightStrategy(140),
            new FixedPrice(10.0f)
        );
        disneyPark.addRide("coaster", rollerCoaster);

        // Multi-criteria: 140cm AND VIP (2 conditions)
        Ride extremeRide = new Ride(
            "Extreme Tower",
            new AndAccessStrategy(new MinHeightStrategy(140), new PrimeOnlyAccessStrategy()),
            new FixedPrice(15.0f)
        );
        disneyPark.addRide("extreme", extremeRide);

        // Multi-criteria with 3 conditions: 140cm AND age 10-60 AND VIP
        Ride ultraExtremeRide = new Ride(
            "Ultra Extreme",
            new AndAccessStrategy(
                new MinHeightStrategy(140),
                new MinAgeStrategy(10),
                new MaxAgeStrategy(60),
                new PrimeOnlyAccessStrategy()
            ),
            new FixedPrice(25.0f)
        );
        disneyPark.addRide("ultra", ultraExtremeRide);

        // Multi-criteria: VIP OR taller than 150cm
        Ride specialRide = new Ride(
            "Special VIP Experience",
            new OrAccessStrategy(new PrimeOnlyAccessStrategy(), new MinHeightStrategy(150)),
            new FixedPrice(20.0f)
        );
        disneyPark.addRide("special", specialRide);

        // Test rides
        testRide(disneyPark.getRide("carousel"), child, "Child on Carousel");
        testRide(disneyPark.getRide("ghost"), adultVIP, "VIP Adult on Ghost Train");
        testRide(disneyPark.getRide("coaster"), child, "Child on Roller Coaster (should fail)");
        testRide(disneyPark.getRide("coaster"), tallKid, "Tall kid on Roller Coaster");
        testRide(disneyPark.getRide("extreme"), tallKid, "Tall kid on Extreme (should fail - not VIP)");
        testRide(disneyPark.getRide("extreme"), adultVIP, "VIP Adult on Extreme");
        testRide(disneyPark.getRide("ultra"), adultVIP, "VIP Adult on Ultra Extreme (4 conditions)");
        testRide(disneyPark.getRide("ultra"), tallKid, "Tall kid on Ultra Extreme (should fail)");
        testRide(disneyPark.getRide("special"), teen, "Teen on Special (should fail)");
        testRide(disneyPark.getRide("special"), adultVIP, "VIP on Special (VIP access)");

        // ===== OUT OF ORDER EXAMPLE =====
        System.out.println("\n=== MAINTENANCE MODE ===\n");
        rollerCoaster.outOfOrder();
        System.out.println("Ride name after outOfOrder: " + rollerCoaster.getName());
        testRide(rollerCoaster, tallKid, "Tall kid on out-of-order coaster");
        
        rollerCoaster.open();
        System.out.println("Ride name after open: " + rollerCoaster.getName());
        testRide(rollerCoaster, tallKid, "Tall kid after reopening");

        // ===== FAIRGROUND EXAMPLE =====
        System.out.println("\n=== FAIRGROUND ===\n");
        Fairground fair = new Fairground("Summer Fair");

        // Fairground rides have individual pricing
        Ride bumperCars = new Ride(
            "Bumper Cars",
            new MinAgeStrategy(6),
            new FixedPrice(3.0f)
        );
        fair.addRide("bumpers", bumperCars);

        Ride ferrisWheel = new Ride(
            "Ferris Wheel",
            new OpenAccess(),
            new YoungSpecialPrice(12, 0.3f, 5.0f) // Under 12 get 30% off
        );
        fair.addRide("ferris", ferrisWheel);

        testRide(fair.getRide("bumpers"), child, "Child on Bumper Cars");
        testRide(fair.getRide("ferris"), child, "Child on Ferris Wheel (young discount)");
        testRide(fair.getRide("ferris"), adultVIP, "Adult on Ferris Wheel");

        // Special day: Children only (max age 14)
        System.out.println("\n=== CHILDREN'S DAY AT FAIRGROUND ===\n");
        fair.setSpecialDayAccessRestriction(new MaxAgeStrategy(14));
        
        testRide(fair.getRide("bumpers"), child, "Child on Bumper Cars (children's day)");
        testRide(fair.getRide("bumpers"), adultVIP, "Adult on Bumper Cars (should fail - children's day)");
    }

    private static void testRide(Ride ride, Person person, String description) {
        try {
            float price = ride.welcome(person);
            System.out.println("✓ " + description + " - Price: $" + price);
        } catch (AccessNotGrantedException e) {
            System.out.println("✗ " + description + " - " + e.getMessage());
        }
    }
}
