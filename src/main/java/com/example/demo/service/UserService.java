package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EventsEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.EventsRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventsRepository eventsRepository;

    public boolean addUserDetailsToDatabase(UserEntity userEntity) {
        log.debug("Adding user details to database: {}", userEntity.getUserEmailId());
        userRepository.save(userEntity);
        log.info("User details added to database: {}", userEntity.getUserEmailId());
        return true;
    }

    public boolean checkForCorrectEmail(String email) {
        log.debug("Checking for correct email: {}", email);
        Optional<UserEntity> emailInDataBase = userRepository.findById(email);
        if (emailInDataBase.isPresent()) {
            UserEntity userEntity = emailInDataBase.get();
            String fetchedEmail = userEntity.getUserEmailId();
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
        Optional<UserEntity> passwordInDataBase = userRepository.findById(email);
        if (passwordInDataBase.isPresent()) {
            UserEntity userEntity = passwordInDataBase.get();
            String name = userEntity.getUserName();
            String fetchedPassword = userEntity.getUserPassword();
            if (fetchedPassword.equals(password)) {
                log.info("Password matches for email: {}", email);
                return name;
            }
        }
        log.warn("Password does not match for email: {}", email);
        return "false";
    }

    public List<EventsEntity> getAllEventsForUserPage() {
        log.debug("Fetching all events for user page");
        List<EventsEntity> allAdminEventsInDataBase = eventsRepository.findAll();
        log.info("Fetched {} events for user page", allAdminEventsInDataBase.size());
        return allAdminEventsInDataBase;
    }

    // add event Id to user database
    public boolean addEventIdToUserDataBase(String email, String eventId) {
        log.debug("Adding event ID to user database: userEmail={}, eventId={}", email, eventId);
        Optional<UserEntity> userEntity1 = userRepository.findById(email);
        Optional<EventsEntity> eventsEntity1 = eventsRepository.findById(eventId);
        if (userEntity1.isPresent() && eventsEntity1.isPresent()) {
            UserEntity userEntity = userEntity1.get();
            EventsEntity eventsEntity = eventsEntity1.get();
            userEntity.setEvent(eventsEntity);
            userRepository.save(userEntity);
            log.info("Event ID added to user database: userEmail={}, eventId={}", email, eventId);
            return true;
        }
        log.warn("Failed to add event ID to user database: userEmail={}, eventId={}", email, eventId);
        return false;
    }

    public UserEntity getEventIdFromUserDataBase(String email) {
        log.debug("Fetching event ID from user database for email: {}", email);
        UserEntity userEntity1 = userRepository.findById(email).orElse(null);
        if (userEntity1 != null) {
            log.info("Fetched event ID from user database for email: {}", email);
        } else {
            log.warn("No event ID found in user database for email: {}", email);
        }
        return userEntity1;
    }
}
