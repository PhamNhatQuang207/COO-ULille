public class MaxAgeStrategy implements AccessStrategy {
    public static final String DESCRIPTION = "Maximum age access strategy";
    private int maxAge;
    public MaxAgeStrategy(int maxAge) {
        this.maxAge = maxAge;
    }
    @Override
    public boolean accessGranted(Person person) {
        return person.getAge() <= maxAge;
    }
    @Override
    public String description() {
        return DESCRIPTION + " (max age: " + maxAge + ")";
    }
}
