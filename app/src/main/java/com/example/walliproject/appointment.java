package com.example.walliproject;

public class appointment {
    private int start,end;
    private String userMail,DoctorMail;
    private boolean availability;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getDoctorMail() {
        return DoctorMail;
    }

    public void setDoctorMail(String doctorMail) {
        DoctorMail = doctorMail;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
