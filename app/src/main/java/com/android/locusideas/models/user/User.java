package com.android.locusideas.models.user;

import java.util.Date;

/**
 * Created by anurag on 4/24/16.
 */
public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String type;
    private String password;
    private String facebook;
    private String twitter;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public User() {

    }

    //MARK:- Set & Get User ID.
    void setId(String id) {
        this.id = id;
    }

    String getId() {
        return this.id;
    }

    //MARK:- Set & Get User First Name.
    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getFirstName() {
        return this.firstName;
    }

    //MARK:- Set & Get User Last Name.
    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getLastName() {
        return this.lastName;
    }

    //MARK:- Set & Get User Email.
    void setEmail(String email) {
        this.email = email;
    }

    String getEmail() {
        return this.email;
    }

    //MARK:- Set & Get User Type.
    void setType(String type) {
        this.type = type;
    }

    String getType() {
        return this.type;
    }

    //MARK:- Set & Get User Password.
    void setPassword(String password) {
        this.password = password;
    }

    String getPassword() {
        return this.password;
    }

    void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    String getFacebook() {
        return this.facebook;
    }

    void  setTwitter(String twitter) {
        this.twitter = twitter;
    }

    String getTwitter() {
        return this.twitter;
    }

    void setStatus(String status) {
        this.status = status;
    }

    String getStatus() {
        return this.status;
    }
}
