// Tassianna Price - 2403066
package Java_p;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AuthSystem {

    // Stores all users available to log in (key = username)
    private Map<String, HospitalSystem.User> users = new HashMap<>();
    // Stores the currently logged-in user (null if none)
    private HospitalSystem.User currentUser = null;

    private final Scanner sc = new Scanner(System.in);

    // User registration – Called by UserManager.addUser()
    // Adds a new user to the system so that they can log in.
    public void registerUser(HospitalSystem.User user) {
        users.put(user.getUsername(), user);
    }

    // Password update – Called by UserManager.updateUser()
    // Updates a user's password both in AuthSystem and UserManager.
    public void updatePassword(String username, String newPw) {
        HospitalSystem.User user = users.get(username);
        if (user != null) {
            user.setPassword(newPw);
        }
    }

    // Delete user – Called by UserManager.deleteUser()
    // Deletes a user from the login system.
    public void deleteUser(String username) {
        users.remove(username);
    }

    // Login Function
    public boolean login() {
        System.out.print("\nEnter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        HospitalSystem.User user = users.get(username);

        if (user == null) {
            System.out.println("No such user.");
            return false;
        }

        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password.");
            return false;
        }

        currentUser = user;
        System.out.println("Login successful! Welcome " + user.getUsername() + " (" + user.getRole() + ")");
        return true;
    }

    // Logout (Logs out the current user and resets currentUser to null)
    public void logout() {
        currentUser = null;
        System.out.println("Logged out successfully.");
    }

    // Setup roles and permissions (Determines whether the logged-in user has the given permission.)
    public boolean hasPermission(String permission) {
        if (currentUser == null)
            return false;

        String role = currentUser.getRole();

        switch (role) {
            case "Admin":
                return permission.equals("createUser")
                        || permission.equals("deleteUser")
                        || permission.equals("updateSystemConfig");
             
            case "Manager":
                return permission.equals("approveTransaction")
                        || permission.equals("viewReports");

            case "User":
                return permission.equals("viewOwnData");  
                
            default:
                return false; 
        }
    }  
    
    // Get Current User (Returns the logged-in user object)
    public HospitalSystem.User getCurrentUser() {
        return currentUser;
    }    
}
