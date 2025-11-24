package TD4.AccessStrategy;

import TD4.Person.Person;
import java.util.List;
import java.util.Arrays;

/**
 * Composite strategy that requires ALL conditions to be met (AND logic)
 * Example: MinHeight(140) AND PrimeOnly AND MinAge(10)
 */
public class AndAccessStrategy implements AccessStrategy {
    private final List<AccessStrategy> strategies;

    public AndAccessStrategy(AccessStrategy... strategies) {
        this.strategies = Arrays.asList(strategies);
    }

    public AndAccessStrategy(List<AccessStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public boolean accessGranted(Person person) {
        for (AccessStrategy strategy : strategies) {
            if (!strategy.accessGranted(person)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String description() {
        if (strategies.isEmpty()) {
            return "No restrictions";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strategies.size(); i++) {
            sb.append(strategies.get(i).description());
            if (i < strategies.size() - 1) {
                sb.append(" AND ");
            }
        }
        return sb.toString();
    }
}
