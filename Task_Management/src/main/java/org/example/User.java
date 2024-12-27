package org.example;


public class User {

    String userName,password;

    //declare the attribute userName,password
    public User(String userName, String password) {

        this.userName=userName;
        this.password=password;

    }
    //declare the getter setter and toString methods


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

