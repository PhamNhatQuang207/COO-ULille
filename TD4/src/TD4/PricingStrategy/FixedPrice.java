package TD4.PricingStrategy;
import TD4.Person.Person;
public class FixedPrice implements PricePolicy {
    private final float price;

    public FixedPrice(float price) {
        this.price = price;
    }

    @Override
    public float getPrice(Person p) {
        return price;
    }
    
}
