interface TwoPinSocket{
    void plugInTwoPin();
}

class ThreePinLaptop{
    void plugInThreePin(){
        System.out.println("Laptop đang sạc điện (Dùng chân cắm 3 chấu) ⚡");
    }
}
class PowerAdapter implements TwoPinSocket{
    private ThreePinLaptop laptop;
    public PowerAdapter(ThreePinLaptop laptop){
        this.laptop = laptop;
    }
    @Override
    public void plugInTwoPin(){
        System.out.println("--- Adapter nhận dòng điện từ ổ 2 chấu ---");
        // Quan trọng: Chuyển lời gọi từ 2 chấu sang hành động của 3 chấu
        laptop.plugInThreePin();
    }
}
public class AdapterDemo {
    public static void main(String[] args) {
        // Có cái laptop 3 chấu
        ThreePinLaptop myDell = new ThreePinLaptop();

        // Ổ điện ở nhà là lỗ 2 chấu, không cắm myDell vào trực tiếp được.
        // Phải mua Adapter
        TwoPinSocket adapter = new PowerAdapter(myDell);

        // Cắm Adapter vào tường
        testWallSocket(adapter);
    }

    // Giả lập cái ổ điện trên tường (chỉ nhận TwoPinSocket)
    public static void testWallSocket(TwoPinSocket socket) {
        socket.plugInTwoPin();
    }
}