package com.example.demo.model;

import java.util.List;

import lombok.Data;

@Data
public class EmailRequest {
    private int deptid;
    private List<AlumniDTO> alumniList;

    // getters and setters
}
