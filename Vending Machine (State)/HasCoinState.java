public class HasCoinState implements State {
    VendingMachine machine;
    public HasCoinState(VendingMachine machine){
        this.machine = machine;
    }
    @Override
    public void insertCoin(){
        System.out.println("Bạn đã nạp them xu.");
    }
    @Override
    public void ejectCoin() {
        System.out.println("Tra lai tien");
        machine.setState(machine.noCoinState);
    }
    @Override
    public void turnCrank() {
        System.out.println("Dang xu ly.");
        machine.setState(machine.soldState);
    }
    @Override
    public void dispense() {
        System.out.println("Không thể nhả nước khi chưa quay tay cầm.");
    }
}
