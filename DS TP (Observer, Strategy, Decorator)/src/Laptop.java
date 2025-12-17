public class Laptop implements Product {
    @Override
    public double cost(){
        return 1000.0;
    }

    @Override
    public String description() {
        return "Laptop Gaming";
    }
}
