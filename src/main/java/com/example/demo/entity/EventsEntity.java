package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class EventsEntity {

    @Id
    private String eventId;
    private String eventName;
    private String date;
    private String city;
    private String area;
    private String street;
    private String price;
    private String eventType;
    private String eventEmail;
    private String eventPhoneNumber;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminForeignKey")
    private AdminEntity adminForeignKey;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserEntity> users;

    // Getters and Setters
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventEmail() {
        return eventEmail;
    }

    public void setEventEmail(String eventEmail) {
        this.eventEmail = eventEmail;
    }

    public String getEventPhoneNumber() {
        return eventPhoneNumber;
    }

    public void setEventPhoneNumber(String eventPhoneNumber) {
        this.eventPhoneNumber = eventPhoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AdminEntity getAdminForeignKey() {
        return adminForeignKey;
    }

    public void setAdminForeignKey(AdminEntity adminForeignKey) {
        this.adminForeignKey = adminForeignKey;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "EventsEntity [eventId=" + eventId + ", eventName=" + eventName + ", date=" + date + ", city=" + city
                + ", area=" + area + ", street=" + street + ", price=" + price + ", eventType=" + eventType
                + ", eventEmail=" + eventEmail + ", eventPhoneNumber=" + eventPhoneNumber + ", imageUrl=" + imageUrl
                + ", adminForeignKey=" + adminForeignKey + "]";
    }
}
