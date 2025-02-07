package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AdminEntity;
import com.example.demo.entity.EventsEntity;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminService {

    private static final Logger log = LoggerFactory.getLogger(AdminService.class);
    
    @Autowired
    private AdminRepository adminRepository;
    
    // get admin details by email id 
    public AdminEntity findAdmin(String email) {
        log.debug("Finding admin with email: {}", email);
        AdminEntity adminEntity = adminRepository.findById(email).orElse(null);
        if (adminEntity != null) {
            log.info("Admin found: {}", adminEntity);
        } else {
            log.warn("No admin found with email: {}", email);
        }
        return adminEntity;
    }
    
    public boolean checkForCorrectEmail(String email) {
        log.debug("Checking for correct email: {}", email);
        Optional<AdminEntity> emailInDataBase = adminRepository.findById(email);
        if (emailInDataBase.isPresent()) {
            AdminEntity adminEntity = emailInDataBase.get();
            String fetchedEmail = adminEntity.getEmailId();
            if (fetchedEmail.equals(email)) {
                log.info("Email matches: {}", email);
                return true;
            }
        }
        log.warn("Email does not match: {}", email);
        return false;
    }
    
    public String checkForCorrectPassword(String email, String password) {
        log.debug("Checking for correct password for email: {}", email);
        Optional<AdminEntity> passwordInDataBase = adminRepository.findById(email);
        if (passwordInDataBase.isPresent()) {
            AdminEntity adminEntity = passwordInDataBase.get();
            String name = adminEntity.getName();
            String fetchedPassword = adminEntity.getPassword();
            if (fetchedPassword.equals(password)) {
                log.info("Password matches for email: {}", email);
                return name;
            }
        }
        log.warn("Password does not match for email: {}", email);
        return "false";
    }

    public String getAdminNameFromDataBase(String email) {
        log.debug("Getting admin name from database for email: {}", email);
        Optional<AdminEntity> emailInDataBase = adminRepository.findById(email);
        if (emailInDataBase.isPresent()) {
            AdminEntity adminEntity = emailInDataBase.get();
            String name = adminEntity.getName();
            log.info("Admin name found: {}", name);
            return name;
        }
        log.warn("No admin name found for email: {}", email);
        return null;
    }

    public String getRole() {
        log.debug("Getting role");
        // Implement the method logic
        return null;
    }
}
