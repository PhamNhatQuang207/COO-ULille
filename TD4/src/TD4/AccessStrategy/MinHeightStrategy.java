package TD4.AccessStrategy;
import TD4.Person.Person;

public class MinHeightStrategy implements AccessStrategy {
    private int minHeight;

    public MinHeightStrategy(int minHeight) {
        this.minHeight = minHeight;
    }

    @Override
    public boolean accessGranted(Person person) {
        return person.getHeight() >= minHeight;
    }

    @Override
    public String description() {
        return "Minimum height required: " + minHeight + "cm";
    }
}
