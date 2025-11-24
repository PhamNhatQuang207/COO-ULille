public abstract class ServiceTest{
    protected abstract Service CreateService();
    protected Service service;
    @BeforeEach
    public void init(){
        this.service = CreateService();
    }
    @Test
    public void testServiceOK() throws Exception {
        ServiceUser user = new ServiceUser(2*service.cost());
        int moneyBefore = user.getMoney();
        int nbUseBefore = service.getNbOfUses();

        service.isUsedBy(user);
        assertEquals(moneyBefore - user.getMoney(), service.cost());
        assertEquals(nbUseBefore + 1, service.getNbOfUses());
    }
    @Test
    public void testServiceNotEnoughMoney() throws Exception {
        ServiceUser user = new ServiceUser(service.cost() - 1);
        int moneyBefore = user.getMoney();
        int nbUseBefore = service.getNbofUses();

        service.isUsedBy(user);
        assertEquals(moneyBefore - user.getMoney(), 0);
        assertEquals(nbUseBefore, service.getNbOfUses());
    }
}

public class ServiceOneTest extends ServiceTest{
    @Override
    protected Service CreateService(){
        return new ServiceOne();
    }
}

public class ServiceTwoTest extends ServiceTest{
    @Override
    protected Service CreateService(){
        return new ServiceTwo();
    }
}