public class VendingMachine {
    State noCoinState;
    State hasCoinState;
    State soldState;
    State soldOutState;
    State state;
    int count = 0;
    public VendingMachine(int numberButtons) {
        // Khởi tạo các state (truyền 'this' vào để State gọi ngược lại máy)
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        soldState = new SoldState(this);
        soldOutState = new SoldOutState(this);

        this.count = numberButtons;
        if (numberButtons > 0) {
            state = noCoinState;
        } else {
            state = soldOutState;
        }
    }
    public void insertCoin() {
        state.insertCoin();
    }
    public void ejectCoin() {
        state.ejectCoin();
    }
    public void turnCrank() {
        state.turnCrank();
        // Logic phụ: Nếu quay tay thành công thì máy tự nhả hàng
        if (state == soldState) {
            state.dispense();
        }
    }
    public int getCount(){
        return count;
    }
    // --- Hàm hỗ trợ chuyển đổi trạng thái ---
    void setState(State state) {
        this.state = state;
    }
    
    // --- Hàm giảm kho ---
    void releaseBall() {
        System.out.println("Một chai nước đang lăn ra khe...");
        if (count != 0) {
            count = count - 1;
        }
    }
}
