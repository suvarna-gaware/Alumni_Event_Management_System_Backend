package com.example.demo.service;

import com.example.demo.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public boolean validateAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin123");
    }

    public boolean validateAlumni(String email, String contact) {
        return loginRepository.isAlumniValid(email, contact);
    }

    public boolean validateOrganization(String email, String contact) {
        return loginRepository.isOrganizationValid(email, contact);
    }
}
