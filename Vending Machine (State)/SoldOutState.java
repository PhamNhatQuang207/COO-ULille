public class SoldOutState implements State {
    VendingMachine machine;
    public SoldOutState(VendingMachine machine){
        this.machine = machine;
    }
    @Override
    public void insertCoin(){
        System.out.println("May het nuoc.");
    }
    @Override
    public void ejectCoin() {
        System.out.println("Bạn chưa nhét xu (hoặc máy đã từ chối), không có gì để trả.");
    }
    @Override
    public void turnCrank() {
        System.out.println("May het nuoc.");
    }
    @Override
    public void dispense() {
        System.out.println("Không có nước để nhả.");
    }
}
