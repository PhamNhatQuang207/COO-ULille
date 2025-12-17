public class SMSNoti implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.print("SMS: order " + order.getId() + " paid");
    }
}
