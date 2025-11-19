package Java_p;

public class Main {
    public static void main(String[] args) {
        // --- Payment tests ---
        Payment cash = new CashPayment(10.0);
        Payment card = new CardPayment("12345678", 99.95);
        cash.processPayment();
        card.processPayment();

        // --- Insurance Payment ---
        Payment insurance = new InsurancePayment(150, 0.8, 20, 100); // 80% coverage, $20 deductible, $100 cap
        insurance.processPayment();

        // --- Notification tests ---
        Notification email = new EmailNotification("alice@example.com", "Hello", "This is an email");
        Notification sms = new SMSNotification("+1234567890", "Alert", "This is an SMS");
        email.send();
        sms.send();

        // --- Observer test ---
        AppointmentObserver emailObserver = new AppointmentObserver(email);
        emailObserver.update("Appointment booked!");

        // --- Triage tests ---
        TriageAnalyzer analyzer = new TriageAnalyzer();
        analyzer.addVitalsRecord(55);
        analyzer.addVitalsRecord(70);
        System.out.println("v1: " + analyzer.computeRisk(55)); // LOW
        System.out.println("Moving Average: " + analyzer.computeMovingAverage());
        System.out.println("Deviation detected: " + analyzer.checkForDeviation(90));

        // --- Metrics tests ---
        Metrics metrics = new Metrics();  // Use Metrics class
        metrics.appointmentsBooked.incrementAndGet();
        metrics.highRiskTriage.incrementAndGet();
        metrics.generateWeeklyReport();
    }
}
