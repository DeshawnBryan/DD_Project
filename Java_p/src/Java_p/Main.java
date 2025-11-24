package Java_p;

import java.util.Scanner;

public class Main {
    // Deshawn Bryan - 2403216
    // Feature Tests (Payment, Notification. Triage, Metrics)
    public static void runFeatureTests() {
        System.out.println("\n--- Payment & Notification Tests ---");

        Payment cash = new CashPayment(10.0);
        Payment card = new CardPayment("12345678", 99.95);
        cash.processPayment();
        card.processPayment();
        // Insurance payment
        Payment insurance = new InsurancePayment(150, 0.8, 20, 100);
        insurance.processPayment();

        // Notification tests
        Notification email = new EmailNotification("alice@example.com", "Hello", "This is an email");
        Notification sms = new SMSNotification("+1234567890", "Alert", "This is an SMS");
        email.send();
        sms.send();
        // Observer pattern test
        AppointmentObserver emailObserver = new AppointmentObserver(email);
        emailObserver.update("Appointment booked!");

        // Triage Test
        TriageAnalyzer analyzer = new TriageAnalyzer();
        analyzer.addVitalsRecord(55);
        analyzer.addVitalsRecord(70);
        System.out.println("Risk Level: " + analyzer.computeRisk(55));
        System.out.println("Moving Average: " + analyzer.computeMovingAverage());
        System.out.println("Deviation detected: " + analyzer.checkForDeviation(90));

        // Metrics tests
        Metrics metrics = new Metrics();
        metrics.appointmentsBooked.incrementAndGet();
        metrics.highRiskTriage.incrementAndGet();
        metrics.generateWeeklyReport();

        System.out.println("END OF SYSTEM TESTS\n"); 
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Run test suite once
        runFeatureTests();

        // --- Hospital system objects ---
        // Tassianna Price - 2403066
        AuthSystem auth = new AuthSystem();
        UserManager userManager = new UserManager(auth);        
        //Davaughne Brown
        HospitalSystem.Patient_Registry registry = new HospitalSystem.Patient_Registry();
        HospitalSystem.AppointmentManager apptManager = new HospitalSystem.AppointmentManager();
        HospitalSystem.DoctorManager doctorManager = new HospitalSystem.DoctorManager();
        HospitalSystem.BillingManager billingManager = new HospitalSystem.BillingManager();

        // Login Menu        
        System.out.println("\n~~ East Medical Health Center SYSTEM ~~");

        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine();

            if (c.equals("2")) {
                System.out.println("Exiting...");
                return;
            }

            if (c.equals("1")) {
                if (auth.login()) break;
                System.out.println("Login failed. Try again.");
            } else {
                System.out.println("Invalid option.");
            }
        }

        // Davaughne Brown - 2401831
        // Main System Menu (Accessible after login)
        while (true) {
            System.out.println("\n=== Hospital System ===");
            System.out.println("1. Appointment Manager");
            System.out.println("2. Patient Registry");
            System.out.println("3. Doctor Manager");
            System.out.println("4. Billing");

            // Admin ONLY menu
            if (auth.hasPermission("createUser"))
                System.out.println("5. User Management (Admin Only)");

            System.out.println("6. Logout");
            System.out.println("7. Exit System");
            System.out.print("Choose: ");
            String choice = sc.nextLine();
            
            switch (choice) {
                case "1":  // Appointment Manager
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
                            default: System.out.println("Invalid Option."); break;
                        }
                    }
                    break;

                case "2":  // Patient Registry
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
                                System.out.print("Email: "); String em = sc.nextLine();
                                System.out.print("Address: "); String ad = sc.nextLine();
                                System.out.print("Next of Kin Name: "); String nokN = sc.nextLine();
                                System.out.print("Next of Kin Phone: "); String nokP = sc.nextLine();
                                
                                registry.addPatient(new HospitalSystem.Patient(id, fn, ln, dob, pn, em, ad, nokN, nokP));
                                break;
                                
                            case "2":
                                System.out.print("Enter Patient ID: ");
                                registry.viewPatient(sc.nextLine());
                                break;
                                
                            case "3":
                                System.out.print("Enter Patient ID: ");
                                String upId = sc.nextLine();
                                System.out.print("New Fname: ");
                                String upFn = sc.nextLine();
                                System.out.print("New Lname: ");
                                String upLn = sc.nextLine();
                                System.out.print("Contact (Phone): "); 
                                String upContact = sc.nextLine();
                                System.out.print("Next of Kin: ");
                                String upNok = sc.nextLine();
                                registry.updatePatient(upId, upFn, upLn, 0, upContact, upNok);
                                break;
                                
                            case "4":
                                System.out.print("Enter Patient ID: ");
                                registry.deletePatient(sc.nextLine());
                                break;
                                
