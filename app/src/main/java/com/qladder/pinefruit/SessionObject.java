package com.qladder.pinefruit;

public class SessionObject {

    int image;
    String sessionName;
    String sessionDetails;

    public SessionObject(int image, String sessionName, String sessionDetails)
    {
        this.image = image;
        this.sessionName = sessionName;
        this.sessionDetails=sessionDetails;
    }

    public int getImage() {
        return image;
    }

    public String getSessionName() {
        return sessionName;
    }

    public String getSessionDetails() {
        return sessionDetails;
    }
}
