package com.alexsirbu.tripit.domain;

import java.time.LocalDateTime;

public class Trip {

    private String name;
    private String destination;
    private Types type;
    private Float price;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int rating;

    public Trip(String name, String destination, Types type, Float price, LocalDateTime startDateTime, LocalDateTime endDateTime, int rating) {
        this.name = name;
        this.destination = destination;
        this.type = type;
        this.price = price;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", rating=" + rating +
                '}';
    }
}
