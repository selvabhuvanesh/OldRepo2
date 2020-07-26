package com.qladder.pinefruit;

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

    public ProviderInfo() {
    }

    public ProviderInfo(String providerID,
                        String providerType,
                        String providerOrg,
                        String providerName,
                        String providerLocality,
                        String providerLatitude,
                        String providerLongitude,
                        String providerCity,
                        String providerCountry,
                        String providerStatus) {
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
}
