package com.qladder.pinefruit;

public class SessionInfo {

    String sessionID;
    //String providerID;
    String mfromtime;
    String mtotime;
    String mdate;
    String sessionStatus;
    String sessionName;

    public SessionInfo(String sessionID,
                       String mfromtime,
                       String mtotime,
                       String mdate,
                       String sessionStatus,
                       String sessionName)
    {
        this.sessionID = sessionID;
        //this.providerID = providerID;
        this.mfromtime=mfromtime;
        this.mtotime = mtotime;
        this.mdate = mdate;
        this.sessionStatus = sessionStatus;
        this.sessionName = sessionName;
    }

    public String getSessionID() {
        return sessionID;
    }

   // public String getProviderID() {
   //     return providerID;
   // }

    public String getMfromtime() {
        return mfromtime;
    }

    public String getMtotime() {
        return mtotime;
    }

    public String getMdate() {
        return mdate;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public String getSessionName() {
        return sessionName;
    }
}
