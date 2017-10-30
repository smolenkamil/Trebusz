package com.valkyria.trebusz;

import static android.R.attr.description;

/**
 * Created by Kensaj on 22.10.2017.
 */

public class DatabaseInterface {
    private long id;
    private String date;
    private String day;
    private String hour;
    private String subject;
    private String teacher;
    private String hall;
    private String type;

    public DatabaseInterface(long id, String date, String day, String hour, String subject, String type, String teacher, String hall) {
        this.id = id;
        this.date = date;
        this.day = day;
        this.hour = hour;
        this.subject = subject;
        this.type = type;
        this.teacher = teacher;
        this.hall = hall;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }

    public String getType() {
        return type;
    }

    public String getHall() {
        return hall;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