                            case "5": registry.listAllPatients(); break;
                            case "6": registry.searchPatient(); break;                            
                            default: System.out.println("Invalid Option.");
                        }
                    }
                    break;

                case "3":  // Doctor Manager
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
                                System.out.print("Id: "); String dId = sc.nextLine();
                                System.out.print("Fname: "); String dFn = sc.nextLine();
                                System.out.print("Lname: "); String dLn = sc.nextLine();
                                System.out.print("Specialization: "); String dSpec = sc.nextLine();
                                System.out.print("Phone: "); String dPhone = sc.nextLine();
                                doctorManager.addDoctor(new HospitalSystem.Doctor(dId, dFn, dLn, dSpec, dPhone));
                                break;
                                
                            case "2":
                                System.out.print("Enter Doctor ID: ");
                                doctorManager.viewDoctor(sc.nextLine());
                                break;
                                
                            case "3":
                                System.out.print("Enter Doctor ID: ");
                                String udId = sc.nextLine();
                                System.out.print("New Fname: ");
                                String udFn = sc.nextLine();
                                System.out.print("New Lname: ");
                                String udLn = sc.nextLine();
                                System.out.print("Specialization: ");
                                String udSpec = sc.nextLine();
                                System.out.print("Phone: ");
                                String udPhone = sc.nextLine();
                                doctorManager.updateDoctor(udId, udFn, udLn, udSpec, udPhone);
                                break;
                                
                            case "4":
                                System.out.print("Enter Doctor ID: ");
                                doctorManager.deleteDoctor(sc.nextLine());
                                break;
                                
                            case "5": doctorManager.listAllDoctors(); break;
                            case "6":
                                System.out.print("Enter keyword: ");
                                doctorManager.searchDoctor(sc.nextLine());
                                break;
                                
                            default: System.out.println("Invalid Option.");
                        }
                    }
                    break;

                case "4":  // Billing Manager 
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
                                System.out.print("Invoice ID: ");
                                String invId = sc.nextLine();
                                System.out.print("Patient ID: ");
                                String pId = sc.nextLine();
                                System.out.print("Date: ");
                                String date = sc.nextLine();
                                System.out.print("Amount: ");
                                double amt = Double.parseDouble(sc.nextLine());
                                System.out.print("Taxes: ");
                                double tax = Double.parseDouble(sc.nextLine());
                                billingManager.createInvoice(new HospitalSystem.Invoice(invId, pId, date, amt, tax));
                                break;
                                
                            case "2":
                                System.out.print("Invoice ID: ");
                                billingManager.viewInvoice(sc.nextLine());
                                break;
                                
                            case "3":
                                System.out.print("Invoice ID: ");
                                String upInv = sc.nextLine();
                                System.out.print("New Amount: ");
                                double newAmt = Double.parseDouble(sc.nextLine());
                                System.out.print("New Taxes: ");
                                double newTax = Double.parseDouble(sc.nextLine());
                                billingManager.updateInvoice(upInv, newAmt, newTax);
                                break;
                                
                            case "4":
                                System.out.print("Invoice ID: ");
                                billingManager.deleteInvoice(sc.nextLine());
                                break;
                                
                            case "5":
                                System.out.print("Patient ID: ");
                                billingManager.listInvoicesByPatient(sc.nextLine());
                                break;
                                
                            default: System.out.println("Invalid Option.");
                        }
                    }
                    break;

                // Tassianna Price - 2403066
                // Admin User Management Menu
                case "5": 
                    if (!auth.hasPermission("createUser")) {
                        System.out.println("Access denied — Admins only.");
                        break;
                    }

                    while (true) {
                        System.out.println("\n--- User Management (Admin Only) ---");
                        System.out.println("1. Add User");
                        System.out.println("2. View User");
                        System.out.println("3. View All Users");
                        System.out.println("4. Update User");
                        System.out.println("5. Delete User");
                        System.out.println("6. View Audit Logs");
                        System.out.println("7. Back");
                        System.out.print("Choose: ");

                        String uChoice = sc.nextLine();
                        if (uChoice.equals("7")) break;

                        switch (uChoice) {
                            case "1": userManager.addUser(); break;
                            case "2": userManager.viewUser(); break;
                            case "3": userManager.viewAllUsers(); break;
                            case "4": userManager.updateUser(); break;
                            case "5": userManager.deleteUser(); break;
                            case "6": userManager.viewAuditLogs(); break;
                            default: System.out.println("Invalid Option.");
                        }
                    }
                    break;

                case "6": // Logout
                    auth.logout();
                    System.out.println("Returning to Login Screen...");
                    main(args); // restart program
                    return;

                case "7": // Exit System
                    System.out.println("Goodbye! Have a Nice Day.");
                    return;

                default:
                    System.out.println("Invalid Option."); 
            }
        }
    }
}
