package com.example.demo.controller;

import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginData) {
        String role = loginData.get("role");
        String username = loginData.get("username");
        String password = loginData.get("password");

        Map<String, Object> response = new HashMap<>();

        switch (role.toLowerCase()) {
            case "admin":
                if (loginService.validateAdmin(username, password)) {
                    response.put("status", "success");
                    response.put("role", "Admin");
                    response.put("message", "Admin login successful");
                } else {
                    response.put("status", "error");
                    response.put("message", "Invalid Admin credentials");
                }
                break;

            case "alumni":
                if (loginService.validateAlumni(username, password)) {
                    response.put("status", "success");
                    response.put("role", "Alumni");
                    response.put("message", "Alumni login successful");
                } else {
                    response.put("status", "error");
                    response.put("message", "Invalid Alumni credentials");
                }
                break;

            case "organization":
                if (loginService.validateOrganization(username, password)) {
                    response.put("status", "success");
                    response.put("role", "Organization");
                    response.put("message", "Organization login successful");
                } else {
                    response.put("status", "error");
                    response.put("message", "Invalid Organization credentials");
                }
                break;

            default:
                response.put("status", "error");
                response.put("message", "Invalid role selected");
        }

        return response;
    }
}
