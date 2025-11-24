// Tassianna Price - 2403066
package Java_p;

import java.util.*;

public class UserManager {

    private List<HospitalSystem.User> users = new ArrayList<>();
    private List<String> auditLogs = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    // Check Password Rules
    private boolean isValidPassword(String pw) {
        if (pw.length() < 6) return false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : pw.toCharArray()) {
            if (Character.isDigit(c)) hasDigit = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasDigit && hasSpecial;
    }

    // Prevent Duplicate Username
    private boolean usernameExists(String username) {
        for (HospitalSystem.User u : users) {
            if (u.getUsername().equals(username)) return true;
        }
        return false;
    }

    // ADD USER
    public void addUser() {
        System.out.println("\nAdd User");
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        if (usernameExists(username)) {
            System.out.println("Username exists already! Cannot add duplicate.");
            return;
        }

        System.out.print("Enter user ID: ");
        String id = sc.nextLine();
        System.out.print("Enter password: ");
        String pw = sc.nextLine();

        if (!isValidPassword(pw)) {
            System.out.println("Password must be at least 6 characters, contain a digit and a special character.");
            return;
        }

        HospitalSystem.User newUser = new HospitalSystem.User(username, id, pw);
        users.add(newUser);
        auditLogs.add("User created: " + username + " at " + new Date());

        System.out.println("User added successfully!");
    }

    // VIEW USER
    public void viewUser() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        for (HospitalSystem.User u : users) {
            if (u.getUsername().equals(username)) {
                System.out.println("\nUser Info");
                System.out.println("Username: " + u.getUsername());
                System.out.println("User ID: " + u.getUserId());
                return;
            }
        }
        System.out.println("User NOT Found.");
    }

    // VIEW ALL USERS
    public void viewAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users Found.");
            return;
        }

        System.out.println("\nAll Users");
        for (HospitalSystem.User u : users) {
            System.out.println("Username: " + u.getUsername() + " | ID: " + u.getUserId());
        }
    }

    // UPDATE USER
    public void updateUser() {
        System.out.print("Enter username to update: ");
        String username = sc.nextLine();

        for (HospitalSystem.User u : users) {
            if (u.getUsername().equals(username)) {

                System.out.print("Enter new password: ");
                String newPw = sc.nextLine();

                if (!isValidPassword(newPw)) {
                    System.out.println("Password does not meet the rules.");
                    return;
                }

                u.setPassword(newPw);
                auditLogs.add("Password updated for: " + username + " at " + new Date());

                System.out.println("User updated successfully.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    // DELETE USER
    public void deleteUser() {
        System.out.print("Enter username to delete: ");
        String username = sc.nextLine();

        Iterator<HospitalSystem.User> it = users.iterator();
        while (it.hasNext()) {
            HospitalSystem.User u = it.next();
            if (u.getUsername().equals(username)) {
                it.remove();
                auditLogs.add("User deleted: " + username + " at " + new Date());
                System.out.println("User deleted.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    // AUDIT LOGS
    public void viewAuditLogs() {
        if (auditLogs.isEmpty()) {
            System.out.println("No audit logs yet.");
            return;
        }

        System.out.println("\nAudit Logs");
        for (String log : auditLogs) {
            System.out.println(log);
        }
    }
}
