import org.junit.Test;
import static org.junit.Assert.*;

public class ExamTest {

    @Test
    public void testDecoratorPricing() {
        // Mua Laptop (1000) + Gói quà (10) + Bảo hiểm (50)
        Product myProduct = new Laptop();
        myProduct = new GiftWrap(myProduct);

        assertEquals(1010.0, myProduct.cost(), 0.01); 
        assertEquals("Laptop Gaming + gift wrap", myProduct.description());
    }

    // Test Strategy & Observer: Thanh toán và nhận thông báo
    @Test
    public void testOrderProcess() {
        Order order = new Order(1, 500);
        class MockObserver implements OrderObserver {
            boolean isCalled = false;
            @Override
            public void update(Order o) {
                isCalled = true;
            }
        }
        MockObserver mock = new MockObserver();
        
        order.attach(mock);
        order.setPaymentStrategy(new CreditCardPayment("1234-5678"));
        
        order.processOrder();

        assertTrue("Đơn hàng phải được đánh dấu là đã thanh toán", order.isPaid());
        assertTrue("Observer phải được gọi sau khi thanh toán", mock.isCalled);
    }
}