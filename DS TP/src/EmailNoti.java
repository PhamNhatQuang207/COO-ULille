public class EmailNoti implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.print("Email: order " + order.getId() + " paid");
    }
    
}
