package TD4.AccessStrategy;

import TD4.Person.Person;

public class MinAgeStrategy implements AccessStrategy {
    private int minAge;

    public MinAgeStrategy(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public boolean accessGranted(Person person) {
        return person.getAge() >= minAge;
    }

    @Override
    public String description() {
        return "Minimum age required: " + minAge;
    }
}
