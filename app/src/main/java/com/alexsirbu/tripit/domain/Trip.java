package com.alexsirbu.tripit.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "trips")
public class Trip extends GenericEntity<Long> {

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    private Long id;

    @ColumnInfo(name = "name")
    @NotNull
    private String name;

    @ColumnInfo(name = "destination")
    @NotNull
    private String destination;

    @ColumnInfo(name = "type")
    @NotNull
    private Types type;

    @ColumnInfo(name = "price")
    @NotNull
    private Float price;

    @ColumnInfo(name = "start")
    @NotNull
    private String startDateTime;

    @ColumnInfo(name = "end")
    @NotNull
    private String endDateTime;

    @ColumnInfo(name = "rating")
    @NotNull
    private Integer rating;

    public Trip(@NotNull Long id, @NotNull String name, @NotNull String destination, @NotNull Types type, @NotNull Float price, @NotNull String startDateTime, @NotNull String endDateTime, @NotNull Integer rating) {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.type = type;
        this.price = price;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.rating = rating;
    }

    @Override
    @NotNull
    public Long getId() {
        return id;
    }

    @Override
    public void setId(@NotNull Long id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getDestination() {
        return destination;
    }

    public void setDestination(@NotNull String destination) {
        this.destination = destination;
    }

    @NotNull
    public Types getType() {
        return type;
    }

    public void setType(@NotNull Types type) {
        this.type = type;
    }

    @NotNull
    public Float getPrice() {
        return price;
    }

    public void setPrice(@NotNull Float price) {
        this.price = price;
    }

    @NotNull
    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(@NotNull String startDateTime) {
        this.startDateTime = startDateTime;
    }

    @NotNull
    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(@NotNull String endDateTime) {
        this.endDateTime = endDateTime;
    }

    @NotNull
    public Integer getRating() {
        return rating;
    }

    public void setRating(@NotNull Integer rating) {
        this.rating = rating;
    }

    @NonNull
    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", rating=" + rating +
                '}';
    }
}
