package com.example.lostfoundapp;

public class Item {
    int id;
    String title, description, location, status, contact, date;
    double latitude, longitude;

    public Item(int id, String title, String description, String location, String status, String contact, String date, double latitude, double longitude) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.status = status;
        this.contact = contact;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getStatus() { return status; }
    public String getContact() { return contact; }
    public String getDate() { return date; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}
