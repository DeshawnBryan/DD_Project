package Java_p;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- Hospital system objects ---
        //Davaughne Brown
        HospitalSystem.Patient_Registry registry = new HospitalSystem.Patient_Registry();
        HospitalSystem.AppointmentManager apptManager = new HospitalSystem.AppointmentManager();
        HospitalSystem.DoctorManager doctorManager = new HospitalSystem.DoctorManager();
        HospitalSystem.BillingManager billingManager = new HospitalSystem.BillingManager();

        // --- Payment and Notification Tests ---
        //Davaughne Brown
        System.out.println("\n--- Payment & Notification Tests ---");
        //Deshawn Bryan
        Payment cash = new CashPayment(10.0);
        Payment card = new CardPayment("12345678", 99.95);
        cash.processPayment();
        card.processPayment();
        
        //Deshawn Bryan
        Payment insurance = new InsurancePayment(150, 0.8, 20, 100);
        insurance.processPayment();

        //Deshawn Bryan
        Notification email = new EmailNotification("alice@example.com", "Hello", "This is an email");
        Notification sms = new SMSNotification("+1234567890", "Alert", "This is an SMS");
        email.send();
        sms.send();
        
        //Deshawn Bryan
        AppointmentObserver emailObserver = new AppointmentObserver(email);
        emailObserver.update("Appointment booked!");

        //Deshawn Bryan --TRIAGE TESTS--
        TriageAnalyzer analyzer = new TriageAnalyzer();
        analyzer.addVitalsRecord(55);
        analyzer.addVitalsRecord(70);
        System.out.println("v1: " + analyzer.computeRisk(55));
        System.out.println("Moving Average: " + analyzer.computeMovingAverage());
        System.out.println("Deviation detected: " + analyzer.checkForDeviation(90));

        //Deshawn Bryan --METRICS TESTS--
        Metrics metrics = new Metrics();
        metrics.appointmentsBooked.incrementAndGet();
        metrics.highRiskTriage.incrementAndGet();
        metrics.generateWeeklyReport();

        // --- Hospital system main menu ---
        //Davaughne Brown
        while (true) {
            System.out.println("\n--- Hospital System ---");
            System.out.println("1. Appointment Manager");
            System.out.println("2. Patient Registry");
            System.out.println("3. Doctor Manager");
            System.out.println("4. Billing");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":  // Appointment Menu
                    while (true) {
                        System.out.println("\n--- Appointment Menu ---");
                        System.out.println("1. Schedule Appointment");
                        System.out.println("2. View Appointment");
                        System.out.println("3. Update Appointment");
                        System.out.println("4. Cancel Appointment");
                        System.out.println("5. List Appointments by Patient");
                        System.out.println("6. List Appointments by Doctor");
                        System.out.println("7. Back");
                        System.out.print("Choose: ");
                        String apptChoice = sc.nextLine();
                        if (apptChoice.equals("7")) break;

                        switch (apptChoice) {
                            case "1": apptManager.scheduleAppointment(); break;
                            case "2": apptManager.viewAppointment(); break;
                            case "3": apptManager.updateAppointment(); break;
                            case "4": apptManager.cancelAppointment(); break;
                            case "5": apptManager.listAppointmentsByPatient(); break;
                            case "6": apptManager.listAppointmentsByDoctor(); break;
                            default: System.out.println("Invalid option."); break;
                        }
                    }
                    break;

                case "2":  // Patient Registry Menu
                    while (true) {
                        System.out.println("\n--- Patient Registry ---");
                        System.out.println("1. Add Patient");
                        System.out.println("2. View Patient");
                        System.out.println("3. Update Patient");
                        System.out.println("4. Delete Patient");
                        System.out.println("5. List All Patients");
                        System.out.println("6. Search Patient");
                        System.out.println("7. Back");
                        System.out.print("Choose: ");
                        String pchoice = sc.nextLine();
                        if (pchoice.equals("7")) break;

                        switch (pchoice) {
                            case "1":
                                System.out.print("Id: "); String id = sc.nextLine();
                                System.out.print("Fname: "); String fn = sc.nextLine();
                                System.out.print("Lname: "); String ln = sc.nextLine();
                                System.out.print("DOB: "); String dob = sc.nextLine();
                                System.out.print("Phone: "); String pn = sc.nextLine();
                                System.out.print("Email: "); String e = sc.nextLine();
                                System.out.print("Address: "); String A = sc.nextLine();
                                System.out.print("Next of Kin Name: "); String NOKN = sc.nextLine();
                                System.out.print("Next of Kin Phone: "); String NOKP = sc.nextLine();
                                registry.addPatient(new HospitalSystem.Patient(id, fn, ln, dob, pn, e, A, NOKN, NOKP));
                                break;
                            case "2": System.out.print("Enter Patient ID: "); registry.viewPatient(sc.nextLine()); break;
                            case "3":
                                System.out.print("Enter Patient ID: "); String upId = sc.nextLine();
                                System.out.print("New Fname: "); String upFn = sc.nextLine();
                                System.out.print("New Lname: "); String upLn = sc.nextLine();
                                System.out.print("Contact (phone): "); String upContact = sc.nextLine();
                                System.out.print("Next of Kin: "); String upNok = sc.nextLine();
                                registry.updatePatient(upId, upFn, upLn, 0, upContact, upNok);
                                break;
                            case "4": System.out.print("Enter Patient ID: "); registry.deletePatient(sc.nextLine()); break;
                            case "5": registry.listAllPatients(); break;
                            case "6": registry.searchPatient(); break;
                            default: System.out.println("Invalid option."); break;
                        }
                    }
                    break;

                case "3":  // Doctor Manager Menu
                    while (true) {
                        System.out.println("\n--- Doctor Menu ---");
                        System.out.println("1. Add Doctor");
                        System.out.println("2. View Doctor");
                        System.out.println("3. Update Doctor");
                        System.out.println("4. Delete Doctor");
                        System.out.println("5. List All Doctors");
                        System.out.println("6. Search Doctor");
                        System.out.println("7. Back");
                        System.out.print("Choose: ");
                        String dchoice = sc.nextLine();
                        if (dchoice.equals("7")) break;

                        switch (dchoice) {
                            case "1":
                                System.out.print("Id: "); String id = sc.nextLine();
                                System.out.print("Fname: "); String fn = sc.nextLine();
                                System.out.print("Lname: "); String ln = sc.nextLine();
                                System.out.print("Specialization: "); String spec = sc.nextLine();
                                System.out.print("Phone: "); String phone = sc.nextLine();
                                doctorManager.addDoctor(new HospitalSystem.Doctor(id, fn, ln, spec, phone));
                                break;
                            case "2": System.out.print("Enter Doctor ID: "); doctorManager.viewDoctor(sc.nextLine()); break;
                            case "3":
                                System.out.print("Enter Doctor ID: "); String upId = sc.nextLine();
                                System.out.print("New Fname: "); String upFn = sc.nextLine();
                                System.out.print("New Lname: "); String upLn = sc.nextLine();
                                System.out.print("Specialization: "); String upSpec = sc.nextLine();
                                System.out.print("Phone: "); String upPhone = sc.nextLine();
                                doctorManager.updateDoctor(upId, upFn, upLn, upSpec, upPhone);
                                break;
                            case "4": System.out.print("Enter Doctor ID: "); doctorManager.deleteDoctor(sc.nextLine()); break;
                            case "5": doctorManager.listAllDoctors(); break;
                            case "6": System.out.print("Enter keyword: "); doctorManager.searchDoctor(sc.nextLine()); break;
                            default: System.out.println("Invalid option."); break;
                        }
                    }
                    break;

                case "4":  // Billing Menu
                    while (true) {
                        System.out.println("\n--- Billing Menu ---");
                        System.out.println("1. Create Invoice");
                        System.out.println("2. View Invoice");
                        System.out.println("3. Update Invoice");
                        System.out.println("4. Delete Invoice");
                        System.out.println("5. List Invoices by Patient");
                        System.out.println("6. Back");
                        System.out.print("Choose: ");
                        String bchoice = sc.nextLine();
                        if (bchoice.equals("6")) break;

                        switch (bchoice) {
                            case "1":
                                System.out.print("Invoice ID: "); String invId = sc.nextLine();
                                System.out.print("Patient ID: "); String patId = sc.nextLine();
                                System.out.print("Date: "); String date = sc.nextLine();
                                System.out.print("Amount: "); double amt = Double.parseDouble(sc.nextLine());
                                System.out.print("Taxes: "); double tax = Double.parseDouble(sc.nextLine());
                                billingManager.createInvoice(new HospitalSystem.Invoice(invId, patId, date, amt, tax));
                                break;
                            case "2": System.out.print("Invoice ID: "); billingManager.viewInvoice(sc.nextLine()); break;
                            case "3":
                                System.out.print("Invoice ID: "); String upInv = sc.nextLine();
                                System.out.print("New Amount: "); double newAmt = Double.parseDouble(sc.nextLine());
                                System.out.print("New Taxes: "); double newTax = Double.parseDouble(sc.nextLine());
                                billingManager.updateInvoice(upInv, newAmt, newTax);
                                break;
                            case "4": System.out.print("Invoice ID: "); billingManager.deleteInvoice(sc.nextLine()); break;
                            case "5": System.out.print("Patient ID: "); billingManager.listInvoicesByPatient(sc.nextLine()); break;
                            default: System.out.println("Invalid option."); break;
                        }
                    }
                    break;

                case "5":
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
