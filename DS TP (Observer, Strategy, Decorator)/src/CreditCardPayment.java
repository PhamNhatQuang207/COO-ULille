class CreditCardPayment implements PaymentStrategy{
    private final String cardNumber;
    public CreditCardPayment(String cardNb){
        this.cardNumber = cardNb;
    }
    @Override
    public void pay(int amount){
        System.out.print("Paid " + amount + " by " + this.cardNumber);
    }
}