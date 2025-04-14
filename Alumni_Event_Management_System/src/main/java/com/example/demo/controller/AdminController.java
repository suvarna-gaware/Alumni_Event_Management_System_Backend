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
    public Admin login(@RequestBody Admin admin) {
        Admin loggedInAdmin = adminService.login(admin.getUsername(), admin.getPassword());
        if (loggedInAdmin != null) {
            return loggedInAdmin;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
