package TD4.PricingStrategy;
import TD4.Person.Person;

public abstract class AbstractPricePolicyDecorator implements PricePolicy {
    private final PricePolicy decoratedPolicy;

    public AbstractPricePolicyDecorator(PricePolicy pricePolicy) {
        this.decoratedPolicy = pricePolicy;
    }

    @Override
    public float getPrice(Person p) {
        return decoratedPolicy.getPrice(p);
    }

    protected PricePolicy getDecoratedPolicy() {
        return decoratedPolicy;
    }
}
