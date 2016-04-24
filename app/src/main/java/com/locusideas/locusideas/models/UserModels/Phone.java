package com.locusideas.locusideas.models.UserModels;

/**
 * Created by anurag on 4/24/16.
 */
public class Phone {

    private String subscriberNumber;
    private String countryCode;
    private Boolean isVerifed;

    public Phone() {}

    public Boolean getIsVerifed() {
        return isVerifed;
    }

    public void setIsVerifed(Boolean isVerifed) {
        this.isVerifed = isVerifed;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }
}
