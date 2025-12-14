public abstract class ProductDecorator implements Product {
    protected Product tempProduct;
    public ProductDecorator(Product product){
        this.tempProduct = product;
    }

    @Override
    public double cost() {
        return tempProduct.cost();
    }

    @Override
    public String description() {
        return tempProduct.description();
    }
    
}
