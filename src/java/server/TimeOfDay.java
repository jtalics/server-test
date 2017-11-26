package server;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

import java.util.Date;

public class TimeOfDay {

    private long id;

    private ZonedDateTime dateTime;

    public TimeOfDay() {
        // Jackson deserialization
    }

    public TimeOfDay(long id, ZonedDateTime zdt) {
        this.id = id;
        this.dateTime = zdt;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getComment() {
        return("from Java ZonedDateTime");
    }

    @JsonProperty
    public int getDayOfMonth() {
        return dateTime.getDayOfMonth();
    }
    @JsonProperty
    public int getDayOfYear() {

        return dateTime.getDayOfYear();
    }
    @JsonProperty
    public int getHour() {

        return dateTime.getHour();
    }
    @JsonProperty
    public int getMinute() {

        return dateTime.getMinute();
    }
    @JsonProperty
    public int getMonthValue() {

        return dateTime.getMonthValue();
    }
    @JsonProperty
    public int getSecond() {

        return dateTime.getSecond();
    }
    @JsonProperty
    public int getYear() {

        return dateTime.getYear();
    }
    @JsonProperty
    public String getZone() {

        return dateTime.getZone().getId();
    }
}
