package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public boolean login(String username, String password) {
        return adminRepository.login(username, password);
    }
}
