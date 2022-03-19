package com.example.walliproject;

import java.util.Arrays;

public class Users {
    private String name;
    private String email;
    private String password;
    int accessLevel;
    appointment [] appointments;

    public Users(String name, String email, String password, int accessLevel) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accessLevel=" + accessLevel +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public appointment[] getAppointments() {
        return appointments;
    }

    public void setAppointments(appointment[] appointments) {
        this.appointments = appointments;
    }

}
