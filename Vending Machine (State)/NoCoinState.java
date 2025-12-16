public class NoCoinState implements State {
    VendingMachine machine;

    public NoCoinState(VendingMachine machine) {
        this.machine = machine;
    }
    @Override
    public void insertCoin(){
        System.out.println("Bạn đã nạp xu.");
        machine.setState(machine.hasCoinState);
    }
    @Override
    public void ejectCoin() {
        System.out.println("Bạn chưa nạp xu mà?");
    }
    @Override
    public void turnCrank() {
        System.out.println("Vui lòng nạp xu trước.");
    }
    @Override
    public void dispense() {
        System.out.println("Vui lòng nạp xu trước.");
    }
}
