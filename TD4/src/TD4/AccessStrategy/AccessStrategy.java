package TD4.AccessStrategy;
import TD4.Person.Person;
public interface AccessStrategy {
    boolean accessGranted(Person person);
    String description();
}
