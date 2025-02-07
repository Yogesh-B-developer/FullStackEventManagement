package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usertable")
public class UserEntity {

    @Id
    private String userEmailId;

    private String userName;
    private String userPassword;
    private String confirmUserPassword;
    private String userMobileNumber;
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "eventId")
    private EventsEntity event;

    // Getters and Setters
    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getConfirmUserPassword() {
        return confirmUserPassword;
    }

    public void setConfirmUserPassword(String confirmUserPassword) {
        this.confirmUserPassword = confirmUserPassword;
    }

    public String getUserMobileNumber() {
        return userMobileNumber;
    }

    public void setUserMobileNumber(String userMobileNumber) {
        this.userMobileNumber = userMobileNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public EventsEntity getEvent() {
        return event;
    }

    public void setEvent(EventsEntity event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "UserEntity [userEmailId=" + userEmailId + ", userName=" + userName + ", userPassword=" + userPassword
                + ", confirmUserPassword=" + confirmUserPassword + ", userMobileNumber=" + userMobileNumber + ", role="
                + role + "]";
    }
}
