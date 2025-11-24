package TD4.AccessStrategy;

import TD4.Person.Person;

/**
 * Composite strategy that requires AT LEAST ONE condition to be met (OR logic)
 * Example: PrimeOnly OR MinHeight(150)
 */
public class OrAccessStrategy implements AccessStrategy {
    private final AccessStrategy strategy1;
    private final AccessStrategy strategy2;

    public OrAccessStrategy(AccessStrategy strategy1, AccessStrategy strategy2) {
        this.strategy1 = strategy1;
        this.strategy2 = strategy2;
    }

    @Override
    public boolean accessGranted(Person person) {
        return strategy1.accessGranted(person) || strategy2.accessGranted(person);
    }

    @Override
    public String description() {
        return strategy1.description() + " OR " + strategy2.description();
    }
}
