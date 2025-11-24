// Tassianna Price - 2403066
package Java_p;

import java.util.*;

public class AuthSystem {

    private Map<String, HospitalSystem.User> users = new HashMap<>();
    private Map<String, HospitalSystem.Role> roles = new HashMap<>();

    private HospitalSystem.User currentUser = null;
    private final Scanner sc = new Scanner(System.in);

    // Setup roles and permissions
    public AuthSystem() {

        // Admin Role
        HospitalSystem.Role admin = new HospitalSystem.Role("Admin");
        admin.addPermission(new HospitalSystem.Permission("createUser"));
        admin.addPermission(new HospitalSystem.Permission("deleteUser"));
        admin.addPermission(new HospitalSystem.Permission("updateSystemConfig"));
        roles.put("Admin", admin);

        // Manager Role
        HospitalSystem.Role manager = new HospitalSystem.Role("Manager");
        manager.addPermission(new HospitalSystem.Permission("approveTransaction"));
        manager.addPermission(new HospitalSystem.Permission("viewReports"));
        roles.put("Manager", manager);

        // User Role
        HospitalSystem.Role user = new HospitalSystem.Role("User");
        user.addPermission(new HospitalSystem.Permission("viewOwnData"));
        roles.put("User", user);

        // default admin account
        HospitalSystem.User adminUser = new HospitalSystem.User("admin", "1000", "Admin@123");
        users.put("admin", adminUser);
    }

    // Login Function
    public boolean login() {
        System.out.print("\nEnter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (!users.containsKey(username)) {
            System.out.println("No such user.");
            return false;
        }

        HospitalSystem.User u = users.get(username);
        if (u.login(username, password)) {
            currentUser = u;
            System.out.println("Logged in as: " + username);
            return true;
        }

        return false;
    }

    // Logout Function
    public void logout() {
        if (currentUser != null) {
            System.out.println("User " + currentUser.getUsername() + " logged out.");
            currentUser = null;
        }
    }

    // Get Current User
    public HospitalSystem.User getCurrentUser() {
        return currentUser;
    }

    // ADD users from UserManager to AuthSystem
    public void registerUser(HospitalSystem.User u) {
        users.put(u.getUsername(), u);
    }

    // Get Role for a user
    // EVERYONE EXCEPT "admin" IS TREATED AS REGULAR USER
    public HospitalSystem.Role getRoleForUser(String username) {
        if (username.equals("admin")) return roles.get("Admin");
        return roles.get("User");
    }

    // Permission check
    public boolean hasPermission(String permName) {
        if (currentUser == null) return false;

        HospitalSystem.Role r = getRoleForUser(currentUser.getUsername());
        return r.hasPermission(permName);
    }
}
