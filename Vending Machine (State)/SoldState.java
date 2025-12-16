public class SoldState implements State {
    VendingMachine machine;

    public SoldState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Vui lòng đợi, đang nhả nước...");
    }

    @Override
    public void ejectCoin() {
        System.out.println("Đã quay tay cầm rồi, không thể hoàn tiền.");
    }

    @Override
    public void turnCrank() {
        System.out.println("Đừng quay nữa, nước đang ra rồi!");
    }

    @Override
    public void dispense() {
        // Logic nhả hàng nằm ở đây!
        machine.releaseBall(); // Trừ kho: count - 1
        
        if (machine.getCount() > 0) {
            // Nếu còn hàng -> Quay về chờ khách tiếp theo
            machine.setState(machine.noCoinState);
        } else {
            // Nếu hết hàng -> Chuyển sang SoldOut
            System.out.println("Hết sạch nước rồi!");
            machine.setState(machine.soldOutState);
        }
    }
}