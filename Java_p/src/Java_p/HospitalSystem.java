  //Davaughne Brown


package Java_p;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class HospitalSystem {

    private static final Scanner sc = new Scanner(System.in); // single shared scanner

    // ---------------- Permission ----------------
    static class Permission {
        private String name;
        public Permission(String n) { 
            name = n; 
        }
        public String getName() { 
            return name; 
        }
    }

    // ---------------- Role ----------------
    static class Role {
        private String name;
        private List<Permission> permissions;

        public Role() { 
            name = ""; permissions = new ArrayList<>(); 
        }
        public Role(String n) { 
            name = n; permissions = new ArrayList<>(); 
        }
        public void addPermission(final Permission p) { 
            permissions.add(p); 
        }
        public boolean hasPermission(String permName) {
            for (Permission p : permissions) 
                if (p.getName().equals(permName)) return true;
            return false;
        }
        public String getName() { 
            return name; 
        }
    }

    // ---------------- User ----------------
    static class User {
        private String username, userId, password;
        private boolean locked = false;
        private int loginAttempts = 0;

        public User() { 
            username = ""; userId = ""; password = ""; 
        }
        public User(String un, String id, String pw) { 
            username = un; userId = id; password = pw; 
        }
        public String getUsername() { 
            return username; 
        }
        public String getUserId() { 
            return userId; 
        }
        public String getPassword() { 
            return password; 
        }
        public void setPassword(String newPass) { 
            password = newPass; 
        }

        public boolean login(final String inputUser, final String inputPass) {
            if (locked) { 
                System.out.println("Account is locked."); 
                return false; 
            }
            if (inputUser.equals(username) && inputPass.equals(password)) { 
                loginAttempts = 0; 
                System.out.println("Login successful."); 
                return true; 
            }
            loginAttempts++; 
            System.out.println("Login failed.");
            if (loginAttempts >= 3) { 
                locked = true; 
                System.out.println("Account locked due to too many failed attempts."); 
            }
            return false;
        }
    }

    // ---------------- Patient ----------------
    static class Patient {
        private String patientId, Fname, Lname, DOB, phoneNum, email, address, nextOfKinName, nextOfKinPhone;

        public Patient(String Id, String FN, String LN, String dob, String pn, String e, String A, String NOKN, String NOKP) {
            patientId = Id; Fname = FN; Lname = LN; DOB = dob; 
            phoneNum = pn; email = e; address = A; 
            nextOfKinName = NOKN; nextOfKinPhone = NOKP;
        }

        public String getId() { 
            return patientId; 
        }
        public String getFullName() { 
            return Fname + " " + Lname; 
        }
        public String getPhone() { 
            return phoneNum; 
        }
        public String getEmail() { 
            return email; 
        }
        public String getAddress() { 
            return address; 
        }
        public String getNextOfKinName() { 
            return nextOfKinName; 
        }
        public String getNextOfKinPhone() { 
            return nextOfKinPhone; 
        }

        public void updateContact(String newPhone, String newEmail, String newAddress) { 
            phoneNum = newPhone; 
            email = newEmail; 
            address = newAddress; 
        }
        public void updateNextOfKin(String name, String phone) { 
            nextOfKinName = name; 
            nextOfKinPhone = phone; 
        }
        public void update(String FN, String LN, int ageIgnored, String contact, String nok) { 
            Fname = FN; 
            Lname = LN; 
            phoneNum = contact; 
            nextOfKinName = nok; 
        }

        public void display() {
            System.out.println("\n--- Patient Information ---");
            System.out.println("ID: " + patientId);
            System.out.println("Name: " + Fname + " " + Lname);
            System.out.println("DOB: " + DOB);
            System.out.println("Phone: " + phoneNum);
            System.out.println("Email: " + email);
            System.out.println("Address: " + address);
            System.out.println("Next of Kin: " + nextOfKinName);
            System.out.println("NOK Phone: " + nextOfKinPhone);
            System.out.println();
        }
    }

    // ---------------- Patient_Registry ----------------
    static class Patient_Registry {
        private List<Patient> patients = new ArrayList<>();

        private int findIndexById(final String id) {
            for (int i = 0; i < patients.size(); i++) 
                if (patients.get(i).getId().equals(id)) return i;
            return -1;
        }

        public void addPatient(final Patient p) { 
            patients.add(p); 
            System.out.println("Patient added successfully."); 
        }
        public void viewPatient(final String id) { 
            int index = findIndexById(id); 
            if (index != -1) 
                patients.get(index).display(); 
            else 
                System.out.println("Patient not found."); 
        }
        public void updatePatient(String id, String fn, String ln, int age, String contact, String nok) { 
            int index = findIndexById(id); 
            if (index != -1) { 
                patients.get(index).update(fn, ln, age, contact, nok); 
                System.out.println("Patient updated successfully."); 
            } else 
                System.out.println("Patient not found."); 
        }
        public void deletePatient(final String id) { 
            int index = findIndexById(id); 
            if (index != -1) { 
                patients.remove(index); 
                System.out.println("Patient deleted successfully."); 
            } else 
                System.out.println("Patient not found."); 
        }
        public void viewAllPatients() { 
            if (patients.isEmpty()) { 
                System.out.println("No patients in the registry."); 
                return; 
            } 
            for (Patient p : patients) 
                p.display(); 
        }
        public void searchPatient() { 
            System.out.print("Enter Patient ID to search: "); 
            String id = sc.nextLine(); 
            for (Patient p : patients) { 
                if (p.getId().equals(id)) { 
                    System.out.println("\nPatient Found!"); 
                    p.display(); 
                    return; 
                } 
            } 
            System.out.println("No patient found with ID: " + id); 
        }
        public void listAllPatients() { 
            if (patients.isEmpty()) { 
                System.out.println("No patients registered."); 
                return; 
            } 
            for (Patient p : patients) 
                p.display(); 
        }
    }

    // ---------------- Appointment ----------------
    static class Appointment {
        private String appointmentId, patientId, staffId, time, date, reason, status;
        public Appointment(String id, String patient, String staff, String t, String d, String r) { 
            appointmentId = id; 
            patientId = patient; 
            staffId = staff; 
            time = t; 
            date = d; 
            reason = r; 
            status = "SCHEDULED"; 
        }
        public String getId() { return appointmentId; }
        public String getPatient() { return patientId; }
        public String getStaff() { return staffId; }
        public String getTime() { return time; }
        public String getDate() { return date; }
        public String getReason() { return reason; }
        public String getStatus() { return status; }
        public void setDate(String d) { date = d; }
        public void setTime(String t) { time = t; }
        public void setReason(String r) { reason = r; }
        public void setId(String id) { appointmentId = id; }
        public void setPatient(String p) { patientId = p; }
        public void setStaff(String s) { staffId = s; }
        public void setStatus(String s) { status = s; }
        public void print() { 
            System.out.println(
                "Appointment ID: " + appointmentId + 
                " | Patient: " + patientId + 
                " | Staff: " + staffId + 
                " | Date: " + date + 
                " | Time: " + time + 
                " | Reason: " + reason + 
                " | Status: " + status
            ); 
        }
    }

    // ---------------- AppointmentManager ----------------
    static class AppointmentManager {
        private List<Appointment> appointments = new ArrayList<>();
        private int bufferMinutes = 15;

        private String generateAppointmentId() { 
            return "A" + (1000 + appointments.size() + 1); 
        }

        private LocalDateTime parseDateTime(String date, String time) {
            try { 
                return LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time)); 
            } catch (Exception e) { 
                return null; 
            }
        }

        private boolean hasOverlap(String staffId, String date, String time) {
            LocalDateTime newStart = parseDateTime(date, time);
            if (newStart == null) return true;

            for (Appointment ap : appointments) {
                if (!ap.getStaff().equals(staffId)) continue;
                if (!ap.getDate().equals(date)) continue;

                LocalDateTime existing = parseDateTime(ap.getDate(), ap.getTime());
                if (existing == null) continue;

                long diff = Math.abs(ChronoUnit.MINUTES.between(existing, newStart));
                if (diff < bufferMinutes) return true;
            }
            return false;
        }

        public void scheduleAppointment() { 
            System.out.println("\n--- Schedule Appointment ---"); 
            System.out.print("Enter Patient ID: "); 
            String patient = sc.nextLine(); 
            System.out.print("Enter Staff ID: "); 
            String staff = sc.nextLine(); 
            System.out.print("Enter Date (YYYY-MM-DD): "); 
            String date = sc.nextLine(); 
            System.out.print("Enter Time (HH:MM): "); 
            String time = sc.nextLine(); 
            System.out.print("Enter Reason: "); 
            String reason = sc.nextLine(); 

            if (parseDateTime(date, time) == null) { 
                System.out.println("Invalid date/time format."); 
                return; 
            } 
            if (hasOverlap(staff, date, time)) { 
                System.out.println("Cannot schedule: overlapping appointment."); 
                return; 
            } 

            String id = generateAppointmentId(); 
            appointments.add(new Appointment(id, patient, staff, time, date, reason)); 
            System.out.println("Appointment scheduled successfully! Appointment ID: " + id); 
        }

        public void viewAppointment() { 
            System.out.print("\nEnter Appointment ID to view: "); 
            String id = sc.nextLine(); 
            for (Appointment appt : appointments) { 
                if (appt.getId().equals(id)) { 
                    appt.print(); 
                    return; 
                } 
            } 
            System.out.println("Appointment not found."); 
        }

        public void updateAppointment() { 
            System.out.print("\nEnter Appointment ID to update: "); 
            String id = sc.nextLine(); 
            for (Appointment appt : appointments) { 
                if (appt.getId().equals(id)) { 
                    System.out.print("Enter new Date (YYYY-MM-DD): "); 
                    String newDate = sc.nextLine(); 
                    System.out.print("Enter new Time (HH:MM): "); 
                    String newTime = sc.nextLine(); 
                    System.out.print("Enter new Reason: "); 
                    String newReason = sc.nextLine(); 

                    if (hasOverlap(appt.getStaff(), newDate, newTime)) { 
                        System.out.println("Cannot update: conflict."); 
                        return; 
                    } 

                    appt.setDate(newDate); 
                    appt.setTime(newTime); 
                    appt.setReason(newReason); 
                    System.out.println("Appointment updated successfully."); 
                    return; 
                } 
            } 
            System.out.println("Appointment not found."); 
        }

        public void cancelAppointment() { 
            System.out.print("\nEnter Appointment ID to cancel: "); 
            String id = sc.nextLine(); 
            for (int i = 0; i < appointments.size(); i++) { 
                if (appointments.get(i).getId().equals(id)) { 
                    appointments.remove(i); 
                    System.out.println("Appointment cancelled."); 
                    return; 
                } 
            } 
            System.out.println("Appointment not found."); 
        }

        public void listAppointmentsByPatient() { 
            System.out.print("\nEnter Patient ID: "); 
            String patientId = sc.nextLine(); 
            boolean found = false; 
            for (Appointment appt : appointments) { 
                if (appt.getPatient().equals(patientId)) { 
                    appt.print(); 
                    found = true; 
                } 
            } 
            if (!found) System.out.println("No appointments found."); 
        }

        public void listAppointmentsByDoctor() { 
            System.out.print("\nEnter Staff ID: "); 
            String staffId = sc.nextLine(); 
            boolean found = false; 
            for (Appointment appt : appointments) { 
                if (appt.getStaff().equals(staffId)) { 
                    appt.print(); 
                    found = true; 
                } 
            } 
            if (!found) System.out.println("No appointments found."); 
        }
    }

    // ---------------- Doctor ----------------
    static class Doctor {
        private String doctorId, Fname, Lname, specialization, phone;
        public Doctor(String id, String fn, String ln, String spec, String ph) { 
            doctorId = id; 
            Fname = fn; 
            Lname = ln; 
            specialization = spec; 
            phone = ph; 
        }
        public String getId() { return doctorId; }
        public String getFirstName() { return Fname; }
        public String getLastName() { return Lname; }
        public String getSpecialization() { return specialization; }
        public String getPhone() { return phone; }
        public void setFirstName(String fn) { Fname = fn; }
        public void setLastName(String ln) { Lname = ln; }
        public void setSpecialization(String spec) { specialization = spec; }
        public void setPhone(String ph) { phone = ph; }
        public void display() { 
            System.out.println(
                "ID: " + doctorId + 
                ", Name: " + Fname + " " + Lname + 
                ", Specialization: " + specialization + 
                ", Phone: " + phone
            ); 
        }
    }

    // ---------------- DoctorManager ----------------
    static class DoctorManager {
        private List<Doctor> doctors = new ArrayList<>();
        public void addDoctor(Doctor doc) { 
            doctors.add(doc); 
            System.out.println("Doctor added successfully."); 
        }
        public void viewDoctor(String id) { 
            for (Doctor doc : doctors) { 
                if (doc.getId().equals(id)) { 
                    doc.display(); 
                    return; 
                } 
            } 
            System.out.println("Doctor not found."); 
        }
        public void updateDoctor(String id, String fn, String ln, String spec, String phone) { 
            for (Doctor doc : doctors) { 
                if (doc.getId().equals(id)) { 
                    doc.setFirstName(fn); 
                    doc.setLastName(ln); 
                    doc.setSpecialization(spec); 
                    doc.setPhone(phone); 
                    System.out.println("Doctor updated successfully."); 
                    return; 
                } 
            } 
            System.out.println("Doctor not found."); 
        }
        public void deleteDoctor(String id) { 
            for (Iterator<Doctor> it = doctors.iterator(); it.hasNext();) { 
                Doctor d = it.next(); 
                if (d.getId().equals(id)) { 
                    it.remove(); 
                    System.out.println("Doctor deleted successfully."); 
                    return; 
                } 
            } 
            System.out.println("Doctor not found."); 
        }
        public void searchDoctor(String keyword) { 
            boolean found = false; 
            for (Doctor doc : doctors) { 
                if (doc.getFirstName().equals(keyword) || 
                    doc.getLastName().equals(keyword) || 
                    doc.getSpecialization().equals(keyword)) { 
                        doc.display(); 
                        found = true; 
                } 
            } 
            if (!found) System.out.println("No doctors match your search."); 
        }
        public void listAllDoctors() { 
            if (doctors.isEmpty()) { 
                System.out.println("No doctors available."); 
                return; 
            } 
            for (Doctor doc : doctors) 
                doc.display(); 
        }
    }

    // ---------------- Invoice ----------------
    static class Invoice {
        private String invoiceId, patientId, date;
        private double amount, taxes, total;
        public Invoice(String invId, String patId, String invDate, double amt, double tax) { 
            invoiceId = invId; 
            patientId = patId; 
            date = invDate; 
            amount = amt; 
            taxes = tax; 
            total = amount + taxes; 
        }
        public String getInvoiceId() { return invoiceId; }
        public String getPatientId() { return patientId; }
        public String getDate() { return date; }
        public double getAmount() { return amount; }
        public double getTaxes() { return taxes; }
        public double getTotal() { return total; }
        public void setAmount(double amt) { amount = amt; updateTotal(); }
        public void setTaxes(double tax) { taxes = tax; updateTotal(); }
        public void setDate(String invDate) { date = invDate; }
        public void updateTotal() { total = amount + taxes; }
        public void displayInvoice() { 
            System.out.println(
                "Invoice ID: " + invoiceId + 
                "\nPatient ID: " + patientId + 
                "\nDate: " + date + 
                "\nAmount: $" + amount + 
                "\nTaxes: $" + taxes + 
                "\nTotal: $" + total
            ); 
        }
    }

    // ---------------- BillingManager ----------------
    static class BillingManager {
        private List<Invoice> invoices = new ArrayList<>();
        public void createInvoice(Invoice inv) { 
            invoices.add(inv); 
            System.out.println("Invoice created successfully."); 
        }
        public void viewInvoice(String invoiceId) { 
            for (Invoice inv : invoices) { 
                if (inv.getInvoiceId().equals(invoiceId)) { 
                    inv.displayInvoice(); 
                    return; 
                } 
            } 
            System.out.println("Invoice not found."); 
        }
        public void updateInvoice(String invoiceId, double newAmount, double newTaxes) { 
            for (Invoice inv : invoices) { 
                if (inv.getInvoiceId().equals(invoiceId)) { 
                    inv.setAmount(newAmount); 
                    inv.setTaxes(newTaxes); 
                    System.out.println("Invoice updated."); 
                    return; 
                } 
            } 
            System.out.println("Invoice not found."); 
        }
        public void deleteInvoice(String invoiceId) { 
            for (Iterator<Invoice> it = invoices.iterator(); it.hasNext();) { 
                Invoice inv = it.next(); 
                if (inv.getInvoiceId().equals(invoiceId)) { 
                    it.remove(); 
                    System.out.println("Invoice deleted."); 
                    return; 
                } 
            } 
            System.out.println("Invoice not found."); 
        }
        public void listInvoicesByPatient(String patientId) { 
            boolean found = false; 
            for (Invoice inv : invoices) { 
                if (inv.getPatientId().equals(patientId)) { 
                    inv.displayInvoice(); 
                    found = true; 
                } 
            } 
            if (!found) System.out.println("No invoices found for patient."); 
        }
    }
}

