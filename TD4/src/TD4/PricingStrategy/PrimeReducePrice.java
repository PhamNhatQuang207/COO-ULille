package TD4.PricingStrategy;
import TD4.Person.Person;
public class PrimeReducePrice extends AbstractPricePolicyDecorator {
    private final float rate;

    public PrimeReducePrice(PricePolicy pricePolicy, float rate) {
        super(pricePolicy);
        this.rate = rate;
    }

    @Override
    public float getPrice(Person p) {
        float basePrice = getDecoratedPolicy().getPrice(p);
        if (p.isPrime()) {
            return basePrice * (1 - rate);
        }
        return basePrice;
    }   
} 