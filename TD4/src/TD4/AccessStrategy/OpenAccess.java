package TD4.AccessStrategy;
import TD4.Person.Person;
public class OpenAccess implements AccessStrategy {
   public static final String DESCRIPTION = "Free access for everyone";
   public OpenAccess() {
   }
   @Override
   public boolean accessGranted(Person person) {
       return true;
    }
    @Override
    public String description() {
        return DESCRIPTION;
    }
}
