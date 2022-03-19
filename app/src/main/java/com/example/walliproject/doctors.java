package com.example.walliproject;

public class doctors {
    String name,Specialty,Location,UserEmail;
    private int workingHoursStart,workingHoursEnd,rating;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return Specialty;
    }

    public void setSpecialty(String specialty) {
        Specialty = specialty;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public int getWorkingHoursStart() {
        return workingHoursStart;
    }

    public void setWorkingHoursStart(int workingHoursStart) {
        this.workingHoursStart = workingHoursStart;
    }

    public int getWorkingHoursEnd() {
        return workingHoursEnd;
    }

    public void setWorkingHoursEnd(int workingHoursEnd) {
        this.workingHoursEnd = workingHoursEnd;
    }

}
