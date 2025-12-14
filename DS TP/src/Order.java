import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int amount;
    private PaymentStrategy paymentStrategy;
    private List<OrderObserver> observers = new ArrayList<>(); 
    private boolean isPaid = false;
    public Order(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    public void processOrder(){
        if (paymentStrategy == null){
            throw new RuntimeException("Payment method");
        }
        paymentStrategy.pay(amount);
        this.isPaid = true;
        notifyObserver();
    }
    public void attach(OrderObserver observer) {
        observers.add(observer);
    }
    private void notifyObserver() {
        for (OrderObserver observer : observers) {
            observer.update(this);
        }
    }
    public int getId() { return id; }
    public boolean isPaid() { return isPaid; }
}
