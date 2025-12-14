public class GiftWrap extends ProductDecorator {
    public GiftWrap(Product product) { super(product); }

    @Override
    public double cost() { return super.cost() + 10.0; } 

    @Override
    public String description() { return super.description() + " + gift wrap"; }
}
