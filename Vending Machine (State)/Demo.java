public class Demo {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(5); // Có 5 chai

        System.out.println("--- Test 1: Mua bình thường ---");
        machine.insertCoin();
        machine.turnCrank(); // Mong đợi: Ra nước, còn 4 chai

        System.out.println("\n--- Test 2: Nạp rồi đòi lại tiền ---");
        machine.insertCoin();
        machine.ejectCoin(); // Mong đợi: Trả tiền, trạng thái về NoCoin
        machine.turnCrank(); // Mong đợi: Báo lỗi chưa nạp tiền

        System.out.println("\n--- Test 3: Mua hết hàng ---");
        // ... Code mua liên tục 4 lần nữa xem nó có chuyển sang SoldOut không
    }
}