package com.example.lostfoundapp;

public class Item {
    int id;
    String title, description, location, status, contact, date;

    public Item(int id, String title, String description, String location, String status, String contact, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.status = status;
        this.contact = contact;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getContact() {
        return contact;
    }

    public String getDate() {
        return date;
    }
}
