package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.EventsEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EventsService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserRequestController {

    private static final Logger log = LoggerFactory.getLogger(UserRequestController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    private EventsService eventsService;
    @Autowired
    private UserService userService;

    @PostMapping("/signup/over")
    public String postUserSignUp(@ModelAttribute UserEntity userEntity) {
        log.debug("Received user signup request for: {}", userEntity.getUserEmailId());
        userService.addUserDetailsToDatabase(userEntity);
        log.info("User signed up successfully: {}", userEntity.getUserEmailId());
        return "userlogin";
    }

    @GetMapping("/user/profile")
    public String getUserProfilePage(HttpSession session, Model model) {
        try {
            String userEmail = (String) session.getAttribute("userEmail");
            log.debug("Fetching profile for user: {}", userEmail);
            UserEntity a = userService.getEventIdFromUserDataBase(userEmail);
            session.setAttribute("userDetails", a);
            session.setAttribute("userEmailFromDataBase", a.getUserEmailId());
            session.setAttribute("userNameFromDataBase", a.getUserName());
            session.setAttribute("userMobileNumberFromDataBase", a.getUserMobileNumber());
            session.setAttribute("userRoleFromDataBase", a.getRole());
            session.setAttribute("eventMessage", "Events Registered By You");

            if (a.getEvent() == null) {
                session.setAttribute("noEvent", "You haven't registered for any event yet!");
                log.info("No event registered for user: {}", userEmail);
            } else {
                session.setAttribute("eventName", a.getEvent().getEventName());
                session.setAttribute("imageUrl", a.getEvent().getImageUrl());
                session.setAttribute("eventDate", a.getEvent().getDate());
                log.info("Event registered for user: {}", a.getEvent());
                session.setAttribute("events", a.getEvent());
                session.setAttribute("noEvent", null);
            }
        } catch (Exception e) {
            log.error("Error fetching profile for user", e);
            session.setAttribute("noEvent", "You haven't registered for any event");
        }

        return "userprofile";
    }

    @PostMapping("/usercheck")
    public String loginSuccessOrNot(HttpServletRequest request, HttpServletResponse response,
                                    RedirectAttributes redirectAttributes, HttpSession session, @RequestParam String email) {
        String adminOrUser = request.getParameter("adminOrUser");
        log.debug("User login attempt with email: {}", email);

        if ("user".equalsIgnoreCase(adminOrUser)) {
            String emailFromClient = request.getParameter("email");
            String password = request.getParameter("password");

            boolean userEmailTrue = userService.checkForCorrectEmail(emailFromClient);
            String userPasswordTrue = userService.checkForCorrectPassword(emailFromClient, password);
            String userName = "";
            boolean passwordTrueOrFalse = true;
            if ("false".equals(userPasswordTrue)) {
                passwordTrueOrFalse = false;
            } else {
                userName += userPasswordTrue;
            }
            if (userEmailTrue && passwordTrueOrFalse) {
                session.setAttribute("userName", userName);
                session.setAttribute("userEmail", emailFromClient);
                log.info("Login successful for user: {}", emailFromClient);
                return "redirect:/user/profile";
            } else if (!userEmailTrue) {
                redirectAttributes.addFlashAttribute("userEmailError", "User email is wrong");
                log.warn("Login failed - incorrect email: {}", emailFromClient);
                return "redirect:/userlogin";
            } else if (!passwordTrueOrFalse) {
                redirectAttributes.addFlashAttribute("userPassowrdError", "Password is wrong");
                log.warn("Login failed - incorrect password for email: {}", emailFromClient);
                return "redirect:/userlogin";
            }
        }
        log.warn("Login failed - user not recognized: {}", email);
        return "login";
    }

    @GetMapping("/userlogout")
    public String userLogout() {
        log.debug("User logged out");
        return "userlogin";
    }

    @GetMapping("/userdashboard")
    public String userDashBoard() {
        log.debug("User dashboard accessed");
        return "userdashboard";
    }

    @GetMapping("/userevents")
    public String loadUserEvents(Model model) {
        log.debug("Loading user events");
        List<EventsEntity> allUserEventsInDatabase = userService.getAllEventsForUserPage();
        model.addAttribute("allUserEventsInDatabase", allUserEventsInDatabase);
        log.info("User events loaded: {}", allUserEventsInDatabase.size());
        return "userevents";
    }

    @PostMapping("/registerevent/{eventId}")
    public String resgisterForEventByUser(@PathVariable String eventId, HttpSession session) {
        String userEmailForaddingEvent = (String) session.getAttribute("userEmail");
        log.debug("User registering for event: {} with email: {}", eventId, userEmailForaddingEvent);

        boolean added = userService.addEventIdToUserDataBase(userEmailForaddingEvent, eventId);
        if (added) {
            log.info("Event registration successful for user: {}", userEmailForaddingEvent);
        } else {
            log.warn("Event registration failed for user: {}", userEmailForaddingEvent);
        }
        return "redirect:/user/profile";
    }
}
