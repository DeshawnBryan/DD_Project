//Deshawn Bryan
package Java_p;

public abstract class Payment {
    protected double amount;

    public Payment(double amount) { 
        this.amount = amount; 
    }

    public abstract void processPayment();
}

class CashPayment extends Payment {
    public CashPayment(double amount) { 
        super(amount); 
    }

    @Override
    public void processPayment() {
        System.out.println("Cash payment: " + amount);
    }
}

class CardPayment extends Payment {
    private String cardNumber;

    public CardPayment(String cardNumber, double amount) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment() {
        System.out.println("Card payment: ****" + cardNumber.substring(cardNumber.length() - 4) + " amount: " + amount);
    }
}

class InsurancePayment extends Payment {
    private double coveragePercentage;
    private double deductible;
    private double perVisitCap;

    public InsurancePayment(double amount, double coveragePercentage, double deductible, double perVisitCap) {
        super(amount);
        this.coveragePercentage = coveragePercentage;
        this.deductible = deductible;
        this.perVisitCap = perVisitCap;
    }

    @Override
    public void processPayment() {
        double insuranceCovered = (amount - deductible) * coveragePercentage;
        double patientPay = amount - insuranceCovered;
        if (patientPay > perVisitCap) {
            patientPay = perVisitCap;
        }
        System.out.println("Insurance payment: Covered " + insuranceCovered + ", Patient pays: " + patientPay);
    }
}

class CombinedPayment extends Payment {
    private Payment payment1;
    private Payment payment2;

    public CombinedPayment(Payment payment1, Payment payment2) 
    {
        super(payment1.amount + payment2.amount);
        this.payment1 = payment1;
        this.payment2 = payment2;
    }

    @Override
    public void processPayment() {
        payment1.processPayment();
        payment2.processPayment();
    }
}

