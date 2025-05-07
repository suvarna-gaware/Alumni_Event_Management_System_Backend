package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") 
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/adminLogin")
    public String login(@RequestBody Admin admin) {
        boolean loggedInAdmin = adminService.login(admin.getUsername(), admin.getPassword());
        if (loggedInAdmin) {
            return "login sucess....";
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
