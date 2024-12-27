package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Authenticator {

    private ArrayList<User> userList;
    private User authenticatedUser;

    // Declare attributes: users - a list of users, authenticatedUser - the currently authenticated user
    // Generate getter and setter methods for both attributes

    public Authenticator() {
        this.userList = new ArrayList<User>();
        userList.add(new User("John", "pass1"));
        userList.add(new User("user1", "pass2"));
        userList.add(new User("user2", "pass3"));
        userList.add(new User("user3", "pass3"));
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    /**
     * Authenticates a user with the given username and password.
     *
     * @param username The username b bgfreto authenticate.
     * @param password The password associated with the username.
     * @return True if authentication is successful, false otherwise.
     */
    public boolean authenticateUser(String username, String password) {
        Iterator iterator = this.userList.listIterator();
        User user;
        while (iterator.hasNext()) {
            user = (User) iterator.next();
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                this.authenticatedUser = user;
                return true;
            }
        }
        return false;
    }
    // Iterate through the users list
    // Check if the given username and password match any user's credentials
    // If a match is found, assign the user to the authenticatedUser and return true

    /**
     * Authenticates a new user with the given username and password.
     *
     * @param username The username of the new user.
     * @param password The password associated with the new user.
     * @return The authenticated user if authentication is successful, null otherwise.
     */
    public User authenticateNewUser(String username, String password) {
        boolean checkFlag = authenticateUser(username, password);
        if (checkFlag) {
            authenticatedUser = this.authenticatedUser;
            System.out.println("Login Successful");
        } else {
            System.out.println("Invalid username/password");
        }
        return authenticatedUser;
    }
    // Call the authenticateUser method to check if the new user's credentials are valid
    // If the authenticateUser method returns true, set authenticatedUser to the authenticated user
    // Display an appropriate message

}

