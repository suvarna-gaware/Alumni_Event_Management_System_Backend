package com.example.demo.model;

import lombok.Data;

@Data
public class Event {
    private int eventid;
    private int deptid;
    private String eventname;
    private String eventdate;
    private String eventtime;
    private String location;
}
