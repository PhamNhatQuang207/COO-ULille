package TD4.PricingStrategy;

import TD4.Person.Person;

public interface PricePolicy {
    float getPrice(Person p);
}
