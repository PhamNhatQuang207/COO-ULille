package TD4.AccessStrategy;

import TD4.Person.Person;

public class PrimeOnlyAccessStrategy implements AccessStrategy {
    
    @Override
    public boolean accessGranted(Person person) {
        return person.isPrime();
    }

    @Override
    public String description() {
        return "Access only for prime members";
    }
}
