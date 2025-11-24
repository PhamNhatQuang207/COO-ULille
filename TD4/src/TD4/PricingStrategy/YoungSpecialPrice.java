package TD4.PricingStrategy;
import TD4.Person.Person;
public class YoungSpecialPrice extends FixedPrice {
    public int age;
    public float reduceRate;
    public YoungSpecialPrice(int age, float reduceRate, float price) {
        super(price);
        this.age = age;
        this.reduceRate = reduceRate;
    }
    @Override
    public float getPrice(Person p) {
        if (p.getAge() < age) {
            return super.getPrice(p) * (1 - reduceRate);
        } else {
            return super.getPrice(p);
        }
    }
}