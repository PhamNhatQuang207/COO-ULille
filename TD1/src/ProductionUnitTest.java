private class MockUnit extends ProductionUnit {
    public int processCalled = 0;
    public int getProductionDuration() {
        return 10;
    }
    @Override
    public void process(ManufacturedProduct p) throws ProductionProblemException {
        this.processCalled++;
        super.process(p);
    }
    @Override
    protected void handleProductProcess(ManufacturedProduct p){}
}

private class MockProduct extends ManufacturedProduct {
    public MockProduct() {
        super(1);
    }
    public MockProduct(int resourcesNeeded) {
        resourcesNeeded = 10;
    }
}

public class ProductionUnitTest {
    private ManufacturedProduct product;
    private MockUnit unit;
    @BeforeEach
    public void init() {
        this.product = new MockProduct();
        this.unit = new MockUnit();
    }
    @Test
    public void testOperateCallsProcess() throws Exception {
        this.unit.addAvailableResources(100);
        this.unit.operate(this.product);
        assertEquals(1, this.unit.getNbProcessedProducts());
        assertEquals(1, this.unit.processCalled);
    }

    @Test
    public void testProcessNotEnoughResources() {
        assertThrows(ProductionProblemException.class,
            () -> {
                this.unit.process(this.product);
        });
    }
    @Test
    public void testProcessOK() throws ProductionProblemException {
        this.unit.addAvailableResources(100);
        this.initialResources = this.unit.getAvailableResources();
        this.initialDuration = this.product.getProductionDuration();
        this.unit.process(this.product);
        assertEquals(this.product.requiredResourceQuantity(), this.initialResources - this.unit.getAvailableResources());
        assertEquals(this.product.getProductionDuration(), this.initialDuration + this.unit.getProductionDuration());
    }
    @Test
    public void testOperateCountProductsWhenEnoughResources() throws Exception {
        this.unit.addAvailableResources(100);
        this.unit.operate(this.product);
        this.unit.operate(this.product);
        assertEquals(2, this.unit.getNbProcessedProducts());
        assertEquals(2, this.unit.processCalled);
    }
    @Test
    public void testOperateNotCountProductsWhenNotEnoughResources() throws Exception {
        this.unit.addAvailableResources(15);
        this.unit.operate(this.product);
        this.unit.operate(this.product);
        assertEquals(1, this.unit.getNbProcessedProducts());
        assertEquals(2, this.unit.processCalled);
    }
}