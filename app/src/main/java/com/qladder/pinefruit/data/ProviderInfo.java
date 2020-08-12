package com.qladder.pinefruit.data;

public class ProviderInfo {

    private String providerID;
    private String providerType;
    private String providerOrg;
    protected String providerName;
    protected String providerLocality;
    private String providerLatitude;
    private String providerLongitude;
    private String providerCity;
    private String providerCountry;
    private String providerStatus;
    private String userEmail;
    private String userName;
    private String userID;


    public ProviderInfo(String providerID,
                        String providerType,
                        String providerOrg,
                        String providerName,
                        String providerLocality,
                        String providerLatitude,
                        String providerLongitude,
                        String providerCity,
                        String providerCountry,
                        String providerStatus,
                        String userEmail,
                        String userID,
                        String userName) {
        this.providerID = providerID;
        this.providerType = providerType;
        this.providerOrg = providerOrg;
        this.providerName = providerName;
        this.providerLocality = providerLocality;
        this.providerLatitude = providerLatitude;
        this.providerLongitude = providerLongitude;
        this.providerCity = providerCity;
        this.providerCountry = providerCountry;
        this.providerStatus = providerStatus;
        this.userEmail=userEmail;
        this.userID = userID;
        this.userName=userName;
    }


    public String getProviderType() {
        return providerType;
    }

    public String getProviderCity() { return providerCity;  }

    public String getProviderCountry() {  return providerCountry; }

    public String getProviderStatus() { return providerStatus; }

    public String getProviderID() { return providerID; }

    public String getProviderOrg() {
        return providerOrg;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getProviderLocality() {
        return providerLocality;
    }

    public String getProviderLatitude() {
        return providerLatitude;
    }

    public String getProviderLongitude() {
        return providerLongitude;
    }

    public String getUserEmail() {        return userEmail;    }

    public String getUserName() {        return userName;    }

    public String getUserID() {        return userID;    }

    public ProviderInfo() {

    }

}
