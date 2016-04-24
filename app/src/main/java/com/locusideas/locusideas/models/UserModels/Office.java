package com.locusideas.locusideas.models.UserModels;


/**
 * Created by anurag on 4/24/16.
 */
public class Office {

    String title;
    Address address;

    public Office() {}

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    static class Address {
        private String street;
        private String locality;
        private String city;
        private String state;
        private String country;
        private Integer postalCode;

        public Address() {

        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getStreet() {
            return this.street;
        }

        public void setLocality(String locality) {
            this.locality = locality;
        }

        public String getLocality() {
            return this.locality;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity() {
            return this.city;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return this.state;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountry() {
            return this.country;
        }

        public void setPostalCode(Integer postalCode) {
            this.postalCode = postalCode;
        }

        public Integer getPostalCode() {
            return this.postalCode;
        }
    }
}
