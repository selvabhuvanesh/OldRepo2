package com.qladder.pinefruit;

public class ProviderInfo {

    String providerID;
    String providerOrg;
    String providerName;
    String providerLocality;
    String providerLatitude;
    String providerLongitude;

    public ProviderInfo() {
    }

    public ProviderInfo(String providerID, String providerOrg, String providerName, String providerLocality, String providerLatitude, String providerLongitude) {
        this.providerID = providerID;
        this.providerOrg = providerOrg;
        this.providerName = providerName;
        this.providerLocality = providerLocality;
        this.providerLatitude = providerLatitude;
        this.providerLongitude = providerLongitude;
    }

    public String getProviderID() {
        return providerID;
    }

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
